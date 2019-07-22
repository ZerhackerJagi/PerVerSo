package logik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import comparatoren.*;
import interfaces.*;
import speicher.Dateizugriff;

public class Arbeitsbereichverwaltung implements VerwaltungIF,Serializable {

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private static Arbeitsbereichverwaltung uniqueInstance;
	private static int arbeitsbereichnummer;
	private static ArrayList <Arbeitsbereich> bereiche;

	
//******************** KONSTRUKTOR ********************
		
	public static Arbeitsbereichverwaltung getInstance() {
		/*@author: 		Jakob Kuechler
		 *@date: 		20.06.2019
		 *@description:	gibt die einzige Instanz von Arbeitsbereichverwaltung aus (Singleton)
		 */
			
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
	
	
	@Override
	public void start() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description:	fuegt Grundbereiche bei der Ersterstellung ein 
		 */
		
		add("undefined","unzugeordnete Mitarbeiter");
		add("ausgeschieden","ausgeschiedene Mitarbeiter");
	}
	
	
//******************** VERWALTUNG ********************
	
	public void add(String name, String beschreibung) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	fuegt einen Arbeitsbereich hinzu 
		 */
		
		bereiche.add(new Arbeitsbereich(arbeitsbereichnummer, name, beschreibung));
		arbeitsbereichnummer++;
	}

	
	@Override
	public boolean delete(int nummer) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description:	Bereiche an Hand der uebergebenen Nummer suchen und loeschen
		 */
		
		Arbeitsbereich bereich = (Arbeitsbereich) suchen(nummer);
		if (bereich != null) {
			bereiche.remove(bereich);
			return true;
		}
		return false;
	}
	
	
//******************** AUSGABE ********************
	
	@Override
	public void show () {
		/*@author: 		Soeren Hebestreit
		 *@date: 		22.06.2019
		 *@description:	Bereiche anzeigen (Konsole)
		 */
		
		if (bereiche.size()!=0) {
			for (int i = 0; i < bereiche.size(); i++) {
				bereiche.get(i).display();
			}	
		} else {
			System.out.println("Empty");
		}
	}


//******************** SORTIEREN & SUCHEN ********************
	
	@Override
	public void sortName() {
		// TODO Auto-generated method stub
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Arbeitsbereiche nach Name sortieren
		 */
		
		Collections.sort(bereiche,new ArbeitsbereichNameComparator());
	}


	@Override
	public void sortNumber() {
		// TODO Auto-generated method stub
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Arbeitsbereich nach Nummer sortieren
		 */
		
		Collections.sort(bereiche,new ArbeitsbereichNummerComparator());
	}


	@Override
	public Object suchen(int nummer) {
		// TODO Auto-generated method stub
		/*@author: 		Soeren Hebestreit
		 *@date: 		22.06.2019
		 *@description:	bekommt eine Arbeitsbereichnummer und durchsucht Bereiche anhand dessen
		 */
		
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
	
	@Override
	public void speichern() throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	erzeugt ein Dateizugriff und uebergibt die zu speichernden Daten 
		 */
		
		Dateizugriff data = new Dateizugriff();
		data.speichern(bereiche);	
	}
		
		
	@Override
	public void laden() throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	erzeugt ein Dateizugriff und laedt Daten in die Mitarbeiterliste
		 */
			
		Dateizugriff data = new Dateizugriff();
		bereiche = (ArrayList<Arbeitsbereich>) data.laden();
	}


//******************** GETTER & SETTER ********************	
	
	public static ArrayList<Arbeitsbereich> getBereiche() {
		return bereiche;
	}


	public static void setBereiche(ArrayList<Arbeitsbereich> bereiche) {
		Arbeitsbereichverwaltung.bereiche = bereiche;
	}


}
