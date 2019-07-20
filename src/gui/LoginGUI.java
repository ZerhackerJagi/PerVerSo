package gui;

import javax.swing.*;

import logik.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		setSize(480,240);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(100, 150, 200));
		getContentPane().setLayout(null);
		
		JLabel lblLoginPerverso = new JLabel("PerVerSo - Login");
		lblLoginPerverso.setForeground(new Color(255, 245, 235));
		lblLoginPerverso.setFont(new Font("Dialog", Font.BOLD, 21));
		lblLoginPerverso.setBounds(164, 20, 240, 40);
		getContentPane().add(lblLoginPerverso);
		
		JLabel lblBenutzername = new JLabel("Benutzername: ");
		lblBenutzername.setForeground(new Color(255, 245, 235));
		lblBenutzername.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBenutzername.setBounds(40, 80, 120, 20);
		getContentPane().add(lblBenutzername);
		
		JLabel lblPasswort = new JLabel("Passwort: ");
		lblPasswort.setForeground(new Color(255, 245, 235));
		lblPasswort.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPasswort.setBounds(40, 120, 120, 20);
		getContentPane().add(lblPasswort);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(160, 78, 180, 24);
		getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 118, 180, 24);
		getContentPane().add(passwordField);
			
		JButton btnAnmelden = new JButton("Anmelden");
		btnAnmelden.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(checkAnmeldung(tfUsername.getText(), new String(passwordField.getPassword()))) {
					setVisible(false);
					if(user.getBerechtigung() instanceof Admin) {
						new AdminGUI(user.getPersonalnummer());
						dispose();
					}else {
						new MitarbeiterGUI(user.getPersonalnummer());
						dispose();
					}
				}
				
			}
		});
		btnAnmelden.setBounds(160, 158, 180, 24);
		getContentPane().add(btnAnmelden);
		
		setVisible(true);
	}
	
	
//******************** INTERNE FUNKTIONEN ********************
	
	private boolean checkAnmeldung(String username, String password) {
		/*@author:		Jakob Kuechler, Soeren Hebestreit
		 *@date: 		xx.xx.2019, 19.07.2019
		 *@description: Passwortkontrolle
		 */
		
		Mitarbeiter ma;
		for(int i = 0; i < Personalverwaltung.getaMA().size(); i++) {
			ma = Personalverwaltung.getaMA().get(i);
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
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
				
		new LoginGUI();
	}

}
