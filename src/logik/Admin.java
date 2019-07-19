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
	
	
	public boolean editMAName(int personalnummer, String name) {
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Mitarbeitername bearbeiten
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false;
				
		// MA existent
		ma.setName(name);
		return true;
	}
	
	
	public boolean editMAVorname(int personalnummer, String vorname) {
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Mitarbeitervorname bearbeiten
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false;
				
		// MA existent
		ma.setVorname(vorname);
		return true;
	}
	
	
	public boolean editMAGeburtstag(int personalnummer, int day, int month, int year) {
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Mitarbeitergeburtsdatum bearbeiten
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false;
				
		// MA existent
		ma.setGeburtsdatum(new Datum(day, month, year));
		return true;
	}
	
	
	public boolean editMAGeschlecht(int personalnummer, char gender) {
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Mitarbeitergeschlecht bearbeiten
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false;
				
		// MA existent
		ma.setGeschlecht(gender);
		return true;
	}
	
	
	public boolean editMABenutzername(int personalnummer, String benutzername) {
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Mitarbeiterkennung bearbeiten
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false;
				
		// MA existent
		ma.setBenutzername(benutzername);
		return true;
	}
	
	
	public boolean editMAPasswort(int personalnummer, String passwort) {
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Mitarbeiterpasswort bearbeiten
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false;
				
		// MA existent
		ma.setPasswort(passwort);
		return true;
	}
	
	
	public boolean resetMABerechtigung(int personalnummer) {
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Mitarbeiterberechtigung zuruecksetzen oder als User wieder einsetzen 
		 *				angegebene Personalnummer darf nicht der angemeldeten entsprechen, um sich nicht auszusperren
		 */
		
		if (personalnummer == personalID) return false;
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false;
				
		// MA existent
		if(ma.getBerechtigung() == null) {
			ma.setBerechtigung(new User(personalnummer));
		} else {
			ma.setBerechtigung(null);
		}
		return true;
	}
	
	
	public boolean changeBerechtigung(int personalnummer) {
		/* 
		 *@author:		Jakob Kuechler, Soeren Hebestreit
		 *@date: 		20.06.2019, 18.07.2019
		 *@description: bekommt Personalnummer (Integer) und aendert die Berechtigung des Mitarbeiters
		 *				in die bisher nicht vorhandene (Admin -> User und User -> Admin)
		 *				angegebene Personalnummer darf nicht der angemeldeten entsprechen, um sich nicht auszusperren
		 */
		
		if (personalnummer == personalID) return false;
		
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
			//System.out.println("Mitarbeiternummer nicht vergeben");
			return false;
		}
		return true;
	}
	
	
	public boolean editMAEinstellungsdatum(int personalnummer, int day, int month, int year) {
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Mitarbeitereinstellungsdatum bearbeiten
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false;
				
		// MA existent
		ma.setEinstellungsdatum(new Datum(day, month, year));
		return true;
	}
	
	
	public boolean editMAAusscheidungsdatum(int personalnummer, int day, int month, int year) {
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Mitarbeiterausscheidungsdatum bearbeiten
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false;
				
		// MA existent
		Datum endday = new Datum(day, month, year);
		ma.setAusscheidungsdatum(endday);
		
		// falls MA bereits in Grundbereich 1 - ausgeschieden verschoben wurde dort das Startdatum aendern
		Zugehoerigkeit last = ma.getActualAB();
		if (last.getArbeitsbereichnummer() == 1) {
			last.setStart(endday);
		}
		return true;
	}
	
	
	public boolean resetMAAzk(int personalnummer) {
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Mitarbeiter-AZK zuruecksetzen oder wieder einsetzen (leer)
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false;
				
		// MA existent
		if(ma.getAzk() == null) {
			ma.setAzk(new Arbeitszeitkonto());
		} else {
			ma.setAzk(null);
		}
		return true;
	}
	
	
	public boolean editMAStatus(int personalnummer, Statustyp status) {
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Mitarbeiter-AZK zuruecksetzen oder wieder einsetzen (leer)
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false;
				
		// MA existent
		ma.setStatus(status);
		return true;
	}
	
	
	public boolean deleteMA(int personalnummer, int day, int month, int year) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Mitarbeiter nach ausgeschieden verschieben, Berechtigung etc. loeschen
		 *				angegebene Personalnummer darf nicht der angemeldeten entsprechen, um sich nicht auszusperren
		 */
		
		if (personalnummer == personalID) return false;
		
		// Loeschen nur moeglich, falls Ausscheiden in der Vergangenheit
		Datum today = new Datum();
		Datum endday = new Datum(day, month, year);
		if (today.compareTo(endday) < 1) return false;
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
		
		// MA nicht existent
		if (ma == null) return false; 
		
		// MA existent, Ausscheidungsdatum setzen, neu zuordnen Grundbereich 1 - ausgeschieden, Reset 
		ma.setAusscheidungsdatum(endday);
		linkMAtoAB(personalnummer, 1, day, month, year);
		resetMA(personalnummer);
		return true;	
	}
	
	
	public boolean resetMA(int personalnummer) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Berechtigung etc. eines Mitarbeiters loeschen
		 *				angegebene Personalnummer darf nicht der angemeldeten entsprechen, um sich nicht auszusperren
		 */
		
		if (personalnummer == personalID) return false;
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
		
		// MA nicht existent
		if (ma == null) return false; 
		
		// MA existent
		ma.setAzk(null);
		ma.setBerechtigung(null);
		ma.setStatus(null);
		return true;	
	}
	
	
	public boolean removeMA(int personalnummer) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Mitarbeiter komplett entfernen
		 *				angegebene Personalnummer darf nicht der angemeldeten entsprechen, um sich nicht auszusperren
		 */
		
		if (personalnummer == personalID) return false;
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		return pv.delete(personalnummer);	
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
		 *@description: Arbeitsbereich loeschen, Bereich 0 und 1 sind geschuetzt
		 */
		
		if (arbeitsbereichnummer > 1) {
			Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
			return abv.delete(arbeitsbereichnummer);
		}
		return false;
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
