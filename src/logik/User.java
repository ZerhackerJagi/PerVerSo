package logik;

import java.io.Serializable;

import extern.Datum;

public class User extends Berechtigung implements Serializable {

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int personalID;

//******************** KONSTRUKTOR ********************	
	
	public User(int personalnummer) {
		personalID = personalnummer;
	}
		
//******************** FUNKTIONEN ********************	
	
	public void showAZK() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Arbeitszeitkonto anzeigen, Urlaub aktuelles Jahr anzeigen
		 */
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitszeitkonto azk = ((Mitarbeiter) pv.suchen(personalID)).getAzk();		
		azk.display();
		System.out.println("\n--------Urlaub--------");
		azk.showUrlaub(new Datum().getJahr());
	}
	
//******************** GETTER & SETTER ********************
	
	public int getPersonalID() {
		return personalID;
	}
	
}
