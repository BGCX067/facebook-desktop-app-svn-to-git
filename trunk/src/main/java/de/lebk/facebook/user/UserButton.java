package de.lebk.facebook.user;

import javax.swing.JButton;

public class UserButton extends JButton {
	private User user;

	public UserButton(String userName, User user) {
		super(userName);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
