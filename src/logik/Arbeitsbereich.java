package logik;

import java.io.Serializable;
import java.util.ArrayList;

public class Arbeitsbereich implements Serializable{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int arbeitsbereichnummer;
	private String name;
	private String beschreibung;
	private ArrayList <Zugehoerigkeit> inabteilung;
	
	
//******************** KONSTRUKTOR ********************
	
	public Arbeitsbereich(int arbeitsbereichnummer, String name, String beschreibung) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Konstruktor Arbeitsbereich
		 */
		
		this.arbeitsbereichnummer = arbeitsbereichnummer;
		this.name = name;
		this.beschreibung = beschreibung;
		inabteilung = new ArrayList <Zugehoerigkeit> ();
	}


//******************** AUSGABE ********************

	public String toString() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Textrueckgabe String
		 */
		
		return arbeitsbereichnummer+"\t"+name;
	}
	
	
	public void display() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Textausgabe Konsole
		 */
		
		System.out.println(arbeitsbereichnummer+"\t"+name+"\t\t"+beschreibung);
	}
	
	/*
	public void showMitarbeiter() {
		if (inabteilung.size()!=0) {
			for (int i = 0; i < bereiche.size(); i++) {
				bereiche.get(i).display();
				
			}	
		} else {
			System.out.println("keine Mitarbeiter vorhanden");
		}
	}
	*/

	
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
	
	
	public ArrayList<Zugehoerigkeit> getInabteilung() {
		return inabteilung;
	}


	public void setInabteilung(ArrayList<Zugehoerigkeit> inabteilung) {
		this.inabteilung = inabteilung;
	}	
	
	
}