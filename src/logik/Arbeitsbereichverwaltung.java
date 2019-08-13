package logik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import comparatoren.ArbeitsbereichNameComparator;
import comparatoren.ArbeitsbereichNummerComparator;
import interfaces.VerwaltungIF;
import speicher.Dateizugriff;
/*@author: 		Soeren Hebestreit
 *@date: 		20.06.2019
 *@description:
 */

public class Arbeitsbereichverwaltung implements VerwaltungIF,Serializable {

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private static Arbeitsbereichverwaltung uniqueInstance;
	private static int arbeitsbereichnummer;
	private static ArrayList <Arbeitsbereich> bereiche;
	private static String path = "DataABV.avd";
	
//******************** KONSTRUKTOR ********************
	
	/*@author: 		Jakob Kuechler
	 *@date: 		20.06.2019
	 *@description:	gibt die einzige Instanz von Arbeitsbereichverwaltung aus (Singleton)
	 */
	public static Arbeitsbereichverwaltung getInstance() {
				
		if(uniqueInstance == null) {
			uniqueInstance = new Arbeitsbereichverwaltung();
		}
		return uniqueInstance;
	}
	
	private Arbeitsbereichverwaltung() {
		arbeitsbereichnummer = 0;
		bereiche = new ArrayList <Arbeitsbereich> ();
		start();
	}
	
	/*
	 *@description:	fuegt Grundbereiche bei der Ersterstellung ein 
	 */
	public void start() {
			
		add("Undefined","unzugeordnete Mitarbeiter");
		add("Ausgeschieden","ausgeschiedene Mitarbeiter");
	}
		
//******************** VERWALTUNG ********************
	
	public void add(String name, String beschreibung) {
		/*
		 *@description:	fuegt einen Arbeitsbereich hinzu 
		 */
		
		bereiche.add(new Arbeitsbereich(arbeitsbereichnummer, name, beschreibung));
		arbeitsbereichnummer++;
	}

	/*
	 *@description:	Bereiche an Hand der uebergebenen Nummer suchen und loeschen
	 */
	public boolean delete(int nummer) {
				
		Arbeitsbereich bereich = (Arbeitsbereich) suchen(nummer);
		if (bereich != null) {
			bereiche.remove(bereich);
			return true;
		}
		return false;
	}
		
//******************** AUSGABE ********************
	
	/*
	 *@description:	Bereiche anzeigen (Konsole)
	 */
	public void show () {
			
		if (bereiche.size()!=0) {
			for (int i = 0; i < bereiche.size(); i++) {
				bereiche.get(i).display();
			}	
		} else {
			System.out.println("Empty");
		}
	}

//******************** SORTIEREN & SUCHEN ********************
	
	/*
	 *@description:	Arbeitsbereiche nach Name sortieren
	 */
	public void sortName() {
			
		Collections.sort(bereiche,new ArbeitsbereichNameComparator());
	}

	/*
	 *@description:	Arbeitsbereich nach Nummer sortieren
	 */
	public void sortNumber() {
			
		Collections.sort(bereiche,new ArbeitsbereichNummerComparator());
	}

	/*
	 *@description:	bekommt eine Arbeitsbereichnummer und durchsucht Bereiche anhand dessen
	 */
	public Object suchen(int nummer) {
			
		if (bereiche.size()!=0) {
			for (int i = 0; i < bereiche.size(); i++) {
				if (bereiche.get(i).getArbeitsbereichnummer() == nummer) {
					return bereiche.get(i);
				}
			}
		}
		return null;
	}

//******************** LOAD & SAVE ********************
	
	/*@author: 		Charly Spina
	 *@description:	setzt den Modus/datenbank fürs speichern und laden
	 */
	public void setModus(String modus) {
		Dateizugriff data = Dateizugriff.getInstance();
		data.setDatenBank(modus);
	}
	
	/*
	 *@description:	erzeugt ein Dateizugriff und uebergibt die zu speichernden Daten 
	 */
	public void speichern() throws Exception {
			
		Dateizugriff data = Dateizugriff.getInstance();
		data.speichern(bereiche);	
	}
			
	/*
	 *@description:	erzeugt ein Dateizugriff und laedt Daten in die Mitarbeiterliste
	 */
	public void laden() throws Exception {
				
		Dateizugriff data = Dateizugriff.getInstance();
		bereiche = (ArrayList<Arbeitsbereich>) data.laden();
		arbeitsbereichnummer  = bereiche.get(bereiche.size()-1).getArbeitsbereichnummer()+1;
	}

//******************** GETTER & SETTER ********************	
	
	public static ArrayList<Arbeitsbereich> getBereiche() {
		return bereiche;
	}

	public static void setBereiche(ArrayList<Arbeitsbereich> bereiche) {
		Arbeitsbereichverwaltung.bereiche = bereiche;
	}
	
	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		Arbeitsbereichverwaltung.path = path;
	}
	
//********************Testumgebung**************************
	/*
	 * @author: Charly Spina
	 */
	public void resetArbeitsbereichverwaltung() {
		uniqueInstance = new Arbeitsbereichverwaltung();
	}

}
