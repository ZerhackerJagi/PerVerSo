package gui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import _programmstart.Programmstart;
import extern.Datum;
import logik.Admin;
import logik.Mitarbeiter;
import logik.Personalverwaltung;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
/*@author:		Soeren Hebestreit
 *@date: 		22.07.2019
 *@description: Mitarbeiter editieren oder anlegen
 */	

public class AddEintragGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	
//******************** KONSTRUKTOR ********************
	
	public AddEintragGUI(int PID, int wer) {
			
		setIconImages(Programmstart.iconlist);
		
		setSize(400, 320);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(wer);
		
		JLabel lblFunktion = new JLabel("Eintrag hinzufügen");
		lblFunktion.setForeground(new Color(255, 255, 255));
		lblFunktion.setFont(new Font("Dialog", Font.BOLD, 21));
		lblFunktion.setBounds(24, 8, 380, 36);
		getContentPane().add(lblFunktion);
		
		JLabel lblName = new JLabel("PNr. "+wer+" - "+ma.getVorname()+" "+ma.getName());
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setBounds(24, 36, 240, 24);
		getContentPane().add(lblName);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 480, 64);
		getContentPane().add(rahmenOben);
		
		int y = 40;
		int x = 172;
		
		JLabel lblStart = new JLabel("Startdatum:");
		lblStart.setBounds(40, y+40, 120, 20);
		getContentPane().add(lblStart);
		
		JTextField tfStartT = new JTextField();
		tfStartT.setBounds(x, y+38, 40, 24);
		getContentPane().add(tfStartT);
		
		JLabel lblStartPunkt1 = new JLabel(".",0);
		lblStartPunkt1.setBounds(x+40, y+40, 10, 20);
		getContentPane().add(lblStartPunkt1);
		
		JTextField tfStartM = new JTextField();
		tfStartM.setBounds(x+50, y+38, 40, 24);
		getContentPane().add(tfStartM);
		
		JLabel lblStartPunkt2 = new JLabel(".",0);
		lblStartPunkt2.setBounds(x+90, y+40, 10, 20);
		getContentPane().add(lblStartPunkt2);
		
		JTextField tfStartJ = new JTextField();
		tfStartJ.setBounds(x+100, y+38, 80, 24);
		getContentPane().add(tfStartJ);
		
		JLabel lblEnde = new JLabel("Enddatum:");
		lblEnde.setBounds(40, y+80, 120, 20);
		getContentPane().add(lblEnde);
		
		JTextField tfEndeT = new JTextField();
		tfEndeT.setBounds(x, y+78, 40, 24);
		getContentPane().add(tfEndeT);
		
		JLabel lblEndePunkt1 = new JLabel(".",0);
		lblEndePunkt1.setBounds(x+40, y+80, 10, 20);
		getContentPane().add(lblEndePunkt1);
		
		JTextField tfEndeM = new JTextField();
		tfEndeM.setBounds(x+50, y+78, 40, 24);
		getContentPane().add(tfEndeM);
		
		JLabel lblEndePunkt2 = new JLabel(".",0);
		lblEndePunkt2.setBounds(x+90, y+80, 10, 20);
		getContentPane().add(lblEndePunkt2);
		
		JTextField tfEndeJ = new JTextField();
		tfEndeJ.setBounds(x+100, y+78, 80, 24);
		getContentPane().add(tfEndeJ);
		
		JLabel lblTage = new JLabel("Arbeitstage:");
		lblTage.setBounds(40, y+120, 120, 20);
		getContentPane().add(lblTage);
		
		JTextField tfTage = new JTextField();
		tfTage.setBounds(x, y+120, 40, 24);
		getContentPane().add(tfTage);
		
		ButtonGroup group = new ButtonGroup();
		JRadioButton rdbtnUrlaub = new JRadioButton("Urlaub");
		rdbtnUrlaub.setBackground(new Color(255, 255, 255));
		rdbtnUrlaub.setBounds(x, y+160, 80, 24);
		group.add(rdbtnUrlaub);
		getContentPane().add(rdbtnUrlaub);
		rdbtnUrlaub.setSelected(true);
		
		JRadioButton rdbtnKrankheit = new JRadioButton("Krankheit");
		rdbtnKrankheit.setBackground(new Color(255, 255, 255));
		rdbtnKrankheit.setBounds(x+80, y+160, 80, 24);
		group.add(rdbtnKrankheit);
		getContentPane().add(rdbtnKrankheit);		
		
		JPanel rahmenMitte = new JPanel();
		rahmenMitte.setBackground(new Color(100, 150, 200));
		rahmenMitte.setBounds(0, y+196, 400, 4);
		getContentPane().add(rahmenMitte);
		
		JButton btnConfirm = new JButton("OK");
		btnConfirm.setBackground(new Color(255, 255, 255));
		btnConfirm.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tfStartT.getText().isEmpty() || tfStartM.getText().isEmpty() || tfStartJ.getText().isEmpty() || tfEndeT.getText().isEmpty() || tfEndeM.getText().isEmpty() || tfEndeJ.getText().isEmpty() || tfTage.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen.", null, JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						Datum start = new Datum(Integer.parseInt(tfStartT.getText()),Integer.parseInt(tfStartM.getText()),Integer.parseInt(tfStartJ.getText()));
						Datum ende = new Datum(Integer.parseInt(tfEndeT.getText()),Integer.parseInt(tfEndeM.getText()),Integer.parseInt(tfEndeJ.getText()));			
						if(start.getJahr() != ende.getJahr()) {
							JOptionPane.showMessageDialog(null, "Bitte nicht jahresübergreifend angeben.", null, JOptionPane.INFORMATION_MESSAGE);
						} else if(start.compareTo(ende) == 1) {
							JOptionPane.showMessageDialog(null, "Das Enddatum liegt vor dem Startdatum!", null, JOptionPane.INFORMATION_MESSAGE);
						} else {	
							Admin admin = new Admin(PID);
							if(rdbtnUrlaub.isSelected()) {
								try {
									admin.addAZKUrlaub(wer, start, ende, Integer.parseInt(tfTage.getText()));
									pv.speichern();
									dispose();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							} else {
								try {
									admin.addAZKKrankheit(wer, start, ende, Integer.parseInt(tfTage.getText()));
									pv.speichern();
									dispose();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}
					} catch (Exception e2) {
						e2.printStackTrace();
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
		new AddEintragGUI(100000,100001);
	}
}
