package logik;

import java.io.Serializable;

import extern.Datum;
/*
 *@author: 	Soeren Hebestreit
 *@date: 		19.07.2019
 *@description:	
 */
public abstract class Eintrag implements Serializable {

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int arbeitstage;
	private Datum start;
	private Datum ende;
								
//******************** KONSTRUKTOR ********************
	
	//Konstruktor Krankheitseintraege
	public Eintrag (Datum start, Datum ende, int arbeitstage) {
					
		this.arbeitstage = arbeitstage;
		this.start = start;
		this.ende = ende;
	}

//******************** AUSGABE ********************

	//Textrueckgabe String
	public String toString() {
				
		return start+"\t"+ende+"\t("+arbeitstage+" Arbeitstage)";
	}
	
	//Textausgabe Konsole
	public void display() {
				
		System.out.println(start+"\t"+ende+"\t("+arbeitstage+" Arbeitstage)");
	}
		
//******************** GETTER & SETTER ********************	

	public int getArbeitstage() {
		return arbeitstage;
	}

	public Datum getStart() {
		return start;
	}

	public Datum getEnde() {
		return ende;
	}			

}
