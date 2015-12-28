package de.lebk.facebook.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import de.lebk.facebook.user.User;
import de.lebk.facebook.user.UserButton;

public class UserToolBar extends JToolBar {
	
	JButton btnEmpty = new JButton("Add Users");
	private FeedPane feedPane;
	
	public UserToolBar(Boolean userExist, FeedPane feedPane){
		AddEmptyButton();
		this.feedPane = feedPane;
	}
	
	private void AddEmptyButton(){
		btnEmpty.setEnabled(false);
		this.add(btnEmpty);
	}
	
	public void AddSingleButton(User user){
		if (this.getComponentIndex(btnEmpty) != 0){
			RemoveSingleButton(btnEmpty);
		}
		JButton btnNewUser = new UserButton(user.getCredentials().getEmail(), user);
		btnNewUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserToolBar.this.feedPane.actualise(((UserButton)e.getSource()).getUser());
			}
		});
		this.add(btnNewUser);
		this.RemoveSingleButton(btnEmpty);
		this.repaint();
	}
	
	
	private void RemoveSingleButton(JButton button){
		this.remove(button);		
	}
	
	/**
	 * Wennn die Liste der angemeldeten User sich ändert, dann wird 
	 * signalisiert, dass die GUI sich zu aktualisieren hat.
	 * @param user Der neu angelegte User
	 */
	public void actionPerformed(User user) {
		AddSingleButton(user);
		feedPane.actualise(user);
	}

}
