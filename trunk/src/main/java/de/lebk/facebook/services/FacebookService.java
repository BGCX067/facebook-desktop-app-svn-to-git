package de.lebk.facebook.services;

import java.security.InvalidParameterException;

import javax.security.auth.login.CredentialException;

import de.lebk.facebook.ressources.FacebookAuthResource;
import de.lebk.facebook.user.UserCredentials;
import facebook4j.Facebook;
import facebook4j.FacebookException;
/**
 * Dieser Service bietet die Möglichkeit sich mit seinen Login Daten,
 * Email und Passwort, anzumelden.
 * Dazu bedient sich der Service der {@link FacebookAuthResource}.
 * Geprüft werden die Daten auf Inhalt und auf Richtigkeit.
 * In beiden Fällen, sofern negativ ausgehend, werden {@link CredentialException}, 
 * {@link InvalidParameterException} oder die {@link FacebookException} geschmissen.
 * geschmissen.
 * @author TomyDev
 */
public interface FacebookService {
	/**
	 * Loggt den User mittels LoginDaten bei Facebook an.
	 * @param creds Die NutzerDaten in Form eines {@link UserCredentials} Obejkts
	 * @return Eine Facebook instanz, welche Zugriff auf Account Information bietet.
	 * @throws CredentialException, wenn die LoginDaten fehlhaft waren
	 * @throws FacebookException Wenn beim kontaktieren der Facebookserver fehler auftreten
	 * @throws InvalidParameterException Wenn einer der beiden Parameter leer ist.		
	 */
	 Facebook userLogin(UserCredentials creds)  throws CredentialException, FacebookException, InvalidParameterException;
}
