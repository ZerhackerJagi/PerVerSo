package logik;

import java.io.Serializable;

import extern.Datum;
/*@author: 		Soeren Hebestreit
 *@date: 		19.07.2019
 *@description:	Konstruktor Urlaubseintraege
 */

public class Urlaubseintrag extends Eintrag implements Serializable{

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;			
			
//******************** KONSTRUKTOR ********************
			
	public Urlaubseintrag(Datum start, Datum ende, int arbeitstage) {
					
		super(start, ende, arbeitstage);
	}
}
