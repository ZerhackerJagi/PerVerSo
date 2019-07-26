package logik;

import java.io.Serializable;

import extern.Datum;

public class Zugehoerigkeit implements Serializable {
	
//******************** PARAMETER ********************
	
	private static final long serialVersionUID = 1L;
	private Datum start;
	private int arbeitsbereichnummer;
	private String bemerkung;
		
//******************** KONSTRUKTOR ********************
	
	public Zugehoerigkeit(Datum start, int arbeitsbereichnummer, String bemerkung) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Konstruktor: Datum, Arbeitsbereichnummer
		 */ 
		
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
	
	public Zugehoerigkeit() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		2019
		 *@description:	Konstruktor: Datum heute, Arbeitsbereich nicht zugeordnet
		 */ 
		
		this.start = new Datum();
		this.arbeitsbereichnummer = 0;
		this.bemerkung = "";
	}
	
//******************** AUSGABE ********************
	
	public String toString() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Textrueckgabe String
		 */
		
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
