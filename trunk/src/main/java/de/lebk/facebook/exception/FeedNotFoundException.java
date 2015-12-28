package de.lebk.facebook.exception;

import facebook4j.FacebookException;

public class FeedNotFoundException extends FacebookException {

	public FeedNotFoundException(String message) {
		super(message);
	}

}
