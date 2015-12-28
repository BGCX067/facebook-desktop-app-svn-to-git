package de.lebk.facebook.services;

import java.security.InvalidParameterException;

import javax.security.auth.login.CredentialException;

import de.lebk.facebook.exception.FeedNotFoundException;
import de.lebk.facebook.user.UserCredentials;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Media;
import facebook4j.Post;
import facebook4j.PostUpdate;
import facebook4j.ResponseList;


public class NewsfeedServiceImpl implements NewsfeedService{
	
	Facebook facebook;

	FacebookService service;

	public NewsfeedServiceImpl() {
		service = new FacebookServiceImpl();
	}
	@Override
	public void postFeed(PostUpdate feed) throws FacebookException {
		facebook.postStatusMessage(feed.getMessage());
	}

	@Override
	public Post getFeed(int iD) throws FeedNotFoundException {
		try {
			ResponseList<Post> posts = null;
			Post post = null;
			posts = facebook.getFeed();
			post = posts.get(iD);
			return post;
		} catch (FacebookException e) {
			throw new FeedNotFoundException("Die angegbene ID des Feeds, lieferte kein Resultat.");
		}
		
	}

	@Override
	public ResponseList<Post> getAllFeeds() throws FacebookException{
		return facebook.getFeed();
	}

	@Override
	public void deleteFeed(String iD) throws FacebookException {
		facebook.deletePost(iD);
	}

	@Override
	public void updateFeed(String iD, PostUpdate value) throws FacebookException {
		facebook.postFeed(iD, value);
	}

	@Override
	public void postPhoto(Media photo) throws FacebookException {
		facebook.postPhoto(photo);
	}

	@Override
	public void connectUser(UserCredentials creds) throws FacebookException, CredentialException, InvalidParameterException {
		facebook = service.userLogin(creds);
	}

}
