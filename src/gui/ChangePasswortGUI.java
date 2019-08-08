package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import _programmstart.Programmstart;
import logik.Admin;
import logik.Mitarbeiter;
import logik.Personalverwaltung;
import logik.User;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;

public class ChangePasswortGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
		
//******************** KONSTRUKTOR ********************
	
	public ChangePasswortGUI(int PID) {
		/*@author:		Soeren Hebestreit
		 *@date: 		24.07.2019
		 *@description: Passwort aendern
		 */	
		setIconImages(Programmstart.iconlist);
		
		setSize(400, 320);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(PID);
		
		JLabel lblFunktion = new JLabel("Passwort ändern");
		lblFunktion.setForeground(new Color(255, 255, 255));
		lblFunktion.setFont(new Font("Dialog", Font.BOLD, 21));
		lblFunktion.setBounds(24, 8, 380, 36);
		getContentPane().add(lblFunktion);
		
		JLabel lblPNr = new JLabel("PNr. "+PID+" - "+ma.getVorname()+" "+ma.getName());
		lblPNr.setForeground(new Color(255, 255, 255));
		lblPNr.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPNr.setBounds(24, 36, 380, 24);
		getContentPane().add(lblPNr);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 480, 64);
		getContentPane().add(rahmenOben);
		
		int y = 40;
		int x = 172;
		
		JLabel lblPasswortAlt = new JLabel("altes Passwort:");
		lblPasswortAlt.setBounds(40, y+40, 120, 20);
		getContentPane().add(lblPasswortAlt);
		
		JPasswordField passwordFieldOld = new JPasswordField();
		passwordFieldOld.setBounds(x, y+38, 180, 24);
		getContentPane().add(passwordFieldOld);
			
		JLabel lblPasswortNeu = new JLabel("neues Passwort:");
		lblPasswortNeu.setBounds(40, y+80, 120, 20);
		getContentPane().add(lblPasswortNeu);
		
		JPasswordField passwordFieldNew = new JPasswordField();
		passwordFieldNew.setBounds(x, y+78, 180, 24);
		getContentPane().add(passwordFieldNew);
		
		JPanel rahmenMitte = new JPanel();
		rahmenMitte.setBackground(new Color(100, 150, 200));
		rahmenMitte.setBounds(0, y+196, 400, 4);
		getContentPane().add(rahmenMitte);
		
		JButton btnConfirm = new JButton("OK");
		btnConfirm.setBackground(new Color(255, 255, 255));
		btnConfirm.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(new String(passwordFieldOld.getPassword()).isEmpty() || new String(passwordFieldNew.getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen.", null, JOptionPane.INFORMATION_MESSAGE);
				} else {
					if(ma.getPasswort().equals(new String(passwordFieldOld.getPassword()))){
						if(ma.getBerechtigung() instanceof Admin) {
							((Admin)ma.getBerechtigung()).changePasswort(new String(passwordFieldOld.getPassword()), new String(passwordFieldNew.getPassword()));
						} else {
							((User)ma.getBerechtigung()).changePasswort(new String(passwordFieldOld.getPassword()), new String(passwordFieldNew.getPassword()));
						}
						try {
							pv.speichern();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Falsches Passwort.", null, JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnConfirm.setBounds(x, y+216, 64, 24);
		getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.setBackground(new Color(255, 255, 255));
		btnCancel.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(x+80, y+216, 100, 24);
		getContentPane().add(btnCancel);
		
		setVisible(true);	
	}		

	public static void main(String[] args) throws Exception {
		
		Personalverwaltung.getInstance().laden();
		new ChangePasswortGUI(100001);
	}
}
