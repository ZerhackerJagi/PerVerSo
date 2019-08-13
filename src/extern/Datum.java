package extern;

import java.util.GregorianCalendar;
import java.io.*;
/*@author: 		Soeren Hebestreit
 *@date: 		2019
 */ 
public class Datum implements Comparable <Datum>, Serializable {
	
//******************** PARAMETER ********************
	
	private static final long serialVersionUID = 1L;
	private int Jahr;
	private int Monat;
	private int Tag;
	
//******************** KONSTRUKTOR ********************

	public Datum (int Tag, int Monat, int Jahr) {
				
		this.Jahr = Jahr;
		this.Monat = Monat;
		this.Tag = Tag;	
	}
	
	/*
	 *@description:	Konstruktor: Monat-Jahr
	 */
	public Datum (int Monat, int Jahr) {
				
		this.Jahr = Jahr;
		this.Monat = Monat;
		this.Tag = 1;	
	}
	
	/*
	 *@description:	Konstruktor: Jahr
	 */
	public Datum (int Jahr) {
			
		this.Jahr = Jahr;
		this.Monat = 1;
		this.Tag = 1;	
	}
	
	/*
	 *@description:	Konstruktor heute
	 */
	public Datum () {
				
		GregorianCalendar cal = new GregorianCalendar();
		
		this.Jahr = cal.get(GregorianCalendar.YEAR);
		this.Monat = cal.get(GregorianCalendar.MONTH)+1; // von 0…11!
		this.Tag = cal.get(GregorianCalendar.DATE);	
	}
		
//******************** AUSGABE ********************	
	
	/*
	 *@description:	Textrueckgabe String
	 */
	public String toString () {
			
		return String.format("%02d" , this.Tag)+"."+String.format("%02d" , this.Monat)+"."+this.Jahr;
	}
	
	/*
	 *@description:	Textausgabe Konsole Monatsname + Jahr
	 */
	public void display () {
			
		String[] Monate = {"Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
		System.out.println (Monate[this.Monat-1]+" "+this.Jahr);	
	}
	
//******************** COMPERATOR ********************	
	
	/*
	 *@description:	Comperator Datumsformat
	 */
	public int compareTo (Datum anderesDatum) {
				
		if (Jahr < anderesDatum.Jahr) return -1;
		if (Jahr > anderesDatum.Jahr) return 1;
		if (Monat < anderesDatum.Monat) return -1;
		if (Monat > anderesDatum.Monat) return 1;
		if (Tag < anderesDatum.Tag) return -1;
		if (Tag > anderesDatum.Tag) return 1;
		
		return 0;
	}
		
//******************** GETTER & SETTER ********************

	public int getJahr() {
		return Jahr;
	}

	public void setJahr(int jahr) {
		Jahr = jahr;
	}

	public int getMonat() {
		return Monat;
	}

	public void setMonat(int monat) {
		Monat = monat;
	}

	public int getTag() {
		return Tag;
	}

	public void setTag(int tag) {
		Tag = tag;
	}

}
