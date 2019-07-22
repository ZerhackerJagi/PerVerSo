package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import logik.*;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class PVGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int wahl;
	
	
//******************** KONSTRUKTOR ********************
	
	public PVGUI(int PID) {
		/*@author:		Soeren Hebestreit
		 *@date: 		21.07.2019
		 *@description: Hauptmenue Personalverwaltung, Mitarbeiterauswahl
		 */	
		
		// Haupt- und Steuerungsbereich
		setSize(800, 640);
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		try {
			pv.laden();
			abv.laden();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Mitarbeiter ma = ((Mitarbeiter) pv.suchen(PID));
		
		JLabel lblPV = new JLabel("Mitarbeiterverwaltung");
		lblPV.setFont(new Font("Dialog", Font.BOLD, 21));
		lblPV.setForeground(new Color(255, 255, 255));
		lblPV.setBounds(24, 20, 360, 24);
		getContentPane().add(lblPV);
		
		JLabel lblName = new JLabel("Angemeldet: "+ma.getVorname()+" "+ma.getName());
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(24, 48, 360, 24);
		getContentPane().add(lblName);
		
		JButton btnBack = new JButton("zurück");
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(680, 24, 80, 40);
		getContentPane().add(btnBack);		
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 800, 88);
		getContentPane().add(rahmenOben);
		
		JPanel rahmenRechts = new JPanel();
		rahmenRechts.setBackground(new Color(100, 150, 200));
		rahmenRechts.setBounds(240, 0, 4, 640);
		getContentPane().add(rahmenRechts);

		JLabel lblStammdaten = new JLabel("Stammdaten");
		lblStammdaten.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStammdaten.setBounds(24, 100, 240, 24);
		getContentPane().add(lblStammdaten);
		
		JButton btnAnlegen = new JButton("Mitarbeiter anlegen");
		btnAnlegen.setBackground(new Color(255, 255, 255));
		btnAnlegen.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				new StatistikGUI(PID);
			}
		});
		btnAnlegen.setBounds(24, 140, 200, 24);
		getContentPane().add(btnAnlegen);
		
		JButton btnBearbeiten = new JButton("Mitarbeiter bearbeiten");
		btnBearbeiten.setBackground(new Color(255, 255, 255));
		btnBearbeiten.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				new StatistikGUI(PID);
			}
		});
		btnBearbeiten.setBounds(24, 164, 200, 24);
		getContentPane().add(btnBearbeiten);
		
		JButton btnEntfernen = new JButton("Mitarbeiter entfernen");
		btnEntfernen.setBackground(new Color(255, 255, 255));
		btnEntfernen.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				new StatistikGUI(PID);
			}
		});
		btnEntfernen.setBounds(24, 188, 200, 24);
		getContentPane().add(btnEntfernen);
		
		JLabel lblKennung = new JLabel("Kennung und Berechtigung");
		lblKennung.setFont(new Font("Dialog", Font.BOLD, 14));
		lblKennung.setBounds(24, 240, 240, 24);
		getContentPane().add(lblKennung);
		
		JButton btnKennung = new JButton("Kennung bearbeiten");
		btnKennung.setBackground(new Color(255, 255, 255));
		btnKennung.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				new StatistikGUI(PID);
			}
		});
		btnKennung.setBounds(24, 280, 200, 24);
		getContentPane().add(btnKennung);
		
		JButton btnEditBerechtigung = new JButton("Berechtigung ändern");
		btnEditBerechtigung.setBackground(new Color(255, 255, 255));
		btnEditBerechtigung.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				new StatistikGUI(PID);
			}
		});
		btnEditBerechtigung.setBounds(24, 304, 200, 24);
		getContentPane().add(btnEditBerechtigung);
		
		JButton btnResetBerechtigung = new JButton("Berechtigung zurücksetzen");
		btnResetBerechtigung.setBackground(new Color(255, 255, 255));
		btnResetBerechtigung.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				new StatistikGUI(PID);
			}
		});
		btnResetBerechtigung.setBounds(24, 328, 200, 24);
		getContentPane().add(btnResetBerechtigung);
		
		JLabel lblAZK = new JLabel("Arbeitszeitkonto");
		lblAZK.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAZK.setBounds(24, 380, 240, 24);
		getContentPane().add(lblAZK);
		
		JButton btnEditAZK = new JButton("Arbeitszeitkonto bearbeiten");
		btnEditAZK.setBackground(new Color(255, 255, 255));
		btnEditAZK.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				new StatistikGUI(PID);
			}
		});
		btnEditAZK.setBounds(24, 420, 200, 24);
		getContentPane().add(btnEditAZK);
		
		JButton btnResetAZK = new JButton("Arbeitszeitkonto zurücksetzen");
		btnResetAZK.setBackground(new Color(255, 255, 255));
		btnResetAZK.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				new StatistikGUI(PID);
			}
		});
		btnResetAZK.setBounds(24, 444, 200, 24);
		getContentPane().add(btnResetAZK);
		
		JLabel lblLink = new JLabel("Zugehörigkeit");
		lblLink.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLink.setBounds(24, 496, 240, 24);
		getContentPane().add(lblLink);
		
		JButton btnNewLink = new JButton("Zugehörigkeit ändern");
		btnNewLink.setBackground(new Color(255, 255, 255));
		btnNewLink.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				new StatistikGUI(PID);
			}
		});
		btnNewLink.setBounds(24, 536, 200, 24);
		getContentPane().add(btnNewLink);
		
		// Auswahl-Anzeige
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(244, 442, 540, 4);//464
		getContentPane().add(rahmenUnten);
		
		JLabel[][] lblData = new JLabel[6][2];
		
		lblData[0][0] = new JLabel("PNr.:");
		lblData[0][0].setBounds(256, 452, 240, 24);
		getContentPane().add(lblData[0][0]);
		
		lblData[0][1] = new JLabel("");
		lblData[0][1].setBounds(400, 452, 240, 24);
		getContentPane().add(lblData[0][1]);
		
		lblData[1][0] = new JLabel("Name:");
		lblData[1][0].setBounds(256, 472, 240, 24);
		getContentPane().add(lblData[1][0]);
		
		lblData[1][1] = new JLabel("");
		lblData[1][1].setBounds(400, 472, 240, 24);
		getContentPane().add(lblData[1][1]);
		
		lblData[2][0] = new JLabel("Geburtstag:");
		lblData[2][0].setBounds(256, 492, 240, 24);
		getContentPane().add(lblData[2][0]);
		
		lblData[2][1] = new JLabel("");
		lblData[2][1].setBounds(400, 492, 240, 24);
		getContentPane().add(lblData[2][1]);
		
		lblData[3][0] = new JLabel("Einstellung:");
		lblData[3][0].setBounds(256, 512, 240, 24);
		getContentPane().add(lblData[3][0]);
		
		lblData[3][1] = new JLabel("");
		lblData[3][1].setBounds(400, 512, 240, 24);
		getContentPane().add(lblData[3][1]);
		
		lblData[4][0] = new JLabel("Ausscheiden:");
		lblData[4][0].setBounds(256, 532, 240, 24);
		getContentPane().add(lblData[4][0]);
		
		lblData[4][1] = new JLabel("");
		lblData[4][1].setBounds(400, 532, 240, 24);
		getContentPane().add(lblData[4][1]);
		
		lblData[5][0] = new JLabel("Arbeitsbereich:");
		lblData[5][0].setBounds(256, 552, 240, 24);
		getContentPane().add(lblData[5][0]);
		
		lblData[5][1] = new JLabel("");
		lblData[5][1].setBounds(400, 552, 240, 24);
		getContentPane().add(lblData[5][1]);
		
		setVisible(true);
		
		// Auswahlbereich
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(255, 255, 255)));
		panel.setBounds(244, 88, 540, 354);
		JScrollPane sp = new JScrollPane(panel);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setLocation(244, 88);
		sp.setSize(540, 354);//514
		sp.setViewportView(panel);
		getContentPane().add(sp);
		
		// Auswahl
		String[][] rowData = getData();
		String[] columnNames = {"PID","Name","Vorname"};	
		JTable table = new JTable(rowData, columnNames);
		table.setBounds(244, 88, 20, 300);
		table.setAutoResizeMode(0);
		table.getColumnModel().getColumn( 0 ).setPreferredWidth( 50 );
		table.getColumnModel().getColumn( 1 ).setPreferredWidth( 200 );
		table.getColumnModel().getColumn( 2 ).setPreferredWidth( 250 );
		table.setShowGrid( false ); 
		table.setShowHorizontalLines( true );
		table.setRowHeight( 20 );
		table.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION);

		JTable x = table;
		x.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				wahl = Integer.parseInt((String) x.getValueAt(x.rowAtPoint(e.getPoint()),0));
				lblData[0][1].setText(Personalverwaltung.getaMA().get(wahl).getPersonalnummer()+"");
				lblData[1][1].setText(Personalverwaltung.getaMA().get(wahl).getName()+", "+Personalverwaltung.getaMA().get(wahl).getVorname());
				lblData[2][1].setText(Personalverwaltung.getaMA().get(wahl).getGeburtsdatum()+"");
				lblData[3][1].setText(Personalverwaltung.getaMA().get(wahl).getEinstellungsdatum()+"");
				lblData[4][1].setText(Personalverwaltung.getaMA().get(wahl).getAusscheidungsdatum()+"");
				lblData[5][1].setText(((Arbeitsbereich)abv.suchen(Personalverwaltung.getaMA().get(wahl).getActualAB().getArbeitsbereichnummer())).getName());
			}
		});
		panel.add(table);
		getContentPane().add(sp);
		
		
