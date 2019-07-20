package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AdminGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;

	
//******************** KONSTRUKTOR ********************	
	
	public AdminGUI(int PID) {
		/*@author:		Jakob Kuechler, Soeren Hebestreit
		 *@date: 		xx.xx.2019, 20.07.2019
		 *@description: Admin GUI
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
		
		JLabel labelMA = new JLabel("Administrator");
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
		
		JButton btnPV = new JButton("Mitarbeiter verwalten");
		btnPV.setBackground(new Color(255, 255, 255));
		btnPV.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				//setVisible(false);
				
			}
		});
		btnPV.setBounds(12, 284, 200, 24);
		getContentPane().add(btnPV);
		
		JButton btnABV = new JButton("Arbeitsbereich verwalten");
		btnABV.setBackground(new Color(255, 255, 255));
		btnABV.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				//setVisible(false);
				
			}
		});
		btnABV.setBounds(12, 308, 200, 24);
		getContentPane().add(btnABV);
		
		JButton btnAP = new JButton("Arbeitspl�ne verwalten");
		btnAP.setBackground(new Color(255, 255, 255));
		btnAP.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);	
			}
		});
		btnAP.setBounds(12, 332, 200, 24);
		getContentPane().add(btnAP);
		
		JButton btnStatistik = new JButton("Statistiken");
		btnStatistik.setBackground(new Color(255, 255, 255));
		btnStatistik.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				//setVisible(false);
				
			}
		});
		btnStatistik.setBounds(12, 356, 200, 24);
		getContentPane().add(btnStatistik);
		
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

		
		
//		JMenuBar menuBar = new JMenuBar();
//		setJMenuBar(menuBar);
//		
//		JLabel lblLaden = new JLabel("Laden ...");
//		menuBar.add(lblLaden);
//		
//		JLabel lblSpeichernUnter = new JLabel("Speichern unter ...");
//		menuBar.add(lblSpeichernUnter);
//		lblSpeichernUnter.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// VERKNÜPFUNG SPEICHERN UNTER TO DO
//				
//				
//			}
//			public void mouseEntered(MouseEvent e) {
//				lblSpeichernUnter.setForeground(new Color(51, 204, 0));
//				
//			}
//			public void mouseExited(MouseEvent e) {
//				lblSpeichernUnter.setForeground(new Color(0,0,0));
//				
//			}
//		});
//		lblLaden.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// VERKNÜPFUNG LADEN TO DO
//				
//			}
//			public void mouseEntered(MouseEvent e) {
//				lblLaden.setForeground(new Color(51, 204, 0));
//				
//			}
//			public void mouseExited(MouseEvent e) {
//				lblLaden.setForeground(new Color(0,0,0));
//				
//			}
//		});
		
		setVisible(true);
	}

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
				
		new AdminGUI(0);
	}
}
