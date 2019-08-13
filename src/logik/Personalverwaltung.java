package logik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import comparatoren.MitarbeiterNameComparator;
import comparatoren.MitarbeiterNummerComparator;
import extern.Datum;
import interfaces.VerwaltungIF;
import speicher.Dateizugriff;
/*@author: 		Soeren Hebestreit
 *@date: 		20.06.2019
 *@description:	
 */
public class Personalverwaltung implements VerwaltungIF,Serializable {
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private static Personalverwaltung uniqueInstance; 	
	private static int personalnummer;
	private static ArrayList <Mitarbeiter> aMA;
	private static String path = "DataPV.pvd";
		
//******************** KONSTRUKTOR ********************
	
	/*@author: 		Jakob Kuechler
	 *@date: 		20.06.2019
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
	
	/*
	 *@description:	fuegt Standardadmin bei der Ersterstellung hinzu 
	 */
	public void start() {
			
		aMA.add(new Mitarbeiter(personalnummer, "admin", "default", 'd', new Datum(), "admin", new Admin(personalnummer), new Datum(), new Arbeitszeitkonto(0,0,0,0), new Zugehoerigkeit(new Datum(),0,"Standardadmin anlegen")));
		personalnummer ++;
	}
	
//******************** VERWALTUNG ********************
	
	/*
	 *@description:	fuegt einen Mitarbeiter hinzu, komplette Angabe
	 */
	public void add(String name, String vorname, char gender, Datum geburtstag, Datum einstellung, int bereichsnummer, String pwd) throws Exception {
		
		Zugehoerigkeit wo = new Zugehoerigkeit(einstellung,bereichsnummer,"Einstellung");
		aMA.add(new Mitarbeiter(personalnummer,name,vorname,gender,geburtstag,pwd,new User(personalnummer),einstellung,new Arbeitszeitkonto(),wo));
		personalnummer ++;
	}
	
	/*
	 *@description:	fuegt einen Mitarbeiter hinzu, autogenerierter Username und Passwort
	 */
	public void add(String name, String vorname, char gender, Datum geburtstag, Datum einstellung, int bereichsnummer) throws Exception {
		
		// Beispiel Passwort: sH_715re
		// String passwort = vorname.substring(0,1).toLowerCase()+name.substring(0,1).toUpperCase()+"_"+einstellung.getMonat()+geburtstag.getTag()+vorname.charAt(vorname.length()/2)+name.charAt(name.length()/3);
		Zugehoerigkeit wo = new Zugehoerigkeit(einstellung,bereichsnummer,"Einstellung");
		aMA.add(new Mitarbeiter(personalnummer,name,vorname,gender,geburtstag,personalnummer+"",new User(personalnummer),einstellung,new Arbeitszeitkonto(),wo));
		personalnummer ++;
	}

	/*
	 *@description:	Bereiche an Hand der uebergebenen Nummer suchen und loeschen
	 */
	public boolean delete(int nummer) {
		
		Mitarbeiter wen = (Mitarbeiter) suchen(nummer);
		if (wen != null) {
			aMA.remove(wen);
			return true;
		}
		return false;
	}
	
//******************** AUSGABE ********************	
	
	/*
	 *@description:	Mitarbeiter anzeigen (Konsole) 
	 */
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
	
	/*
	 *@description:	Mitarbeiterliste nach Name, Vorname sortieren
	 */
	public void sortName() {
	
		Collections.sort(aMA,new MitarbeiterNameComparator());
	}

	/*
	 *@description:	Mitarbeiterliste nach Personalnummer sortieren
	 */
	public void sortNumber() {
		
		Collections.sort(aMA,new MitarbeiterNummerComparator());
	}
	
	/*
	 *@description:	bekommt eine Personalnummer und durchsucht Mitarbeiterliste anhand dessen
	 */
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
		data.speichern(aMA);	
	}	
		
	/*
	 *@description:	erzeugt ein Dateizugriff und laedt Daten in die Mitarbeiterliste
	 */
	public void laden() throws Exception {
			
		Dateizugriff data = Dateizugriff.getInstance();
		aMA = (ArrayList<Mitarbeiter>) data.laden();
		personalnummer = aMA.get(aMA.size()-1).getPersonalnummer()+1;
	}
			
//******************** GETTER & SETTER ********************

	public static ArrayList<Mitarbeiter> getaMA() {
		return aMA;
	}

	public static void setaMA(ArrayList<Mitarbeiter> aMA) {
		Personalverwaltung.aMA = aMA;
	}
	
	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		Personalverwaltung.path = path;
	}
	
//******************** TESTUMGEBUNG **************************
	
	/*
	 * @author: 		Charly Spina
	 */
	public void resetPersonalverwaltung() {
		uniqueInstance = new Personalverwaltung();
	}

}
