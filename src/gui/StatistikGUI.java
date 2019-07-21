package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logik.*;

import java.awt.Font;
import java.awt.Color;

public class StatistikGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	
	
//******************** KONSTRUKTOR ********************
	
	public StatistikGUI(int PID) {
		/*@author:		Soeren Hebestreit
		 *@date: 		20.07.2019
		 *@description: GUI zur Ansicht des Arbeitsplanes, Wochenauswahl
		 */
		
		setSize(360, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = ((Mitarbeiter) pv.suchen(PID));
		
		JLabel lblAZK = new JLabel("Statistikmenue");
		lblAZK.setFont(new Font("Dialog", Font.BOLD, 21));
		lblAZK.setForeground(new Color(255, 255, 255));
		lblAZK.setBounds(24, 20, 360, 24);
		getContentPane().add(lblAZK);
		
		JLabel lblName = new JLabel(ma.getVorname()+" "+ma.getName());
		lblName.setFont(new Font("Dialog", Font.BOLD, 21));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(24, 44, 360, 24);
		getContentPane().add(lblName);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 360, 88);
		getContentPane().add(rahmenOben);
		
		JLabel lblSoll = new JLabel("Jahr");
		lblSoll.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSoll.setBounds(24, 100, 100, 24);
		getContentPane().add(lblSoll);
		
		JLabel lblSollData = new JLabel("blub");
		lblSollData.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSollData.setBounds(160, 100, 100, 24);
		getContentPane().add(lblSollData);
		
		JLabel lblUeber = new JLabel("Kalenderwoche");
		lblUeber.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUeber.setBounds(24, 124, 100, 24);
		getContentPane().add(lblUeber);
		
		JLabel lblUeberData = new JLabel("blub");
		lblUeberData.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUeberData.setBounds(160, 124, 100, 24);
		getContentPane().add(lblUeberData);
		
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(0, 200, 360, 4);
		getContentPane().add(rahmenUnten);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("pictures/under-construction.png"));
		lblLogo.setBounds(40, 220, 360, 360);
		getContentPane().add(lblLogo);
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
				
		new StatistikGUI(1);
	}
}
