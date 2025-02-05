package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import _programmstart.Programmstart;
import logik.Arbeitsbereichverwaltung;
import logik.Mitarbeiter;
import logik.Personalverwaltung;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*@author:		Jakob Kuechler, Soeren Hebestreit
 *@date: 		xx.xx.2019, 19.07.2019
 *@description: Mitarbeiter GUI
 */

public class MitarbeiterGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	public boolean openShowAzk = false;
	public boolean openShowVerlauf = false;
	public boolean openChangePasswort = false;
	
//******************** KONSTRUKTOR ********************
	
	public MitarbeiterGUI(int PID) {
			
		setIconImages(Programmstart.iconlist);
		
		setSize(240, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("pictures/HWR-Logo_black_small.png"));
		lblLogo.setBounds(60, 40, 200, 32);
		getContentPane().add(lblLogo);
		
		JLabel labelMA = new JLabel("Mitarbeiter");
		labelMA.setFont(new Font("Dialog", Font.BOLD, 21));
		labelMA.setBounds(12, 120, 160, 24);
		getContentPane().add(labelMA);
		
		JLabel labelHauptmenu = new JLabel("Hauptmenue");
		labelHauptmenu.setFont(new Font("Dialog", Font.BOLD, 21));
		labelHauptmenu.setBounds(12, 144, 160, 24);
		getContentPane().add(labelHauptmenu);
		
		JButton btnAZK = new JButton("Arbeitszeitkonto");
		btnAZK.setBackground(new Color(255, 255, 255));	
		btnAZK.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if (openShowAzk == false) {
					openShowAzk = true;
					ShowAzkGUI showAzk = new ShowAzkGUI(PID);
					showAzk.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							openShowAzk = false;
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "Arbeitszeitkontenansicht bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAZK.setBounds(12, 220, 200, 24);
		getContentPane().add(btnAZK);
		
		JButton btnVerlauf = new JButton("Verlauf anzeigen");
		btnVerlauf.setBackground(new Color(255, 255, 255));	
		btnVerlauf.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if (openShowVerlauf == false) {
					openShowVerlauf = true;
					ShowVerlaufGUI showVerlauf = new ShowVerlaufGUI(PID);
					showVerlauf.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							openShowVerlauf = false;
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "Verlaufsansicht bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnVerlauf.setBounds(12, 264, 200, 24);
		getContentPane().add(btnVerlauf);
		
		JButton btnPasswort = new JButton("Passwort �ndern");
		btnPasswort.setBackground(new Color(255, 255, 255));
		btnPasswort.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if (openChangePasswort == false) {
					openChangePasswort = true;
					ChangePasswortGUI changePasswort = new ChangePasswortGUI(PID);
					changePasswort.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							openChangePasswort = false;
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "Passwort �ndern bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnPasswort.setBounds(12, 356, 200, 24);
		getContentPane().add(btnPasswort);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(255, 255, 255));
		btnExit.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);	
			}
		});
		btnExit.setBounds(12, 400, 200, 24);
		getContentPane().add(btnExit);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 240, 8);
		getContentPane().add(rahmenOben);
		
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(0, 196, 240, 8);
		getContentPane().add(rahmenUnten);
		
		setVisible(true);
		
		if(((Mitarbeiter)Personalverwaltung.getInstance().suchen(PID)).getChangePasswort()){
			new ChangePasswortGUI(PID);
		}
	}
}
