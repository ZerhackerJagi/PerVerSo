package logik;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

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
		int personalnummer = createPersonalnummer();
		int abteilungsnummer = 0;
		aktiveMA[0] = new Mitarbeiter("minda","admin","passwort",'d',new Date(),new Admin(),new Default(),new Zugehoerigkeit(new Date(),personalnummer,abteilungsnummer), personalnummer);
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
		
		return personalnummer+1;
	}
	
	
	// ÖFFENTLICHE METHODEN
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void create() {
		// 
		createMAonTerminal();
		
	}
	
	
	public void add(String name,String vorname,String passwort,char gender,int bdayyear,int bdaymonth,int bdayday) throws Exception {
		Date bday = new Date(bdayyear,bdaymonth,bdayday);
		int personalnummer = createPersonalnummer();
		int abteilungsnummer = 0;
		aMA.add(new Mitarbeiter(name, vorname, passwort, gender, bday, new User(),new Default(), new Zugehoerigkeit(new Date(), personalnummer, abteilungsnummer), personalnummer));
	}
	public void display () {
		// alle Konten anzeigen
		
		if (aMA.size()!=0) {
			for (int i = 0; i < aMA.size(); i++) {
				System.out.println(aMA.get(i));
				
			}	
		} else {
			System.out.println("Empty");
		}
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

	

	@Override
	public Object suchen(int nummer) {
		/*@author: 		Jakob Küchler
		 *@date: 		20.06.2019
		 *@description:	Bekommt eine Personalnummer und durchsucht aktiveMA anhand dessen.
		 */
		
		for(int i=0; i<aktiveMA.length;i++) {
			if(aktiveMA[i].getPersonalnummer() == nummer) {
				return aktiveMA[i];
			}
		}
		return null;
	}
	
	public void show() {
		/*@author: 		Jakob Küchler
		 *@date: 		20.06.2019
		 *@description:	Zeigt alle MA der Liste aktiveMA an. 
		 */
		
		for(int i = 0; i<aktiveMA.length;i++) {
			System.out.println("Mitarbeiter: "+aktiveMA[i]);
		}
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


	@Override
	public void sortName() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sortNumber() {
		// TODO Auto-generated method stub
		
	}

	
}
