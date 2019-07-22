package gui;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import logik.Arbeitsbereichverwaltung;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class AddMAGUI2 extends JFrame{
	private JTextField tFVorname;
	private JTextField tFNachname;
	private JTextField tFUrlaub;
	private JTextField tfArbeitszeit;
	private JTextField tfBdayTag;
	private JTextField tfBdayMonat;
	private JTextField tfBdayJahr;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public AddMAGUI2() {
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
		lblArbeitsstundenwoche.setBounds(10, 263, 123, 30);
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
//		panel.add(rdbtnStandarduser);
//		pack();
		
		JButton btnHinzufgen = new JButton("Add");
		btnHinzufgen.setBounds(27, 409, 89, 23);
		getContentPane().add(btnHinzufgen);
//		panel.add(btnHinzufgen);
		
		JButton btnAddMore = new JButton("Add More");
		btnAddMore.setBounds(126, 409, 89, 23);
		getContentPane().add(btnAddMore);
//		panel.add(btnAddMore);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAbbrechen.setBounds(225, 409, 89, 23);
		getContentPane().add(btnAbbrechen);
//		panel.add(btnAbbrechen);
		
		JLabel lblEinstellungZumddmmjjjj = new JLabel("Einstellung zum (ddmmjjjj)");
		lblEinstellungZumddmmjjjj.setBounds(10, 304, 154, 30);
		getContentPane().add(lblEinstellungZumddmmjjjj);
//		panel.add(lblEinstellungZumddmmjjjj);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(175, 309, 40, 20);
		getContentPane().add(textField);
		 
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(225, 309, 40, 20);
		getContentPane().add(textField_1);
//		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(275, 309, 79, 20);
		getContentPane().add(textField_2);
//		panel.add(textField_2);
		
		JLabel lblAbteilung = new JLabel("Abteilung");
		lblAbteilung.setBounds(10, 345, 85, 30);
		getContentPane().add(lblAbteilung);

		
//		panel.add(list);
		
		JLabel lblGeschlecht = new JLabel("Geschlecht (m/w/d)");
		lblGeschlecht.setBounds(10, 109, 123, 30);
		getContentPane().add(lblGeschlecht);
//		panel.add(lblGeschlecht);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(175, 114, 27, 20);
		getContentPane().add(textField_3);
//		panel.add(textField_3);
		
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
				
				Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
				String[] auswahl = new String[av.getBereiche().size()];
				for(int i = 0;i<av.getBereiche().size();i++) {
					auswahl[i] = av.getBereiche().get(i).toString();
				}
				JComboBox comboBox = new JComboBox(auswahl);
				comboBox.setBounds(174, 350, 180, 20);
				getContentPane().add(comboBox);
//		panel.add(lblMitarbeiterHinzufgen);
		
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
				
		new AddMAGUI2();
	}
}
