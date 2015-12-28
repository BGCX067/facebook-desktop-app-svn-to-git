package de.lebk.facebook.gui.usedguis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.InvalidParameterException;

import javax.security.auth.login.CredentialException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.lebk.facebook.gui.MainGUI;
import de.lebk.facebook.services.NewsfeedService;
import de.lebk.facebook.services.NewsfeedServiceImpl;
import de.lebk.facebook.user.User;
import de.lebk.facebook.user.UserCredentials;
import facebook4j.FacebookException;

public class NewUser extends JFrame {

	private JPanel contentPane;
	private JTextField txtNickName;
	private JTextField txtUserName;
	private JPasswordField pwdPassWord;
	private MainGUI mainGui;
	
	public NewUser(MainGUI mainGui) {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		this.mainGui = mainGui;
		setBounds(100, 100, 254, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblBenutzername = new JLabel("Benutzername");
		lblBenutzername.setBounds(10, 73, 84, 14);
		contentPane.add(lblBenutzername);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(10, 89, 219, 20);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		txtUserName.setText("thomas-michael.koeper@gmx.de");
		JLabel lblPasswort = new JLabel("Passwort");
		lblPasswort.setBounds(10, 134, 46, 14);
		contentPane.add(lblPasswort);
		
		pwdPassWord = new JPasswordField();
		pwdPassWord.setBounds(10, 159, 219, 20);
		pwdPassWord.setText("");
		contentPane.add(pwdPassWord);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(10, 201, 89, 23);
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 NewUser.this.mainGui.addLoggedInUser(new User(loginUser(), new UserCredentials(txtUserName.getText(), String.valueOf(pwdPassWord.getPassword()))));
			}
		});
		contentPane.add(btnSpeichern);
		
		JButton btnZurcksetzen = new JButton("Zurücksetzen");
		btnZurcksetzen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearFields();
			}
		});
		btnZurcksetzen.setBounds(109, 201, 120, 23);
		contentPane.add(btnZurcksetzen);
		
		JButton btnAbort = new JButton("Abbrechen");
		btnAbort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnAbort.setBounds(10, 237, 219, 23);
		contentPane.add(btnAbort);
		setVisible(true);
	}
	
	private void ClearFields() {
		txtNickName.setText("");
		txtUserName.setText("");
		pwdPassWord.setText("");
	}

	private NewsfeedService loginUser() {

		try {
			NewsfeedServiceImpl service = new NewsfeedServiceImpl();
			service.connectUser(new UserCredentials(txtUserName.getText(),
					String.valueOf(pwdPassWord.getPassword())));
			this.setVisible(false);
			return service;
		} catch (CredentialException | InvalidParameterException
				| FacebookException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		} finally {
			this.setVisible(false);
		}
		return null;
	}
}
