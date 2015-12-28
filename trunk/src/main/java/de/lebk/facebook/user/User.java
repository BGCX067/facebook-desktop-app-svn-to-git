package de.lebk.facebook.user;

import de.lebk.facebook.services.NewsfeedService;

public class User {
	private NewsfeedService service;
	private UserCredentials credentials;
	
	public User(NewsfeedService service, UserCredentials credentials) {
		super();
		this.service = service;
		this.credentials = credentials;
	}
	
	public NewsfeedService getService() {
		return service;
	}
	
	public UserCredentials getCredentials() {
		return credentials;
	}
	
	
}
