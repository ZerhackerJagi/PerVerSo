package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import comparatoren.MitarbeiterNameComparator;
import comparatoren.MitarbeiterNummerComparator;
import logik.Arbeitsbereich;
import logik.Arbeitsbereichverwaltung;
import logik.Mitarbeiter;
import logik.Personalverwaltung;
import logik.Zugehoerigkeit;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;

public class ShowMitarbeiterABGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private static JTable table;
	
//******************** KONSTRUKTOR ********************
	
	public ShowMitarbeiterABGUI(int ID) {
		/*@author:		Soeren Hebestreit
		 *@date: 		27.07.2019
		 *@description: GUI zur Ansicht der Mitarbeiter eines Bereiches
		 */
		
		setSize(400, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		
		JLabel lblVerlauf = new JLabel("Mitarbeiter");
		lblVerlauf.setFont(new Font("Dialog", Font.BOLD, 21));
		lblVerlauf.setForeground(new Color(255, 255, 255));
		lblVerlauf.setBounds(24, 8, 360, 36);
		getContentPane().add(lblVerlauf);
		
		JLabel lblName = new JLabel("Bereich: "+((Arbeitsbereich)abv.suchen(ID)).getName());
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(24, 36, 360, 24);
		getContentPane().add(lblName);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 400, 64);
		getContentPane().add(rahmenOben);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(255, 255, 255)));
		
		JScrollPane sp = new JScrollPane(panel);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(25, 64, 370, 408);
		sp.setBorder( null );
		sp.setViewportView(panel);
		getContentPane().add(sp);
		
		ArrayList<Mitarbeiter> mitarbeiterliste = new ArrayList<Mitarbeiter>();
		for(int i=0; i < Personalverwaltung.getaMA().size(); i++) {
			if(Personalverwaltung.getaMA().get(i).getActualAB().getArbeitsbereichnummer() == ID)
			mitarbeiterliste.add(Personalverwaltung.getaMA().get(i));
		}
		table = new JTable(getModel(mitarbeiterliste)) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		}; 
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setRowHeight(30);
		setColWidth();
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { 
					if(table.columnAtPoint(e.getPoint()) == 0) {
						Collections.sort(mitarbeiterliste,new MitarbeiterNummerComparator());
					} else {
						Collections.sort(mitarbeiterliste,new MitarbeiterNameComparator());
					}
					table.setModel(getModel(mitarbeiterliste));
					setColWidth();
				}
			}
		});
		panel.add(table);			
		
		setVisible(true);
	}
	
	private static DefaultTableModel getModel(ArrayList<Mitarbeiter> mitarbeiterliste) {
		/*@author:		Soeren Hebestreit
		 *@date: 		27.07.2019
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
		
		table.getColumnModel().getColumn( 0 ).setPreferredWidth( 50 );
		table.getColumnModel().getColumn( 1 ).setPreferredWidth( 130 );
		table.getColumnModel().getColumn( 2 ).setPreferredWidth( 160 );
	}
	
	public static void main(String[] args) throws Exception {
			
		Personalverwaltung.getInstance().laden();
		Arbeitsbereichverwaltung.getInstance().laden();	
		new ShowMitarbeiterABGUI(0);
	}
}
