package de.lebk.facebook.ressources;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;


import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.auth.OAuthSupport;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.internal.http.HttpClientImpl;
import facebook4j.internal.http.HttpRequest;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.http.RequestMethod;

public class FacebookAuthResource implements Authentication {
	
	// ------------------ constants -------------------
	
	private static final String CALLBACK_URL = "https://www.facebook.com/connect/login_success.html";

	// -------------------- fields --------------------
	
	private String _email;
	private String _pwd;

	// ----------------- constructor ------------------
		
	public FacebookAuthResource(String email, String pwd) {
		_email = email;
		_pwd = pwd;
	}

	// ------------------- methods --------------------
		
	@Override
	public AccessToken getApplicationAcesstoken() {
		Configuration configuration = createConfiguration().build();
		AccessToken accessToken = null;
		try {
			OAuthSupport oAuthSupport = new OAuthAuthorization(configuration);
			accessToken = oAuthSupport.getOAuthAppAccessToken();
			return accessToken;

		} catch (FacebookException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.lebk.facebook.facebook.Authentication#createConfiguration()
	 */
	@Override
	public ConfigurationBuilder createConfiguration() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
	   
	    cb.setDebugEnabled(true);
	    cb.setOAuthAppId("575918535807451");
	    cb.setOAuthAppSecret("9df8c9797de42fc7df035afca6edd63e");
	    cb.setOAuthPermissions("email,publish_stream,read_stream,friends_about_me,friends_actions.me,friends_birthday,friends_events,friends_location,friends_photo_video_tags,friends_relationship_details,friends_status,friends_website,friends_actions.books,friends_actions.video,friends_checkins,friends_interests,friends_notes,friends_photos,friends_relationships,friends_groups,friends_likes,create_note,photo_upload,publish_stream,read_stream,publish_actions,status_update,export_stream,read_requests,share_item,create_event,user_actions.news,user_groups,user_likes,user_questions,user_status,user_interests,user_notes");
		
		return cb;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.lebk.facebook.facebook.Authentication#getAccessToken()
	 */
	@Override
	public AccessToken getAccessToken() throws FacebookException {
	    
		// Bereitstellung vom CookieManger, desen Berechtigungen werden CookiePolicy.ACCEPT_ALL gesetzt,
		// somit akzeptieren wir jeden Cookie von Facebook
		CookieManager manager = new CookieManager();
	    manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
	    CookieHandler.setDefault(manager);
	    
	    
	    // Erstellung der Facebook Instance mit hilfe des ConfigurationBuilder, in diesen sind 
	    // unter anderem die Einstellung fuer die Application gespeichert. (AppId, AppSecret, Permissions)
	    Facebook facebook = new FacebookFactory(createConfiguration().build()).getInstance();
	    HttpClientImpl http = new HttpClientImpl();
	    HttpResponse response;
	    
	    
	    // Die authorizationURL setzt sich zusammen, aus der CALLBACK_URL und unseren gewuenschten response type
	    String authorizationURL = facebook.getOAuthAuthorizationURL(CALLBACK_URL) + "&response_type=token";
	    response = http.get(authorizationURL);
	  
	    
	    // Durch die erfolgreiche Anfrage an http.get(authorizationURL) bekommen wir unsere login URL von Facebook
	    // bereitgestellt. Der wichtigste Teil fuer die Anwendung ist hier der Parameter "Location", in diesem Teil
	    // befindet sich die URL an die wir unser Login Aufruf schicken.
	    String loginURL = response.getResponseHeader("Location");
	    response = http.get(loginURL);
	    
	    
	    // @TODO: Api_key sollte nicht fest in der URL stehen.
	    // Es wird versucht an die URL ein POST-Anfrage zu schicken, sollte die Anmeldung ohne Fehler verlaufen,
	    // werden wir auf die "dialog" Seite weitergeleitet, aus dieser koennen wir dann den Access Token entnehmen.
	    response = http.request(new HttpRequest(RequestMethod.POST, "https://www.facebook.com/login.php?skip_api_login=1&api_key=575918535807451&signed_next=1&next=https%3A%2F%2Fwww.facebook.com%2Fdialog%2Foauth%3Fredirect_uri%3Dhttps%253A%252F%252Fwww.facebook.com%252Fconnect%252Flogin_success.html%26scope%3Demail%252Cpublish_stream%26response_type%3Dtoken%26client_id%3D575918535807451%26ret%3Dlogin&cancel_uri=https%3A%2F%2Fwww.facebook.com%2Fconnect%2Flogin_success.html%3Ferror%3Daccess_denied%26error_code%3D200%26error_description%3DPermissions%2Berror%26error_reason%3Duser_denied%23_%3D_&display=page&email="+_email+"&pass="+ _pwd, null, null, null));

	    
	    // Sollte der "Location" Parameter vorhanden sein, werden wir auf die "dialog" Seite weitergeleitet.
	    String dialogURL = response.getResponseHeader("Location").replaceAll("&amp%3B", "&");
	    
	    
	    // Redirect zur "dialoURL" Von hier koennen wir den Access Token abfangen.
	    response = http.request(new HttpRequest(RequestMethod.GET, dialogURL, null, null, null));
	    
	    String redirectURL = response.getResponseHeader("Location");
	    
	    // extract access token (ignore expires_in)
	    String accessToken = redirectURL.substring(redirectURL.indexOf("#access_token=")+14, redirectURL.indexOf("&expires"));
	    return new AccessToken(accessToken, null);
	  }
}
