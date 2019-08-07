package logik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import comparatoren.MitarbeiterNameComparator;
import comparatoren.MitarbeiterNummerComparator;
import extern.Datum;
import interfaces.VerwaltungIF;
import speicher.Dateizugriff;

public class Personalverwaltung implements VerwaltungIF,Serializable {
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private static Personalverwaltung uniqueInstance; 	
	private static int personalnummer;
	private static ArrayList <Mitarbeiter> aMA;
	private static String path = "DataPV.ppvs";
		
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
		personalnummer = 100000;
		aMA = new ArrayList <Mitarbeiter> ();
		start();
	}
	
	@Override
	public void start() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description:	fuegt Standardadmin bei der Ersterstellung hinzu 
		 */
		
		aMA.add(new Mitarbeiter(personalnummer, "admin", "admin", 'd', new Datum(), "admin", new Admin(personalnummer), new Datum(), new Arbeitszeitkonto(), new Zugehoerigkeit(new Datum(),0,"Standardadmin anlegen")));
		personalnummer ++;
	}
	
//******************** VERWALTUNG ********************
	
	public void add(String name, String vorname, char gender, Datum geburtstag, Datum einstellung, int bereichsnummer, String pwd) throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description:	fuegt einen Mitarbeiter hinzu, komplette Angabe
		 */
	
		Zugehoerigkeit wo = new Zugehoerigkeit(einstellung,bereichsnummer,"Einstellung");
		aMA.add(new Mitarbeiter(personalnummer,name,vorname,gender,geburtstag,pwd,new User(personalnummer),einstellung,new Arbeitszeitkonto(),wo));
		personalnummer ++;
	}
	
	public void add(String name, String vorname, char gender, Datum geburtstag, Datum einstellung, int bereichsnummer) throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description:	fuegt einen Mitarbeiter hinzu, autogenerierter Username und Passwort
		 */
	
		// Beispiel Passwort: sH_715re
		// String passwort = vorname.substring(0,1).toLowerCase()+name.substring(0,1).toUpperCase()+"_"+einstellung.getMonat()+geburtstag.getTag()+vorname.charAt(vorname.length()/2)+name.charAt(name.length()/3);
		Zugehoerigkeit wo = new Zugehoerigkeit(einstellung,bereichsnummer,"Einstellung");
		aMA.add(new Mitarbeiter(personalnummer,name,vorname,gender,geburtstag,personalnummer+"",new User(personalnummer),einstellung,new Arbeitszeitkonto(),wo));
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
	
	public void setModus(String modus) {
		Dateizugriff data = Dateizugriff.getInstance();
		data.setDatenBank(modus);
	}
	
	@Override
	public void speichern() throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	erzeugt ein Dateizugriff und uebergibt die zu speichernden Daten 
		 */
		
		Dateizugriff data = Dateizugriff.getInstance();
		data.speichern(aMA);	
	}	
		
	@Override
	public void laden() throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	erzeugt ein Dateizugriff und laedt Daten in die Mitarbeiterliste
		 */
			
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
	
	public void resetPersonalverwaltung() {
		uniqueInstance = new Personalverwaltung();
	}

}
