package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;

import _programmstart.Programmstart;
import logik.*;
/*@author:		Jakob Kuechler, Soeren Hebestreit
 *@date: 		xx.xx.2019, 19.07.2019
 *@description: Login GUI
 */

public class LoginGUI extends JFrame{
	
//******************** PARAMETER ********************
	
	private static final long serialVersionUID = 1L;
	private Mitarbeiter user;
		
//******************** KONSTRUKTOR ********************
	
	public LoginGUI() {
		
		setIconImages(Programmstart.iconlist);
		
		setSize(480,240);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(100, 150, 200));
		getContentPane().setLayout(null);
		setResizable(false);
		
		JLabel lblLoginPerverso = new JLabel("PerVerSo - Login");
		lblLoginPerverso.setForeground(new Color(255, 255, 255));
		lblLoginPerverso.setFont(new Font("Dialog", Font.BOLD, 21));
		lblLoginPerverso.setBounds(164, 20, 240, 40);
		getContentPane().add(lblLoginPerverso);
		
		JLabel lblPersonalnummer = new JLabel("Personal-Nr.: ");
		lblPersonalnummer.setForeground(new Color(255, 255, 255));
		lblPersonalnummer.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPersonalnummer.setBounds(40, 80, 120, 20);
		getContentPane().add(lblPersonalnummer);
		
		JLabel lblPasswort = new JLabel("Passwort: ");
		lblPasswort.setForeground(new Color(255, 255, 255));
		lblPasswort.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPasswort.setBounds(40, 120, 120, 20);
		getContentPane().add(lblPasswort);
		
		JTextField tfPersonalnummer = new JTextField();
		tfPersonalnummer.setBounds(160, 78, 180, 24);
		getContentPane().add(tfPersonalnummer);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(160, 118, 180, 24);
		getContentPane().add(passwordField);
			
		JButton btnAnmelden = new JButton("Anmelden");
		btnAnmelden.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				anmelden(tfPersonalnummer,passwordField);				
			}
		});
		
		btnAnmelden.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				anmelden(tfPersonalnummer,passwordField);			
			}
		});
		btnAnmelden.setBounds(160, 158, 180, 24);
		getRootPane().setDefaultButton(btnAnmelden);
		getContentPane().add(btnAnmelden);
		
		setVisible(true);
	}
	
//******************** INTERNE FUNKTIONEN ********************
	/*
	 * @author: Charly Spina
	 */
	private void ladeneu() {
		try {
			Personalverwaltung.getInstance().setModus("Beispiel");
			Personalverwaltung.getInstance().laden();
			Arbeitsbereichverwaltung.getInstance().laden();
			return;
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Beispiel Datenbank nicht vorhanden", null, JOptionPane.INFORMATION_MESSAGE);
		}		
	}
	
	/*
	 * @author: Charly Spina(nicht die logik)
	 */
	private void anmelden(JTextField tfPersonalnummer, JPasswordField passwordField) {
		
		if(tfPersonalnummer.getText().equals("Beispiel")) {
			setVisible(false);
			ladeneu();
			new AdminGUI(100000);
			dispose();
		}
		else if(checkAnmeldung(tfPersonalnummer.getText(), new String(passwordField.getPassword()))) {
			setVisible(false);
			if(user.getBerechtigung() instanceof Admin) {
				new AdminGUI(user.getPersonalnummer());
				dispose();
			} else if(user.getBerechtigung() instanceof User){
				new MitarbeiterGUI(user.getPersonalnummer());
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Keine Berechtigung!", null, JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/*
	 *@description: Passwortkontrolle
	 */
	private boolean checkAnmeldung(String personalnummer, String password) {
				
		try {
			Mitarbeiter ma = (Mitarbeiter) Personalverwaltung.getInstance().suchen(Integer.parseInt(personalnummer));
			if(ma != null) {
				if(ma.getPasswort().equals(password)) {
					user = ma;
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Ungültiger Benutzername oder Passwort.", null, JOptionPane.INFORMATION_MESSAGE);
		return false;
	}
}
