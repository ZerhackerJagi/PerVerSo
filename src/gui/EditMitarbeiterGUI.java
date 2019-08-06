package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import extern.Datum;
import logik.Admin;
import logik.Arbeitsbereich;
import logik.Arbeitsbereichverwaltung;
import logik.Mitarbeiter;
import logik.Personalverwaltung;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class EditMitarbeiterGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	
//******************** KONSTRUKTOR ********************
	
	public EditMitarbeiterGUI(int PID, int wer, boolean edit) {
		/*@author:		Soeren Hebestreit
		 *@date: 		22.07.2019
		 *@description: Mitarbeiter editieren oder anlegen
		 */	
		
		setSize(400, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(wer);
		
		JLabel lblFunktion = new JLabel("");
		if(edit == true) {
			lblFunktion.setText("Mitarbeiter editieren");
		} else {
			lblFunktion.setText("Mitarbeiter anlegen");
		}
		lblFunktion.setForeground(new Color(255, 255, 255));
		lblFunktion.setFont(new Font("Dialog", Font.BOLD, 21));
		lblFunktion.setBounds(24, 8, 380, 36);
		getContentPane().add(lblFunktion);
		
		JLabel lblPNr = new JLabel("PNr. "+wer);
		lblPNr.setForeground(new Color(255, 255, 255));
		lblPNr.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPNr.setBounds(24, 36, 240, 24);
		getContentPane().add(lblPNr);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 480, 64);
		getContentPane().add(rahmenOben);
		
		int y = 40;
		int x = 172;
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(40, y+40, 120, 20);
		getContentPane().add(lblName);
		
		JTextField tfName = new JTextField();
		if(edit == true) {
			tfName.setText(ma.getName());
		}
		tfName.setBounds(x, y+38, 180, 24);
		getContentPane().add(tfName);
			
		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setBounds(40, y+80, 120, 20);
		getContentPane().add(lblVorname);
		
		JTextField tfVorname = new JTextField();
		if(edit == true) {
			tfVorname.setText(ma.getVorname());
		}
		tfVorname.setBounds(x, y+78, 180, 24);
		getContentPane().add(tfVorname);
		
		JLabel lblGender = new JLabel("Geschlecht:");
		lblGender.setBounds(40, y+120, 120, 20);
		getContentPane().add(lblGender);
		
		Character [] genderListe = {' ','d','m','w'};
		JComboBox<Character> genderBox = new JComboBox<Character>(genderListe);
		if(edit == true) {
			genderBox.setSelectedItem(ma.getGeschlecht());
		}
		genderBox.setBackground(new Color(255, 255, 255));
		genderBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		genderBox.setBounds(x, y+118, 180, 24);
		genderBox.setMaximumRowCount(4);
		getContentPane().add(genderBox);
		
		JLabel lblGeburtstag = new JLabel("Geburtstag:");
		lblGeburtstag.setBounds(40, y+160, 120, 20);
		getContentPane().add(lblGeburtstag);
		
		JTextField tfGeburtstagT = new JTextField();
		if(edit == true) {
			tfGeburtstagT.setText(ma.getGeburtsdatum().getTag()+"");
		}
		tfGeburtstagT.setBounds(x, y+158, 40, 24);
		getContentPane().add(tfGeburtstagT);
		
		JLabel lblGebPunkt1 = new JLabel(".",0);
		lblGebPunkt1.setBounds(x+40, y+160, 10, 20);
		getContentPane().add(lblGebPunkt1);
		
		JTextField tfGeburtstagM = new JTextField();
		if(edit == true) {
			tfGeburtstagM.setText(ma.getGeburtsdatum().getMonat()+"");
		}
		tfGeburtstagM.setBounds(x+50, y+158, 40, 24);
		getContentPane().add(tfGeburtstagM);
		
		JLabel lblGebPunkt2 = new JLabel(".",0);
		lblGebPunkt2.setBounds(x+90, y+160, 10, 20);
		getContentPane().add(lblGebPunkt2);
		
		JTextField tfGeburtstagJ = new JTextField();
		if(edit == true) {
			tfGeburtstagJ.setText(ma.getGeburtsdatum().getJahr()+"");
		}
		tfGeburtstagJ.setBounds(x+100, y+158, 80, 24);
		getContentPane().add(tfGeburtstagJ);
		
		JPanel rahmenMitte = new JPanel();
		rahmenMitte.setBackground(new Color(100, 150, 200));
		rahmenMitte.setBounds(0, y+196, 400, 4);
		getContentPane().add(rahmenMitte);
		
		JLabel lblSollstunden = new JLabel("Arbeitsstunden:");
		lblSollstunden.setBounds(40, y+216, 120, 20);
		getContentPane().add(lblSollstunden);
		
		JTextField tfSollstunden = new JTextField();
		if(edit == true) {
			tfSollstunden.setText(ma.getAzk().getSollstunden()+"");
		}
		tfSollstunden.setBounds(x, y+214, 180, 24);
		getContentPane().add(tfSollstunden);
		
		JLabel lblUrlaub = new JLabel("Urlaubstage:");
		lblUrlaub.setBounds(40, y+256, 120, 20);
		getContentPane().add(lblUrlaub);
		
		JTextField tfUrlaub = new JTextField();
		if(edit == true) {
			tfUrlaub.setText(ma.getAzk().getUrlaubbasis()+"");
		}
		tfUrlaub.setBounds(x, y+254, 180, 24);
		getContentPane().add(tfUrlaub);
		
		JLabel lblEinstellung = new JLabel("Engestellt zum:");
		lblEinstellung.setBounds(40, y+296, 120, 20);
		getContentPane().add(lblEinstellung);
		
		JTextField tfEinstellungT = new JTextField();
		if(edit == true) {
			tfEinstellungT.setText(ma.getEinstellungsdatum().getTag()+"");
			tfEinstellungT.setEditable(false);
		}
		tfEinstellungT.setBounds(x, y+294, 40, 24);
		getContentPane().add(tfEinstellungT);
		
		JLabel lblEinstellungPunkt1 = new JLabel(".",0);
		lblEinstellungPunkt1.setBounds(x+40, y+296, 10, 20);
		getContentPane().add(lblEinstellungPunkt1);
		
		JTextField tfEinstellungM = new JTextField();
		if(edit == true) {
			tfEinstellungM.setText(ma.getEinstellungsdatum().getMonat()+"");
			tfEinstellungM.setEditable(false);
		}
		tfEinstellungM.setBounds(x+50, y+294, 40, 24);
		getContentPane().add(tfEinstellungM);
		
		JLabel lblEinstellungPunkt2 = new JLabel(".",0);
		lblEinstellungPunkt2.setBounds(x+90, y+296, 10, 20);
		getContentPane().add(lblEinstellungPunkt2);
		
		JTextField tfEinstellungJ = new JTextField();
		if(edit == true) {
			tfEinstellungJ.setText(ma.getEinstellungsdatum().getJahr()+"");
			tfEinstellungJ.setEditable(false);
		}
		tfEinstellungJ.setBounds(x+100, y+294, 80, 24);
		getContentPane().add(tfEinstellungJ);
		
		JLabel lblBereich = new JLabel("Arbeitsbereich:");
		lblBereich.setBounds(40, y+336, 120, 20);
		getContentPane().add(lblBereich);		
		
		String[] bereiche = new String[Arbeitsbereichverwaltung.getBereiche().size()];
		for(int i = 0;i<Arbeitsbereichverwaltung.getBereiche().size();i++) {
			bereiche[i] = Arbeitsbereichverwaltung.getBereiche().get(i).toString();
		}	
		JComboBox<String> bereicheBox = new JComboBox<String>(bereiche);
		if(edit == true) {
			JTextField tfBereich = new JTextField();
			tfBereich.setText(((Arbeitsbereich)av.suchen(ma.getActualAB().getArbeitsbereichnummer())).getName());
			tfBereich.setEditable(false);
			tfBereich.setBounds(x, y+334, 180, 24);
			getContentPane().add(tfBereich);
		} else {
			bereicheBox.setBackground(new Color(255, 255, 255));
			bereicheBox.setFont(new Font("Dialog", Font.PLAIN, 12));
			bereicheBox.setBounds(x, y+334, 180, 24);
			bereicheBox.setMaximumRowCount(8);
			getContentPane().add(bereicheBox);
		}
		
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(0, y+372, 400, 4);
		getContentPane().add(rahmenUnten);
		
		JButton btnConfirm = new JButton("OK");
		btnConfirm.setBackground(new Color(255, 255, 255));
		btnConfirm.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tfName.getText().isEmpty() || tfVorname.getText().isEmpty() || tfGeburtstagT.getText().isEmpty() || tfGeburtstagM.getText().isEmpty() || tfGeburtstagJ.getText().isEmpty() || tfEinstellungT.getText().isEmpty() || tfEinstellungM.getText().isEmpty() || tfEinstellungJ.getText().isEmpty() || tfSollstunden.getText().isEmpty() || tfUrlaub.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen.", null, JOptionPane.INFORMATION_MESSAGE);
				} else {
					Admin admin = new Admin(PID);
					Character gender = (Character)genderBox.getSelectedItem();
					if(gender == ' ') {
						gender = 'd';
					}
					if(edit == true) {
						try {
							admin.editMAStammdaten(wer, tfName.getText(), tfVorname.getText(), gender, new Datum(Integer.parseInt(tfGeburtstagT.getText()),Integer.parseInt(tfGeburtstagM.getText()),Integer.parseInt(tfGeburtstagJ.getText())));
							admin.editAZKVertragsdaten(wer, Integer.parseInt(tfSollstunden.getText()), Integer.parseInt(tfUrlaub.getText()));		
							try {
								pv.speichern();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							dispose();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					} else {
						try {
							admin.addMA(tfName.getText(), tfVorname.getText(), gender, new Datum(Integer.parseInt(tfGeburtstagT.getText()),Integer.parseInt(tfGeburtstagM.getText()),Integer.parseInt(tfGeburtstagJ.getText())), new Datum(Integer.parseInt(tfEinstellungT.getText()),Integer.parseInt(tfEinstellungM.getText()),Integer.parseInt(tfEinstellungJ.getText())), Integer.parseInt(((String)bereicheBox.getSelectedItem()).substring(0,((String)bereicheBox.getSelectedItem()).indexOf(" "))));
							admin.editAZKVertragsdaten(wer, Integer.parseInt(tfSollstunden.getText()), Integer.parseInt(tfUrlaub.getText()));	
							try {
								pv.speichern();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							dispose();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
					
				}
			}
		});
		btnConfirm.setBounds(x, y+392, 64, 24);
		getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.setBackground(new Color(255, 255, 255));
		btnCancel.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(x+80, y+392, 100, 24);
		getContentPane().add(btnCancel);
		
		setVisible(true);	
	}		

	public static void main(String[] args) throws Exception {
		
		Arbeitsbereichverwaltung.getInstance().laden();
		Personalverwaltung.getInstance().laden();
		new EditMitarbeiterGUI(100000,Personalverwaltung.getaMA().get(Personalverwaltung.getaMA().size()-1).getPersonalnummer()+1,false);
		new EditMitarbeiterGUI(100000,100001,true);
	}
}
