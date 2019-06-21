package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Hauptmenue extends JFrame{
	public Hauptmenue() {
		getContentPane().setBackground(new Color(0, 139, 139));
		getContentPane().setLayout(null);
		
		JLabel lblMitarbeiterVerwalten = new JLabel("Mitarbeiter verwalten");
		lblMitarbeiterVerwalten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// VERKNÜPFUNG MITARBEITER VERWALTEN
				
				
			}
		});
		lblMitarbeiterVerwalten.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMitarbeiterVerwalten.setBounds(85, 126, 251, 30);
		getContentPane().add(lblMitarbeiterVerwalten);
		
		JLabel lblArbeitsbereicheVerwalten = new JLabel("Arbeitsbereiche verwalten");
		lblArbeitsbereicheVerwalten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// VERKNÜPFUNG ARBEITSBEREICH VERWALTEN
				
			}
		});
		lblArbeitsbereicheVerwalten.setFont(new Font("Dialog", Font.BOLD, 14));
		lblArbeitsbereicheVerwalten.setBounds(85, 168, 251, 30);
		getContentPane().add(lblArbeitsbereicheVerwalten);
		
		JLabel lblAuswertungenAnzeigen = new JLabel("Auswertungen anzeigen");
		lblAuswertungenAnzeigen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// VERKNÜPFUNG AUSWERTUNG ANZEIGEN 
			
			}
		});
		lblAuswertungenAnzeigen.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAuswertungenAnzeigen.setBounds(83, 210, 253, 30);
		getContentPane().add(lblAuswertungenAnzeigen);
		
		JLabel lblArbeitsplaeneVerwalten = new JLabel("Arbeitsplaene verwalten");
		lblArbeitsplaeneVerwalten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// VERKNÜPFUNG ARBEITSPLÄNE VERWALTEN
				
				
			}
		});
		lblArbeitsplaeneVerwalten.setFont(new Font("Dialog", Font.BOLD, 14));
		lblArbeitsplaeneVerwalten.setBounds(85, 252, 251, 30);
		getContentPane().add(lblArbeitsplaeneVerwalten);
		
		JLabel lblProgrammBeenden = new JLabel("Programm beenden");
		lblProgrammBeenden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// PROGRAMM BEENDEN
				System.exit(EXIT_ON_CLOSE);	
			}
		});
		lblProgrammBeenden.setFont(new Font("Dialog", Font.BOLD, 14));
		lblProgrammBeenden.setBounds(85, 294, 251, 30);
		getContentPane().add(lblProgrammBeenden);
		
		JLabel lblSpeichernUnter = new JLabel("Speichern unter ...");
		lblSpeichernUnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// VERKNÜPFUNG SPEICHERN UNTER TO DO
				
				
			}
		});
		lblSpeichernUnter.setBounds(304, 0, 156, 30);
		getContentPane().add(lblSpeichernUnter);
		
		JLabel lblLaden = new JLabel("Laden ...");
		lblLaden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// VERKNÜPFUNG LADEN TO DO
				
			}
		});
		lblLaden.setBounds(12, 0, 156, 30);
		getContentPane().add(lblLaden);
		
		JLabel lblPerverso = new JLabel("PerVerSo Hauptmenue");
		lblPerverso.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPerverso.setBounds(85, 42, 288, 45);
		getContentPane().add(lblPerverso);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		
		
=======
		new Hauptmenue();
>>>>>>> branch 'master' of https://github.com/ZerhackerJagi/PerVerSo.git
	}

}
