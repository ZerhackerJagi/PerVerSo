package logik;


import java.io.Serializable;
import java.util.ArrayList;

import extern.Datum;


public class Mitarbeiter implements Serializable {

//******************** PARAMETER ********************
	
	private static final long serialVersionUID = 1L;
	
	private int personalnummer;
	private String name;
	private String vorname;
	private char geschlecht;
	private Datum geburtsdatum;
	
	private String benutzername;
	private String passwort;
	private Berechtigung berechtigung;
	
	private Datum einstellungsdatum;
	private Datum ausscheidungsdatum;
	private Arbeitszeitkonto azk;
	private ArrayList<Zugehoerigkeit> zugehoerigkeit;
	
	
//******************** KONSTRUKTOR ********************
	
	public Mitarbeiter(int personalnummer, String name, String vorname, char geschlecht, Datum geburtstag, 
			String benutzername, String passwort, Berechtigung berechtigung, 
			Datum einstellungsdatum, Arbeitszeitkonto azk, Zugehoerigkeit zugehoerigkeit) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Konstruktor Mitarbeiter komplett
		 */
		
		this.personalnummer = personalnummer;
		this.name = name;
		this.vorname = vorname;
		this.geschlecht = geschlecht;
		this.geburtsdatum = geburtstag;
		
		this.benutzername = benutzername;
		this.passwort = passwort;
		this.berechtigung = berechtigung;
		
		this.einstellungsdatum = einstellungsdatum;
		this.ausscheidungsdatum = null;
		this.azk = azk;
		this.zugehoerigkeit = new ArrayList<Zugehoerigkeit>();
		this.zugehoerigkeit.add(zugehoerigkeit);
	}


//******************** AUSGABE ********************
	
	public String toString() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Textrueckgabe String
		 */
		
		return  personalnummer+"\t"+name+" "+vorname;
	}
	
	
	public void display() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Textausgabe Konsole
		 */
		
		System.out.println(personalnummer+"\t"+name+" "+vorname+"\t"+geschlecht+"\t"+geburtsdatum);
	}


//******************** GETTER & SETTER ********************
	
	public int getPersonalnummer() {
		return personalnummer;
	}


	//public void setPersonalnummer(int personalnummer) {
	//	this.personalnummer = personalnummer;
	//}


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


	public String getBenutzername() {
		return benutzername;
	}


	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}


	public String getPasswort() {
		return passwort;
	}


	public void setPasswort(String passwort) {
		this.passwort = passwort;
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
	
}
