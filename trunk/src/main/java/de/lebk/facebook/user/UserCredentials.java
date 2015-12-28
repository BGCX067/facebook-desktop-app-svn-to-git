package de.lebk.facebook.user;
/**
 * Diese Klasse bildet einen Benutzer ab, mit seiner Email, Passwort und gegebenenfalls
 * den Access Token.
 * 
 * @TODO: Das Passwort sollte bei gelegenheit verschlüsselt werden.
 * 
 * @author Köper, Reiberger
 * @since 03.12.2013 08:20 UHR
 *
 */
public class UserCredentials {

	// -------------------- FIELDS --------------------
	
	private final String email;
	private final String password;
	private String accessToken;

	// ----------------- CONSTRUCTOR ------------------
	
	public UserCredentials(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public UserCredentials(String email, String password, String accessToken) {
		this(email, password);
		this.accessToken = accessToken;
	}
	
	// ------------------- GETTER ---------------------
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
}
