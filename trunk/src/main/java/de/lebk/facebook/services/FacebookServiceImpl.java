package de.lebk.facebook.services;

import java.security.InvalidParameterException;

import javax.security.auth.login.CredentialException;

import de.lebk.facebook.ressources.Authentication;
import de.lebk.facebook.ressources.FacebookAuthResource;
import de.lebk.facebook.user.UserCredentials;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import facebook4j.conf.ConfigurationBuilder;

public class FacebookServiceImpl implements FacebookService {

	@Override
	public Facebook userLogin(UserCredentials creds) throws CredentialException, FacebookException ,InvalidParameterException{
		if(null == creds.getEmail() || null == creds.getPassword()){
			throw new InvalidParameterException("Username or password is empty");
		}
	    Authentication auth = new FacebookAuthResource(creds.getEmail(), creds.getPassword());
	    AccessToken token = auth.getAccessToken();
        if(null == token){
        	throw new CredentialException("Username or password is wrong OR the applications has no permissions");
        }
        ConfigurationBuilder conf = auth.createConfiguration();
        conf.setOAuthAccessToken(token.getToken());
        return new FacebookFactory(conf.build()).getInstance();
	}

}
