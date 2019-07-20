package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuBar;
import javax.swing.border.LineBorder;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AdminGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminGUI(int PID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(51, 204, 0));
		getContentPane().setLayout(null);
		
		JLabel lblPerverso = new JLabel("Hauptmenue");
		lblPerverso.setForeground(new Color(255, 245, 238));
		lblPerverso.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPerverso.setBounds(86, 37, 238, 45);
		getContentPane().add(lblPerverso);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(102, 255, 51)));
		panel.setBackground(new Color(51, 204, 0));
		panel.setBounds(85, 110, 230, 240);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblArbeitsplaeneVerwalten = new JLabel("Arbeitsplaene verwalten");

		lblArbeitsplaeneVerwalten.setBounds(4, 16, 174, 17);
		panel.add(lblArbeitsplaeneVerwalten);
		lblArbeitsplaeneVerwalten.setForeground(Color.WHITE);
		lblArbeitsplaeneVerwalten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// VERKNÜPFUNG ARBEITSPLÄNE VERWALTEN
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblArbeitsplaeneVerwalten.setForeground(new Color(0,0,0));
				
			}
			public void mouseExited(MouseEvent e) {
				lblArbeitsplaeneVerwalten.setForeground(new Color(255,255,255));
				
			}
		});
		lblArbeitsplaeneVerwalten.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblAuswertungenAnzeigen = new JLabel("Auswertungen anzeigen");
		lblAuswertungenAnzeigen.setBounds(4, 45, 172, 17);
		panel.add(lblAuswertungenAnzeigen);
		lblAuswertungenAnzeigen.setForeground(Color.WHITE);
		lblAuswertungenAnzeigen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// VERKNÜPFUNG AUSWERTUNG ANZEIGEN 
			
			}
			public void mouseEntered(MouseEvent e) {
				lblAuswertungenAnzeigen.setForeground(new Color(0,0,0));
				
			}
			public void mouseExited(MouseEvent e) {
				lblAuswertungenAnzeigen.setForeground(new Color(255,255,255));
				
			}
		});
		lblAuswertungenAnzeigen.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblArbeitsbereicheVerwalten = new JLabel("Arbeitsbereiche verwalten");
		lblArbeitsbereicheVerwalten.setBounds(4, 74, 187, 17);
		panel.add(lblArbeitsbereicheVerwalten);
		lblArbeitsbereicheVerwalten.setForeground(Color.WHITE);
		lblArbeitsbereicheVerwalten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// VERKNÜPFUNG ARBEITSBEREICH VERWALTEN
				new ABUebersichtGUI();
				setVisible(false);
				dispose();
				
			}
			public void mouseEntered(MouseEvent e) {
				lblArbeitsbereicheVerwalten.setForeground(new Color(0,0,0));
				
			}
			public void mouseExited(MouseEvent e) {
				lblArbeitsbereicheVerwalten.setForeground(new Color(255,255,255));
				
			}
		});
		lblArbeitsbereicheVerwalten.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblMitarbeiterVerwalten = new JLabel("Mitarbeiter verwalten");
		lblMitarbeiterVerwalten.setBounds(4, 103, 155, 17);
		panel.add(lblMitarbeiterVerwalten);
		lblMitarbeiterVerwalten.setForeground(Color.WHITE);
		lblMitarbeiterVerwalten.setBackground(Color.WHITE);
		lblMitarbeiterVerwalten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// VERKNÜPFUNG MITARBEITER VERWALTEN
				
				
			}
			public void mouseEntered(MouseEvent e) {
				lblMitarbeiterVerwalten.setForeground(new Color(0,0,0));
				
			}
			public void mouseExited(MouseEvent e) {
				lblMitarbeiterVerwalten.setForeground(new Color(255,255,255));
				
			}
		});
		lblMitarbeiterVerwalten.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblProgrammBeenden = new JLabel("Programm beenden");
		lblProgrammBeenden.setBounds(4, 198, 140, 17);
		panel.add(lblProgrammBeenden);
		lblProgrammBeenden.setForeground(Color.WHITE);
		lblProgrammBeenden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// PROGRAMM BEENDEN
				System.exit(0);	
			}
			public void mouseEntered(MouseEvent e) {
				lblProgrammBeenden.setForeground(new Color(0,0,0));
				
			}
			public void mouseExited(MouseEvent e) {
				lblProgrammBeenden.setForeground(new Color(255,255,255));
				
			}
		});
		lblProgrammBeenden.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/jakobkuchler/Downloads/HWR-Logo_black_small.png"));
		lblNewLabel.setBounds(6, 416, 150, 34);
		getContentPane().add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JLabel lblLaden = new JLabel("Laden ...");
		menuBar.add(lblLaden);
		
		JLabel lblSpeichernUnter = new JLabel("Speichern unter ...");
		menuBar.add(lblSpeichernUnter);
		lblSpeichernUnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// VERKNÜPFUNG SPEICHERN UNTER TO DO
				
				
			}
			public void mouseEntered(MouseEvent e) {
				lblSpeichernUnter.setForeground(new Color(51, 204, 0));
				
			}
			public void mouseExited(MouseEvent e) {
				lblSpeichernUnter.setForeground(new Color(0,0,0));
				
			}
		});
		lblLaden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// VERKNÜPFUNG LADEN TO DO
				
			}
			public void mouseEntered(MouseEvent e) {
				lblLaden.setForeground(new Color(51, 204, 0));
				
			}
			public void mouseExited(MouseEvent e) {
				lblLaden.setForeground(new Color(0,0,0));
				
			}
		});
		
		
		getContentPane().add(panel);
		
		JLabel label = new JLabel("Arbeitszeitkonto ansehen");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				label.setForeground(new Color(0,0,0));
				
			}
			public void mouseExited(MouseEvent e) {
				label.setForeground(new Color(255,255,255));
				
			}
		});
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(4, 132, 200, 17);
		panel.add(label);
		setSize(450, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
				
		new AdminGUI(0);
	}
}
