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

	public void addMA(String name, String vorname, char gender, Datum geburtstag, Datum einstellung, int bereichsnummer,
			String user, String pwd) throws Exception {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Mitarbeiter hinzufuegen, mit Kennung
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.add(name, vorname, gender, geburtstag, einstellung, bereichsnummer, user, pwd);
	}

	public void addMA(String name, String vorname, char gender, Datum geburtstag, Datum einstellung, int bereichsnummer)
			throws Exception {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Mitarbeiter hinzufuegen, ohne Kennung
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.add(name, vorname, gender, geburtstag, einstellung, bereichsnummer);
	}

	public boolean editMAStammdaten(int personalnummer, String name, String vorname, char gender, Datum geburtstag) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 19.07.2019
		 * 
		 * @description: Mitarbeiterstammdaten bearbeiten (Name, Vorname, Geschlecht,
		 * Geburtstag)
		 */

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

	public boolean editMABenutzername(int personalnummer, String benutzername) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 19.07.2019
		 * 
		 * @description: Mitarbeiterkennung bearbeiten
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.setBenutzername(benutzername);
		return true;
	}

	public boolean editMAPasswort(int personalnummer, String passwort) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 19.07.2019
		 * 
		 * @description: Mitarbeiterpasswort bearbeiten
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.setPasswort(passwort);
		return true;
	}

	public boolean changeMABerechtigung(int personalnummer, Berechtigung berechtigung) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 24.07.2019
		 * 
		 * @description: Mitarbeiterberechtigung zuruecksetzen oder als User wieder
		 * einsetzen angegebene Personalnummer darf nicht der angemeldeten entsprechen,
		 * um sich nicht auszusperren
		 */

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

	public boolean editMAEinstellungsdatum(int personalnummer, Datum einstellung) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 19.07.2019
		 * 
		 * @description: Mitarbeitereinstellungsdatum bearbeiten
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.setEinstellungsdatum(einstellung);
		return true;
	}

	public boolean ausscheidenMA(int personalnummer, Datum ausscheiden) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Mitarbeiter nach ausgeschieden verschieben, Berechtigung loeschen,
		 * angegebene Personalnummer darf nicht der angemeldeten entsprechen,
		 * um sich nicht auszusperren
		 */

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

	public boolean removeMA(int personalnummer) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Mitarbeiter komplett entfernen, angegebene Personalnummer darf
		 * nicht der angemeldeten entsprechen, um sich nicht auszusperren
		 */

		if (personalnummer == personalID) {
			return false;
		}

		Personalverwaltung pv = Personalverwaltung.getInstance();
		return pv.delete(personalnummer);
	}

//******************** VERWALTUNG ARBEITSZEITKONTEN ******************** 	

	public boolean editAZKLimit(int personalnummer, int ueberminutenmin, int ueberminutenmax) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Vertragsdaten bzgl. AZK anpassen (Vertrag + Dienstvereinbarung)
		 */

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
	
	public boolean editAZKVertragsdaten(int personalnummer, int sollstunden, int urlaubbasis) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 22.07.2019
		 * 
		 * @description: Vertragsdaten bzgl. AZK anpassen (Vertrag)
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.getAzk().setSollstunden(sollstunden);
		ma.getAzk().setUrlaubbasis(urlaubbasis);
		return true;
	}
	
	public boolean addAZKUeberminuten(int personalnummer, int betrag) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Ueberminuten hinzufuegen
		 */

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

	public boolean addAZKUrlaub(int personalnummer, Datum start, Datum ende, int tage) throws Exception {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Urlaubseintrag erstellen
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.getAzk().addUrlaub(start, ende, tage);
		return true;
	}

	public boolean addAZKKrankheit(int personalnummer, Datum start, Datum ende, int tage) throws Exception {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Krankheitseintrag erstellen
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.getAzk().addKrankheit(start, ende, tage);
		return true;
	}

	public boolean removeAZKUrlaub(int personalnummer, int eintrag) throws Exception {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Urlaubseintrag loeschen
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ma.getAzk().deleteUrlaub(eintrag);
		return true;
	}

	public boolean removeAZKKrankheit(int personalnummer, int eintrag) throws Exception {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Krankheitseintrag loeschen
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}
			
		ma.getAzk().deleteKrankheit(eintrag);
		return true;
	}

//******************** VERWALTUNG ARBEITSBEREICHE ******************** 

	public void addAB(String name, String beschreibung) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Arbeitsbereich hinzufuegen
		 */

		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		abv.add(name, beschreibung);
	}

	public boolean editABName(int arbeitsbereichnummer, String name) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Arbeitsbereich suchen und Name aendern
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
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Arbeitsbereich suchen und Beschreibung aendern
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
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Arbeitsbereich loeschen, Bereich 0 und 1 sind geschuetzt
		 */

		if (arbeitsbereichnummer > 1) {
			Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
			return abv.delete(arbeitsbereichnummer);
		}
		return false;
	}

//******************** VERWALTUNG ZUGEHOERIGKEIT ******************** 

	public boolean linkMAtoAB(int personalnummer, int arbeitsbereichnummer, Datum datum, String bemerkung) {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 18.07.2019
		 * 
		 * @description: Mitarbeiter einem (neuen) Bereich zuordnen, mit Datumsangabe
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);

		if (ma == null) {
			return false;
		}

		ArrayList<Zugehoerigkeit> zlist = ma.getZugehoerigkeit();
		zlist.add(new Zugehoerigkeit(datum, arbeitsbereichnummer, bemerkung));
		ma.setZugehoerigkeit(zlist);
		return true;
	}

//******************** STATISTIKEN ABFRAGEN ********************	

	public void showStatistics(int position, int abteilungsnummer) {
		// TO DO
	}

//******************** FUNKTIONEN ********************	

	public void showAZK() {
		/*
		 * @author: Soeren Hebestreit
		 * 
		 * @date: 19.07.2019
		 * 
		 * @description: Arbeitszeitkonto anzeigen, Urlaub aktuelles Jahr anzeigen
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

}
