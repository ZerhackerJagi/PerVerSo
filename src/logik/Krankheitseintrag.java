package logik;

import java.io.Serializable;

import extern.Datum;
/*@author: 		Soeren Hebestreit
 *@date: 		19.07.2019
 *@description:	Konstruktor Krankheitseintraege
 */

public class Krankheitseintrag extends Eintrag implements Serializable{

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
			
//******************** KONSTRUKTOR ********************
			
	public Krankheitseintrag(Datum start, Datum ende, int arbeitstage) {
					
		super(start, ende, arbeitstage);
	}
}
