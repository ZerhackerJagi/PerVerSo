package logik;

import java.io.Serializable;

import extern.Datum;
/*
 *@author: 		Soeren Hebestreit
 *@date: 		21.06.2019
 *@description:	
 */
public class Zugehoerigkeit implements Serializable {
	
//******************** PARAMETER ********************
	
	private static final long serialVersionUID = 1L;
	private Datum start;
	private int arbeitsbereichnummer;
	private String bemerkung;
		
//******************** KONSTRUKTOR ********************
	
	//Konstruktor: Datum, Arbeitsbereichnummer
	public Zugehoerigkeit(Datum start, int arbeitsbereichnummer, String bemerkung) {
		 	
		this.start = start;
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		Arbeitsbereich bereich = (Arbeitsbereich) abv.suchen(arbeitsbereichnummer);
		if (bereich == null) {
			this.arbeitsbereichnummer = 0;
		} else {
			this.arbeitsbereichnummer = arbeitsbereichnummer;
		}
		this.bemerkung = bemerkung;
	}
	
	//Konstruktor: Datum heute, Arbeitsbereich nicht zugeordnet
	public Zugehoerigkeit() {
		
		this.start = new Datum();
		this.arbeitsbereichnummer = 0;
		this.bemerkung = "";
	}
	
//******************** AUSGABE ********************
	
	//Textrueckgabe String
	public String toString() {
		
		return start+"\t"+arbeitsbereichnummer+"\t"+bemerkung;
	}
	
//******************** GETTER & SETTER ********************
	
	public Datum getStart() {
		return start;
	}

	public void setStart(Datum start) {
		this.start = start;
	}

	public int getArbeitsbereichnummer() {
		return arbeitsbereichnummer;
	}

	public void setArbeitsbereichnummer(int arbeitsbereichnummer) {
		this.arbeitsbereichnummer = arbeitsbereichnummer;
	}
	
	public String getBemerkung() {
		return bemerkung;
	}

	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}

}
