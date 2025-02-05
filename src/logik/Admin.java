package logik;

import java.io.Serializable;
import java.util.ArrayList;
import extern.Datum;
/*@author: Soeren Hebestreit
 * @date: 18.07.2019
 * @description: Mitarbeiter hinzufuegen, mit Kennung
 */

public class Admin extends Berechtigung implements Serializable {

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int personalID;

//******************** KONSTRUKTOR ********************	

	public Admin(int personalnummer) {
		personalID = personalnummer;
	}

//******************** VERWALTUNG MITARBEITER ********************
	
	/* 
	 * @description: Mitarbeiter hinzufuegen, mit Kennung
	 */
	public void addMA(String name, String vorname, char gender, Datum geburtstag, Datum einstellung, int bereichsnummer,
			String pwd) throws Exception {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.add(name, vorname, gender, geburtstag, einstellung, bereichsnummer, pwd);
	}

	/*
	 * @description: Mitarbeiter hinzufuegen, ohne Kennung
	 */
	public void addMA(String name, String vorname, char gender, Datum geburtstag, Datum einstellung, int bereichsnummer)
			throws Exception {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.add(name, vorname, gender, geburtstag, einstellung, bereichsnummer);
	}

	/*
	 * @description: Mitarbeiterstammdaten bearbeiten (Name, Vorname, Geschlecht,
	 * Geburtstag)
	 */
	public boolean editMAStammdaten(int personalnummer, String name, String vorname, char gender, Datum geburtstag) {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.setName(name);
		ma.setVorname(vorname);
		ma.setGeburtsdatum(geburtstag);
		ma.setGeschlecht(gender);
		return true;
	}

	/*
	 * @description: Mitarbeiterpasswort bearbeiten
	 */
	public boolean editMAPasswort(int personalnummer, String passwort) {	

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.setPasswort(passwort);
		ma.setChangePasswort(true);
		return true;
	}

	/*
	 * @description: Mitarbeiterberechtigung zuruecksetzen oder als User wieder
	 * einsetzen angegebene Personalnummer darf nicht der angemeldeten entsprechen,
	 * um sich nicht auszusperren
	 */
	public boolean changeMABerechtigung(int personalnummer, Berechtigung berechtigung) {
		
		if (personalnummer == personalID) {
			return false;
		}

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.setBerechtigung(berechtigung);
		return true;
	}

	/*
	 * @description: Mitarbeitereinstellungsdatum bearbeiten
	 */
	public boolean editMAEinstellungsdatum(int personalnummer, Datum einstellung) {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.setEinstellungsdatum(einstellung);
		return true;
	}

	/*
	 * @description: Mitarbeiter nach ausgeschieden verschieben, Berechtigung loeschen,
	 * angegebene Personalnummer darf nicht der angemeldeten entsprechen,
	 * um sich nicht auszusperren
	 */
	public boolean ausscheidenMA(int personalnummer, Datum ausscheiden) {
		
		if (personalnummer == personalID) {
			return false;
		}

		Datum today = new Datum();
		if (today.compareTo(ausscheiden) < 0) {
			return false;
		}

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.setAusscheidungsdatum(ausscheiden);
		linkMAtoAB(personalnummer, 1, ausscheiden, "Ausscheiden");
		ma.setBerechtigung(null);
		return true;
	}

	/*
	 * @description: Mitarbeiter komplett entfernen, angegebene Personalnummer darf
	 * nicht der angemeldeten entsprechen, um sich nicht auszusperren
	 */
	public boolean removeMA(int personalnummer) {
		
		if (personalnummer == personalID) {
			return false;
		}

		Personalverwaltung pv = Personalverwaltung.getInstance();
		return pv.delete(personalnummer);
	}

//******************** VERWALTUNG ARBEITSZEITKONTEN ******************** 	

