package logik;

import java.io.Serializable;
/*@author: 		Soeren Hebestreit
 *@date: 		21.06.2019
 *@description:
 */
public class Arbeitsbereich implements Serializable{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int arbeitsbereichnummer;
	private String name;
	private String beschreibung;
	
//******************** KONSTRUKTOR ********************
	
	/*
	 *@description:	Konstruktor Arbeitsbereich
	 */
	public Arbeitsbereich(int arbeitsbereichnummer, String name, String beschreibung) {
			
		this.arbeitsbereichnummer = arbeitsbereichnummer;
		this.name = name;
		this.beschreibung = beschreibung;
	}

//******************** AUSGABE ********************

	/*
	 *@description:	Textrueckgabe String
	 */
	public String toString() {
			
		return arbeitsbereichnummer+" \t"+name;
	}
	
	/*
	 *@description:	Textausgabe Konsole
	 */
	public void display() {
			
		System.out.println(arbeitsbereichnummer+"\t"+name+"\t"+beschreibung);
	}
		
//******************** GETTER & SETTER ********************
	
	public int getArbeitsbereichnummer() {
		return arbeitsbereichnummer;
	}

	public void setArbeitsbereichnummer(int arbeitsbereichnummer) {
		this.arbeitsbereichnummer = arbeitsbereichnummer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
}