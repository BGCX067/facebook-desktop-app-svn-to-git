package de.lebk.facebook.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import de.lebk.facebook.gui.functions.GUIFunctions;
import de.lebk.facebook.user.User;
import facebook4j.FacebookException;
import facebook4j.Post;

public class FeedPane extends JList<Post> {
	
	private Boolean JustAfterOpen;
	private GUIFunctions functions;
	private DefaultListModel<Post> model;
	

	public FeedPane(DefaultListModel<Post> model) {
		super(model);
		this.model = model;
		functions = new GUIFunctions();
//		this.setPreferredSize(new Dimension(413,331));
		SpringLayout sl_feedPane = new SpringLayout();
		this.setLayout(sl_feedPane);
		JustAfterOpen = true;
		PaintComponents();
		this.setCellRenderer(new NewsFeedCellRenderer());
	}
	
	
	public Boolean getJustAfterOpen() {
		return JustAfterOpen;
	}

	public void setJustAfterOpen(Boolean justAfterOpen) {
		JustAfterOpen = justAfterOpen;
	}

	
	public void ResizePanel(Integer length) {
		this.setPreferredSize(new Dimension(413,length));
		this.repaint();
	}
	
	public void PaintComponents() {
		PaintWelcomeMessage();
	}
	
	private void PaintWelcomeMessage() {
		JLabel feedText = new JLabel(functions.createWelcomeMessage());
		feedText.setFont(new Font("Consolas", Font.PLAIN, 14));
		feedText.setBounds(10, 10, 385, 300);
		this.add(feedText);
	}

	public void actualise(User user) {
		this.removeAll();
		this.model.removeAllElements();
		try {
			List<Post> feeds = user.getService().getAllFeeds();
			drawitems(feeds);
			ResizePanel(feeds.size()*10);
		} catch (FacebookException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}


	private void drawitems(List<Post> feeds) {
		if(!feeds.isEmpty()){
		model.addElement(feeds.get(0));
			feeds.remove(0);
			drawitems(feeds);
		}
		this.repaint();
	}




}
