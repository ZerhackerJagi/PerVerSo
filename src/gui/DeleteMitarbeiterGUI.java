package gui;

import javax.swing.JButton;
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

public class DeleteMitarbeiterGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	
//******************** KONSTRUKTOR ********************
	
	public DeleteMitarbeiterGUI(int PID, int wer) {
		/*@author:		Soeren Hebestreit
		 *@date: 		24.07.2019
		 *@description: Mitarbeiter loeschen oder ausscheiden
		 */	
		
		setSize(400, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
		Mitarbeiter ma = ((Mitarbeiter) pv.suchen(wer));
		
		JLabel lblFunktion = new JLabel("Mitarbeiter entfernen");
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
		
		JLabel lblBereich = new JLabel("Arbeitsbereich:");
		lblBereich.setBounds(40, y+130, 120, 20);
		getContentPane().add(lblBereich);		
		
		JLabel lblBereichData = new JLabel(((Arbeitsbereich)av.suchen(ma.getActualAB().getArbeitsbereichnummer())).getName());
		lblBereichData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBereichData.setBounds(x, y+130, 180, 20);
		getContentPane().add(lblBereichData);
		
		JLabel lblHinweisDelete = new JLabel("Hinweis");
		lblHinweisDelete.setForeground(new Color(255, 255, 255));
		lblHinweisDelete.setFont(new Font("Dialog", Font.BOLD, 14));
		lblHinweisDelete.setBounds(40, y+170, 200, 20);
		getContentPane().add(lblHinweisDelete);
		
		JLabel lblHinweisTextDelete = new JLabel("<html>Löschen entfernt den Mitarbeiter vollständig aus der Verwaltung</html>");
		lblHinweisTextDelete.setForeground(new Color(255, 255, 255));
		lblHinweisTextDelete.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblHinweisTextDelete.setBounds(40, y+190, 200, 40);
		getContentPane().add(lblHinweisTextDelete);
		
		JButton btnDelete = new JButton("Löschen");
		btnDelete.setBackground(new Color(255, 255, 255));
		btnDelete.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(PID == wer) {
					JOptionPane.showMessageDialog(null, "Diese Option kann nicht auf den Anwender selbst angewandt werden.", null, JOptionPane.INFORMATION_MESSAGE);
				} else {
					if((JOptionPane.showConfirmDialog(null, ma.getVorname()+" "+ma.getName()+" löschen?", null, JOptionPane.YES_NO_OPTION)) == 0) {
						Admin admin = new Admin(PID);
						admin.removeMA(wer);
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
		btnDelete.setBounds(x+80, y+188, 100, 24);
		getContentPane().add(btnDelete);
		
		JPanel rahmenMitte = new JPanel();
		rahmenMitte.setBackground(new Color(100, 150, 200));
		rahmenMitte.setBounds(0, y+166, 400, 68);
		getContentPane().add(rahmenMitte);		
		
		JLabel lblAusscheiden = new JLabel("Ausgeschieden am:");
		lblAusscheiden.setBounds(40, y+256, 120, 20);
		getContentPane().add(lblAusscheiden);
		
		JTextField tfAusscheidenT = new JTextField();
		tfAusscheidenT.setBounds(x, y+254, 40, 24);
		getContentPane().add(tfAusscheidenT);
		
		JLabel lblAusscheidenPunkt1 = new JLabel(".",0);
		lblAusscheidenPunkt1.setBounds(x+40, y+256, 10, 20);
		getContentPane().add(lblAusscheidenPunkt1);
		
		JTextField tfAusscheidenM = new JTextField();
		tfAusscheidenM.setBounds(x+50, y+254, 40, 24);
		getContentPane().add(tfAusscheidenM);
		
		JLabel lblAusscheidenPunkt2 = new JLabel(".",0);
		lblAusscheidenPunkt2.setBounds(x+90, y+256, 10, 20);
		getContentPane().add(lblAusscheidenPunkt2);
		
		JTextField tfAusscheidenJ = new JTextField();
		tfAusscheidenJ.setBounds(x+100, y+254, 80, 24);
		getContentPane().add(tfAusscheidenJ);
		
		JLabel lblHinweisTextLeave = new JLabel("<html>Ausscheiden setzt die Zugehörigkeit des Mitarbeiters auf Ausgeschieden und löscht die Berechtigung. Das Ausscheidungsdatum muss in der Vergangenheit liegen.</html>");
		lblHinweisTextLeave.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblHinweisTextLeave.setBounds(40, y+290, 320, 56);
		getContentPane().add(lblHinweisTextLeave);
		
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(0, y+372, 400, 4);
		getContentPane().add(rahmenUnten);
		
		JButton btnLeave = new JButton("Mitarbeiter ausscheiden");
		btnLeave.setBackground(new Color(255, 255, 255));
		btnLeave.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(PID == wer) {
					JOptionPane.showMessageDialog(null, "Diese Option kann nicht auf den Anwender selbst angewandt werden.", null, JOptionPane.INFORMATION_MESSAGE);
				} else if(tfAusscheidenT.getText().isEmpty() || tfAusscheidenM.getText().isEmpty() || tfAusscheidenJ.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Bitte Ausscheidungsdatum ausfüllen.", null, JOptionPane.INFORMATION_MESSAGE);
				} else {
					Datum ausscheiden = new Datum(Integer.parseInt(tfAusscheidenT.getText()),Integer.parseInt(tfAusscheidenM.getText()),Integer.parseInt(tfAusscheidenJ.getText()));
					Datum einstellung = ma.getEinstellungsdatum();
					Datum today = new Datum();
					if (today.compareTo(ausscheiden) < 0){
						JOptionPane.showMessageDialog(null, "Das Ausscheidungsdatum liegt nicht in der Vergangenheit!", null, JOptionPane.INFORMATION_MESSAGE);
					} else if(ausscheiden.compareTo(einstellung) < 0) {
						JOptionPane.showMessageDialog(null, "Das Ausscheidungsdatum vor dem Einstellungsdatum!", null, JOptionPane.INFORMATION_MESSAGE);
					}else {
						if((JOptionPane.showConfirmDialog(null, ma.getVorname()+" "+ma.getName()+" ausscheiden?", null, JOptionPane.YES_NO_OPTION)) == 0) {
							Admin admin = new Admin(PID);
							admin.ausscheidenMA(wer, ausscheiden);
							try {
								pv.speichern();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						dispose();
						}
					}
				}
			}
		});
		btnLeave.setBounds(40, y+392, 200, 24);
		getContentPane().add(btnLeave);
		
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
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		pv.laden();
		abv.laden();
		pv.add("Test", "Test", 'd', new Datum(), new Datum(), 0);
		Personalverwaltung.getaMA().get(Personalverwaltung.getaMA().size()-1).setBerechtigung(new Admin(Personalverwaltung.getaMA().size()-1));
		new DeleteMitarbeiterGUI(0,Personalverwaltung.getaMA().size()-1);
	}
	
}
