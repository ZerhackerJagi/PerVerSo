package logik;

import java.io.Serializable;


public class Arbeitsbereich implements Serializable{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int arbeitsbereichnummer;
	private String name;
	private String beschreibung;
	
	
//******************** KONSTRUKTOR ********************
	
	public Arbeitsbereich(int arbeitsbereichnummer, String name, String beschreibung) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Konstruktor Arbeitsbereich
		 */
		
		this.arbeitsbereichnummer = arbeitsbereichnummer;
		this.name = name;
		this.beschreibung = beschreibung;
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
	
<<<<<<< HEAD
=======
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
>>>>>>> branch 'master' of https://github.com/ZerhackerJagi/PerVerSo.git

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