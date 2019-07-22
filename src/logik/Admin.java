package logik;

import java.io.Serializable;
import java.util.ArrayList;
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
	
	public void addMA(String name, String vorname, char gender, Datum geburtstag, Datum einstellung, int bereichsnummer, String user, String pwd) throws Exception{
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Mitarbeiter hinzufuegen, mit Kennung
		 */
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.add(name, vorname, gender, geburtstag, einstellung, bereichsnummer, user, pwd);
	}
	
	
	public void addMA(String name, String vorname, char gender, Datum geburtstag, Datum einstellung, int bereichsnummer) throws Exception{
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Mitarbeiter hinzufuegen, ohne Kennung
		 */
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.add(name, vorname, gender, geburtstag, einstellung, bereichsnummer);
	}
	
	
	public boolean editMAStammdaten(int personalnummer, String name, String vorname, char gender, Datum geburtstag) {
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Mitarbeiterstammdaten bearbeiten (Name, Vorname, Geschlecht, Geburtstag)
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false;
				
		// MA existent
		ma.setName(name);
		ma.setVorname(vorname);
		ma.setGeburtsdatum(geburtstag);
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
	
	
	public boolean changeMABerechtigung(int personalnummer) {
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
	
	
	public boolean editMAEinstellungsdatum(int personalnummer, Datum einstellung) {
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
		ma.setEinstellungsdatum(einstellung);
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
	
	
	public boolean deleteMA(int personalnummer, Datum ausscheiden) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Mitarbeiter nach ausgeschieden verschieben, Berechtigung etc. loeschen
		 *				angegebene Personalnummer darf nicht der angemeldeten entsprechen, um sich nicht auszusperren
		 */
		
		if (personalnummer == personalID) return false;
		
		// Loeschen nur moeglich, falls Ausscheiden in der Vergangenheit
		Datum today = new Datum();
		if (today.compareTo(ausscheiden) < 1) return false;
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
		
		// MA nicht existent
		if (ma == null) return false; 
		
		// MA existent, Ausscheidungsdatum setzen, neu zuordnen Grundbereich 1 - ausgeschieden, Reset 
		ma.setAusscheidungsdatum(ausscheiden);
		linkMAtoAB(personalnummer, 1, ausscheiden);
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
	
	public boolean editAZKVertragsdaten(int personalnummer, int sollstunden, int urlaubbasis, int ueberminutenmin, int ueberminutenmax) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Vertragsdaten bzgl. AZK anpassen 
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false; 
				
		// MA existent
		ma.getAzk().setSollstunden(sollstunden);
		ma.getAzk().setUrlaubbasis(urlaubbasis);
		ma.getAzk().setUeberminutenmin(ueberminutenmin);
		ma.getAzk().setUeberminutenmax(ueberminutenmax);
		return true;	
	}
	
	
	public boolean addAZKUeberminuten(int personalnummer, int betrag) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Ueberminuten hinzufuegen 
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false; 
				
		// MA existent
		if (betrag < 0) {
			ma.getAzk().addPlus(betrag);
		} else {
			ma.getAzk().addMinus(betrag);
		}
		return true;	
	}
	
	
	public boolean starteAZKJahr(int personalnummer) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Ueberminuten hinzufuegen 
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false; 
				
		// MA existent
		ma.getAzk().neuesJahr();
		return true;	
	}
	
	
	public boolean editAZKUrlaubsberechnung(int personalnummer, int urlaubskontingent, int urlaubgenommen) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Kontingent und genommenen Urlaub bearbeiten (Noteingriff falls z.B. voreilig neues AZK-Jahr ausgefuehrt wurde) 
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false; 
				
		// MA existent
		ma.getAzk().setUrlaubskontingent(urlaubskontingent);
		ma.getAzk().setUrlaubgenommen(urlaubgenommen);
		return true;	
	}
	
	
	public boolean addAZKUrlaub(int personalnummer, Datum start, Datum ende, int tage) throws Exception {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Urlaubseintrag erstellen 
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false; 
				
		// MA existent
		ma.getAzk().addUrlaub(start, ende, tage);
		return true;
	}
	
	
	public boolean addAZKKrankheit(int personalnummer, Datum start, Datum ende, int tage) throws Exception {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Krankheitseintrag erstellen 
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false; 
				
		// MA existent
		ma.getAzk().addKrankheit(start, ende, tage);
		return true;
	}
	
	
	public boolean removeAZKUrlaub(int personalnummer, int eintrag) throws Exception {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Urlaubseintrag loeschen 
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false; 
				
		// MA existent
		ma.getAzk().deleteUrlaub(eintrag);
		return true;
	}
	
	
	public boolean removeAZKKrankheit(int personalnummer, int eintrag) throws Exception {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Krankheitseintrag loeschen 
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
				
		// MA nicht existent
		if (ma == null) return false; 
				
		// MA existent
		ma.getAzk().deleteKrankheit(eintrag);
		return true;
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
		
		if(arbeitsbereichnummer == 1) {
			ma.setAusscheidungsdatum(new Datum());
		}
	}
	
	
	public void linkMAtoAB(int personalnummer, int arbeitsbereichnummer, Datum datum) {
		/*@author:		Soeren Hebestreit
		 *@date: 		18.07.2019
		 *@description: Mitarbeiter einem (neuen) Bereich zuordnen, mit Datumsangabe
		 */
		
		// MA suchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
		
		Zugehoerigkeit z = new Zugehoerigkeit(datum, arbeitsbereichnummer);
		
		// Zugehoerigkeit hinzufuegen
		ArrayList<Zugehoerigkeit> zlist = ma.getZugehoerigkeit();
		zlist.add(z);
		ma.setZugehoerigkeit(zlist);
		
		if(arbeitsbereichnummer == 1) {
			ma.setAusscheidungsdatum(datum);
		}
	}
	
	
//******************** STATISTIKEN ABFRAGEN ********************	
	
	public void showStatistics(int position, int abteilungsnummer) {
		// TO DO
	}
	
	
//******************** FUNKTIONEN ********************	
	
	public void showAZK() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Arbeitszeitkonto anzeigen, Urlaub aktuelles Jahr anzeigen
		 */
			
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitszeitkonto azk = ((Mitarbeiter) pv.suchen(personalID)).getAzk();		
		azk.display();
		System.out.println("\n--------Urlaub--------");
		azk.showUrlaub(new Datum().getJahr());
	}
	
	
//******************** GETTER & SETTER ********************
	
	public int getPersonalID() {
		return personalID;
	}


//	public void setPersonalID(int personalID) {
//		this.personalID = personalID;
//	}
	
	
}
