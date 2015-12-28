package de.lebk.facebook.gui.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class GUIFunctions {
	
	private static File userData = null;
	
	public GUIFunctions() {
		
	}
	
	public Boolean getUserList() {
		if (userData != null) {
			if (openUserList()) {
				return(true);
			}
				return(false);
		} else {
			createUserList();
			return(false);
		}
	}
	
	private void createUserList(){
		FileWriter fileWriter = null;
		try {
			userData = new File("userData.bin");
			fileWriter = new FileWriter(userData);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Fehler beim Erstellen neuer Anmeldedaten", "Datei-Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public Boolean openUserList() {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(userData);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			return(userData.length() != 0);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim Lesen der Anmeldedaten","Datenfehler",JOptionPane.ERROR_MESSAGE);
			return(false);
		}
		//if (userData)
	}
	
	public String createWelcomeMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>------------------------------------------------------<br><br>");
		sb.append("Willkommen bei der Facebook Desktop App!<br><br>");
		sb.append("Um zu starten,<br>");
		sb.append("erstellen Sie bitte einen neuen Benutzer,<br>");
		sb.append("oder wählen Sie links einen neuen Benutzer<br>");
		sb.append("aus der Liste aus!<br><br>");
		sb.append("------------------------------------------------------</html>");
		return(sb.toString());
	}	

}
