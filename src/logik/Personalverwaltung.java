package logik;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import comparatoren.*;
import extern.Datum;
import gui.*;
import interfaces.*;
import speicher.Dateizugriff;


// SINGLETON!
public class Personalverwaltung implements VerwaltungIF,Serializable {

	// PRIVAT
	private static final long serialVersionUID = 1L;
	private static Mitarbeiter[] aktiveMA; //MA Liste
	private static ArrayList <Mitarbeiter> aMA;
	private static Personalverwaltung uniqueInstance; //Einzigartige Instanz
	private static int personalnummer;
	
	
	


	public static Personalverwaltung getInstance() {
		/*@author: 		Jakob Kuechler
		 *@date: 		20.06.2019
		 *@description:	Gibt die einzige Instanz von Personalverwaltung aus (Singleton)
		 */
			
		if(uniqueInstance == null) {
			uniqueInstance = new Personalverwaltung();
		}
		return uniqueInstance;
	}
	
	
	// KONSTRUKTOR
	private Personalverwaltung() {
		aktiveMA = new Mitarbeiter[1];
		aktiveMA[0] = new Mitarbeiter(createPersonalnummer(),"nimda","admin",'d',new Datum(),"admin","passwort",new Admin(),new Datum(),new Arbeitszeitkonto(),new Zugehoerigkeit(new Datum(),0), new Default());
		aMA = new ArrayList <Mitarbeiter> ();
		
	}
	
	// PRIVATE METHODEN (HILFSMITTEL)
	
	private void extendListAktiveMA() {
		/*@author: 		Jakob Küchler
		 *@date: 		20.06.2019
		 *@description:	Erweitert die Liste aktiveMA um ein Feld 
		 */
		Mitarbeiter[] newList =	new Mitarbeiter[(aktiveMA.length+1)];
		System.arraycopy(aktiveMA, 0, newList, 0, aktiveMA.length);
		this.aktiveMA = newList;
	}
	
	private int createPersonalnummer() {
		/*@author: 		Jakob Küchler
		 *@date: 		20.06.2019
		 *@description:	Erstellt automatisiert eine Personalnummer und gibt diese zurück. 
		 */
		
		return personalnummer++;
	}
	
	
	// ÖFFENTLICHE METHODEN
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		new Hauptmenue();
	}
	

	@Override
	public void create() {
		// 
		createMAonTerminal();
		
	}
	
	
	public void add(String name,String vorname,char gender,int day,int month,int year) throws Exception {
		Datum bday = new Datum(day,month,year);
		aMA.add(new Mitarbeiter(createPersonalnummer(),name,vorname,gender,bday,"user","passwort",new User(),new Datum(),new Arbeitszeitkonto(),new Zugehoerigkeit(new Datum(),0), new Default()));
	}
	
	
	
	private void createMAonTerminal() {
		/*@author: 		Jakob Küchler
		 *@date: 		20.06.2019
		 *@description:	Fügt neuen Mitarbeiter per Terminal hinzu. Benötigt mehrere Eingaben durch User. Standardberechtigung: User 
		 */
		
		// Liste um 1 Mitarbeiter erweitern
		extendListAktiveMA();
		
		// Abfrage essentieller Infos für Konstruktor
		try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Nachname: ");
		String name = br.readLine();
		System.out.print("Vorname: ");
		String vorname = br.readLine();
		System.out.print("Passwort eingeben: ");
		String passwort = br.readLine();
		System.out.print("Geschlecht (m/w/d): ");
		String eingabe = br.readLine();
		char gender = eingabe.charAt(0);
		System.out.print("Geburtsjahr: ");
		int bdayyear = Integer.parseInt(br.readLine());
		System.out.print("Geburtsmonat: ");
		int bdaymonth = Integer.parseInt(br.readLine());
		System.out.print("Geburtstag: ");
		int bdayday = Integer.parseInt(br.readLine());
		Date bday = new Date(bdayyear,bdaymonth,bdayday);
		
		//String name, String vorname, String passwort, char geschlecht, Date geburtstag, Berechtigung berechtigung, Status status, Zugehoerigkeit zugehoerigkeit)		
		//aktiveMA[aktiveMA.length-1] = new Mitarbeiter(name, vorname, passwort, gender, bday, new User(),new Default(), new ArrayList<Zugehoerigkeit>(), createPersonalnummer());
	}catch (Exception e) {
		}
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	

//******************** AUSGABE ********************	
	
	public void show() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		22.06.2019
		 *@description:	Mitarbeiter anzeigen (Konsole) 
		 */
		
		if (aMA.size()!=0) {
			for (int i = 0; i < aMA.size(); i++) {
				aMA.get(i).display();
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
		 *@description:	Mitarbeiterliste nach Name, Vorname sortieren
		 */
		
		Collections.sort(aMA,new MitarbeiterNameComparator());
	}


	@Override
	public void sortNumber() {
		// TODO Auto-generated method stub
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Mitarbeiterliste nach Personalnummer sortieren
		 */
		
		Collections.sort(aMA,new MitarbeiterNummerComparator());
	}
	
	
	@Override
	public Object suchen(int nummer) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		22.06.2019
		 *@description:	bekommt eine Personalnummer und durchsucht Mitarbeiterliste anhand dessen
		 */
		
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
	
	@Override
	public void speichern() throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	erzeugt ein Dateizugriff und uebergibt die zu speichernden Daten 
		 */
		
		Dateizugriff data = new Dateizugriff();
		data.speichern(aMA);	
	}
		
		
	@Override
	public void laden() throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	erzeugt ein Dateizugriff und laedt Daten in die Mitarbeiterliste
		 */
			
		Dateizugriff data = new Dateizugriff();
		aMA = (ArrayList<Mitarbeiter>) data.laden();
	}
			

//******************** GETTER & SETTER ********************
	
	public static Mitarbeiter[] getAktiveMA() {
		return aktiveMA;
	}


	public static void setAktiveMA(Mitarbeiter[] aktiveMA) {
		Personalverwaltung.aktiveMA = aktiveMA;
	}


	public static ArrayList<Mitarbeiter> getaMA() {
		return aMA;
	}


	public static void setaMA(ArrayList<Mitarbeiter> aMA) {
		Personalverwaltung.aMA = aMA;
	}

	
}