//			Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
//			// dynamisches Erzeugen der Label
//			JLabel[] label = new JLabel[Personalverwaltung.getaMA().size()];
//			int k = 5;
//			for(int i=0; i < Personalverwaltung.getaMA().size(); i++) {
//				label[i] = new JLabel("<html><table><tr><td width=80>"+Personalverwaltung.getaMA().get(i).getPersonalnummer()+"</td><td>"+Personalverwaltung.getaMA().get(i).getName()+", "+Personalverwaltung.getaMA().get(i).getVorname()+"</td></tr></html>");
//				label[i].setBounds(8, k , 400, 20);
//				k+=20;
//				label[i].addMouseListener(new MouseAdapter() {
//					public void mouseClicked(MouseEvent e) {
//						JLabel label = (JLabel)e.getSource();
//						lblWahl.setText("ausgewählt: "+label.getText().substring(label.getText().lastIndexOf("</td><td>")+9,label.getText().lastIndexOf("</td></tr></html>")));
//						wahl = Integer.parseInt(label.getText().substring(30,label.getText().lastIndexOf("</td><td>")));
//						//System.out.println(wahl);
//						lblData[0][1].setText(Personalverwaltung.getaMA().get(wahl).getPersonalnummer()+"");
//						lblData[1][1].setText(Personalverwaltung.getaMA().get(wahl).getName()+", "+Personalverwaltung.getaMA().get(wahl).getVorname());
//						lblData[2][1].setText(Personalverwaltung.getaMA().get(wahl).getGeburtsdatum()+"");
//						lblData[3][1].setText(Personalverwaltung.getaMA().get(wahl).getEinstellungsdatum()+"");
//						lblData[4][1].setText(Personalverwaltung.getaMA().get(wahl).getAusscheidungsdatum()+"");
//						lblData[5][1].setText(((Arbeitsbereich)abv.suchen(Personalverwaltung.getaMA().get(wahl).getActualAB().getArbeitsbereichnummer())).getName());
//						label.setForeground(Color.RED);
//					}
//					public void mouseEntered(MouseEvent e) {
//						JLabel label = (JLabel)e.getSource();
//						label.setForeground(new Color(100,150,200));
//					}
//					public void mouseExited(MouseEvent e) {
//						JLabel label = (JLabel)e.getSource();
//						label.setForeground(Color.DARK_GRAY);
//					}
//				});
//				panel.add(label[i]);
//			}
						
			setVisible(true);	
			rowData = getData();
			table = new JTable( rowData, columnNames );
			setVisible(true);	
		}		
		
	private static String [][] getData() {
		String [][] Data = new String [Personalverwaltung.getaMA().size()][3];
		for(int i=0; i < Personalverwaltung.getaMA().size(); i++) {
			Data[i][0] = Personalverwaltung.getaMA().get(i).getPersonalnummer()+"";
			Data[i][1] = Personalverwaltung.getaMA().get(i).getName();
			Data[i][2] = Personalverwaltung.getaMA().get(i).getVorname();
		}
		return Data;
	}
		
	
		
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
				
		new PVGUI(1);
	}
}
