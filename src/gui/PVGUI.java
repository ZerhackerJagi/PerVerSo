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

import logik.Arbeitsbereich;
import logik.Arbeitsbereichverwaltung;
import logik.Mitarbeiter;
import logik.Personalverwaltung;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Color;

public class PVGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private static int wahl = -1;
	private static JTable table;
	private static JLabel[][] lblData = new JLabel[6][2];
	public boolean openAddMA = false;
	public boolean openEditMA = false;
	public boolean openDelMA = false;
	public boolean openEditKennung = false;
	public boolean openEditBerechtigung = false;
	public boolean openEditAzk = false;
	public boolean openEditZug = false;
	public boolean openShowVerlauf = false;
	
//******************** KONSTRUKTOR ********************
	
	public PVGUI(int PID) {
		/*@author:		Soeren Hebestreit
		 *@date: 		21.07.2019
		 *@description: Hauptmenue Personalverwaltung, Mitarbeiterauswahl
		 */	
	
		setSize(800, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
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
				if (openAddMA == false) {
					openAddMA = true;
					EditMitarbeiterGUI addMa = new EditMitarbeiterGUI(PID,Personalverwaltung.getaMA().get(Personalverwaltung.getaMA().size()-1).getPersonalnummer()+1,false);
					addMa.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							table.setModel(getModel(Personalverwaltung.getaMA()));
							setColWidth();
							openAddMA = false;
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "Mitarbeiter hinzufügen bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAnlegen.setBounds(24, 140, 200, 24);
		getContentPane().add(btnAnlegen);
		
		JButton btnBearbeiten = new JButton("Mitarbeiter bearbeiten");
		btnBearbeiten.setBackground(new Color(255, 255, 255));
		btnBearbeiten.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(wahl >=0 ) {
					if (openEditMA == false) {
						openEditMA = true;
						EditMitarbeiterGUI editMa = new EditMitarbeiterGUI(PID,wahl,true);
						editMa.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								getInfo(wahl);
								table.setModel(getModel(Personalverwaltung.getaMA()));
								setColWidth();
								openEditMA = false;
							}
						});
					} else {
						JOptionPane.showMessageDialog(null, "Mitarbeiter bearbeiten bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBearbeiten.setBounds(24, 164, 200, 24);
		getContentPane().add(btnBearbeiten);
		
		JButton btnEntfernen = new JButton("Mitarbeiter entfernen");
		btnEntfernen.setBackground(new Color(255, 255, 255));
		btnEntfernen.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				if(wahl == PID) {
					JOptionPane.showMessageDialog(null, "Diese Option kann nicht auf den Anwender selbst angewandt werden.", null, JOptionPane.INFORMATION_MESSAGE);
				} else if(wahl >=0 && wahl != PID) {
					if (openDelMA == false) {
						openDelMA = true;
						DeleteMitarbeiterGUI delMa = new DeleteMitarbeiterGUI(PID,wahl);
						delMa.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								wahl = -1;
								getInfo(wahl);
								table.setModel(getModel(Personalverwaltung.getaMA()));
								setColWidth();
								openDelMA = false;
							}
						});
					} else {
						JOptionPane.showMessageDialog(null, "Mitarbeiter entfernen bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
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
				if(wahl >=0 ) {
					if (openEditKennung == false) {
						openEditKennung = true;
						EditPasswortGUI editKennung = new EditPasswortGUI(PID,wahl);
						editKennung.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								openEditKennung = false;
							}
						});
					} else {
						JOptionPane.showMessageDialog(null, "Kennung bearbeiten bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnKennung.setBounds(24, 280, 200, 24);
		getContentPane().add(btnKennung);
		
		JButton btnEditBerechtigung = new JButton("Berechtigung ändern");
		btnEditBerechtigung.setBackground(new Color(255, 255, 255));
		btnEditBerechtigung.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(wahl == PID) {
					JOptionPane.showMessageDialog(null, "Diese Option kann nicht auf den Anwender selbst angewandt werden.", null, JOptionPane.INFORMATION_MESSAGE);
				} else if(wahl >=0 && wahl != PID) {
					if (openEditBerechtigung == false) {
						openEditBerechtigung = true;
						EditBerechtigungGUI editBerechtigung = new EditBerechtigungGUI(PID,wahl);
						editBerechtigung.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								openEditBerechtigung = false;
							}
						});
					} else {
						JOptionPane.showMessageDialog(null, "Berechtigung ändern bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnEditBerechtigung.setBounds(24, 304, 200, 24);
		getContentPane().add(btnEditBerechtigung);
		
		JLabel lblAZK = new JLabel("Arbeitszeitkonto");
		lblAZK.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAZK.setBounds(24, 356, 240, 24);
		getContentPane().add(lblAZK);
		
		JButton btnEditAZK = new JButton("Arbeitszeitkonto bearbeiten");
		btnEditAZK.setBackground(new Color(255, 255, 255));
		btnEditAZK.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(wahl >=0 ) {
					if (openEditAzk == false) {
						openEditAzk = true;
						EditAzkGUI editAzk = new EditAzkGUI(PID, wahl);
						editAzk.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								getInfo(wahl);
								table.setModel(getModel(Personalverwaltung.getaMA()));
								setColWidth();
								openEditAzk = false;
							}
						});
					} else {
						JOptionPane.showMessageDialog(null, "Arbeitszeitkonto bearbeiten bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnEditAZK.setBounds(24, 396, 200, 24);
		getContentPane().add(btnEditAZK);
		
		JLabel lblLink = new JLabel("Zugehörigkeit");
		lblLink.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLink.setBounds(24, 448, 240, 24);
		getContentPane().add(lblLink);
		
		JButton btnNewLink = new JButton("Zugehörigkeit ändern");
		btnNewLink.setBackground(new Color(255, 255, 255));
		btnNewLink.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(wahl >=0 ) {
					if (openEditZug == false) {
						openEditZug = true;
						EditLinkingMaGUI editLink = new EditLinkingMaGUI(PID,wahl);
						editLink.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								getInfo(wahl);
								table.setModel(getModel(Personalverwaltung.getaMA()));
								setColWidth();
								openEditZug = false;
							}
						});
					} else {
						JOptionPane.showMessageDialog(null, "Zugehörigkeit ändern bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewLink.setBounds(24, 488, 200, 24);
		getContentPane().add(btnNewLink);
		
		JButton btnVerlauf = new JButton("Verlauf anzeigen");
		btnVerlauf.setBackground(new Color(255, 255, 255));
		btnVerlauf.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(wahl >=0 ) {
					if (openShowVerlauf == false) {
						openShowVerlauf = true;
						ShowVerlaufGUI showVerlauf = new ShowVerlaufGUI(wahl);
						showVerlauf.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								getInfo(wahl);
								table.setModel(getModel(Personalverwaltung.getaMA()));
								setColWidth();
								openShowVerlauf = false;
							}
						});
					} else {
						JOptionPane.showMessageDialog(null, "Verlauf anzeigen bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnVerlauf.setBounds(24, 512, 200, 24);
		getContentPane().add(btnVerlauf);
		
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(244, 442, 640, 4);
		getContentPane().add(rahmenUnten);
		
		lblData[0][0] = new JLabel("PNr.:");
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
		
		lblData[2][0] = new JLabel("Geburtstag:");
		lblData[2][0].setBounds(256, 492, 240, 24);
		getContentPane().add(lblData[2][0]);
		
		lblData[2][1] = new JLabel("");
		lblData[2][1].setFont(new Font("Dialog", Font.PLAIN, 12));
		lblData[2][1].setBounds(400, 492, 360, 24);
		getContentPane().add(lblData[2][1]);
		
		lblData[3][0] = new JLabel("Einstellung:");
		lblData[3][0].setBounds(256, 512, 240, 24);
		getContentPane().add(lblData[3][0]);
		
		lblData[3][1] = new JLabel("");
		lblData[3][1].setFont(new Font("Dialog", Font.PLAIN, 12));
		lblData[3][1].setBounds(400, 512, 360, 24);
		getContentPane().add(lblData[3][1]);
		
		lblData[4][0] = new JLabel("Ausscheiden:");
		lblData[4][0].setBounds(256, 532, 240, 24);
		getContentPane().add(lblData[4][0]);
		
		lblData[4][1] = new JLabel("");
		lblData[4][1].setFont(new Font("Dialog", Font.PLAIN, 12));
		lblData[4][1].setBounds(400, 532, 360, 24);
		getContentPane().add(lblData[4][1]);
		
		lblData[5][0] = new JLabel("Arbeitsbereich:");
		lblData[5][0].setBounds(256, 552, 240, 24);
		getContentPane().add(lblData[5][0]);
		
		lblData[5][1] = new JLabel("");
		lblData[5][1].setFont(new Font("Dialog", Font.PLAIN, 12));
		lblData[5][1].setBounds(400, 552, 360, 24);
		getContentPane().add(lblData[5][1]);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(255, 255, 255)));
		
		JScrollPane sp = new JScrollPane(panel);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setLocation(244, 88);
		sp.setSize(550, 354);
		sp.setViewportView(panel);
		getContentPane().add(sp);
		
		table = new JTable(getModel(Personalverwaltung.getaMA())) {
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
						pv.sortNumber();
					} else {
						pv.sortName();
					}
					table.setModel(getModel(Personalverwaltung.getaMA()));
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
	
	private static DefaultTableModel getModel(ArrayList<Mitarbeiter> mitarbeiterliste) {
		/*@author:		Soeren Hebestreit
		 *@date: 		22.07.2019
		 *@description: Tabellenmodell (inkl. Daten) erzeugen
		 */
		
		String [][] Data = new String [mitarbeiterliste.size()][3];
		for(int i=0; i < mitarbeiterliste.size(); i++) {
			Data[i][0] = mitarbeiterliste.get(i).getPersonalnummer()+"";
			Data[i][1] = mitarbeiterliste.get(i).getName();
			Data[i][2] = mitarbeiterliste.get(i).getVorname();
		}
		String[] columnNames = {"PID","Name","Vorname"};
		DefaultTableModel model = new DefaultTableModel(Data, columnNames);
		
		return model;
	}
	
	private static void setColWidth() {
		/*@author:		Soeren Hebestreit
		 *@date: 		22.07.2019
		 *@description: Spaltenbreite der Tabelle aendern
		 */
		
		table.getColumnModel().getColumn( 0 ).setPreferredWidth( 80 );
		table.getColumnModel().getColumn( 1 ).setPreferredWidth( 180 );
		table.getColumnModel().getColumn( 2 ).setPreferredWidth( 240 );
	}
	
	private static void getInfo(int number) {
		/*@author:		Soeren Hebestreit
		 *@date: 		22.07.2019
		 *@description: Daten bezueglich Auswahl anzeigen
		 */
		
		if(number < 0) {
			lblData[0][1].setText("");
			lblData[1][1].setText("");
			lblData[2][1].setText("");
			lblData[3][1].setText("");
			lblData[4][1].setText("");
			lblData[5][1].setText("");							
		} else {
			Mitarbeiter ma = ((Mitarbeiter) Personalverwaltung.getInstance().suchen(number));
			lblData[0][1].setText(ma.getPersonalnummer()+"");
			lblData[1][1].setText(ma.getName()+", "+ma.getVorname());
			lblData[2][1].setText(ma.getGeburtsdatum()+"");
			lblData[3][1].setText(ma.getEinstellungsdatum()+"");
			lblData[4][1].setText(ma.getAusscheidungsdatum()+"");
			lblData[5][1].setText(((Arbeitsbereich)Arbeitsbereichverwaltung.getInstance().suchen(ma.getActualAB().getArbeitsbereichnummer())).getName());
		}
	}
		
	public static void main(String[] args) throws Exception {
		Personalverwaltung.getInstance().setDatenBank("Beispiel");	
		Arbeitsbereichverwaltung.getInstance().laden();	
		Personalverwaltung.getInstance().laden();
		new PVGUI(100000);
	}
	
}
