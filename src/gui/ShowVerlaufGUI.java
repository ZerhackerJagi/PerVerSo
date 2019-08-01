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

import logik.Arbeitsbereich;
import logik.Arbeitsbereichverwaltung;
import logik.Mitarbeiter;
import logik.Personalverwaltung;
import logik.Zugehoerigkeit;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

public class ShowVerlaufGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private static JTable table;
	
//******************** KONSTRUKTOR ********************
	
	public ShowVerlaufGUI(int PID) {
		/*@author:		Soeren Hebestreit
		 *@date: 		20.07.2019
		 *@description: GUI zur Ansicht der Zugehörigkeiten eines Mitarbeiters
		 */
		
		setSize(360, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung.getInstance();
		Mitarbeiter ma = ((Mitarbeiter) pv.suchen(PID));
		
		JLabel lblVerlauf = new JLabel("Verlauf");
		lblVerlauf.setFont(new Font("Dialog", Font.BOLD, 21));
		lblVerlauf.setForeground(new Color(255, 255, 255));
		lblVerlauf.setBounds(24, 8, 360, 36);
		getContentPane().add(lblVerlauf);
		
		JLabel lblName = new JLabel(ma.getVorname()+" "+ma.getName());
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(24, 36, 360, 24);
		getContentPane().add(lblName);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 360, 64);
		getContentPane().add(rahmenOben);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(255, 255, 255)));
		
		JScrollPane sp = new JScrollPane(panel);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(25, 64, 330, 548);
		sp.setBorder( null );
		sp.setViewportView(panel);
		getContentPane().add(sp);
		
		table = new JTable(getModel(ma.getZugehoerigkeit())) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);
		formatTable(ma.getZugehoerigkeit());
		panel.add(table);			
		
		setVisible(true);
	}
	
	private static DefaultTableModel getModel(ArrayList<Zugehoerigkeit> liste) {
		/*@author:		Soeren Hebestreit
		 *@date: 		26.07.2019
		 *@description: Tabellenmodell (inkl. Daten) erzeugen
		 */
		
		String [][] Data = new String [liste.size()*2][2];
		for(int i=0; i < liste.size()*2; i=i+2) {
			Data[i][0] = liste.get(i/2).getStart()+"";
			Data[i][1] = ((Arbeitsbereich) Arbeitsbereichverwaltung.getInstance().suchen(liste.get(i/2).getArbeitsbereichnummer())).getName();
			Data[i+1][0] = "";
			Data[i+1][1] = liste.get(i/2).getBemerkung();
		}
		String[] columnNames = {"Datum","Arbeitsbereich"};
		DefaultTableModel model = new DefaultTableModel(Data, columnNames);
		
		return model;
	}
	
	private void formatTable(ArrayList<Zugehoerigkeit> liste) {
		/*@author:		Soeren Hebestreit
		 *@date: 		26.07.2019
		 *@description: Zugehoerigkeits-Tabelle formatieren
		 */
		
		class CellRenderer extends JTextArea implements TableCellRenderer {
			/*@author:		Soeren Hebestreit
			 *@date: 		26.07.2019
			 *@description: CellRenderer zur Formatierung der Zellen (Text: oben, ungerade Zeilen: Fett)
			 */

			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				this.setText((String)value);
		        this.setWrapStyleWord(true);            
		        this.setLineWrap(true);         

			    if ((row & 1) != 0) {
			    	this.setFont(this.getFont().deriveFont(Font.PLAIN));
			    	table.setRowHeight(row,14+51);
			    } else {
			    	this.setFont(this.getFont().deriveFont(Font.BOLD));
			    	table.setRowHeight(row,20);
			    }
			    return this;
			}
		}
		
		CellRenderer cr = new CellRenderer();  
		table.getColumnModel().getColumn(0).setCellRenderer(cr);
		table.getColumnModel().getColumn(1).setCellRenderer(cr);
       
		table.getColumnModel().getColumn(0).setPreferredWidth( 100 );
		table.getColumnModel().getColumn(1).setPreferredWidth( 200 );
	}
	
	public static void main(String[] args) throws Exception {
			
		Personalverwaltung.getInstance().laden();
		Arbeitsbereichverwaltung.getInstance().laden();	
		new ShowVerlaufGUI(100001);
	}
}
