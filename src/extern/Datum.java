package extern;


import java.util.GregorianCalendar;
import java.io.*;


public class Datum implements Comparable <Datum>, Serializable {
	
//******************** PARAMETER ********************
	
	private static final long serialVersionUID = 1L;
	private int Jahr;
	private int Monat;
	private int Tag;
	
	
//******************** KONSTRUKTOR ********************

	public Datum (int Tag, int Monat, int Jahr) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		2019
		 *@description:	Konstruktor: Tag-Monat-Jahr
		 */ 
		
		this.Jahr = Jahr;
		this.Monat = Monat;
		this.Tag = Tag;	
	}
	
	
	public Datum (int Monat, int Jahr) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		2019
		 *@description:	Konstruktor: Monat-Jahr
		 */
		
		this.Jahr = Jahr;
		this.Monat = Monat;
		this.Tag = 1;	
	}
	
	
	public Datum (int Jahr) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		2019
		 *@description:	Konstruktor: Jahr
		 */
		
		this.Jahr = Jahr;
		this.Monat = 1;
		this.Tag = 1;	
	}
	
	
	public Datum () {
		/*@author: 		Soeren Hebestreit
		 *@date: 		2019
		 *@description:	Konstruktor heute
		 */
		
		GregorianCalendar cal = new GregorianCalendar();
		
		this.Jahr = cal.get(GregorianCalendar.YEAR);
		this.Monat = cal.get(GregorianCalendar.MONTH)+1; // von 0…11!
		this.Tag = cal.get(GregorianCalendar.DATE);	
	}
	
	
//******************** AUSGABE ********************	
	
	public String toString () {
		/*@author: 		Soeren Hebestreit
		 *@date: 		2019
		 *@description:	Textrueckgabe String
		 */
		
		return this.Tag+"."+this.Monat+"."+this.Jahr;
	}
	
	
	public void display () {
		/*@author: 		Soeren Hebestreit
		 *@date: 		2019
		 *@description:	Textausgabe Konsole Monatsname + Jahr
		 */
		
		String[] Monate = {"Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
		
		System.out.println (Monate[this.Monat-1]+" "+this.Jahr);	
	}

	
//******************** COMPERATOR ********************	
	
	public int compareTo (Datum anderesDatum) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		2019
		 *@description:	Comperator Datumsformat
		 */
		
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
