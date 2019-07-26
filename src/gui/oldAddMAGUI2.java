package gui;

import javax.swing.JFrame;
import extern.*;
import javafx.scene.control.ComboBox;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import logik.Arbeitsbereichverwaltung;
import logik.Personalverwaltung;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class oldAddMAGUI2 extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField tFVorname;
	private JTextField tFNachname;
	private JTextField tFUrlaub;
	private JTextField tfArbeitszeit;
	private JTextField tfBdayTag;
	private JTextField tfBdayMonat;
	private JTextField tfBdayJahr;
	private JTextField tfEinstellTag;
	private JTextField tfEinstellMonat;
	private JTextField tfEinstellJahr;
	private JTextField textField_3;
	private JTextField tfGeschlecht;
	public oldAddMAGUI2() {
		getContentPane().setLayout(null);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 150, 200));
		panel.setBounds(0, 462, 344, -463);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setBounds(10, 33, 85, 30);
		getContentPane().add(lblVorname);
//		panel.add(lblVorname);
		
		JLabel lblNachname = new JLabel("Nachname");
		lblNachname.setBounds(10, 68, 85, 30);
		getContentPane().add(lblNachname);
//		panel.add(lblNachname);
		
		JLabel lblGeburtsdatumddmmjjjj = new JLabel("Geburtsdatum (ddmmjjjj)");
		lblGeburtsdatumddmmjjjj.setBounds(10, 150, 140, 30);
		getContentPane().add(lblGeburtsdatumddmmjjjj);
//		panel.add(lblGeburtsdatumddmmjjjj);
		
		JLabel lblUrlaubstage = new JLabel("Urlaubstage");
		lblUrlaubstage.setBounds(10, 222, 85, 30);
		getContentPane().add(lblUrlaubstage);
//		panel.add(lblUrlaubstage);
		
		JLabel lblArbeitsstundenwoche = new JLabel("Arbeitsstunden (Woche)");
		lblArbeitsstundenwoche.setBounds(10, 263, 155, 30);
		getContentPane().add(lblArbeitsstundenwoche);
//		panel.add(lblArbeitsstundenwoche);
		
		tFVorname = new JTextField();
		tFVorname.setBounds(175, 38, 179, 20);
		getContentPane().add(tFVorname);
		tFVorname.setColumns(10);
//		panel.add(tFVorname);
		
		tFNachname = new JTextField();
		tFNachname.setColumns(10);
		tFNachname.setBounds(175, 73, 179, 20);
		getContentPane().add(tFNachname);
//		panel.add(tFNachname);
		
		tFUrlaub = new JTextField();
		tFUrlaub.setColumns(10);
		tFUrlaub.setBounds(175, 227, 90, 20);
		getContentPane().add(tFUrlaub);
//		panel.add(tFUrlaub);
		
		tfArbeitszeit = new JTextField();
		tfArbeitszeit.setColumns(10);
		tfArbeitszeit.setBounds(175, 268, 90, 20);
		getContentPane().add(tfArbeitszeit);
//		panel.add(tfArbeitszeit);
		
		tfBdayTag = new JTextField();
		tfBdayTag.setColumns(10);
		tfBdayTag.setBounds(175, 155, 40, 20);
		getContentPane().add(tfBdayTag);
//		panel.add(tfBdayTag);
		
		tfBdayMonat = new JTextField();
		tfBdayMonat.setColumns(10);
		tfBdayMonat.setBounds(225, 155, 40, 20);
		getContentPane().add(tfBdayMonat);
//		panel.add(tfBdayMonat);
		
		tfBdayJahr = new JTextField();
		tfBdayJahr.setColumns(10);
		tfBdayJahr.setBounds(275, 155, 79, 20);
		getContentPane().add(tfBdayJahr);
//		panel.add(tfBdayJahr);
		
		Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
		String[] auswahl = new String[av.getBereiche().size()];
		for(int i = 0;i<av.getBereiche().size();i++) {
			auswahl[i] = av.getBereiche().get(i).toString();
		}
		JComboBox comboBox = new JComboBox(auswahl);
		comboBox.setMaximumRowCount(5);
		comboBox.setBounds(174, 350, 180, 20);
		getContentPane().add(comboBox);
		
		ButtonGroup group = new ButtonGroup();
		JRadioButton rdbtnAdministrator = new JRadioButton("Administrator");
		rdbtnAdministrator.setBounds(74, 192, 109, 23);
		getContentPane().add(rdbtnAdministrator);
		group.add(rdbtnAdministrator);
//		panel.add(rdbtnAdministrator);
		
		JRadioButton rdbtnStandarduser = new JRadioButton("Standarduser");
		rdbtnStandarduser.setBounds(212, 192, 109, 23);
		getContentPane().add(rdbtnStandarduser);
		group.add(rdbtnStandarduser);
		rdbtnStandarduser.setSelected(true);
