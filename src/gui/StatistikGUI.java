package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logik.Arbeitsbereich;
import logik.Arbeitsbereichverwaltung;
import logik.Auswertung;
import logik.Personalverwaltung;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import _programmstart.Programmstart;
import extern.Datum;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import java.awt.Dimension;

import javax.swing.JCheckBox;
/*@author:		Soeren Hebestreit (Layout), Jakob Küchler (Content)
 *@date: 		20.07.2019, 31.07.2019
 *@description: GUI zur Ansicht der Statistiken, Arbeitsbereichsauswahl
 */

public class StatistikGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Datum selectedDate = new Datum();
	private Object selectedAB=Arbeitsbereichverwaltung.getBereiche().get(0);
	
//******************** KONSTRUKTOR ********************
	
	public StatistikGUI(int PID) {
			
		setIconImages(Programmstart.iconlist);
		
		setSize(600, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		JLabel lblStatistik = new JLabel("Statistikmenue");
		lblStatistik.setFont(new Font("Dialog", Font.BOLD, 21));
		lblStatistik.setForeground(new Color(255, 255, 255));
		lblStatistik.setBounds(24, 20, 360, 24);
		getContentPane().add(lblStatistik);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 594, 64);
		getContentPane().add(rahmenOben);
		
		// **** AUSWAHL DER ARBEITSBEREICHE ****
		
		JLabel lblArbeitsbereich = new JLabel("Arbeitsbereich");
		lblArbeitsbereich.setBounds(40, 80, 120, 20);
		getContentPane().add(lblArbeitsbereich);
		
		int length = Arbeitsbereichverwaltung.getBereiche().size();
		String[] list = new String[length-1];
		list[0] = Arbeitsbereichverwaltung.getBereiche().get(0).getName();
		if (length > 1) {
			for(int i = 2;i<length;i++) {
				list[i-1] = Arbeitsbereichverwaltung.getBereiche().get(i).getName();
			}
		}

		JComboBox<String> cBArbeitsbereiche = new JComboBox<String>(list);
		cBArbeitsbereiche.setMaximumRowCount(8);
		getContentPane().add(cBArbeitsbereiche);
		cBArbeitsbereiche.setBackground(new Color(255,255,255));
		cBArbeitsbereiche.setBounds(172, 78, 200, 24);
		getContentPane().add(cBArbeitsbereiche);
		
		JLabel lblJahr = new JLabel("Jahr");
		lblJahr.setBounds(40, 120, 100, 24);
		getContentPane().add(lblJahr);
		
		JTextField tfDay = new JTextField();
		tfDay.setText(selectedDate.getTag()+"");
		tfDay.setBounds(172, 118, 40, 24);
		getContentPane().add(tfDay);
		
		JLabel lblPunkt1 = new JLabel(".",0);
		lblPunkt1.setBounds(212, 120, 10, 20);
		getContentPane().add(lblPunkt1);
		
		JTextField tfMonth = new JTextField();
		tfMonth.setText(selectedDate.getMonat()+"");
		tfMonth.setBounds(222, 118, 40, 24);
		getContentPane().add(tfMonth);
		
		JLabel lblPunkt2 = new JLabel(".",0);
		lblPunkt2.setBounds(262, 120, 10, 20);
		getContentPane().add(lblPunkt2);
		
		int yearActually = new Datum().getJahr();
		int yearOldest = yearActually;
		// Frühestes Einstellungsdatum finden
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++) {
			if(Personalverwaltung.getaMA().get(i).getEinstellungsdatum().getJahr()<yearOldest&&((yearActually-10)<=Personalverwaltung.getaMA().get(i).getEinstellungsdatum().getJahr())) {
				yearOldest = Personalverwaltung.getaMA().get(i).getEinstellungsdatum().getJahr();
			}
		}
	
		String[] lastYears = new String[(yearActually-yearOldest+1)];
		System.out.println("Länge Liste: "+(yearActually-yearOldest+1));
		for(int i = 0;i<lastYears.length;i++) {
			lastYears[i] = ""+(yearActually-i);
		}
				
		JComboBox<String> comboBoxYear = new JComboBox<String>(lastYears);
		comboBoxYear.setBackground(new Color(255,255,255));
		comboBoxYear.setBounds(272, 118, 100, 24);
		getContentPane().add(comboBoxYear);
		
		JCheckBox ckbMitUnternehmen = new JCheckBox("prozentual");
		ckbMitUnternehmen.setBackground(new Color(255,255,255));
		ckbMitUnternehmen.setBounds(412, 78, 124, 24);
		getContentPane().add(ckbMitUnternehmen);
		
		JPanel rahmenMitte = new JPanel();
		rahmenMitte.setBackground(new Color(100, 150, 200));
		rahmenMitte.setBounds(0, 196, 600, 4);
		getContentPane().add(rahmenMitte);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255,255,255));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.revalidate();
		
		JPanel frei1 = new JPanel();
		frei1.setBackground(new Color(255, 255, 255));
		frei1.setPreferredSize(new Dimension(100, 24));
		panel.add(frei1);
		
		JPanel pnlReport = new JPanel();
		pnlReport.setPreferredSize(new Dimension(240, 240));
		pnlReport.setLayout(new BoxLayout(pnlReport, BoxLayout.PAGE_AXIS));
		panel.add(pnlReport);
		
		JPanel frei2 = new JPanel();
		frei2.setBackground(new Color(255, 255, 255));
		frei2.setPreferredSize(new Dimension(100, 48));
		panel.add(frei2);
		
		JPanel pnlReportGender = new JPanel();
		pnlReportGender.setPreferredSize(new Dimension(240, 240));
		pnlReportGender.setLayout(new BoxLayout(pnlReportGender, BoxLayout.PAGE_AXIS));
		panel.add(pnlReportGender);
		
		JPanel frei3 = new JPanel();
		frei3.setBackground(new Color(255, 255, 255));
		frei3.setPreferredSize(new Dimension(100, 12));
		panel.add(frei3);
		
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setPreferredSize(new Dimension(100, 4));
		panel.add(rahmenUnten);
		
		JPanel frei4 = new JPanel();
		frei4.setBackground(new Color(255, 255, 255));
		frei4.setPreferredSize(new Dimension(100, 24));
		panel.add(frei4);
		
		JButton btnAktualisieren = new JButton("Aktualisieren");
		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					selectedDate = new Datum(Integer.parseInt(tfDay.getText()), Integer.parseInt(tfMonth.getText()), Integer.parseInt((String) comboBoxYear.getSelectedItem()));
				} catch (Exception e0) {
					try {
						selectedDate = new Datum(1, Integer.parseInt(tfMonth.getText()), Integer.parseInt((String) comboBoxYear.getSelectedItem()));
					} catch (Exception e1) {
						try {
							selectedDate = new Datum(Integer.parseInt(tfDay.getText()), 1, Integer.parseInt((String) comboBoxYear.getSelectedItem()));
						} catch (Exception e2) {
							selectedDate = new Datum(1, 1, Integer.parseInt((String) comboBoxYear.getSelectedItem()));
						}
					}
				}
				System.out.println(selectedDate);
				
				// Alte Daten löschen
				try{
					panel.remove(table);
				} catch(Exception e) {
					
				}
								
				// Inititalisierung
				selectedAB = cBArbeitsbereiche.getSelectedItem();
				Auswertung a = new Auswertung();
				
				int gewaehltesJahr = Integer.parseInt((String) comboBoxYear.getSelectedItem());
				Arbeitsbereich gewaehlterAB = getContentPaneValue();				
							
				ChartPanel chartPanelGender = null;
				ChartPanel chartPanel = null;
				
				if(ckbMitUnternehmen.isSelected()) {
					
					// Barchart für Altersverteilung (Unternehmen)
					
					DefaultCategoryDataset dcd = new DefaultCategoryDataset();
					System.out.println("Arbeitsbereich");
					a.calcAlterPercent(gewaehlterAB.getArbeitsbereichnummer(), selectedDate);
					dcd.setValue(a.getAgeUnder30p(), "Altersanteil Arbeitsbereich (%)", "unter 30");
					dcd.setValue(a.getAge30to39p(), "Altersanteil Arbeitsbereich (%)", "30 - 39");
					dcd.setValue(a.getAge40to50p(), "Altersanteil Arbeitsbereich (%)", "40 - 50");
					dcd.setValue(a.getAgeOver50p(), "Altersanteil Arbeitsbereich (%)", "über 50");
					
					System.out.println("\n********************\nGesamtunternehmen");
					a.calcAlterPercent(-1,selectedDate);
					dcd.setValue(a.getAgeUnder30Allp(), "Altersanteil Unternehmen (%)", "unter 30");
					dcd.setValue(a.getAge30to39Allp(), "Altersanteil Unternehmen (%)", "30 - 39");
					dcd.setValue(a.getAge40to50Allp(), "Altersanteil Unternehmen (%)", "40 - 50");
					dcd.setValue(a.getAgeOver50Allp(), "Altersanteil Unternehmen (%)", "über 50");
					
					// Barchart für Altersverteilung (Arbeitsbereich)

					JFreeChart jchart = ChartFactory.createBarChart("Altersverteilung", "Altersgruppe", "Prozentuale Häufigkeit", dcd, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot = jchart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					
					chartPanel = new ChartPanel(jchart);
					
					DefaultCategoryDataset dcdGender = new DefaultCategoryDataset();
					
					// Barchart für Geschlechterverteilung (Arbeitsbereich)
					
					a.calcGeschlechtPercent(gewaehlterAB.getArbeitsbereichnummer(), selectedDate);
					dcdGender.setValue(a.getCountGenderMp(), "Geschlechterverteilung Arbeitsbereich (%)", "m");
					dcdGender.setValue(a.getCountGenderWp(), "Geschlechterverteilung Arbeitsbereich (%)", "w");
					dcdGender.setValue(a.getCountGenderDp(), "Geschlechterverteilung Arbeitsbereich (%)", "d");
					
					// Barchart für Geschlechterverteilung (Unternehmen)
					
					a.calcGeschlechtPercent(-1, selectedDate);
					dcdGender.setValue(a.getCountGenderMAllp(), "Geschlechterverteilung Unternehmen (%)", "m");
					dcdGender.setValue(a.getCountGenderWAllp(), "Geschlechterverteilung Unternehmen (%)", "w");
					dcdGender.setValue(a.getCountGenderDAllp(), "Geschlechterverteilung Unternehmen (%)", "d");
					
					JFreeChart jchartGender = ChartFactory.createBarChart("Geschlechterverteilung", "Geschlecht", "Prozentuale Häufigkeit", dcdGender, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plotGender = jchartGender.getCategoryPlot();
					plotGender.setRangeGridlinePaint(Color.black);
					chartPanelGender = new ChartPanel(jchartGender);
					
				} else {
				
					// **************************************************************************
					// ******************  Barchart für Altersverteilung ************************
					
					a.showDurchschnittsalter(gewaehlterAB.getArbeitsbereichnummer(), selectedDate);
					DefaultCategoryDataset dcd = new DefaultCategoryDataset();
					dcd.setValue(a.getAgeUnder30(), "Alter Arbeitsbereich", "unter 30");
					dcd.setValue(a.getAge30to39(), "Alter Arbeitsbereich", "30 - 39");
					dcd.setValue(a.getAge40to50(), "Alter Arbeitsbereich", "40 - 50");
					dcd.setValue(a.getAgeOver50(), "Alter Arbeitsbereich", "über 50");
					
					a.showDurchschnittsalter(-1, selectedDate);
					dcd.setValue(a.getAgeUnder30All(), "Alter Unternehmen", "unter 30");
					dcd.setValue(a.getAge30to39All(), "Alter Unternehmen", "30 - 39");
					dcd.setValue(a.getAge40to50All(), "Alter Unternehmen", "40 - 50");
					dcd.setValue(a.getAgeOver50All(), "Alter Unternehmen", "über 50");
					
					// Größten Wert rausfinden für y-Achse (Alter)
					double maxValueAge=0;
					if(a.getAgeUnder30All()>maxValueAge) {maxValueAge = a.getAgeUnder30All();}
					if(a.getAge30to39All()>maxValueAge) {maxValueAge = a.getAge30to39All();}
					if(a.getAge40to50All()>maxValueAge) {maxValueAge = a.getAge40to50All();}
					if(a.getAgeOver50All()>maxValueAge) {maxValueAge = a.getAgeOver50All();}
					
					JFreeChart jchart = ChartFactory.createBarChart("Altersverteilung", "Altersgruppe", "Häufigkeit", dcd, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot = jchart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					
					// Zahlen über Graph schreiben
					
					CategoryItemRenderer renderer = ((CategoryPlot) jchart.getCategoryPlot()).getRenderer();
					renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
					renderer.setBaseItemLabelsVisible(true);
					ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
					renderer.setBasePositiveItemLabelPosition(position);
					
					// Größe der y-Achse festlegen
					NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
					rangeAxis.setRange(0.0, (maxValueAge+5));
					
					chartPanel = new ChartPanel(jchart);
					
					// ********************************************************************************
					// ******************  Barchart für Geschlechterverteilung ************************
					
					a.showGeschlechterverteilung(gewaehlterAB.getArbeitsbereichnummer(), selectedDate);
					DefaultCategoryDataset dcdGender = new DefaultCategoryDataset();
					dcdGender.setValue(a.getCountGenderM(), "Geschlecht", "m");
					dcdGender.setValue(a.getCountGenderW(), "Geschlecht", "w");
					dcdGender.setValue(a.getCountGenderD(), "Geschlecht", "d");
					
					a.showGeschlechterverteilung(-1, selectedDate);
					dcdGender.setValue(a.getCountGenderMAllp(), "Geschlecht Unternehmen", "m");
					dcdGender.setValue(a.getCountGenderWAllp(), "Geschlecht Unternehmen", "w");
					dcdGender.setValue(a.getCountGenderDAllp(), "Geschlecht Unternehmen", "d");
					
					JFreeChart jchartGender = ChartFactory.createBarChart("Geschlechterverteilung", "Geschlecht", "Häufigkeit", dcdGender, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plotGender = jchartGender.getCategoryPlot();
					
					// Zahlen über Graph schreiben
					
					CategoryItemRenderer rendererGender = ((CategoryPlot) jchartGender.getCategoryPlot()).getRenderer();
					rendererGender.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
					rendererGender.setBaseItemLabelsVisible(true);
					ItemLabelPosition positionGender = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
					renderer.setBasePositiveItemLabelPosition(positionGender);
					
					// Größten Wert rausfinden für y-Achse Geschlecht
					double maxValueGender=0;
					if(a.getCountGenderMAllp()>maxValueGender) {maxValueGender = a.getCountGenderMAllp();}
					if(a.getCountGenderWAllp()>maxValueGender) {maxValueGender = a.getCountGenderWAllp();}
					if(a.getCountGenderDAllp()>maxValueGender) {maxValueGender = a.getCountGenderDAllp();}
					
					// Größe der y-Achse festlegen
					NumberAxis rangeAxisGender = (NumberAxis) plotGender.getRangeAxis();
					rangeAxisGender.setRange(0.0, (maxValueGender+5));
					
					plotGender.setRangeGridlinePaint(Color.black);
					chartPanelGender = new ChartPanel(jchartGender);
				}
				
				// Tabelle mit allen Daten
				table = new JTable();
				
				NumberFormat n = NumberFormat.getInstance();
				n.setMaximumFractionDigits(2); // max. 2 stellen hinter komma
				
				if(gewaehltesJahr == (new Datum()).getJahr()) {
					table.setModel(new DefaultTableModel(
						new Object[][] {
							{null,"<html><b>"+gewaehlterAB.getName()+"</b></html>","<html><b>Unternehmen</b></html>"},
							{null,null,null},
							{"     Durchschnittsalter", ""+a.showDurchschnittsalter(gewaehlterAB.getArbeitsbereichnummer(), selectedDate),a.showDurchschnittsalter(-1, selectedDate)},
							{"     Fehltage gesamt", a.showFehltage(gewaehlterAB.getArbeitsbereichnummer()),a.showFehltage(-1)},
							{"     maximale Fehltage", a.showFehltageMaximal(gewaehlterAB.getArbeitsbereichnummer()),a.showFehltageMaximal(-1)},
							{"     Überstunden", a.showUeberstunden(gewaehlterAB.getArbeitsbereichnummer()),a.showUeberstunden(-1)},
							{"     Überstunden im Durchschnitt", a.showUeberstundenSchnitt(gewaehlterAB.getArbeitsbereichnummer()),a.showUeberstundenSchnitt(-1)},
							{"     Flukuationsquote", a.showFluktuationsquote(gewaehlterAB.getArbeitsbereichnummer(), selectedDate),a.showFluktuationsquoteAll(selectedDate)},
							{"     Anzahl MitarbeiterInnen",a.showAnzahlMitarbeiterInArbeitsbereich(gewaehlterAB.getArbeitsbereichnummer(), selectedDate),a.showAnzahlMitarbeiterImUnternehmen(selectedDate)},
							{null,null,null},
						},
						new String[] {
								"Statistik", "Arbeitsbereich", "Unternehmen"
						}
					));
				} else {
					table.setModel(new DefaultTableModel(
							new Object[][] {
								{null,"<html><b>"+gewaehlterAB.getName()+"</b></html>","<html><b>Unternehmen</b></html>"},
								{null,null,null},
								{"     Durchschnittsalter", ""+a.showDurchschnittsalter(gewaehlterAB.getArbeitsbereichnummer(), selectedDate),a.showDurchschnittsalter(-1, selectedDate)},
								{"     Flukuationsquote", a.showFluktuationsquote(gewaehlterAB.getArbeitsbereichnummer(), selectedDate),a.showFluktuationsquoteAll(selectedDate)},
								{"     Anzahl MitarbeiterInnen",a.showAnzahlMitarbeiterInArbeitsbereich(gewaehlterAB.getArbeitsbereichnummer(), selectedDate),a.showAnzahlMitarbeiterImUnternehmen(selectedDate)},
								{null,null,null},
							},
							new String[] {
									"Statistik", "Arbeitsbereich", "Unternehmen"
							}
						));
				}
				table.getColumnModel().getColumn(0).setPreferredWidth(150);
				table.getColumnModel().getColumn(1).setPreferredWidth(75);
				table.getColumnModel().getColumn(2).setPreferredWidth(75);
				table.setShowGrid(false);
				pnlReport.removeAll();
				pnlReportGender.removeAll();
				pnlReport.add(chartPanel);
				pnlReportGender.add(chartPanelGender);
				panel.add(table);
				pnlReportGender.updateUI();
				pnlReport.updateUI();				
			}
		});
		btnAktualisieren.setBounds(412, 118, 124, 24);
		btnAktualisieren.setBackground(new Color(255,255,255));
		getContentPane().add(btnAktualisieren);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 200, 595, 408);
		scrollPane.setLayout(new ScrollPaneLayout());
		getContentPane().add(scrollPane);

		panel.updateUI();
		getContentPane().update(getGraphics());
		
		setVisible(true);
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
