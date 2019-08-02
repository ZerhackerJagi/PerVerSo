package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logik.Admin;
import logik.Mitarbeiter;
import logik.Personalverwaltung;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class EditLimitGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
		
//******************** KONSTRUKTOR ********************
	
	public EditLimitGUI(int PID, int wer) {
		/*@author:		Soeren Hebestreit
		 *@date: 		24.07.2019
		 *@description: Mitarbeiterkennung und -passwort editieren
		 */	
		
		setSize(400, 320);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(wer);
		
		JLabel lblFunktion = new JLabel("Überminutengrenzen editieren");
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
		
		int y = 40;
		int x = 172;
		
		JLabel lblMax = new JLabel("Überminuten Max:");
		lblMax.setBounds(40, y+40, 120, 20);
		getContentPane().add(lblMax);
		
		JTextField tfMax = new JTextField();
		tfMax.setText(""+ma.getAzk().getUeberminutenmax());
		tfMax.setBounds(x, y+38, 180, 24);
		getContentPane().add(tfMax);
			
		JLabel lblMin = new JLabel("Überminuten Min:");
		lblMin.setBounds(40, y+80, 120, 20);
		getContentPane().add(lblMin);
		
		JTextField tfMin = new JTextField();
		tfMin.setText(""+ma.getAzk().getUeberminutenmin());
		tfMin.setBounds(x, y+78, 180, 24);
		getContentPane().add(tfMin);
		
		JPanel rahmenMitte = new JPanel();
		rahmenMitte.setBackground(new Color(100, 150, 200));
		rahmenMitte.setBounds(0, y+196, 400, 4);
		getContentPane().add(rahmenMitte);
		
		JButton btnConfirm = new JButton("OK");
		btnConfirm.setBackground(new Color(255, 255, 255));
		btnConfirm.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tfMax.getText().isEmpty()||tfMin.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen.", null, JOptionPane.INFORMATION_MESSAGE);
				} else {
					Admin admin = new Admin(PID);
					admin.editAZKLimit(wer, Integer.parseInt(tfMin.getText()), Integer.parseInt(tfMax.getText()));
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
		Personalverwaltung.getInstance().setDatenBank("Beispiel");	
		Personalverwaltung.getInstance().laden();
		new EditLimitGUI(100000,100001);
	}
}
