package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import _programmstart.Programmstart;
import logik.Admin;
import logik.Arbeitsbereich;
import logik.Arbeitsbereichverwaltung;
import logik.Mitarbeiter;
import logik.Personalverwaltung;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Color;
/*@author:		Soeren Hebestreit
 *@date: 		24.07.2019
 *@description: Hauptmenue Arbeitsbereichverwaltung, Arbeitsbereichauswahl
 */	

public class ABVGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private static int wahl = -1;
	private static JTable table;
	private static JLabel[][] lblData = new JLabel[3][2];
	public boolean openAddAB = false;
	public boolean openEditAB = false;
	public boolean openDelAB = false;
	public boolean openShowMA = false;
	
//******************** KONSTRUKTOR ********************
	
	public ABVGUI(int PID) {
		
		setIconImages(Programmstart.iconlist);
		
		setSize(800, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		Mitarbeiter ma = ((Mitarbeiter) pv.suchen(PID));
		
		JLabel lblPV = new JLabel("Arbeitsbereichverwaltung");
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

		JLabel lblStammdaten = new JLabel("Arbeitsbereiche");
		lblStammdaten.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStammdaten.setBounds(24, 100, 240, 24);
		getContentPane().add(lblStammdaten);
		
		JButton btnAnlegen = new JButton("Arbeitsbereich anlegen");
		btnAnlegen.setBackground(new Color(255, 255, 255));
		btnAnlegen.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if (openAddAB == false) {
					openAddAB = true;
					EditBereichGUI addAb = new EditBereichGUI(PID,Arbeitsbereichverwaltung.getBereiche().get(Arbeitsbereichverwaltung.getBereiche().size()-1).getArbeitsbereichnummer()+1,false);
					addAb.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							table.setModel(getModel(Arbeitsbereichverwaltung.getBereiche()));
							setColWidth();
							openAddAB = false;
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "Arbeitsbereich hinzufügen bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAnlegen.setBounds(24, 140, 200, 24);
		getContentPane().add(btnAnlegen);
		
		JButton btnBearbeiten = new JButton("Arbeitsbereich bearbeiten");
		btnBearbeiten.setBackground(new Color(255, 255, 255));
		btnBearbeiten.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(wahl == 0 || wahl == 1) {
					JOptionPane.showMessageDialog(null, "Dieser Arbeitsbereich ist für die Bearbeitung gesperrt.", null, JOptionPane.INFORMATION_MESSAGE);
				} else if(wahl >=0 ) {
					if (openEditAB == false) {
						openEditAB = true;
						EditBereichGUI editAb = new EditBereichGUI(PID,wahl,true);
						editAb.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								table.setModel(getModel(Arbeitsbereichverwaltung.getBereiche()));
								setColWidth();
								openEditAB = false;
							}
						});
					} else {
						JOptionPane.showMessageDialog(null, "Arbeitsbereich bearbeiten bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBearbeiten.setBounds(24, 164, 200, 24);
		getContentPane().add(btnBearbeiten);
		
		JButton btnEntfernen = new JButton("Arbeitsbereich entfernen");
		btnEntfernen.setBackground(new Color(255, 255, 255));
		btnEntfernen.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				if(wahl == 0 || wahl == 1) {
					JOptionPane.showMessageDialog(null, "Dieser Arbeitsbereich ist für die Bearbeitung gesperrt.", null, JOptionPane.INFORMATION_MESSAGE);
				} else if(wahl >=0 && wahl != 0 && wahl != 1) {
					boolean empty = true;
					for(int i=0; i < Personalverwaltung.getaMA().size(); i++) {
						if(Personalverwaltung.getaMA().get(i).getActualAB().getArbeitsbereichnummer() == wahl) {
							empty = false;
							break;
						}
					}
					if(empty) {
						if((JOptionPane.showConfirmDialog(null, "Bereich "+((Arbeitsbereich)abv.suchen(wahl)).getName()+" löschen?", null, JOptionPane.YES_NO_OPTION)) == 0) {
							Admin admin = new Admin(PID);
							admin.delAB(wahl);
							try {
								abv.speichern();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							wahl = -1;
							getInfo(wahl);
							table.setModel(getModel(Arbeitsbereichverwaltung.getBereiche()));
							setColWidth();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Es befinden sich noch Mitarbeiter im Arbeitsbereich.", null, JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnEntfernen.setBounds(24, 188, 200, 24);
		getContentPane().add(btnEntfernen);
		
		JLabel lblLink = new JLabel("Zugehörigkeit");
		lblLink.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLink.setBounds(24, 448, 240, 24);
		getContentPane().add(lblLink);
		
		JButton btnNewLink = new JButton("Mitarbeiter anzeigen");
		btnNewLink.setBackground(new Color(255, 255, 255));
		btnNewLink.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(wahl >=0 ) {
					if (openShowMA == false) {
						openShowMA = true;
						ShowMitarbeiterABGUI showMa = new ShowMitarbeiterABGUI(wahl);
						showMa.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								table.setModel(getModel(Arbeitsbereichverwaltung.getBereiche()));
								setColWidth();
								openShowMA = false;
							}
						});
					} else {
						JOptionPane.showMessageDialog(null, "Mitarbeiter anzeigen bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewLink.setBounds(24, 488, 200, 24);
		getContentPane().add(btnNewLink);

		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(244, 442, 640, 4);//464
		getContentPane().add(rahmenUnten);
		
		lblData[0][0] = new JLabel("Nr.:");
		lblData[0][0].setBounds(256, 452, 240, 24);
		getContentPane().add(lblData[0][0]);
		
		lblData[0][1] = new JLabel("");
		lblData[0][1].setFont(new Font("Dialog", Font.PLAIN, 12));
		lblData[0][1].setBounds(400, 452, 360, 24);
		getContentPane().add(lblData[0][1]);
		
		lblData[1][0] = new JLabel("Name:");
		lblData[1][0].setBounds(256, 472, 240, 24);
		getContentPane().add(lblData[1][0]);
		
		lblData[1][1] = new JLabel("");
		lblData[1][1].setFont(new Font("Dialog", Font.PLAIN, 12));
		lblData[1][1].setBounds(400, 472, 360, 24);
		getContentPane().add(lblData[1][1]);
		
		lblData[2][0] = new JLabel("Beschreibung:");
		lblData[2][0].setBounds(256, 492, 240, 24);
		getContentPane().add(lblData[2][0]);
		
		lblData[2][1] = new JLabel("");
		lblData[2][1].setHorizontalAlignment(JLabel.LEFT);
		lblData[2][1].setVerticalAlignment(JLabel.TOP);
		lblData[2][1].setFont(new Font("Dialog", Font.PLAIN, 12));
		lblData[2][1].setBounds(400, 496, 360, 72);
		getContentPane().add(lblData[2][1]);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(255, 255, 255)));
		
		JScrollPane sp = new JScrollPane(panel);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setLocation(244, 88);
		sp.setSize(550, 354);
		sp.setViewportView(panel);
		getContentPane().add(sp);

		table = new JTable(getModel(Arbeitsbereichverwaltung.getBereiche())) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		table.setShowVerticalLines(false);
		table.setRowHeight(20);
		setColWidth();
		table.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { 
					if(table.columnAtPoint(e.getPoint()) == 0) {
						abv.sortNumber();
					} else {
						abv.sortName();
					}
					table.setModel(getModel(Arbeitsbereichverwaltung.getBereiche()));
					setColWidth();  
				} 
				if (e.getClickCount() == 1) {
					wahl = Integer.parseInt((String) table.getValueAt(table.rowAtPoint(e.getPoint()),0));
					getInfo(wahl);
				}	
			}
		});
		panel.add(table);	

		setVisible(true);	
	}	
	
	/*
	 *@description: Tabellenmodell (inkl. Daten) erzeugen
	 */
	private static DefaultTableModel getModel(ArrayList<Arbeitsbereich> bereichsliste) {
				
		String [][] Data = new String [bereichsliste.size()][2];
		for(int i=0; i < bereichsliste.size(); i++) {
			Data[i][0] = bereichsliste.get(i).getArbeitsbereichnummer()+"";
			Data[i][1] = bereichsliste.get(i).getName();
		}
		String[] columnNames = {"ID","Name"};
		DefaultTableModel model = new DefaultTableModel(Data, columnNames);
		
		return model;
	}
	
	/*
	 *@description: Spaltenbreite der Tabelle aendern
	 */
	private static void setColWidth() {
				
		table.getColumnModel().getColumn( 0 ).setPreferredWidth( 50 );
		table.getColumnModel().getColumn( 1 ).setPreferredWidth( 450 );
	}
	
	/*
	 *@description: Daten bezueglich Auswahl anzeigen
	 */
	private static void getInfo(int number) {
			
		if(number < 0) {
			lblData[0][1].setText("");
			lblData[1][1].setText("");
			lblData[2][1].setText("");						
		} else {
			Arbeitsbereich ab = (Arbeitsbereich) Arbeitsbereichverwaltung.getInstance().suchen(number);
			lblData[0][1].setText(ab.getArbeitsbereichnummer()+"");
			lblData[1][1].setText(ab.getName());
			lblData[2][1].setText("<html>"+ab.getBeschreibung()+"</html>");
		}
	}
		
	public static void main(String[] args) throws Exception {
		
		Arbeitsbereichverwaltung.getInstance().laden();		
		Personalverwaltung.getInstance().laden();
		new ABVGUI(100000);
	}
	
}
