package gui;

import javax.swing.*;

import logik.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends JFrame{
	private JTextField tfUsername;
	private JPasswordField passwordField;
	private Mitarbeiter user;
	public LoginGUI() {
		getContentPane().setBackground(new Color(0, 204, 0));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Benutzername: ");
		lblNewLabel.setBounds(45, 95, 125, 30);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPasswort = new JLabel("Passwort: ");
		lblPasswort.setBounds(45, 124, 125, 30);
		getContentPane().add(lblPasswort);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(174, 101, 172, 24);
		getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 128, 172, 24);
		getContentPane().add(passwordField);
		
		JLabel lblLoginPerverso = new JLabel("Login - PerVerSo");
		lblLoginPerverso.setFont(new Font("Dialog", Font.BOLD, 17));
		lblLoginPerverso.setBounds(174, 38, 253, 34);
		getContentPane().add(lblLoginPerverso);
		
		JButton btnAnmelden = new JButton("Anmelden");
		btnAnmelden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(checkAnmeldung(tfUsername.getText(), passwordField.getText())) {
					setVisible(false);
					if(user.getBerechtigung() instanceof Admin) {
						new AdminGUI();
						dispose();
					}else {
						new MitarbeiterGUI();
						dispose();
					}
				}
				
			}
		});
		btnAnmelden.setBounds(174, 164, 172, 29);
		getContentPane().add(btnAnmelden);
		
		setSize(500,250);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	private boolean checkAnmeldung(String username, String password) {
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma;
		for(int i = 0;i<pv.getaMA().size();i++) {
			ma = pv.getaMA().get(i);
			if(username.equals(ma.getBenutzername())){
				if(password.equals(ma.getPasswort())) {
					user = ma;
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Personalverwaltung.getInstance().start();
	}
}
