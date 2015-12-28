package de.lebk.facebook.ressources;

import facebook4j.FacebookException;
import facebook4j.auth.AccessToken;
import facebook4j.conf.ConfigurationBuilder;
/**
 * Diese Klasse dient dem authentifizieren von einem User gegen 
 * Faceboook. 
 * @author TomyDev
 */
public interface Authentication {
	
	/**
	 * Diese Methode liefert das AccessToken zurück. Mit diesem Token kann man Aktionen
	 * auf Facebook, innerhalb der Berechtigungen, ausführen. 
	 * 
	 * @return Dieses Token berechtigt den User während der Session aktionen auszuführen.
	 * @throws FacebookException Wenn beim kontaktieren der Facebookserver fehler auftreten
	 */
	public abstract AccessToken getAccessToken() throws FacebookException;
	
	/**
	 * Das ApplikationToken ermöglicht der Anwendung gegenüber Facebook zu authentifizieren.
	 * @return Token für die Applikation.
	 */
	public AccessToken getApplicationAcesstoken();
	
	/**
	 * Erstellt die Konfiguration für den Anmeldeprozess z.B. das AccessToken 
	 */
	public ConfigurationBuilder createConfiguration();

}