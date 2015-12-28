package de.lebk.facebook.services;

import java.security.InvalidParameterException;
import java.util.List;

import javax.security.auth.login.CredentialException;

import de.lebk.facebook.exception.FeedNotFoundException;
import de.lebk.facebook.user.UserCredentials;
import facebook4j.FacebookException;
import facebook4j.Media;
import facebook4j.Post;
import facebook4j.PostUpdate;
/**
 * Dieser Service stellt verschiedene Methoden für den 
 * Newsfeed des Benutzers bereit.
 * 
 * Ausnahmen, exceptions, sollten in der GUI abgefangen werden und
 * dem Benutzer angezeigt werden.
 * 
 * Die FacebookException ist ein Indikator für Fehleingaben des Benutzers.
 * 
 * @author Reiberger, Frieling Köper
 */
public interface NewsfeedService {
	/**
	 * Diese Methode erlaubt es ein Post zu veröffentlichen.
	 * 
	 * @param post Das neu zu erstellende PostObjekt, welches  nacher auf
	 * 				der Pinnwand des Benutzers erscheind.
	 * @throws FacebookException Wenn der Benutzer zu wenig berechtigungen freigegeben hat.
	 * 							 Wenn das Objekt leer ist.
	 * 							 Wenn die Server nicht erreichbar sind.
	 */
	public void postFeed(PostUpdate post) throws FacebookException;
	
	/**
	 * Diese Methode liefert einen bestimmten Post zurück, welcher durch 
	 * die mitgelieferte ID ermittelt wird.
	 * 
	 * @param iD Die ID des Feed Elementes
	 * @return PostObjekt welches angefordert wurde
	 * @throws FeedNotFoundException Wenn die Id kein Ergebniss liefert.
	 */
	public Post getFeed(int iD) throws FeedNotFoundException;
	
	/**
	 * Diese Methode liefert alle Benutzerspezifischen Feeds zurück.
	 * @return Liefert alle Feeds des Nutzers zurück.
	 * @throws FacebookException Wenn der Benutzer zu wenig berechtigungen freigegeben hat.
	 * 							 Wenn das Objekt leer ist.
	 * 							 Wenn die Server nicht erreichbar sind.
	 */
	public List<Post> getAllFeeds() throws FacebookException;
	
	/**
	 * Diese Methode erlaubt es einen Feed zu löschen anhand der übergebenden ID.
	 * @param iD Die ID des zu löschenden Feeds.
	 * @throws FacebookException Wenn der Benutzer zu wenig berechtigungen freigegeben hat.
	 * 							 Wenn das Objekt leer ist.
	 * 							 Wenn die Server nicht erreichbar sind.
	 */
	public void deleteFeed(String iD) throws FacebookException;
	
	/**
	 * Diese Methode erlaubt es einen Feed im Inhalt zu updaten.
	 * @param iD Die ID des zu updatenden Feed
	 * @param value Der Neue Wert des Feeds.
	 * @throws FacebookException Wenn der Benutzer zu wenig berechtigungen freigegeben hat.
	 * 							 Wenn das Objekt leer ist.
	 * 							 Wenn die Server nicht erreichbar sind.
	 */
	public void updateFeed(String iD, PostUpdate value) throws FacebookException;
		
	/**
	 * Diese Methode erlaubt es dem Nutzer ein Foto zu posten.
	 * @param photo Das Foto vom Typ Media
	 * @throws FacebookException Wenn der Benutzer zu wenig berechtigungen freigegeben hat.
	 * 							 Wenn das Objekt leer ist.
	 * 							 Wenn die Server nicht erreichbar sind.
	 */
	public void postPhoto(Media photo) throws FacebookException;
	
	/**
	 *  Diese Methode erlaubt es einen User zu authentifizieren.
	 * @param creds Die NutzerDaten in form eines {@link UserCredentials} objekt
	 * @throws FacebookException Wenn der Benutzer zu wenig berechtigungen freigegeben hat.
	 * 							 Wenn das Objekt leer ist.
	 * 							 Wenn die Server nicht erreichbar sind.
	 * @throws CredentialException Wenn die Nutzerdaten falsch sind.
	 * @throws InvalidParameterException Wenn die BenutzerDaten leer sind.
	 */
	public void connectUser(UserCredentials creds) throws FacebookException, CredentialException, InvalidParameterException;

}
