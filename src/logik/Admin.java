package logik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import extern.Datum;

public class Admin extends Berechtigung implements Serializable {

//******************** PARAMETER ********************
	
	private static final long serialVersionUID = 1L;
	private int personalID;
	
	
//******************** KONSTRUKTOR ********************	

	public Admin(int personalnummer) {
		personalID = personalnummer;
	}
	
	
//******************** VERWALTUNG MITARBEITER ********************
	
	public boolean changeBerechtigung(int personalnummer) {
		/* 
		 *@author:		Jakob Kuechler, Soeren Hebestreit
		 *@date: 		20.06.2019, 18.07.2019
		 *@description: bekommt Personalnummer (Integer) und aendert die Berechtigung des Mitarbeiters
		 *				in die bisher nicht vorhandene (Admin -> User und User -> Admin)
		 *				angegebene Personalnummer darf nicht der angemeldeten entsprechen, um sich nicht auszusperren
		 */
		
		if (personalnummer == personalID) {
			return false;
		}
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
		
		// MA Berechtigung neu setzen
		try {	
			if(ma.getBerechtigung() instanceof User) {
				ma.setBerechtigung(new Admin(personalnummer));
			} else {
				ma.setBerechtigung(new User(personalnummer));
			}
		} catch(NullPointerException e) {
			System.out.println("Mitarbeiternummer nicht vergeben");
			return false;
		}
		return true;
	}
	
	
	public void addMA(String name, String vorname, char gender, int bday, int bmonth, int byear, int sday, int smonth, int syear, int bereichsnummer, String user, String pwd) throws Exception{
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Mitarbeiter hinzufuegen, mit Kennung
		 */
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.add(name, vorname, gender, bday, bmonth, byear, sday, smonth, syear, bereichsnummer, user, pwd);
	}
	
	
	public void addMA(String name, String vorname, char gender, int bday, int bmonth, int byear, int sday, int smonth, int syear, int bereichsnummer) throws Exception{
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Mitarbeiter hinzufuegen, ohne Kennung
		 */
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.add(name, vorname, gender, bday, bmonth, byear, sday, smonth, syear, bereichsnummer);
	}
	
	
	public void editMAName(int personalnummer) {
		// TO DO
	}
	
	
	public void editMAVorname(int personalnummer) {
		// TO DO
	}
	
	
	public void editMAGeburtstag(int personalnummer) {
		// TO DO
	}
	
	
	public void editMAGender(int personalnummer) {
		// TO DO
	}
	
	
	public void editMAUsername(int personalnummer) {
		// TO DO
	}
	
	
	public void editMAPasswort(int personalnummer) {
		// TO DO
	}
	
	
	public void editMABerechtigung(int personalnummer) {
		// TO DO
	}
	
	
	public void editMAStartdatum(int personalnummer) {
		// TO DO
	}
	
	
	public void editMAEnddatum(int personalnummer) {
		// TO DO
	}
	
	
	public void editMAAzk(int personalnummer) {
		// TO DO
	}
	
	
	public void editMAStatus(int personalnummer) {
		// TO DO
	}
	
	
	public boolean delMA(int personalnummer, int eday, int emonth, int eyear) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Mitarbeiter nach ausgeschieden verschieben, Berechtigung etc. loeschen
		 *				angegebene Personalnummer darf nicht der angemeldeten entsprechen, um sich nicht auszusperren
		 */
		
		if (personalnummer == personalID) {
			return false;
		}
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
		
		// MA nicht existent
		if (ma == null) {
			return false;
		} 
		
		// MA existent, neu zuordnen nach ausgeschieden (Grundbereich 1)
		linkMAtoAB(personalnummer, 1, eday, emonth, eyear);
		
		// Ausscheidungsdatum setzen
		Datum endday = new Datum(eday, emonth, eyear);
		ma.setAusscheidungsdatum(endday);
		
		// wenn Ausscheidungsdatum in der Vergangenheit liegt, loesche AZK, Status und Berechtigung
		Datum today = new Datum();
		if (today.compareTo(endday) > 0) {
			resetMA(personalnummer);
		}
		return true;	
	}
	
	
	public boolean resetMA(int personalnummer) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Berechtigung etc. eines Mitarbeiters loeschen
		 *				angegebene Personalnummer darf nicht der angemeldeten entsprechen, um sich nicht auszusperren
		 */
		
		if (personalnummer == personalID) {
			return false;
		}
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
		
		// MA nicht existent
		if (ma == null) {
			return false;
		} 
		
		// MA existent
		ma.setAzk(null);
		ma.setBerechtigung(null);
		ma.setStatus(null);
		return true;	
	}
	
	