//		panel.add(rdbtnStandarduser);
//		pack();
		
		
		
		
		
//		panel.add(btnHinzufgen);
		
		
		
		
		JButton btnAddMore = new JButton("Add More");
		btnAddMore.setBounds(126, 409, 114, 23);
		getContentPane().add(btnAddMore);
//		panel.add(btnAddMore);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				
			}
		});
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAbbrechen.setBounds(250, 409, 104, 23);
		getContentPane().add(btnAbbrechen);
//		panel.add(btnAbbrechen);
		
		JLabel lblEinstellungZumddmmjjjj = new JLabel("Einstellung zum (ddmmjjjj)");
		lblEinstellungZumddmmjjjj.setBounds(10, 304, 154, 30);
		getContentPane().add(lblEinstellungZumddmmjjjj);
//		panel.add(lblEinstellungZumddmmjjjj);
		
		tfEinstellTag = new JTextField();
		tfEinstellTag.setColumns(10);
		tfEinstellTag.setBounds(175, 309, 40, 20);
		getContentPane().add(tfEinstellTag);
		 
		
		tfEinstellMonat = new JTextField();
		tfEinstellMonat.setColumns(10);
		tfEinstellMonat.setBounds(225, 309, 40, 20);
		getContentPane().add(tfEinstellMonat);
//		panel.add(textField_1);
		
		tfEinstellJahr = new JTextField();
		tfEinstellJahr.setColumns(10);
		tfEinstellJahr.setBounds(275, 309, 79, 20);
		getContentPane().add(tfEinstellJahr);
//		panel.add(textField_2);
		
		JLabel lblAbteilung = new JLabel("Abteilung");
		lblAbteilung.setBounds(10, 345, 85, 30);
		getContentPane().add(lblAbteilung);

		
//		panel.add(list);
		
		JLabel lblGeschlecht = new JLabel("Geschlecht (m/w/d)");
		lblGeschlecht.setBounds(10, 109, 123, 30);
		getContentPane().add(lblGeschlecht);
//		panel.add(lblGeschlecht);
		
//		tfGeschlecht = new JTextField();
//		tfGeschlecht.setColumns(10);
//		tfGeschlecht.setBounds(175, 114, 27, 20);
//		getContentPane().add(tfGeschlecht);
////		panel.add(textField_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(100, 150, 200));
		panel_1.setBounds(0, 186, 394, 5);
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(100, 150, 200));
		panel_2.setBounds(0, 0, 394, 30);
		getContentPane().add(panel_2);
		//		panel.add(panel_1);
				
				JLabel lblMitarbeiterHinzufgen = new JLabel("Mitarbeiter hinzuf\u00FCgen ...");
				panel_2.add(lblMitarbeiterHinzufgen);
				lblMitarbeiterHinzufgen.setForeground(new Color(255, 255, 255));
				lblMitarbeiterHinzufgen.setFont(new Font("Tahoma", Font.BOLD, 14));
				

				
				tfGeschlecht = new JTextField();
				tfGeschlecht.setBounds(175, 114, 31, 20);
				getContentPane().add(tfGeschlecht);
				tfGeschlecht.setColumns(10);

//		panel.add(lblMitarbeiterHinzufgen);
		
				
				JButton btnHinzufgen = new JButton("Add");
				btnHinzufgen.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						int bereichsnummer = 0;
						Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
						for(int i = 0; i< av.getBereiche().size();i++) {
							if(av.getBereiche().get(i).toString().equals(comboBox.getSelectedItem())){
								bereichsnummer = av.getBereiche().get(i).getArbeitsbereichnummer();
							}
						}
						System.out.println(tfBdayTag.getText());
						System.out.println(tfBdayMonat);
						System.out.println(tfBdayJahr);
						
						
						Datum Bday = new Datum(Integer.parseInt(tfBdayTag.getText()),Integer.parseInt(tfBdayMonat.getText()), Integer.parseInt(tfBdayJahr.getText()));
						
						Datum Einstelldatum = new Datum(Integer.parseInt(tfEinstellTag.getText()),Integer.parseInt(tfEinstellMonat.getText()), Integer.parseInt(tfEinstellJahr.getText()));
						Personalverwaltung pv = Personalverwaltung.getInstance();
						try {
							pv.add(tFNachname.getText(), tFVorname.getText(), (char) tfGeschlecht.getText().charAt(0),Bday, Einstelldatum,bereichsnummer);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}
				});
				btnHinzufgen.setBounds(27, 409, 89, 23);
				getContentPane().add(btnHinzufgen);
				
				
				
				
//		getContentPane().add(panel);
		setBackground(new Color(100, 150, 200));
		setResizable(false);
		setSize(400, 500);
		setLocationRelativeTo(null);
//		add(panel);
		setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
				
		new oldAddMAGUI2();
	}
}
