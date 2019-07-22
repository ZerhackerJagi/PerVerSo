package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MitarbeiterGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	
	
//******************** KONSTRUKTOR ********************
	
	public MitarbeiterGUI(int PID) {
		/*@author:		Jakob Kuechler, Soeren Hebestreit
		 *@date: 		xx.xx.2019, 19.07.2019
		 *@description: Mitarbeiter GUI
		 */
		
		setSize(240, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
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
		
		JButton btnPlan = new JButton("Arbeitsplan");
		btnPlan.setBackground(new Color(255, 255, 255));
		btnPlan.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				new ShowPlanGUI(PID);
			}
		});
		btnPlan.setBounds(12, 220, 200, 24);
		getContentPane().add(btnPlan);
		
		JButton btnAZK = new JButton("Arbeitszeitkonto");
		btnAZK.setBackground(new Color(255, 255, 255));
		btnAZK.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				new ShowAzkGUI(PID);
			}
		});
		btnAZK.setBounds(12, 244, 200, 24);
		getContentPane().add(btnAZK);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(255, 255, 255));
		btnExit.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);	
			}
		});
		btnExit.setBounds(12, 396, 200, 24);
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
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
				
		new MitarbeiterGUI(1);
	}
}