	/*
	 * @description: Vertragsdaten bzgl. AZK anpassen (Vertrag + Dienstvereinbarung)
	 */
	public boolean editAZKLimit(int personalnummer, int ueberminutenmin, int ueberminutenmax) {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}
		if (ueberminutenmin < 0) {
			ma.getAzk().setUeberminutenmin(ueberminutenmin);
		} else {
			ma.getAzk().setUeberminutenmin(-ueberminutenmin);
		}
		ma.getAzk().setUeberminutenmax(ueberminutenmax);
		return true;
	}
	
	/*
	 * @description: Vertragsdaten bzgl. AZK anpassen (Vertrag)
	 */
	public boolean editAZKVertragsdaten(int personalnummer, int sollstunden, int urlaubbasis) {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.getAzk().setSollstunden(sollstunden);
		ma.getAzk().setUrlaubbasis(urlaubbasis);
		return true;
	}
	
	/*
	 * @description: Ueberminuten hinzufuegen
	 */
	public boolean addAZKUeberminuten(int personalnummer, int betrag) {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		if (betrag < 0) {
			ma.getAzk().addMinus(-betrag);
		} else {
			ma.getAzk().addPlus(betrag);
		}
		return true;
	}

	/*
	 * @description: Urlaubseintrag erstellen
	 */
	public boolean addAZKUrlaub(int personalnummer, Datum start, Datum ende, int tage) throws Exception {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.getAzk().addUrlaub(start, ende, tage);
		return true;
	}

	/*
	 * @description: Krankheitseintrag erstellen
	 */
	public boolean addAZKKrankheit(int personalnummer, Datum start, Datum ende, int tage) throws Exception {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.getAzk().addKrankheit(start, ende, tage);
		return true;
	}

	/*
	 * @description: Urlaubseintrag loeschen
	 */
	public boolean removeAZKUrlaub(int personalnummer, int eintrag) throws Exception {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.getAzk().deleteUrlaub(eintrag);
		return true;
	}

	/*
	 * @description: Krankheitseintrag loeschen
	 */
	public boolean removeAZKKrankheit(int personalnummer, int eintrag) throws Exception {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}
			
		ma.getAzk().deleteKrankheit(eintrag);
		return true;
	}

//******************** VERWALTUNG ARBEITSBEREICHE ******************** 

	/*
	 * @description: Arbeitsbereich hinzufuegen
	 */
	public void addAB(String name, String beschreibung) {
		
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		abv.add(name, beschreibung);
	}

	/*
	 * @description: Arbeitsbereich suchen und Name aendern
	 */
	public boolean editABName(int arbeitsbereichnummer, String name) {
		
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		Arbeitsbereich bereich = (Arbeitsbereich) abv.suchen(arbeitsbereichnummer);
		if (bereich == null) {
			return false;
		} else {
			bereich.setName(name);
			return true;
		}
	}

	/*
	 * @description: Arbeitsbereich suchen und Beschreibung aendern
	 */
	public boolean editABBeschreibung(int arbeitsbereichnummer, String beschreibung) {
		
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		Arbeitsbereich bereich = (Arbeitsbereich) abv.suchen(arbeitsbereichnummer);
		if (bereich == null) {
			return false;
		} else {
			bereich.setBeschreibung(beschreibung);
			return true;
		}
	}

	/*
	 * @description: Arbeitsbereich loeschen, Bereich 0 und 1 sind geschuetzt
	 */
	public boolean delAB(int arbeitsbereichnummer) {
		
		if (arbeitsbereichnummer > 1) {
			Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
			return abv.delete(arbeitsbereichnummer);
		}
		return false;
	}

//******************** VERWALTUNG ZUGEHOERIGKEIT ******************** 

	/*
	 * @description: Mitarbeiter einem (neuen) Bereich zuordnen, mit Datumsangabe
	 */
	public boolean linkMAtoAB(int personalnummer, int arbeitsbereichnummer, Datum datum, String bemerkung) {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ArrayList<Zugehoerigkeit> zlist = ma.getZugehoerigkeit();
		if((arbeitsbereichnummer != 1)&&(ma.getActualAB().getArbeitsbereichnummer()==1)) {
			ma.setAusscheidungsdatum(null);
		}
		zlist.add(new Zugehoerigkeit(datum, arbeitsbereichnummer, bemerkung));
		ma.setZugehoerigkeit(zlist);
		return true;
	}

//******************** STATISTIKEN ABFRAGEN ********************	

	public void showStatistics(int position, int abteilungsnummer) {
		// TO DO
	}

//******************** FUNKTIONEN ********************	

	/*
	 * @description: Arbeitszeitkonto anzeigen, Urlaub aktuelles Jahr anzeigen
	 */
	public void showAZK() {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitszeitkonto azk = ((Mitarbeiter) pv.suchen(personalID)).getAzk();
		azk.display();
		System.out.println("\n--------Urlaub--------");
		azk.showUrlaub(new Datum().getJahr());
	}
	
	/*
	 *@description:	Passwort ändern, wenn altes übereinstimmt
	 */	
	public boolean changePasswort(String alt, String neu) {
				
		Mitarbeiter ma = (Mitarbeiter) Personalverwaltung.getInstance().suchen(personalID);
		if(ma.getPasswort().equals(alt)) {
			ma.setPasswort(neu);
			ma.setChangePasswort(false);
			return true;
		}
		return false;
	}

//******************** GETTER & SETTER ********************

	public int getPersonalID() {
		return personalID;
	}

}
