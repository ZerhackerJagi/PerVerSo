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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import logik.Admin;
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
	private static JLabel lblAchtung;
	public boolean openEditLimit = false;
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

		JLabel lblFunktion = new JLabel("Arbeitszeitkonto verwalten");
		lblFunktion.setFont(new Font("Dialog", Font.BOLD, 21));
		lblFunktion.setForeground(new Color(255, 255, 255));
		lblFunktion.setBounds(24, 20, 360, 24);
		getContentPane().add(lblFunktion);
		
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
		
		JLabel lblWarnung = new JLabel("");
		if (ma.getAzk().getUeberminutenmax() < ma.getAzk().getUeberminuten()) {
			lblWarnung.setText("Obergrenze Überminuten beachten!");		
		} else if (ma.getAzk().getUeberminutenmin() > ma.getAzk().getUeberminuten()) {
			lblWarnung.setText("Untergrenze Überminuten beachten!");
		}
		lblWarnung.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblWarnung.setForeground(new Color(250, 50, 50));
		lblWarnung.setBounds(24, 170, 300, 24);
		getContentPane().add(lblWarnung);

		JLabel lblMinuten = new JLabel("Überminuten:");
		lblMinuten.setBounds(32, 140, 150, 24);
		getContentPane().add(lblMinuten);
		
		JLabel lblMinutenData = new JLabel(""+ma.getAzk().getUeberminuten(),SwingConstants.RIGHT);
		lblMinutenData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMinutenData.setBounds(144, 140, 64, 24);
		getContentPane().add(lblMinutenData);
		
		JTextField tfPlus = new JTextField();
		tfPlus.setBounds(174, 98, 50, 24);
		getContentPane().add(tfPlus);
		
		JButton btnAddPlus = new JButton("Übertrag addieren");
		btnAddPlus.setBackground(new Color(255, 255, 255));
		btnAddPlus.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!tfPlus.getText().isEmpty()) {
					Admin admin = new Admin(PID);
					admin.addAZKUeberminuten(wer, Integer.parseInt(tfPlus.getText()));
					try {
						pv.speichern();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					tfPlus.setText("");
					if (ma.getAzk().getUeberminutenmax() < ma.getAzk().getUeberminuten()) {
						lblWarnung.setText("Obergrenze Überminuten beachten!");		
					} else if (ma.getAzk().getUeberminutenmin() > ma.getAzk().getUeberminuten()) {
						lblWarnung.setText("Untergrenze Überminuten beachten!");
					} else {
						lblWarnung.setText("");
					}
					lblMinutenData.setText(""+ma.getAzk().getUeberminuten());
				}
			}
		});
		btnAddPlus.setBounds(24, 98, 140, 24);
		getContentPane().add(btnAddPlus);
				
		JLabel lblUMax = new JLabel("Überminuten Max:");
		lblUMax.setBounds(32, 330, 150, 24);
		getContentPane().add(lblUMax);
		
		JLabel lblUMaxData = new JLabel(""+ma.getAzk().getUeberminutenmax(),SwingConstants.RIGHT);
		lblUMaxData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUMaxData.setBounds(144, 330, 64, 24);
		getContentPane().add(lblUMaxData);
		
		JLabel lblUMin = new JLabel("Überminuten Min:");
		lblUMin.setBounds(32, 360, 150, 24);
		getContentPane().add(lblUMin);
		
		JLabel lblUMinData = new JLabel(""+ma.getAzk().getUeberminutenmin(),SwingConstants.RIGHT);
		lblUMinData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUMinData.setBounds(144, 360, 64, 24);
		getContentPane().add(lblUMinData);
	
		JButton btnLimit = new JButton("Grenzen ändern");
		btnLimit.setBackground(new Color(255, 255, 255));
		btnLimit.addMouseListener(new MouseAdapter() {				
			@Override
			public void mouseClicked(MouseEvent e) {
				if (openEditLimit == false) {
					openEditLimit = true;
					EditLimitGUI editLimit = new EditLimitGUI(PID,wer);
					editLimit.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							openEditLimit = false;
							if (ma.getAzk().getUeberminutenmax() < ma.getAzk().getUeberminuten()) {
								lblWarnung.setText("Obergrenze Überminuten beachten!");		
							} else if (ma.getAzk().getUeberminutenmin() > ma.getAzk().getUeberminuten()) {
								lblWarnung.setText("Untergrenze Überminuten beachten!");
							} else {
								lblWarnung.setText("");
							}
							lblUMaxData.setText(""+ma.getAzk().getUeberminutenmax());
							lblUMinData.setText(""+ma.getAzk().getUeberminutenmin());
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "Grenzen ändern bereits offen.", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnLimit.setBounds(24, 398, 200, 24);
		getContentPane().add(btnLimit);
		
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
					AddEintragGUI addEintrag = new AddEintragGUI(PID,wer);
					addEintrag.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							table.setModel(getModel(ma.getAzk().getListe()));
							sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel()));
							table.setRowSorter(sorter);
							setColWidth();
							getFilter();
							showAnzahlTage(ma);
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
								admin.removeAZKKrankheit(wer, wahl);
							}		
							pv.speichern();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						wahl = -1;
						table.setModel(getModel(ma.getAzk().getListe()));
						sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel()));
						table.setRowSorter(sorter);
						setColWidth();
						getFilter();
						showAnzahlTage(ma);
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
		
		int length = ma.getAzk().getListe().get(ma.getAzk().getListe().size()-1).getStart().getJahr()-ma.getEinstellungsdatum().getJahr()+2;
		String[] jahre = new String[length];
		jahre[0] = "alle";
		for(int i = 0; i<length-1; i++) {
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
				} else {
					jahr = (String) jahrBox.getSelectedItem();
	            }
				showAnzahlTage(ma);
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
				showAnzahlTage(ma);
				getFilter();
			}
		});
		getContentPane().add(typBox);
		
		JLabel lblUrlaub = new JLabel("Urlaubstage:");
		lblUrlaub.setBounds(456, 518, 150, 24);//456
		getContentPane().add(lblUrlaub);
		
		JLabel lblUrlaubData = new JLabel(ma.getAzk().getUrlaubbasis()+"");
		lblUrlaubData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUrlaubData.setBounds(596, 518, 50, 24);//596
		getContentPane().add(lblUrlaubData);
		
		lblAnzahl = new JLabel("");
		lblAnzahl.setBounds(456, 558, 150, 24);
		getContentPane().add(lblAnzahl);
		
		lblAnzahlData = new JLabel("");
		lblAnzahlData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAnzahlData.setBounds(596, 558, 50, 24);
		getContentPane().add(lblAnzahlData);
		
		lblAchtung = new JLabel("");
		lblAchtung.setForeground(new Color(250, 50, 50));
		lblAchtung.setBounds(636, 558, 100, 24);
		getContentPane().add(lblAchtung);
		
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
	
	private static void showAnzahlTage(Mitarbeiter ma) {
		/*@author:		Soeren Hebestreit
		 *@date: 		27.07.2019
		 *@description: Anzahl Urlaub/Krankheit fuer angegebenes Jahr ermitteln und ausgeben
		 */
		
		int anzahl = 0;
		if(jahr == "" || typ == "") {
			lblAnzahl.setText("");
			lblAnzahlData.setText("");
			lblAchtung.setText("");
		} else if(typ.contains("Urlaub")) {
			for (int i = 0; i < ma.getAzk().getListe().size(); i++) {
				if (ma.getAzk().getListe().get(i) instanceof Urlaubseintrag) {
					if (ma.getAzk().getListe().get(i).getStart().getJahr() == Integer.parseInt(jahr)) {
						anzahl = anzahl + ma.getAzk().getListe().get(i).getArbeitstage();
					}
				}
			}
			lblAnzahl.setText("davon genommen:");
			lblAnzahlData.setText(""+anzahl);
			if(anzahl > ma.getAzk().getUrlaubbasis()) {
				lblAchtung.setText("Überschreitung");
			} else {
				lblAchtung.setText("");
			}	
		} else if(typ.contains("Krankheit"))  {
			for (int i = 0; i < ma.getAzk().getListe().size(); i++) {
				if (ma.getAzk().getListe().get(i) instanceof Krankheitseintrag) {
					if (ma.getAzk().getListe().get(i).getStart().getJahr() == Integer.parseInt(jahr)) {
						anzahl = anzahl + ma.getAzk().getListe().get(i).getArbeitstage();
					}
				}
			}
			lblAnzahl.setText("Krankheitstage:");
			lblAnzahlData.setText(""+anzahl);
		}
	}
		
	public static void main(String[] args) throws Exception {
		
		Personalverwaltung.getInstance().laden();
		new EditAzkGUI(0,1);
	}
	
}
