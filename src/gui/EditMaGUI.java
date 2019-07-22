package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import extern.Datum;
import logik.*;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class EditMaGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	
	
//******************** KONSTRUKTOR ********************
	
	public EditMaGUI(int PID, int wer, boolean edit) {
		/*@author:		Soeren Hebestreit
		 *@date: 		22.07.2019
		 *@description: Mitarbeiter editieren oder anlegen
		 */	
		
		Color schrift = new Color(255, 255, 255);
		setSize(480, 480);
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(100, 150, 200));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		try {
			pv.laden();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Mitarbeiter ma = ((Mitarbeiter) pv.suchen(wer));
		
		JLabel lblFunktion = new JLabel("");
		if(edit == true) {
			lblFunktion.setText("Mitarbeiter editieren");
		} else {
			lblFunktion.setText("Mitarbeiter anlegen");
		}
		lblFunktion.setForeground(schrift);
		lblFunktion.setFont(new Font("Dialog", Font.BOLD, 21));
		lblFunktion.setBounds(40, 20, 380, 40);
		getContentPane().add(lblFunktion);
		
		int k = 88;
		
		JLabel lblPNr = new JLabel("Personalnummer:");
		lblPNr.setForeground(schrift);
		lblPNr.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPNr.setBounds(40, k, 200, 20);
		getContentPane().add(lblPNr);
		
		JTextField tfPNr = new JTextField();
		if(edit == true) {
			tfPNr.setText(PID+"");
		} else {
			tfPNr.setText(Personalverwaltung.getaMA().size()+"");
		}
		tfPNr.setBounds(220, k, 200, 24);
		tfPNr.setEditable(false);
		tfPNr.setBackground(schrift);
		getContentPane().add(tfPNr);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(schrift);
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setBounds(40, k+40, 200, 20);
		getContentPane().add(lblName);
		
		JTextField tfName = new JTextField();
		if(edit == true) {
			tfName.setText(ma.getName());
		}
		tfName.setBounds(220, k+38, 200, 24);
		getContentPane().add(tfName);
			
		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setForeground(schrift);
		lblVorname.setFont(new Font("Dialog", Font.BOLD, 14));
		lblVorname.setBounds(40, k+80, 200, 20);
		getContentPane().add(lblVorname);
		
		JTextField tfVorname = new JTextField();
		if(edit == true) {
			tfVorname.setText(ma.getVorname());
		}
		tfVorname.setBounds(220, k+78, 200, 24);
		getContentPane().add(tfVorname);
		
		JLabel lblGender = new JLabel("Geschlecht (m/w/d):");
		lblGender.setForeground(schrift);
		lblGender.setFont(new Font("Dialog", Font.BOLD, 14));
		lblGender.setBounds(40, k+120, 200, 20);
		getContentPane().add(lblGender);
		
		JTextField tfGender = new JTextField();
		if(edit == true) {
			tfGender.setText(ma.getGeschlecht()+"");
		}
		tfGender.setBounds(220, k+118, 200, 24);
		getContentPane().add(tfGender);
		
		JLabel lblGeburtstag = new JLabel("Geburtstag:");
		lblGeburtstag.setForeground(schrift);
		lblGeburtstag.setFont(new Font("Dialog", Font.BOLD, 14));
		lblGeburtstag.setBounds(40, k+160, 200, 20);
		getContentPane().add(lblGeburtstag);
		
		JTextField tfGeburtstag = new JTextField();
		if(edit == true) {
			tfGeburtstag.setText(ma.getGeburtsdatum()+"");
		}
		tfGeburtstag.setBounds(220, k+158, 200, 24);
		getContentPane().add(tfGeburtstag);
		
		JLabel lblEinstellung = new JLabel("Einstellungsdatum:");
		lblEinstellung.setForeground(schrift);
		lblEinstellung.setFont(new Font("Dialog", Font.BOLD, 14));
		lblEinstellung.setBounds(40, k+200, 200, 20);
		getContentPane().add(lblEinstellung);
		
		JTextField tfEinstellung = new JTextField();
		if(edit == true) {
			tfEinstellung.setText(ma.getEinstellungsdatum()+"");
		}
		tfEinstellung.setBounds(220, k+198, 200, 24);
		getContentPane().add(tfEinstellung);
		
		JLabel lblAusscheiden = new JLabel("Ausscheidungsdatum:");
		lblAusscheiden.setForeground(schrift);
		lblAusscheiden.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAusscheiden.setBounds(40, k+240, 200, 20);
		getContentPane().add(lblAusscheiden);
		
		JTextField tfAusscheiden = new JTextField();
		if(edit == true) {
			tfAusscheiden.setText(ma.getAusscheidungsdatum()+"");
		}
		tfAusscheiden.setBounds(220, k+238, 200, 24);
		getContentPane().add(tfAusscheiden);
		
		JButton btnConfirm = new JButton("OK");
		btnConfirm.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				Admin admin = new Admin(PID);
				//String geb = tfGeburtstag.getText();
				//System.out.println(geb.substring(0, geb.indexOf("."))+"\t"+geb.substring(geb.indexOf(".")+1,geb.lastIndexOf("."))+"\t"+geb.substring(geb.lastIndexOf(".")+1));
				//int x = Integer.parseInt(geb.substring(geb.indexOf(".")+1,geb.lastIndexOf(".")));
				//System.out.println(x);
				Datum geburtstag = new Datum(Integer.parseInt(tfGeburtstag.getText().substring(0, tfGeburtstag.getText().indexOf("."))),Integer.parseInt(tfGeburtstag.getText().substring(tfGeburtstag.getText().indexOf(".")+1,tfGeburtstag.getText().lastIndexOf("."))),Integer.parseInt(tfGeburtstag.getText().substring(tfGeburtstag.getText().lastIndexOf(".")+1)));
				char gender = 'd';
				if(tfGender.getText().charAt(0)=='m') {
					gender = 'm';
				} else if(tfGender.getText().charAt(0)=='w') {
					gender = 'w';
				}
				Datum einstellung = new Datum(Integer.parseInt(tfEinstellung.getText().substring(0, tfEinstellung.getText().indexOf("."))),Integer.parseInt(tfEinstellung.getText().substring(tfEinstellung.getText().indexOf(".")+1,tfEinstellung.getText().lastIndexOf("."))),Integer.parseInt(tfEinstellung.getText().substring(tfEinstellung.getText().lastIndexOf(".")+1)));
				
//				if(edit == true) {
//					admin.editMAStammdaten(wer, tfName.getText(), tfVorname.getText(), gender, geburtstag);
//					admin.editMAEinstellungsdatum(wer, einstellung);
//					admin.editMAAusscheidungsdatum(wer, ausscheiden);					
//				} else {
//					try {
//						admin.addMA(tfName.getText(), tfVorname.getText(), gender, geburtstag, einstellung, 0);
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
				pv.show();
				
			}
		});
		btnConfirm.setBounds(220, k+292, 84, 24);
		getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(316, k+292, 104, 24);
		getContentPane().add(btnCancel);
		
		setVisible(true);	
	}		


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
				
		new EditMaGUI(1,12,true);
	}
}
