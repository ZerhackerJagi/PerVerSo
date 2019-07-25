package logik;

import java.io.Serializable;

import extern.Datum;

public class Urlaubseintrag extends Eintrag implements Serializable{

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;			
			
//******************** KONSTRUKTOR ********************
			
	public Urlaubseintrag(Datum start, Datum ende, int arbeitstage) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Konstruktor Urlaubseintraege
		 */
				
		super(start, ende, arbeitstage);
	}
}
