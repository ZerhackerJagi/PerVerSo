package logik;

import java.io.Serializable;

import extern.Datum;

public class Zugehoerigkeit implements Serializable {
	
//******************** PARAMETER ********************
	
	private static final long serialVersionUID = 1L;
	private Datum start;
	private int arbeitsbereichnummer;
		
//******************** KONSTRUKTOR ********************
	
	public Zugehoerigkeit(Datum start, int arbeitsbereichnummer) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Konstruktor: Datum, Arbeitsbereichnummer
		 */ 
		
		this.start = start;
		// Probe ob Bereich existiert, wenn nicht dann auf Grundbereich setzen
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		Arbeitsbereich bereich = (Arbeitsbereich) abv.suchen(arbeitsbereichnummer);
		if (bereich == null) {
			this.arbeitsbereichnummer = 0;
		} else {
			this.arbeitsbereichnummer = arbeitsbereichnummer;
		}
	}
	
	public Zugehoerigkeit(Datum start) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		2019
		 *@description:	Konstruktor: Datum, Arbeitsbereich nicht zugeordnet
		 */ 
		
		this.start = start;
		this.arbeitsbereichnummer = 0;
	}
	
	public Zugehoerigkeit() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		2019
		 *@description:	Konstruktor: Datum heute, Arbeitsbereich nicht zugeordnet
		 */ 
		
		this.start = new Datum();
		this.arbeitsbereichnummer = 0;
	}
	
//******************** AUSGABE ********************
	
	public String toString() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Textrueckgabe String
		 */
		
		return start+"\t"+arbeitsbereichnummer;
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

}
