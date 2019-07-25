package logik;

import java.io.Serializable;

import extern.Datum;

public abstract class Eintrag implements Serializable {

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int arbeitstage;
	private Datum start;
	private Datum ende;
								
//******************** KONSTRUKTOR ********************
				
	public Eintrag (Datum start, Datum ende, int arbeitstage) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Konstruktor Krankheitseintraege
		 */
					
		this.arbeitstage = arbeitstage;
		this.start = start;
		this.ende = ende;
	}

//******************** AUSGABE ********************

	public String toString() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Textrueckgabe String
		 */
				
		return start+"\t"+ende+"\t("+arbeitstage+" Arbeitstage)";
	}
							
	public void display() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Textausgabe Konsole
		 */
				
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
