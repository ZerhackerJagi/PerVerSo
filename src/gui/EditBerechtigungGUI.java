package gui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import logik.Admin;
import logik.Mitarbeiter;
import logik.Personalverwaltung;
import logik.User;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class EditBerechtigungGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
		
//******************** KONSTRUKTOR ********************
	
	public EditBerechtigungGUI(int PID, int wer) {
		/*@author:		Soeren Hebestreit
		 *@date: 		24.07.2019
		 *@description: Mitarbeiterberechtigung editieren
		 */	
		
		setSize(400, 320);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = ((Mitarbeiter) pv.suchen(wer));
		
		JLabel lblFunktion = new JLabel("Berechtigung ändern");
		lblFunktion.setForeground(new Color(255, 255, 255));
		lblFunktion.setFont(new Font("Dialog", Font.BOLD, 21));
		lblFunktion.setBounds(24, 8, 380, 36);
		getContentPane().add(lblFunktion);
		
		JLabel lblPNr = new JLabel("PNr. "+wer+" - "+ma.getVorname()+" "+ma.getName());
		lblPNr.setForeground(new Color(255, 255, 255));
		lblPNr.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPNr.setBounds(24, 36, 380, 24);
		getContentPane().add(lblPNr);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 480, 64);
		getContentPane().add(rahmenOben);
		
		int x = 172;
		int y = 40;
		
		ButtonGroup group = new ButtonGroup();
		JRadioButton rdbtnNull = new JRadioButton("keine Berechtigung");
		rdbtnNull.setBackground(new Color(255, 255, 255));
		rdbtnNull.setBounds(40, y+40, 200, 24);
		group.add(rdbtnNull);
		getContentPane().add(rdbtnNull);
		if(ma.getBerechtigung() == null) {
			rdbtnNull.setSelected(true);
		}
		
		JRadioButton rdbtnStandarduser = new JRadioButton("Standarduser");
		rdbtnStandarduser.setBackground(new Color(255, 255, 255));
		rdbtnStandarduser.setBounds(40, y+80, 200, 24);
		group.add(rdbtnStandarduser);
		getContentPane().add(rdbtnStandarduser);
		if(ma.getBerechtigung() instanceof User) {
			rdbtnStandarduser.setSelected(true);
		}
		
		JRadioButton rdbtnAdmin = new JRadioButton("Administrator");
		rdbtnAdmin.setBackground(new Color(255, 255, 255));
		rdbtnAdmin.setBounds(40, y+120, 200, 24);
		group.add(rdbtnAdmin);
		getContentPane().add(rdbtnAdmin);
		if(ma.getBerechtigung() instanceof Admin) {
			rdbtnAdmin.setSelected(true);
		}
		
		JPanel rahmenMitte = new JPanel();
		rahmenMitte.setBackground(new Color(100, 150, 200));
		rahmenMitte.setBounds(0, y+196, 400, 4);
		getContentPane().add(rahmenMitte);
		
		JButton btnConfirm = new JButton("OK");
		btnConfirm.setBackground(new Color(255, 255, 255));
		btnConfirm.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(PID == wer) {
					JOptionPane.showMessageDialog(null, "Diese Option kann nicht auf den Anwender selbst angewandt werden.", null, JOptionPane.INFORMATION_MESSAGE);
				} else {
					Admin admin = new Admin(PID);
					if(rdbtnAdmin.isSelected()) {
						admin.changeMABerechtigung(wer, new Admin(wer));
					} else if(rdbtnStandarduser.isSelected()) {
						admin.changeMABerechtigung(wer, new User(wer));
					} else {
						admin.changeMABerechtigung(wer, null);
					}
					try {
						pv.speichern();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					dispose();
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
		new EditBerechtigungGUI(0,1);
	}
}
