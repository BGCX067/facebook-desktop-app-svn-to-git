package de.lebk.facebook.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import de.lebk.facebook.gui.functions.GUIFunctions;
import de.lebk.facebook.gui.usedguis.NewEditPost;
import de.lebk.facebook.gui.usedguis.NewUser;

public class MainToolBar extends JToolBar {

	private JButton btnNewUser;
	private JButton btnNewPost;
	private Boolean HasAddUser;
	private Boolean ButtonDisabled;
	private GUIFunctions functions;
	private NewEditPost newPost;
	private MainGUI mainGUI;
	
	public MainToolBar(Boolean bool,MainGUI mainGUI){
		addButtons();
		if (!bool) {
			disableButtons();
		}
		functions = new GUIFunctions();
		this.mainGUI = mainGUI;
	}
	
	private void addButtons(){
		btnNewUser = new JButton("Neuer Benutzer");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openNewUserGUI();
				HasAddUser = true;
				ReEnableButtons();
			}
		});
		//JButton btnConnect = new JButton("Verbindung Herstellen");
		btnNewPost = new JButton("Neuen Feed-Post erstellen");
		btnNewPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newPost();
			}
		});
		this.add(btnNewUser);
		//this.add(btnConnect);
		this.addSeparator();
		this.add(btnNewPost);
		this.addSeparator();
	}
	
	private void disableButtons() {
		btnNewPost.setEnabled(false);
		ButtonDisabled = true;
	}
	
	private void ReEnableButtons(){
		if ((ButtonDisabled) && (HasAddUser)) {
			btnNewPost.setEnabled(true);
		}
	}
	
	private void openNewUserGUI(){
		new NewUser(mainGUI);
	}
	
	private void newPost() {
		System.out.println(mainGUI.getUserList().get(0).getService());
		newPost = new NewEditPost(true,mainGUI.getUserList().get(0).getService());
	}
}
