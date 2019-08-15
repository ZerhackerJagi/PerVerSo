package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import _programmstart.Programmstart;
import logik.Admin;
import logik.Arbeitsbereich;
import logik.Arbeitsbereichverwaltung;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
/*@author:		Soeren Hebestreit
 *@date: 		25.07.2019
 *@description: Mitarbeiter editieren oder anlegen
 */

public class EditBereichGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;	
	
//******************** KONSTRUKTOR ********************
	
	public EditBereichGUI(int PID, int welcher, boolean edit) {
			
		
		setIconImages(Programmstart.iconlist);
		
		setSize(400, 320);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		Arbeitsbereich ab = (Arbeitsbereich) abv.suchen(welcher);
		
		JLabel lblFunktion = new JLabel("");
		if(edit == true) {
			lblFunktion.setText("Arbeitsbereich editieren");
		} else {
			lblFunktion.setText("Arbeitsbereich anlegen");
		}
		lblFunktion.setForeground(new Color(255, 255, 255));
		lblFunktion.setFont(new Font("Dialog", Font.BOLD, 21));
		lblFunktion.setBounds(24, 8, 380, 36);
		getContentPane().add(lblFunktion);
		
		JLabel lblPNr = new JLabel("Nr. "+welcher);
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
			tfName.setText(ab.getName());
		}
		tfName.setBounds(x, y+38, 180, 24);
		getContentPane().add(tfName);
			
		JLabel lblBeschreibung = new JLabel("Beschreibung:");
		lblBeschreibung.setBounds(40, y+80, 120, 20);
		getContentPane().add(lblBeschreibung);
		
		JTextArea tfBeschreibung = new JTextArea();
		if(edit == true) {
			tfBeschreibung.setText(ab.getBeschreibung());
		}
		tfBeschreibung.setWrapStyleWord(true);
		tfBeschreibung.setLineWrap(true);
		tfBeschreibung.setBorder(new JTextField().getBorder());
		tfBeschreibung.setMargin(new Insets(2,0,0,0));
		tfBeschreibung.setBounds(x, y+78, 180, 104);
		getContentPane().add(tfBeschreibung);
		
		JPanel rahmenMitte = new JPanel();
		rahmenMitte.setBackground(new Color(100, 150, 200));
		rahmenMitte.setBounds(0, y+196, 400, 4);
		getContentPane().add(rahmenMitte);
		
		JButton btnConfirm = new JButton("OK");
		btnConfirm.setBackground(new Color(255, 255, 255));
		btnConfirm.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(welcher == 0 || welcher == 1) {
					JOptionPane.showMessageDialog(null, "Dieser Arbeitsbereich ist für die Bearbeitung gesperrt.", null, JOptionPane.INFORMATION_MESSAGE);
				} else if(tfName.getText().isEmpty() || tfBeschreibung.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen.", null, JOptionPane.INFORMATION_MESSAGE);
				} else {
					Admin admin = new Admin(PID);
					if(edit == true) {
						admin.editABName(welcher, tfName.getText());
						admin.editABBeschreibung(welcher, tfBeschreibung.getText());
					} else {
						admin.addAB(tfName.getText(), tfBeschreibung.getText());
					}
					try {
						abv.speichern();
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
}
