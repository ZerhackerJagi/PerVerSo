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
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.GridArrangement;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class StatistikGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Object selectedAB=Arbeitsbereichverwaltung.getBereiche().get(1);
	
//******************** KONSTRUKTOR ********************
	
	public StatistikGUI(int PID) {
		/*@author:		Soeren Hebestreit, Jakob Küchler
		 *@date: 		20.07.2019, 31.07.2019
		 *@description: GUI zur Ansicht der Statistiken, Arbeitsbereichsauswahl
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
		pnlReport.setPreferredSize(new Dimension(200, 200));
		
		JPanel pnlReportGender = new JPanel();
		pnlReportGender.setPreferredSize(new Dimension(200, 200));
		
		JLabel lblAZK = new JLabel("Statistikmenue");
		lblAZK.setFont(new Font("Dialog", Font.BOLD, 21));
		lblAZK.setForeground(new Color(255, 255, 255));
		lblAZK.setBounds(24, 20, 360, 24);
		getContentPane().add(lblAZK);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 594, 64);
		getContentPane().add(rahmenOben);
		
		JLabel lblSoll = new JLabel("Jahr");
		lblSoll.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSoll.setBounds(24, 80, 100, 24);
		getContentPane().add(lblSoll);
		
		Datum date = new Datum();
		String year = ""+date.getJahr();
		JLabel lblSollData = new JLabel(year);
		lblSollData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSollData.setBounds(180, 80, 100, 24);
		getContentPane().add(lblSollData);
		
		JLabel lblUeber = new JLabel("akt. Kalenderwoche");
		lblUeber.setFont(new Font("Dialog", Font.BOLD, 12));
		lblUeber.setBounds(24, 120, 153, 24);
		getContentPane().add(lblUeber);
		
		// Kalenderwoche berechnen
		LocalDate localDate = LocalDate.of(date.getJahr(), date.getMonat(), date.getTag());
		String weekNumber = ""+localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
		
		JLabel lblUeberData = new JLabel(weekNumber);
		lblUeberData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUeberData.setBounds(180, 120, 100, 24);
		getContentPane().add(lblUeberData);
		
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(0, 200, 600, 4);
		getContentPane().add(rahmenUnten);
		
		String[] list = new String[Arbeitsbereichverwaltung.getBereiche().size()];
		for(int i = 0;i<Arbeitsbereichverwaltung.getBereiche().size();i++) {
			list[i] = Arbeitsbereichverwaltung.getBereiche().get(i).getName();
		}

		
		// **** AUSWAHL DER ARBEITSBEREICHE ****
		JComboBox<String> cBArbeitsbereiche = new JComboBox<String>(list);
		cBArbeitsbereiche.setMaximumRowCount(8);
		getContentPane().add(cBArbeitsbereiche);
		cBArbeitsbereiche.setBackground(new Color(255,255,255));
		cBArbeitsbereiche.setBounds(180, 160, 200, 24);
		
		
		getContentPane().add(cBArbeitsbereiche);
		
		JLabel lblArbeitsbereich = new JLabel("Arbeitsbereich");
		lblArbeitsbereich.setFont(new Font("Dialog", Font.BOLD, 12));
		lblArbeitsbereich.setBounds(24, 160, 120, 24);
		getContentPane().add(lblArbeitsbereich);
		
		JPanel panel = new JPanel();
		panel.revalidate();
		
		JButton btnAktualisieren = new JButton("Aktualisieren");
		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Alte Daten löschen
				try{
					panel.remove(table);
				} catch(Exception e) {
					
				}
				
				
				
				// Inititalisierung
				selectedAB = cBArbeitsbereiche.getSelectedItem();
				Auswertung a = new Auswertung();
				
				Arbeitsbereich gewaehlterAB = getContentPaneValue();
							
				
				// Barchart für Altersverteilung
				a.showDurchschnittsalter(gewaehlterAB.getArbeitsbereichnummer());
				DefaultCategoryDataset dcd = new DefaultCategoryDataset();
				dcd.setValue(a.getAgeUnder30(), "Alter AB", "unter 30");
				dcd.setValue(a.getAge30to39(), "Alter AB", "30 - 39");
				dcd.setValue(a.getAge40to50(), "Alter AB", "40 - 50");
				dcd.setValue(a.getAgeOver50(), "Alter AB", "über 50");
				
//				dcd.setValue(a.getAgeUnder30All(), "Alter Gesamt", "unter 30");
//				dcd.setValue(a.getAge30to39All(), "Alter Gesamt", "30 - 39");
//				dcd.setValue(a.getAge40to50All(), "Alter Gesamt", "40 - 50");
//				dcd.setValue(a.getAgeOver50All(), "Alter Gesamt", "über 50");
				
				
				
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
						{null,null},
						{"Durchschnittsalter", ""+a.showDurchschnittsalter(gewaehlterAB.getArbeitsbereichnummer())},
						{"Fehltage gesamt", a.showFehltage(gewaehlterAB.getArbeitsbereichnummer())},
						{"maximale Fehltage", a.showFehltageMaximal(gewaehlterAB.getArbeitsbereichnummer())},
						{"Überminuten des AB", a.showUeberstunden(gewaehlterAB.getArbeitsbereichnummer())},
						{"Überminuten im Durchschnitt", a.showUeberstundenSchnitt()},
						{"Flukuationsquote des AB", a.showFluktuationsquote(gewaehlterAB.getArbeitsbereichnummer())},
						{"Flukuationsquote Gesamt", a.showFluktuationsquoteAll()},
						{null,null},
					},
					new String[] {
						"Statistik", "Wert"
					}
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(100);
				table.getColumnModel().getColumn(1).setPreferredWidth(100);
				//table.setBounds(47, 274, 200, 115);
//				panel.add(table);
				
				
				// Barchart für Geschlechtsverteilung
				a.showGeschlechtsverteilung(gewaehlterAB.getArbeitsbereichnummer());
				DefaultCategoryDataset dcdGender = new DefaultCategoryDataset();
				dcdGender.setValue(a.getCountGenderM(), "Geschlecht", "m");
				dcdGender.setValue(a.getCountGenderW(), "Geschlecht", "w");
				dcdGender.setValue(a.getCountGenderD(), "Geschlecht", "d");
				dcdGender.setValue(a.getCountGender(), "Geschlecht", "unbekannt");
				
				JFreeChart jchartGender = ChartFactory.createBarChart("Geschlechtsverteilung", "Geschlecht", "Häufigkeit", dcdGender, PlotOrientation.VERTICAL, true, true, false);
				CategoryPlot plotGender = jchartGender.getCategoryPlot();
				plotGender.setRangeGridlinePaint(Color.black);
				ChartPanel chartPanelGender = new ChartPanel(jchartGender);
				
				
				
				
				
				pnlReport.removeAll();
				pnlReportGender.removeAll();
				pnlReport.add(chartPanel);
				panel.add(table);
				pnlReportGender.add(chartPanelGender);
				pnlReportGender.updateUI();
				pnlReport.updateUI();
				
				
				
			}



		});
		btnAktualisieren.setBounds(420, 160, 120, 24);
		btnAktualisieren.setBackground(new Color(255,255,255));
		getContentPane().add(btnAktualisieren);
		
		
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(pnlReport);
		panel.add(pnlReportGender);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(pnlReport);
		pnlReport.setLayout(new BoxLayout(pnlReport, BoxLayout.PAGE_AXIS));
		panel.add(pnlReportGender);
		panel.setBackground(new Color(255,255,255));
		pnlReportGender.setLayout(new BoxLayout(pnlReportGender, BoxLayout.PAGE_AXIS));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 204, 595, 408);
		scrollPane.setLayout(new ScrollPaneLayout());
		getContentPane().add(scrollPane);
		
		
//		getContentPane().add(table);
		

		

		
		setVisible(true);
		panel.updateUI();
		getContentPane().update(getGraphics());
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
	
	public static void main(String[] args) throws Exception {
		
		Arbeitsbereichverwaltung.getInstance().laden();	
		Personalverwaltung.getInstance().laden();
		new StatistikGUI(100000);
	}
}
