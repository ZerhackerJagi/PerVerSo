package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import extern.Datum;
import logik.Admin;
import logik.Arbeitsbereich;
import logik.Arbeitsbereichverwaltung;
import logik.Eintrag;
import logik.Krankheitseintrag;
import logik.Mitarbeiter;
import logik.Personalverwaltung;
import logik.Urlaubseintrag;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;

public class EditAzkGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private static int wahl = -1;
	private static String typ = "";
	private static String jahr = "";
	private static JTable table;
	private static TableRowSorter<TableModel> sorter;
	private static JLabel lblAnzahlData;
	private static JLabel lblAnzahl;
	public boolean openAddMA = false;
	public boolean openEditMA = false;
	public boolean openDelMA = false;
	public boolean openEditKennung = false;
	public boolean openEditBerechtigung = false;
	public boolean openEditAzk = false;
	public boolean openEditZug = false;
	public boolean openAddE = false;
	
//******************** KONSTRUKTOR ********************
	
	public EditAzkGUI(int PID, int wer) {
		/*@author:		Soeren Hebestreit
		 *@date: 		27.07.2019
		 *@description: Arbeitszeitkonto verwalten
		 */	
	
		setSize(800, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = ((Mitarbeiter) pv.suchen(wer));

		JLabel lblPV = new JLabel("Arbeitszeitkonto verwalten");
		lblPV.setFont(new Font("Dialog", Font.BOLD, 21));
		lblPV.setForeground(new Color(255, 255, 255));
		lblPV.setBounds(24, 20, 360, 24);
		getContentPane().add(lblPV);
		
		JLabel lblName = new JLabel("PNr. "+wer+" - "+ma.getVorname()+" "+ma.getName());
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

//		JLabel lblStammdaten = new JLabel("Stammdaten");
//		lblStammdaten.setFont(new Font("Dialog", Font.BOLD, 14));
//		lblStammdaten.setBounds(24, 100, 240, 24);
//		getContentPane().add(lblStammdaten);
//		
//		JButton btnAnlegen = new JButton("Mitarbeiter anlegen");
//		btnAnlegen.setBackground(new Color(255, 255, 255));
//		btnAnlegen.addMouseListener(new MouseAdapter() {				
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if (openAddMA == false) {
//					openAddMA = true;
//					EditMitarbeiterGUI addMa = new EditMitarbeiterGUI(PID,Personalverwaltung.getaMA().get(Personalverwaltung.getaMA().size()-1).getPersonalnummer()+1,false);
//					addMa.addWindowListener(new WindowAdapter() {
//						@Override
//						public void windowClosed(WindowEvent e) {
//							table.setModel(getModel(ma.getAzk().getListe()));
//							setColWidth();
//							openAddMA = false;
//						}
//					});
//				} else {
//					JOptionPane.showMessageDialog(null, "Mitarbeiter hinzufügen bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
//		});
//		btnAnlegen.setBounds(24, 140, 200, 24);
//		getContentPane().add(btnAnlegen);
//		
//		JButton btnBearbeiten = new JButton("Mitarbeiter bearbeiten");
//		btnBearbeiten.setBackground(new Color(255, 255, 255));
//		btnBearbeiten.addMouseListener(new MouseAdapter() {				
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(wahl >=0 ) {
//					if (openEditMA == false) {
//						openEditMA = true;
//						EditMitarbeiterGUI editMa = new EditMitarbeiterGUI(PID,wahl,true);
//						editMa.addWindowListener(new WindowAdapter() {
//							@Override
//							public void windowClosed(WindowEvent e) {
//								getInfo(wahl);
//								table.setModel(getModel(ma.getAzk().getListe()));
//								setColWidth();
//								openEditMA = false;
//							}
//						});
//					} else {
//						JOptionPane.showMessageDialog(null, "Mitarbeiter bearbeiten bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
//		});
//		btnBearbeiten.setBounds(24, 164, 200, 24);
//		getContentPane().add(btnBearbeiten);
//		
//		JButton btnEntfernen = new JButton("Mitarbeiter entfernen");
//		btnEntfernen.setBackground(new Color(255, 255, 255));
//		btnEntfernen.addMouseListener(new MouseAdapter() {	
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(wahl == PID) {
//					JOptionPane.showMessageDialog(null, "Diese Option kann nicht auf den Anwender selbst angewandt werden.", null, JOptionPane.INFORMATION_MESSAGE);
//				} else if(wahl >=0 && wahl != PID) {
//					if (openDelMA == false) {
//						openDelMA = true;
//						DeleteMitarbeiterGUI delMa = new DeleteMitarbeiterGUI(PID,wahl);
//						delMa.addWindowListener(new WindowAdapter() {
//							@Override
//							public void windowClosed(WindowEvent e) {
//								wahl = -1;
//								getInfo(wahl);
//								table.setModel(getModel(ma.getAzk().getListe()));
//								setColWidth();
//								openDelMA = false;
//							}
//						});
//					} else {
//						JOptionPane.showMessageDialog(null, "Mitarbeiter entfernen bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
//		});
//		btnEntfernen.setBounds(24, 188, 200, 24);
//		getContentPane().add(btnEntfernen);
//		
//		JLabel lblKennung = new JLabel("Kennung und Berechtigung");
//		lblKennung.setFont(new Font("Dialog", Font.BOLD, 14));
//		lblKennung.setBounds(24, 240, 240, 24);
//		getContentPane().add(lblKennung);
//		
//		JButton btnKennung = new JButton("Kennung bearbeiten");
//		btnKennung.setBackground(new Color(255, 255, 255));
//		btnKennung.addMouseListener(new MouseAdapter() {				
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(wahl >=0 ) {
//					if (openEditKennung == false) {
//						openEditKennung = true;
//						EditKennungGUI editKennung = new EditKennungGUI(PID,wahl);
//						editKennung.addWindowListener(new WindowAdapter() {
//							@Override
//							public void windowClosed(WindowEvent e) {
//								openEditKennung = false;
//							}
//						});
//					} else {
//						JOptionPane.showMessageDialog(null, "Kennung bearbeiten bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
//		});
//		btnKennung.setBounds(24, 280, 200, 24);
//		getContentPane().add(btnKennung);
//		
//		JButton btnEditBerechtigung = new JButton("Berechtigung ändern");
//		btnEditBerechtigung.setBackground(new Color(255, 255, 255));
//		btnEditBerechtigung.addMouseListener(new MouseAdapter() {				
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(wahl == PID) {
//					JOptionPane.showMessageDialog(null, "Diese Option kann nicht auf den Anwender selbst angewandt werden.", null, JOptionPane.INFORMATION_MESSAGE);
//				} else if(wahl >=0 && wahl != PID) {
//					if (openEditBerechtigung == false) {
//						openEditBerechtigung = true;
//						EditBerechtigungGUI editBerechtigung = new EditBerechtigungGUI(PID,wahl);
//						editBerechtigung.addWindowListener(new WindowAdapter() {
//							@Override
//							public void windowClosed(WindowEvent e) {
//								openEditBerechtigung = false;
//							}
//						});
//					} else {
//						JOptionPane.showMessageDialog(null, "Berechtigung ändern bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
//		});
//		btnEditBerechtigung.setBounds(24, 304, 200, 24);
//		getContentPane().add(btnEditBerechtigung);
//		
//		JLabel lblAZK = new JLabel("Arbeitszeitkonto");
//		lblAZK.setFont(new Font("Dialog", Font.BOLD, 14));
//		lblAZK.setBounds(24, 356, 240, 24);
//		getContentPane().add(lblAZK);
//		
//		JButton btnEditAZK = new JButton("Arbeitszeitkonto bearbeiten");
//		btnEditAZK.setBackground(new Color(255, 255, 255));
//		btnEditAZK.addMouseListener(new MouseAdapter() {				
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(wahl >=0 ) {
//					new StatistikGUI(PID);
//				} else {
//					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
//		});
//		btnEditAZK.setBounds(24, 396, 200, 24);
//		getContentPane().add(btnEditAZK);
//		
//		JLabel lblLink = new JLabel("Zugehörigkeit");
//		lblLink.setFont(new Font("Dialog", Font.BOLD, 14));
//		lblLink.setBounds(24, 448, 240, 24);
//		getContentPane().add(lblLink);
//		
//		JButton btnNewLink = new JButton("Zugehörigkeit ändern");
//		btnNewLink.setBackground(new Color(255, 255, 255));
//		btnNewLink.addMouseListener(new MouseAdapter() {				
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(wahl >=0 ) {
//					if (openEditZug == false) {
//						openEditZug = true;
//						EditLinkingMaGUI editLink = new EditLinkingMaGUI(PID,wahl);
//						editLink.addWindowListener(new WindowAdapter() {
//							@Override
//							public void windowClosed(WindowEvent e) {
//								getInfo(wahl);
//								table.setModel(getModel(ma.getAzk().getListe()));
//								setColWidth();
//								openEditZug = false;
//							}
//						});
//					} else {
//						JOptionPane.showMessageDialog(null, "Zugehörigkeit ändern bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
//		});
//		btnNewLink.setBounds(24, 488, 200, 24);
//		getContentPane().add(btnNewLink);
//		
//		JButton btnVerlauf = new JButton("Verlauf anzeigen");
//		btnVerlauf.setBackground(new Color(255, 255, 255));
//		btnVerlauf.addMouseListener(new MouseAdapter() {				
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(wahl >=0 ) {
//					if (openShowVerlauf == false) {
//						openShowVerlauf = true;
//						ShowVerlaufGUI showVerlauf = new ShowVerlaufGUI(wahl);
//						showVerlauf.addWindowListener(new WindowAdapter() {
//							@Override
//							public void windowClosed(WindowEvent e) {
//								getInfo(wahl);
//								table.setModel(getModel(ma.getAzk().getListe()));
//								setColWidth();
//								openShowVerlauf = false;
//							}
//						});
//					} else {
//						JOptionPane.showMessageDialog(null, "Verlauf anzeigen bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
//		});
//		btnVerlauf.setBounds(24, 512, 200, 24);
//		getContentPane().add(btnVerlauf);
		
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(244, 442, 640, 4);
		getContentPane().add(rahmenUnten);
		
		JButton btnAnlegen = new JButton("Eintrag hinzufügen");
		btnAnlegen.setBackground(new Color(255, 255, 255));
		btnAnlegen.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if (openAddE == false) {
					openAddE = true;
					EditBereichGUI addAb = new EditBereichGUI(PID,Arbeitsbereichverwaltung.getBereiche().get(Arbeitsbereichverwaltung.getBereiche().size()-1).getArbeitsbereichnummer()+1,false);
					addAb.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							table.setModel(getModel(ma.getAzk().getListe()));
							setColWidth();
							getFilter();
							showAnzahl(ma.getAzk().getListe());
							openAddE = false;
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "Arbeitsbereich hinzufügen bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAnlegen.setBounds(296, 458, 200, 24);
		getContentPane().add(btnAnlegen);
		
		JButton btnEntfernen = new JButton("Eintrag entfernen");
		btnEntfernen.setBackground(new Color(255, 255, 255));
		btnEntfernen.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				if(wahl >=0) {
					if((JOptionPane.showConfirmDialog(null, "Eintrag "+wahl+" löschen?", null, JOptionPane.YES_NO_OPTION)) == 0) {
						Admin admin = new Admin(PID);
						try {
							if(ma.getAzk().getListe().get(wahl) instanceof Urlaubseintrag) {
								admin.removeAZKUrlaub(wer, wahl);
							} else if(ma.getAzk().getListe().get(wahl) instanceof Krankheitseintrag){
							pv.speichern();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						wahl = -1;
						table.setModel(getModel(ma.getAzk().getListe()));
						setColWidth();
						getFilter();
						showAnzahl(ma.getAzk().getListe());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bitte Auswahl treffen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnEntfernen.setBounds(516, 458, 200, 24);
		getContentPane().add(btnEntfernen);
		
		JLabel lblJahr = new JLabel("Jahr");
		lblJahr.setBounds(256, 518, 40, 24);
		getContentPane().add(lblJahr);
		
		int length = (new Datum()).getJahr()-ma.getEinstellungsdatum().getJahr()+2;
		String[] jahre = new String[length];
		jahre[0] = "alle";
		for(int i = 0;i<length-1;i++) {
			jahre[i+1] = (ma.getEinstellungsdatum().getJahr()+i)+"";
		}
		JComboBox<String> jahrBox = new JComboBox<String>(jahre);
		jahrBox.setBackground(new Color(255, 255, 255));
		jahrBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		jahrBox.setBounds(296, 518, 100, 24);
		jahrBox.setMaximumRowCount(8);
		jahrBox.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((String) jahrBox.getSelectedItem()).contains("alle")) {
					jahr = "";
					lblAnzahl.setText("");
					lblAnzahlData.setText("");
				} else {
					jahr = (String) jahrBox.getSelectedItem();
	            }
				showAnzahl(ma.getAzk().getListe());
				getFilter();
			}
		});
		getContentPane().add(jahrBox);
		
		JLabel lblTyp = new JLabel("Typ");
		lblTyp.setBounds(256, 558, 40, 24);
		getContentPane().add(lblTyp);
		
		String[] typen = {"alle","Urlaub","Krankheit"};		
		JComboBox<String> typBox = new JComboBox<String>(typen);
		typBox.setBackground(new Color(255, 255, 255));
		typBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		typBox.setBounds(296,558, 100, 24);
		typBox.setMaximumRowCount(4);
		typBox.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((String) typBox.getSelectedItem()).contains("alle")) {
					typ = "";
				} else {
					typ = (String) typBox.getSelectedItem();
				}
				showAnzahl(ma.getAzk().getListe());
				getFilter();
			}
		});
		getContentPane().add(typBox);
		
		JLabel lblUrlaub = new JLabel("Urlaubstage:");
		lblUrlaub.setBounds(456, 518, 150, 24);
		getContentPane().add(lblUrlaub);
		
		JLabel lblUrlaubData = new JLabel(ma.getAzk().getUrlaubbasis()+"");
		lblUrlaubData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUrlaubData.setBounds(596, 518, 100, 24);
		getContentPane().add(lblUrlaubData);
		
		lblAnzahl = new JLabel("");
		lblAnzahl.setBounds(456, 558, 150, 24);
		getContentPane().add(lblAnzahl);
		
		lblAnzahlData = new JLabel("");
		lblAnzahlData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAnzahlData.setBounds(596, 558, 100, 24);
		getContentPane().add(lblAnzahlData);
		
		
		
		
		
	
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(255, 255, 255)));
		
		JScrollPane sp = new JScrollPane(panel);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setLocation(244, 88);
		sp.setSize(550, 354);
		sp.setViewportView(panel);
		getContentPane().add(sp);
		
		table = new JTable(getModel(ma.getAzk().getListe())) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
		table.setRowSorter(sorter);
		getFilter();
		table.setShowVerticalLines(false);
		table.setRowHeight(20);
		setColWidth();
		table.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					wahl = Integer.parseInt((String) table.getValueAt(table.rowAtPoint(e.getPoint()),0));
				}	
			}
		});
		panel.add(table);	

		setVisible(true);	
	}	
	
	private static void getFilter() {
		RowFilter<TableModel, Integer> filter = new RowFilter<TableModel, Integer>() {
	        @Override
	        public boolean include(RowFilter.Entry entry) {
	        	if(((String) entry.getValue(1)).contains(typ) && ((String) entry.getValue(2)).contains(jahr)) {
	            	return true;
	            }
	            return false;
	        }
	    };
		sorter.setRowFilter(filter);
	}

	private static DefaultTableModel getModel(ArrayList<Eintrag> liste) {
		/*@author:		Soeren Hebestreit
		 *@date: 		27.07.2019
		 *@description: Tabellenmodell (inkl. Daten) erzeugen
		 */
		
		String [][] Data = new String [liste.size()][5];
		for(int i=0; i < liste.size(); i++) {
			Data[i][0] = i+"";
			if(liste.get(i) instanceof Urlaubseintrag) {
				Data[i][1] = "Urlaub";
			} else {
				Data[i][1] = "Krankheit";
			}
			Data[i][2] = liste.get(i).getStart()+"";
			Data[i][3] = liste.get(i).getEnde()+"";
			Data[i][4] = liste.get(i).getArbeitstage()+" Tage";
		}
		String[] columnNames = {"Nr.","Art","Von","Bis","Tage"};
		DefaultTableModel model = new DefaultTableModel(Data, columnNames);
		
		return model;
	}
	
	private static void setColWidth() {
		/*@author:		Soeren Hebestreit
		 *@date: 		27.07.2019
		 *@description: Spaltenbreite der Tabelle aendern
		 */
		
		table.getColumnModel().getColumn( 0 ).setPreferredWidth( 50 );
		table.getColumnModel().getColumn( 1 ).setPreferredWidth( 125 );
		table.getColumnModel().getColumn( 2 ).setPreferredWidth( 100 );
		table.getColumnModel().getColumn( 3 ).setPreferredWidth( 125 );
		table.getColumnModel().getColumn( 4 ).setPreferredWidth( 100 );
	}
	
	private static void showAnzahl(ArrayList<Eintrag> liste) {
		/*@author:		Soeren Hebestreit
		 *@date: 		27.07.2019
		 *@description: Anzahl Urlaub/Krankheit fuer angegebenes Jahr ermitteln und ausgeben
		 */
		
		int anzahl = 0;
		if(jahr == "" || typ == "") {
			lblAnzahl.setText("");
			lblAnzahlData.setText("");
		} else if(typ.contains("Urlaub")) {
			for (int i = 0; i < liste.size(); i++) {
				if (liste.get(i) instanceof Urlaubseintrag) {
					if (liste.get(i).getStart().getJahr() == Integer.parseInt(jahr)) {
						anzahl = anzahl + liste.get(i).getArbeitstage();
					}
				}
			lblAnzahl.setText("davon genommen:");
			lblAnzahlData.setText(""+anzahl);	
			}
		} else if(typ.contains("Krankheit"))  {
			for (int i = 0; i < liste.size(); i++) {
				if (liste.get(i) instanceof Krankheitseintrag) {
					if (liste.get(i).getStart().getJahr() == Integer.parseInt(jahr)) {
						anzahl = anzahl + liste.get(i).getArbeitstage();
					}
				}
			}
			lblAnzahl.setText("Krankheitstage:");
			lblAnzahlData.setText(""+anzahl);
		}
	}
		
	public static void main(String[] args) throws Exception {
		
		Arbeitsbereichverwaltung.getInstance().laden();	
		Personalverwaltung.getInstance().laden();
		new EditAzkGUI(0,1);
	}
	
}
