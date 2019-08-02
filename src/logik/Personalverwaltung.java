package logik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import comparatoren.MitarbeiterNameComparator;
import comparatoren.MitarbeiterNummerComparator;
import extern.Datum;
import interfaces.VerwaltungIF;
import speicher.Dateizugriff;
/*
 *@author: 		Soeren Hebestreit
 *@date: 		21.06.2019
 *@description:	
 */
public class Personalverwaltung implements VerwaltungIF,Serializable {
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private static Personalverwaltung uniqueInstance; 	
	private static int personalnummer;
	private static ArrayList <Mitarbeiter> aMA;
		
//******************** KONSTRUKTOR ********************
	
	/*@author: 		Jakob Kuechler
	 *@description:	Gibt die einzige Instanz von Personalverwaltung aus (Singleton)
	 */
	public static Personalverwaltung getInstance() {
			
		if(uniqueInstance == null) {
			uniqueInstance = new Personalverwaltung();
		}
		return uniqueInstance;
	}
	
	private Personalverwaltung() {
		personalnummer = 100000;
		aMA = new ArrayList <Mitarbeiter> ();
		start();
	}
	
	//fuegt Standardadmin bei der Ersterstellung ein 
	public void start() {
		
		aMA.add(new Mitarbeiter(personalnummer, "admin", "admin", 'd', new Datum(), "admin", new Admin(personalnummer), new Datum(), new Arbeitszeitkonto(), new Zugehoerigkeit(new Datum(),0,"Standardadmin anlegen")));
		personalnummer ++;
		aMA.add(new Mitarbeiter(personalnummer, "user", "test", 'd', new Datum(), "passwort", new User(personalnummer), new Datum(), new Arbeitszeitkonto(), new Zugehoerigkeit(new Datum(),0,"Testuser anlegen")));
		personalnummer ++;
		Arbeitszeitkonto azk = aMA.get(1).getAzk();
		azk.addPlus(3000);
		try {
			azk.addUrlaub(new Datum(1,1,2019), new Datum(5,1,2019), 4);
			azk.addKrankheit(new Datum(3,3,2019), new Datum(5,3,2019), 3);
			azk.addUrlaub(new Datum(26,4,2019), new Datum(2,5,2019), 4);
			azk.addUrlaub(new Datum(7,7,2019), new Datum(26,7,2019), 15);
			azk.addKrankheit(new Datum(1,1,2019), new Datum(5,1,2019), 4);
			azk.addUrlaub(new Datum(23,12,2019), new Datum(31,12,2019), 5);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		
		try {
			aMA.get(0).getAzk().addKrankheit(new Datum(3,3,2019), new Datum(6,3,2019), 4);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//******************** VERWALTUNG ********************
	
	//fuegt einen Mitarbeiter hinzu, komplette Angabe
	public void add(String name, String vorname, char gender, Datum geburtstag, Datum einstellung, int bereichsnummer, String user, String pwd) throws Exception {
	
		Zugehoerigkeit wo = new Zugehoerigkeit(einstellung,bereichsnummer,"Einstellung");
		aMA.add(new Mitarbeiter(personalnummer,name,vorname,gender,geburtstag,pwd,new User(personalnummer),einstellung,new Arbeitszeitkonto(),wo));
		personalnummer ++;
	}
	
	//fuegt einen Mitarbeiter hinzu, autogenerierter Username und Passwort
	public void add(String name, String vorname, char gender, Datum geburtstag, Datum einstellung, int bereichsnummer) throws Exception {
	
		// Beispiel Passwort: sH_715re
		// String passwort = vorname.substring(0,1).toLowerCase()+name.substring(0,1).toUpperCase()+"_"+einstellung.getMonat()+geburtstag.getTag()+vorname.charAt(vorname.length()/2)+name.charAt(name.length()/3);
		Zugehoerigkeit wo = new Zugehoerigkeit(einstellung,bereichsnummer,"Einstellung");
		aMA.add(new Mitarbeiter(personalnummer,name,vorname,gender,geburtstag,personalnummer+"",new User(personalnummer),einstellung,new Arbeitszeitkonto(),wo));
		personalnummer ++;
	}

	//Bereiche an Hand der uebergebenen Nummer suchen und loeschen
	public boolean delete(int nummer) {
		
		Mitarbeiter wen = (Mitarbeiter) suchen(nummer);
		if (wen != null) {
			aMA.remove(wen);
			return true;
		}
		return false;
	}
	
//******************** AUSGABE ********************	
	
	//Mitarbeiter anzeigen (Konsole) 
	public void show() {

		if (aMA.size()!=0) {
			for (int i = 0; i < aMA.size(); i++) {
				aMA.get(i).display();
			}	
		} else {
			System.out.println("Empty");
		}
	}

//******************** SORTIEREN & SUCHEN ********************
	
	//Mitarbeiterliste nach Name, Vorname sortieren
	public void sortName() {
		
		Collections.sort(aMA,new MitarbeiterNameComparator());
	}
	
	//Mitarbeiterliste nach Personalnummer sortieren
	public void sortNumber() {
		
		Collections.sort(aMA,new MitarbeiterNummerComparator());
	}
	
	//bekommt eine Personalnummer und durchsucht Mitarbeiterliste anhand dessen
	public Object suchen(int nummer) {
		
		if (aMA.size()!=0) {
			for (int i = 0; i < aMA.size(); i++) {
				if (aMA.get(i).getPersonalnummer() == nummer) {
					return aMA.get(i);
				}
			}
		}
		return null;
	}
	
//******************** LOAD & SAVE ********************
	//erzeugt ein Dateizugriff und uebergibt die zu speichernden Daten 
	public void speichern(String modus) throws Exception {
		
		Dateizugriff data = new Dateizugriff();
		data.speichern(aMA, modus);	
	}	
	
	//erzeugt ein Dateizugriff und laedt Daten in die Mitarbeiterliste
	public void laden(String modus) throws Exception {
			
		Dateizugriff data = new Dateizugriff();
		aMA = (ArrayList<Mitarbeiter>) data.laden(modus);
		personalnummer = aMA.get(aMA.size()-1).getPersonalnummer()+1;
	}
			
//******************** GETTER & SETTER ********************

	public static ArrayList<Mitarbeiter> getaMA() {
		return aMA;
	}

	public static void setaMA(ArrayList<Mitarbeiter> aMA) {
		Personalverwaltung.aMA = aMA;
	}
	
//******************** TESTUMGEBUNG **************************
	/*
	 * @author: Charly Spina
	 * @description: F�r die Tests, �berschreibt die vorhandene instance mit einer neuen
	 */
	public void resetPersonalverwaltung() {
		uniqueInstance = new Personalverwaltung();
	}

}
