package logik;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import comparatoren.*;
import extern.Datum;
import interfaces.*;
import speicher.Dateizugriff;


// SINGLETON!
public class Personalverwaltung implements VerwaltungIF,Serializable {
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private static Personalverwaltung uniqueInstance; 	
	private static int personalnummer;
	private static ArrayList <Mitarbeiter> aMA;
	
//******************** KONSTRUKTOR ********************

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
	
	
	private Personalverwaltung() {
		personalnummer = 0;
		aMA = new ArrayList <Mitarbeiter> ();
		start();
	}
	
	
	@Override
	public void start() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description:	fuegt Standardadmin bei der Ersterstellung ein 
		 */
		
		aMA.add(new Mitarbeiter(personalnummer, "admin", "nimda", 'd', new Datum(), "admin", "passwort", new Admin(personalnummer), new Datum(), new Arbeitszeitkonto(), new Zugehoerigkeit(new Datum(),0), new Default()));
		personalnummer ++;
		aMA.add(new Mitarbeiter(personalnummer, "user", "test", 'd', new Datum(), "user", "passwort", new User(personalnummer), new Datum(), new Arbeitszeitkonto(), new Zugehoerigkeit(new Datum(),0), new Default()));
		personalnummer ++;
	}
	
	
//******************** VERWALTUNG ********************
	
	public void add(String name, String vorname, char gender, int bday, int bmonth, int byear, int sday, int smonth, int syear, int bereichsnummer, String user, String pwd) throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description:	fuegt einen Mitarbeiter hinzu, komplette Angabe
		 */
	
		Datum birthday = new Datum(bday,bmonth,byear);
		Datum startday = new Datum(sday,smonth,syear);
		String username = user;
		String password = pwd;
		Zugehoerigkeit wo = new Zugehoerigkeit(startday,bereichsnummer);
		aMA.add(new Mitarbeiter(personalnummer,name,vorname,gender,birthday,username,password,new User(personalnummer),startday,new Arbeitszeitkonto(),wo, new Default()));
		personalnummer ++;
	}
	
	
	public void add(String name, String vorname, char gender, int bday, int bmonth, int byear, int sday, int smonth, int syear, int bereichsnummer) throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description:	fuegt einen Mitarbeiter hinzu, autogenerierter Username und Passwort
		 */
	
		Datum birthday = new Datum(bday,bmonth,byear);
		Datum startday = new Datum(sday,smonth,syear);
		// Beispiel Username: Hebestreit2
		String username = name+personalnummer;
		// Beispiel Passwort: sH_715re
		String password = vorname.substring(0,1).toLowerCase()+name.substring(0,1).toUpperCase()+"_"+smonth+bday+vorname.charAt(vorname.length()/2)+name.charAt(name.length()/3);
		Zugehoerigkeit wo = new Zugehoerigkeit(startday,bereichsnummer);
		aMA.add(new Mitarbeiter(personalnummer,name,vorname,gender,birthday,username,password,new User(personalnummer),startday,new Arbeitszeitkonto(),wo, new Default()));
		personalnummer ++;
	}


	@Override
	public boolean delete(int nummer) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description:	Bereiche an Hand der uebergebenen Nummer suchen und loeschen
		 */
		
		Mitarbeiter wen = (Mitarbeiter) suchen(nummer);
		if (wen != null) {
			aMA.remove(wen);
			return true;
		}
		return false;
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

	public static ArrayList<Mitarbeiter> getaMA() {
		return aMA;
	}


	public static void setaMA(ArrayList<Mitarbeiter> aMA) {
		Personalverwaltung.aMA = aMA;
	}

	
}
