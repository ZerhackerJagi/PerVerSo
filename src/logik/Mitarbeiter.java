package logik;

import java.io.Serializable;
import java.util.ArrayList;

import extern.Datum;
/*@author: 		Soeren Hebestreit
 *@date: 		21.06.2019
 *@description:	
 */

public class Mitarbeiter implements Serializable {

//******************** PARAMETER ********************
	
	private static final long serialVersionUID = 1L;
	
	private int personalnummer;
	private String name;
	private String vorname;
	private char geschlecht;
	private Datum geburtsdatum;
	
	private String passwort;
	private boolean changePasswort;
	private Berechtigung berechtigung;
	
	private Datum einstellungsdatum;
	private Datum ausscheidungsdatum;
	private Arbeitszeitkonto azk;
	private ArrayList<Zugehoerigkeit> zugehoerigkeit;
		
//******************** KONSTRUKTOR ********************
	
	/*
	 *@description:	Konstruktor Mitarbeiter komplett
	 */
	public Mitarbeiter(int personalnummer, String name, String vorname, char geschlecht, Datum geburtstag, 
			String passwort, Berechtigung berechtigung, 
			Datum einstellungsdatum, Arbeitszeitkonto azk, Zugehoerigkeit zugehoerigkeit) {
		
		this.personalnummer = personalnummer;
		this.name = name;
		this.vorname = vorname;
		this.geschlecht = geschlecht;
		this.geburtsdatum = geburtstag;
		
		this.passwort = passwort;
		this.changePasswort = true;
		this.berechtigung = berechtigung;
		
		this.einstellungsdatum = einstellungsdatum;
		this.ausscheidungsdatum = null;
		this.azk = azk;
		this.zugehoerigkeit = new ArrayList<Zugehoerigkeit>();
		this.zugehoerigkeit.add(zugehoerigkeit);
	}
	
	/*
	 * @author: Charly Spina 
	 * @description: Für die Test(MitarbeiterMock)
	 */
	public Mitarbeiter() {

	}
	
//******************** AUSGABE ********************
	
	/*
	 *@description:	Textrueckgabe String
	 */
	public String toString() {
		
		return  personalnummer+"\t"+name+" "+vorname;
	}
		
	/*
	 *@description:	Textausgabe Konsole
	 */
	public void display() {
		
		System.out.println(personalnummer+"\t"+geschlecht+"\t"+geburtsdatum+"\t"+name+", "+vorname);
	}

//******************** GETTER & SETTER ********************
	
	public int getPersonalnummer() {
		return personalnummer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public char getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(char geschlecht) {
		this.geschlecht = geschlecht;
	}

	public Datum getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(Datum geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	public boolean getChangePasswort() {
		return changePasswort;
	}

	public void setChangePasswort(boolean changePasswort) {
		this.changePasswort = changePasswort;
	}

	public Berechtigung getBerechtigung() {
		return berechtigung;
	}

	public void setBerechtigung(Berechtigung berechtigung) {
		this.berechtigung = berechtigung;
	}

	public Datum getEinstellungsdatum() {
		return einstellungsdatum;
	}

	public void setEinstellungsdatum(Datum einstellungsdatum) {
		this.einstellungsdatum = einstellungsdatum;
	}

	public Datum getAusscheidungsdatum() {
		return ausscheidungsdatum;
	}

	public void setAusscheidungsdatum(Datum ausscheidungsdatum) {
		this.ausscheidungsdatum = ausscheidungsdatum;
	}

	public Arbeitszeitkonto getAzk() {
		return azk;
	}

	public void setAzk(Arbeitszeitkonto azk) {
		this.azk = azk;
	}

	public ArrayList<Zugehoerigkeit> getZugehoerigkeit() {
		return zugehoerigkeit;
	}

	public void setZugehoerigkeit(ArrayList<Zugehoerigkeit> zugehoerigkeit) {
		this.zugehoerigkeit = zugehoerigkeit;
	}
	
	public Zugehoerigkeit getActualAB () {
		return zugehoerigkeit.get(zugehoerigkeit.size()-1);
	}
	
	public int getAlter() {
		Datum heute = new Datum();
		boolean hatteGeburtstag;
		if(heute.getMonat()-this.getGeburtsdatum().getMonat()<0) {
			if(((heute.getTag()-this.getGeburtsdatum().getTag())<0)) {
				hatteGeburtstag = true;
			} else {
				hatteGeburtstag = false;
			}
		}else {
			hatteGeburtstag = false;
		}
		
		if(hatteGeburtstag = false) {
			return heute.getJahr()-this.getGeburtsdatum().getJahr()-1;
		}
		
		return heute.getJahr()-this.getGeburtsdatum().getJahr();
	}
	
}
