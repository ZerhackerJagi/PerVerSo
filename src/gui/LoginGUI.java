package gui;

import javax.swing.*;

import logik.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class LoginGUI extends JFrame{
	
//******************** PARAMETER ********************
	
	private static final long serialVersionUID = 1L;
	private JTextField tfUsername;
	private JPasswordField passwordField;
	private Mitarbeiter user;
	
	
//******************** KONSTRUKTOR ********************
	
	public LoginGUI() {
		/*@author:		Jakob Kuechler, Soeren Hebestreit
		 *@date: 		xx.xx.2019, 19.07.2019
		 *@description: Login GUI
		 */
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setBackground(new Color(100, 150, 200));
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
	
	
//******************** INTERNE FUNKTIONEN ********************
	
	private boolean checkAnmeldung(String username, String password) {
		/*@author:		Jakob Kuechler
		 *@date: 		xx.xx.2019
		 *@description: Passwortkontrolle
		 */
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma;
		for(int i = 0; i < pv.getaMA().size(); i++) {
			ma = pv.getaMA().get(i);
			if(username.equals(ma.getBenutzername())){
				if(password.equals(ma.getPasswort())) {
					user = ma;
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Ungültiger Benutzername oder Passwort.", "Anmeldung", JOptionPane.INFORMATION_MESSAGE); 
					return false;
				}
			}
		}
		JOptionPane.showMessageDialog(null, "Ungültiger Benutzername oder Passwort.", "Anmeldung", JOptionPane.INFORMATION_MESSAGE);
		return false;
	}

}
