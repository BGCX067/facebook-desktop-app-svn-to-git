package de.lebk.facebook.gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import de.lebk.facebook.gui.functions.GUIFunctions;
import de.lebk.facebook.user.User;
import facebook4j.Post;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private GUIFunctions functions;
	private List<User> userList;
	private UserToolBar userToolBar;
	private User currentUser;

	public MainGUI() {
		userList = new ArrayList<>();
	}
	
	/**
	 * Create the frame.
	 * @return 
	 */
	public void MainGUIInit() {
		functions = new GUIFunctions();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		MainToolBar mainToolBar = new MainToolBar(FoundUsers(),this);
		mainToolBar.setBounds(0, 0, 542, 31);
		mainToolBar.setFloatable(false);
		contentPane.add(mainToolBar);
		
		FeedPane feedPane = new FeedPane(new DefaultListModel<Post>());
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(0, 32, 542, 349);
		contentPane.add(splitPane);
		
		userToolBar = new UserToolBar(FoundUsers(),feedPane);
		userToolBar.setOrientation(userToolBar.VERTICAL);
		userToolBar.setFloatable(false);
		splitPane.setLeftComponent(userToolBar);

		
		JScrollPane scrollPane = new JScrollPane(feedPane);
		scrollPane.setPreferredSize(new Dimension(413,331));
		//feedPane.setLayout(new BorderLayout(0, 0));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		splitPane.setRightComponent(scrollPane);
		//userList = new List<User>();

		this.repaint();
	}
	
	private Boolean FoundUsers(){
		return (functions.getUserList());
	}
	
	public Boolean isntNull() {
		return true;
	}

	public List<User> getUserList() {
		return userList;
	}

	public UserToolBar getUserToolBar() {
		return userToolBar;
	}
		
	public void addLoggedInUser(User user){
		userList.add(user);
		userToolBar.actionPerformed(user);
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
	
}