package de.lebk.facebook.gui.usedguis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.lebk.facebook.gui.functions.GUIFunctions;
import de.lebk.facebook.services.NewsfeedService;
import facebook4j.FacebookException;
import facebook4j.PostUpdate;

public class NewEditPost extends JFrame {

	private NewsfeedService feedService;
	private JPanel contentPane;
	private GUIFunctions guiFunctions;
	
	private Boolean isPost;
	private JTextField txtLink;
	private JTextField txtDatei;
	private JTextArea txbText;

	
	public NewEditPost(Boolean IsPost,NewsfeedService feedService) {
		this.isPost = IsPost;
		this.feedService = feedService;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblText = new JLabel("Text");
		lblText.setBounds(10, 11, 46, 14);
		contentPane.add(lblText);
		
		JLabel lblLink = new JLabel("Link");
		lblLink.setBounds(10, 190, 46, 14);
		contentPane.add(lblLink);
		
		txtLink = new JTextField();
		txtLink.setBounds(10, 209, 407, 20);
		contentPane.add(txtLink);
		txtLink.setColumns(10);
		
		txbText = new JTextArea();
		txbText.setBounds(10, 26, 407, 153);
		contentPane.add(txbText);
		
		JLabel lblDatei = new JLabel("Datei");
		lblDatei.setBounds(10, 240, 46, 14);
		contentPane.add(lblDatei);
				
		txtDatei = new JTextField();
		txtDatei.setBounds(10, 265, 289, 20);
		contentPane.add(txtDatei);
		txtDatei.setColumns(10);
		if (!isPost) {
			txtDatei.setEnabled(false);
		}
		
		JButton btnDateiffnen = new JButton("Datei Öffnen");
		btnDateiffnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				jfc.showOpenDialog(NewEditPost.this);
				
				openFile(jfc.getSelectedFile().toString());
			}
		});
		btnDateiffnen.setBounds(309, 264, 108, 23);
		contentPane.add(btnDateiffnen);
		if (!isPost) {
			btnDateiffnen.setEnabled(false);
		}
		
		JButton btnEditPost = new JButton();
		btnEditPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					editPostPost();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEditPost.setBounds(10, 310, 89, 23);
		contentPane.add(btnEditPost);
		if (isPost) {
			btnEditPost.setText("Posten");
		} else {
			btnEditPost.setText("Speichern");
		}
		
		JButton btnZurcksetzen = new JButton("Zurücksetzen");
		btnZurcksetzen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnZurcksetzen.setBounds(110, 310, 117, 23);
		contentPane.add(btnZurcksetzen);
		if (!isPost) {
			btnZurcksetzen.setEnabled(false);
		}
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnAbbrechen.setBounds(237, 310, 180, 23);
		contentPane.add(btnAbbrechen);
		setVisible(true);
	}
	
	private void clearFields() {
		txbText.setText("");
		txtLink.setText("");
		txtDatei.setText("");
	}
	
	private void openFile(String filePath) {
		
		txtDatei.setText(filePath);
	}
	
	private void editPostPost() throws MalformedURLException {
		if (isPost) {
			try {
				createPost();
			} catch (FacebookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			editPost();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void createPost() throws MalformedURLException, FacebookException {
		URL url;
		URL picture;
		File picFile;
		PostUpdate post = new PostUpdate(txbText.getText());
		if (!txtLink.getText().equals("")) {
			url = new URL(txtLink.getText());
			post.setLink(url);
		}
		if (!txtDatei.getText().equals("")) {
			picFile = new File(txtDatei.getText());
			picture = picFile.toURL();
			post.setPicture(picture);
		}
		feedService.postFeed(post);
		this.setVisible(false);
		clearFields();
	}
	
	private void editPost() {
		
		
	}
}