//******************** VERWALTUNG ARBEITSZEITKONTEN ******************** 	
	
	public void addUrlaub(int personalnummer, Date startDatum, Date endDatum) {
		// TO DO
	}
	
	public void rmUrlaub(int personalnummer, int auswahl) {
		// TO DO
	}
	
	public void changeSOLL(int personalnummer, int newSOLL) {
		// TO DO
	}
	
	public void changeDARF_Urlaub(int personalnummer, int newDARF) {
		// TO DO
	}
	
	
//******************** VERWALTUNG ARBEITSBEREICHE ******************** 
	
	public void addAB(String name, String beschreibung) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Arbeitsbereich hinzufuegen
		 */
		
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		abv.add(name, beschreibung);
	}
	
	
	public boolean editABName(int arbeitsbereichnummer, String name) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Arbeitsbereich suchen und Name aendern
		 */
		
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		Arbeitsbereich bereich = (Arbeitsbereich) abv.suchen(arbeitsbereichnummer);
		if (bereich == null) {
			return false;
		} else {
			bereich.setName(name);
			return true;
		}
	}
	
	
	public boolean editABBeschreibung(int arbeitsbereichnummer, String beschreibung) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Arbeitsbereich suchen und Beschreibung aendern
		 */
		
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		Arbeitsbereich bereich = (Arbeitsbereich) abv.suchen(arbeitsbereichnummer);
		if (bereich == null) {
			return false;
		} else {
			bereich.setBeschreibung(beschreibung);
			return true;
		}
	}
	
	
	public boolean delAB(int arbeitsbereichnummer) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Arbeitsbereich loeschen
		 */
		
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		return abv.delete(arbeitsbereichnummer);
	}
	
	
//******************** VERWALTUNG ZUGEHOERIGKEIT ******************** 
	
	public void linkMAtoAB(int personalnummer, int arbeitsbereichnummer) {
		/*@author:		Jakob Kuechler, Soeren Hebestreit
		 *@date: 		21.06.2019, 18.07.2019
		 *@description: Mitarbeiter einem (neuen) Bereich zuordnen, ohne Datumsangabe
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
		
		Zugehoerigkeit z = new Zugehoerigkeit(new Datum(), arbeitsbereichnummer);
		
		// Zugehoerigkeit hinzufuegen
		ArrayList<Zugehoerigkeit> zlist = ma.getZugehoerigkeit();
		zlist.add(z);
		ma.setZugehoerigkeit(zlist);
	}
	
	
	public void linkMAtoAB(int personalnummer, int arbeitsbereichnummer, int sday, int smonth, int syear) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Mitarbeiter einem (neuen) Bereich zuordnen, mit Datumsangabe
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
		
		Zugehoerigkeit z = new Zugehoerigkeit(new Datum(sday, smonth, syear), arbeitsbereichnummer);
		
		// Zugehoerigkeit hinzufuegen
		ArrayList<Zugehoerigkeit> zlist = ma.getZugehoerigkeit();
		zlist.add(z);
		ma.setZugehoerigkeit(zlist);
	}
	
	
//******************** STATISTIKEN ABFRAGEN ********************	
	
	public void showStatistics(int position, int abteilungsnummer) {
		// TO DO
	}
	
	
//******************** VERWALTUNG ARBEITSPLAENE ********************
	
	public void addArbeitsplan(int kw, int abteilungsnummer) {
		// TO DO
	}
	
	public void rmArbeitsplan(int kw, int abteilungsnummer) {
		// TO DO
	}
	
	public void addMAtoSchicht(int kw, int abteilungsnummer, Schicht schicht) {
		// TO DO
	}
	
	public void rmMA(int kw, int abteilungsnummer, Schicht schicht) {
		// TO DO
	}

	
//******************** GETTER & SETTER ********************
	
	public int getPersonalID() {
		return personalID;
	}


//	public void setPersonalID(int personalID) {
//		this.personalID = personalID;
//	}
	
	
}
