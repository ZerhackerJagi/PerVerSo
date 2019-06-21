package gui;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

public class LoginGUI extends JFrame{
	private JTextField tfUsername;
	private JPasswordField passwordField;
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
	}
}
