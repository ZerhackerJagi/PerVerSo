package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logik.Arbeitsbereich;
import logik.Arbeitsbereichverwaltung;
import logik.Auswertung;
import logik.Mitarbeiter;
import logik.Personalverwaltung;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import extern.Datum;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;

public class StatistikGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Object selectedAB=Arbeitsbereichverwaltung.getBereiche().get(1);
	
//******************** KONSTRUKTOR ********************
	
	public StatistikGUI(int PID) {
		/*@author:		Soeren Hebestreit
		 *@date: 		20.07.2019
		 *@description: GUI zur Ansicht des Arbeitsplanes, Wochenauswahl
		 */
		
		setSize(600, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = ((Mitarbeiter) pv.suchen(PID));
		
		JPanel pnlReport = new JPanel();
		pnlReport.setBounds(47, 41, 492, 215);
//		getContentPane().add(pnlReport);
		pnlReport.setLayout(new BoxLayout(pnlReport, BoxLayout.X_AXIS));
		
		JLabel lblAZK = new JLabel("Statistikmenue");
		lblAZK.setFont(new Font("Dialog", Font.BOLD, 21));
		lblAZK.setForeground(new Color(255, 255, 255));
		lblAZK.setBounds(24, 20, 360, 24);
		getContentPane().add(lblAZK);
		
		JLabel lblName = new JLabel(ma.getVorname()+" "+ma.getName());
		lblName.setFont(new Font("Dialog", Font.BOLD, 21));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(24, 44, 360, 24);
		getContentPane().add(lblName);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 594, 88);
		getContentPane().add(rahmenOben);
		
		JLabel lblSoll = new JLabel("Jahr");
		lblSoll.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSoll.setBounds(24, 100, 100, 24);
		getContentPane().add(lblSoll);
		
		Datum date = new Datum();
		String year = ""+date.getJahr();
		JLabel lblSollData = new JLabel(year);
		lblSollData.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSollData.setBounds(187, 99, 100, 24);
		getContentPane().add(lblSollData);
		
		JLabel lblUeber = new JLabel("Akt. Kalenderwoche");
		lblUeber.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUeber.setBounds(24, 124, 153, 24);
		getContentPane().add(lblUeber);
		
		// Kalenderwoche berechnen
		LocalDate localDate = LocalDate.of(date.getJahr(), date.getMonat(), date.getTag());
		String weekNumber = ""+localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
		
		JLabel lblUeberData = new JLabel(weekNumber);
		lblUeberData.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUeberData.setBounds(187, 124, 100, 24);
		getContentPane().add(lblUeberData);
		
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(0, 200, 600, 4);
		getContentPane().add(rahmenUnten);
		
		String[] list = new String[Arbeitsbereichverwaltung.getBereiche().size()];
		for(int i = 0;i<Arbeitsbereichverwaltung.getBereiche().size();i++) {
			list[i] = Arbeitsbereichverwaltung.getBereiche().get(i).getName();
		}

		JComboBox<String> cBArbeitsbereiche = new JComboBox<String>(list);
		cBArbeitsbereiche.setMaximumRowCount(8);
		getContentPane().add(cBArbeitsbereiche);
		
		cBArbeitsbereiche.setBounds(160, 159, 237, 24);
		
		
		getContentPane().add(cBArbeitsbereiche);
		
		JLabel lblArbeitsbereich = new JLabel("Arbeitsbereich");
		lblArbeitsbereich.setFont(new Font("Dialog", Font.BOLD, 14));
		lblArbeitsbereich.setBounds(24, 159, 126, 24);
		getContentPane().add(lblArbeitsbereich);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.revalidate();
		
		panel.add(pnlReport);
		
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(0, 209, 594, 402);
		getContentPane().add(scrollPane);
		
		JButton btnAktualisieren = new JButton("Aktualisieren");
		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Daten auslesen
				
				
				
				// Inititalisierung
				selectedAB = cBArbeitsbereiche.getSelectedItem();
				Auswertung a = new Auswertung();
				
				Arbeitsbereich gewaehlterAB = getContentPaneValue();
							
				
				// Barchart für Altersverteilung
				a.showDurchschnittsalter(gewaehlterAB.getArbeitsbereichnummer());
				DefaultCategoryDataset dcd = new DefaultCategoryDataset();
				dcd.setValue(a.getAgeUnder30(), "Alter", "unter 30");
				dcd.setValue(a.getAge30to39(), "Alter", "30 - 39");
				dcd.setValue(a.getAge40to50(), "Alter", "40 - 50");
				dcd.setValue(a.getAgeOver50(), "Alter", "über 50");
				
				JFreeChart jchart = ChartFactory.createBarChart("Altersverteilung", "Altersgruppe", "Häufigkeit", dcd, PlotOrientation.VERTICAL, true, true, false);
				CategoryPlot plot = jchart.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
//				ChartFrame chartFrm = new ChartFrame("Altersverteilung", jchart,true);
				
//				chartFrm.setVisible(true);
//				chartFrm.setSize(500, 400);
				
				ChartPanel chartPanel = new ChartPanel(jchart);
				
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{"Durchschnittsalter", ""+a.showDurchschnittsalter(gewaehlterAB.getArbeitsbereichnummer())},
						{null, null},
						{null, null},
						{null, null},
					},
					new String[] {
						"Statistik", "Wert"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(200);
				table.getColumnModel().getColumn(1).setPreferredWidth(150);
				table.setBounds(47, 274, 492, 115);
				panel.add(table);
				
				
				pnlReport.removeAll();
				pnlReport.add(chartPanel);
				pnlReport.updateUI();
				
				
				
			}
		});
		btnAktualisieren.setBounds(413, 160, 126, 23);
		getContentPane().add(btnAktualisieren);
		
		JLabel lblAltersverteilung = new JLabel("Altersverteilung");
		lblAltersverteilung.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAltersverteilung.setBounds(47, 215, 150, 19);
		getContentPane().add(lblAltersverteilung);
		
		
//		getContentPane().add(table);
		

		

		
		setVisible(true);
		panel.updateUI();
		getContentPane().update(getGraphics());
	}
	
	public static void main(String[] args) throws Exception {
				
		Arbeitsbereichverwaltung.getInstance().laden();	
		Personalverwaltung.getInstance().laden();
		new StatistikGUI(0);
	}
	
	public Arbeitsbereich getContentPaneValue() {

		Object obj = selectedAB;
		Arbeitsbereich gewaehlterAB = null;
		for(int i = 0;i<Arbeitsbereichverwaltung.getBereiche().size();i++) {
			if(obj.toString().contains(Arbeitsbereichverwaltung.getBereiche().get(i).getName())) {
				gewaehlterAB = Arbeitsbereichverwaltung.getBereiche().get(i);
				i = Arbeitsbereichverwaltung.getBereiche().size();
			}
		}
		return gewaehlterAB;
	}
}
