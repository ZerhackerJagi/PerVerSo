package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import extern.Datum;
import logik.Admin;
import logik.Arbeitsbereich;
import logik.Arbeitsbereichverwaltung;
import logik.Mitarbeiter;
import logik.Personalverwaltung;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class EditLinkingMaGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	
//******************** KONSTRUKTOR ********************
	
	public EditLinkingMaGUI(int PID, int wer) {
		/*@author:		Soeren Hebestreit
		 *@date: 		25.07.2019
		 *@description: Zugehörigkeit eines Mitarbeiters ändern
		 */	
		
		setSize(400, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(wer);
		
		JLabel lblFunktion = new JLabel("Zugehörigkeit ändern");
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
		
		JLabel lblNameData = new JLabel(ma.getName());
		lblNameData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNameData.setBounds(x, y+40, 180, 20);
		getContentPane().add(lblNameData);
			
		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setBounds(40, y+70, 120, 20);
		getContentPane().add(lblVorname);
		
		JLabel lblVornameData = new JLabel(ma.getVorname());
		lblVornameData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblVornameData.setBounds(x, y+70, 180, 20);
		getContentPane().add(lblVornameData); 

		JLabel lblGeburtstag = new JLabel("Geburtstag:");
		lblGeburtstag.setBounds(40, y+100, 120, 20);
		getContentPane().add(lblGeburtstag);
		
		JLabel lblGeburtstagData = new JLabel(ma.getGeburtsdatum()+"");
		lblGeburtstagData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGeburtstagData.setBounds(x, y+100, 180, 20);
		getContentPane().add(lblGeburtstagData);
		
		JLabel lblBereich = new JLabel("aktueller Bereich:");
		lblBereich.setBounds(40, y+130, 120, 20);
		getContentPane().add(lblBereich);		
		
		JLabel lblBereichData = new JLabel(((Arbeitsbereich)abv.suchen(ma.getActualAB().getArbeitsbereichnummer())).getName());
		lblBereichData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBereichData.setBounds(x, y+130, 180, 20);
		getContentPane().add(lblBereichData);
		
		JLabel lblSeit = new JLabel("seit:");
		lblSeit.setBounds(40, y+160, 120, 20);
		getContentPane().add(lblSeit);		
		
		JLabel lblSeitData = new JLabel(ma.getActualAB().getStart()+"");
		lblSeitData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSeitData.setBounds(x, y+160, 180, 20);
		getContentPane().add(lblSeitData);
		
		JPanel rahmenMitte = new JPanel();
		rahmenMitte.setBackground(new Color(100, 150, 200));
		rahmenMitte.setBounds(0, y+196, 400, 4);
		getContentPane().add(rahmenMitte);
		
		JLabel lblSollstunden = new JLabel("neuer Bereich:");
		lblSollstunden.setBounds(40, y+216, 120, 20);
		getContentPane().add(lblSollstunden);
		
		String[] bereiche = new String[Arbeitsbereichverwaltung.getBereiche().size()+1];
		for(int i = 1;i<Arbeitsbereichverwaltung.getBereiche().size()+1;i++) {
			bereiche[i] = Arbeitsbereichverwaltung.getBereiche().get(i-1).toString();
		}
		JComboBox<String> bereicheBox = new JComboBox<String>(bereiche);
		bereicheBox.setBackground(new Color(255, 255, 255));
		bereicheBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		bereicheBox.setBounds(x, y+216, 180, 24);
		bereicheBox.setMaximumRowCount(8);
		getContentPane().add(bereicheBox);
		
		JLabel lblWechsel = new JLabel("zum:");
		lblWechsel.setBounds(40, y+256, 120, 20);
		getContentPane().add(lblWechsel);
		
		JTextField tfWechselT = new JTextField();
		tfWechselT.setBounds(x, y+254, 40, 24);
		getContentPane().add(tfWechselT);
		
		JLabel lblWechselPunkt1 = new JLabel(".",0);
		lblWechselPunkt1.setBounds(x+40, y+256, 10, 20);
		getContentPane().add(lblWechselPunkt1);
		
		JTextField tfWechselM = new JTextField();
		tfWechselM.setBounds(x+50, y+254, 40, 24);
		getContentPane().add(tfWechselM);
		
		JLabel lblWechselPunkt2 = new JLabel(".",0);
		lblWechselPunkt2.setBounds(x+90, y+256, 10, 20);
		getContentPane().add(lblWechselPunkt2);
		
		JTextField tfWechselJ = new JTextField();
		tfWechselJ.setBounds(x+100, y+254, 80, 24);
		getContentPane().add(tfWechselJ);
		
		JLabel lblBemerkung = new JLabel("<html>Bemerkung:<br>(optional)</html>");
		lblBemerkung.setBounds(40, y+296, 120, 40);
		getContentPane().add(lblBemerkung);
		
		JTextArea tfBemerkung = new JTextArea();
		tfBemerkung.setWrapStyleWord(true);
		tfBemerkung.setLineWrap(true);
		tfBemerkung.setBorder(new JTextField().getBorder());
		tfBemerkung.setMargin(new Insets(2,0,0,0));
		tfBemerkung.setBounds(x, y+294, 180, 64);
		getContentPane().add(tfBemerkung);
		
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(0, y+372, 400, 4);
		getContentPane().add(rahmenUnten);
		
		JButton btnConfirm = new JButton("OK");
		btnConfirm.setBackground(new Color(255, 255, 255));
		btnConfirm.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(((String)bereicheBox.getSelectedItem())==null || tfWechselT.getText().isEmpty() || tfWechselM.getText().isEmpty() || tfWechselJ.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen.", null, JOptionPane.INFORMATION_MESSAGE);
				} else if(PID == wer && Integer.parseInt(((String)bereicheBox.getSelectedItem()).substring(0,((String)bereicheBox.getSelectedItem()).indexOf(" "))) == 1) {
					JOptionPane.showMessageDialog(null, "Für diesen Bereich für den Anweder selbst nicht möglich.", null, JOptionPane.INFORMATION_MESSAGE);
				} else {
					Datum wechseln = new Datum(Integer.parseInt(tfWechselT.getText()),Integer.parseInt(tfWechselM.getText()),Integer.parseInt(tfWechselJ.getText()));
					if(wechseln.compareTo(ma.getEinstellungsdatum())<0) {
						JOptionPane.showMessageDialog(null, "Das Wechseldatum darf nicht vor dem der Einstellung liegen.", null, JOptionPane.INFORMATION_MESSAGE);
					} else {
						Admin admin = new Admin(PID);
						admin.linkMAtoAB(wer, Integer.parseInt(((String)bereicheBox.getSelectedItem()).substring(0,((String)bereicheBox.getSelectedItem()).indexOf(" "))), wechseln,tfBemerkung.getText());
						try {
							pv.speichern();
						} catch (Exception e1) {
						e1.printStackTrace();
						}
						dispose();
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
		
		Personalverwaltung.getInstance().setDatenBank("Beispiel");	
		Arbeitsbereichverwaltung.getInstance().laden();
		Personalverwaltung.getInstance().laden();
		new EditLinkingMaGUI(100000,100000);
	}
}
