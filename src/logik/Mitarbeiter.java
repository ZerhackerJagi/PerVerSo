package logik;

import java.util.Date;

public class Mitarbeiter {

	// PRIVATE ATTRIBUTE
	private String name;
	private String vorname;
	private int personalnummer;
	private String benutzername;
	private String passwort;
	private char geschlecht;
	private Date geburtsdatum;
	private Date einstellungsdatum;
	private Date ausscheidungsdatum;
	
	private Berechtigung berechtigung;
	private Arbeitszeitkonto azk;
	private Status status;
	private Zugehoerigkeit zugehoerigkeit;
	
	// KONSTRUKTOR
	public Mitarbeiter(String name, String vorname, String passwort, char geschlecht, Date geburtstag, Berechtigung berechtigung, Status status, Zugehoerigkeit zugehoerigkeit) {
	this.name = name;
	this.vorname = vorname;
	this.passwort = passwort;
	this.geschlecht = geschlecht;
	this.berechtigung = berechtigung;
	this.status = status;
	this.zugehoerigkeit = zugehoerigkeit;
	this.geburtsdatum = geburtstag;
	this.einstellungsdatum = new Date();
	
	}

	
	
	
	
	// GETTER & SETTER
	
	
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

	public int getPersonalnummer() {
		return personalnummer;
	}

	public void setPersonalnummer(int personalnummer) {
		this.personalnummer = personalnummer;
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

	public char getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(char geschlecht) {
		this.geschlecht = geschlecht;
	}

	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public Date getEinstellungsdatum() {
		return einstellungsdatum;
	}

	public void setEinstellungsdatum(Date einstellungsdatum) {
		this.einstellungsdatum = einstellungsdatum;
	}

	public Date getAusscheidungsdatum() {
		return ausscheidungsdatum;
	}

	public void setAusscheidungsdatum(Date ausscheidungsdatum) {
		this.ausscheidungsdatum = ausscheidungsdatum;
	}

	public Berechtigung getBerechtigung() {
		return berechtigung;
	}

	public void setBerechtigung(Berechtigung berechtigung) {
		this.berechtigung = berechtigung;
	}

	public Arbeitszeitkonto getAzk() {
		return azk;
	}

	public void setAzk(Arbeitszeitkonto azk) {
		this.azk = azk;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Zugehoerigkeit getZugehoerigkeit() {
		return zugehoerigkeit;
	}

	public void setZugehoerigkeit(Zugehoerigkeit zugehoerigkeit) {
		this.zugehoerigkeit = zugehoerigkeit;
	}
	
	
	
	
	
	
	
	
	

	
}
