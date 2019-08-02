package logik;

import java.io.Serializable;

import extern.Datum;
/*
 *@author: 		Soeren Hebestreit
 *@date: 		19.07.2019
 *@description:	
 */
public class User extends Berechtigung implements Serializable {

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int personalID;

//******************** KONSTRUKTOR ********************	
	
	public User(int personalnummer) {
		personalID = personalnummer;
	}
		
//******************** FUNKTIONEN ********************	
	
	//Arbeitszeitkonto anzeigen, Urlaub aktuelles Jahr anzeigen
	public void showAZK() {
		
		
		Arbeitszeitkonto azk = ((Mitarbeiter) Personalverwaltung.getInstance().suchen(personalID)).getAzk();		
		azk.display();
		System.out.println("\n--------Urlaub--------");
		azk.showUrlaub(new Datum().getJahr());
	}
	
	//Passwort ändern, wenn altes übereinstimmt
	public boolean changePasswort(String alt, String neu) {
		
		Mitarbeiter ma = (Mitarbeiter) Personalverwaltung.getInstance().suchen(personalID);
		if(ma.getPasswort().equals(alt)) {
			ma.setPasswort(neu);
			ma.setChangePasswort(false);
			return true;
		}
		return false;
	}
	
//******************** GETTER & SETTER ********************
	
	public int getPersonalID() {
		return personalID;
	}
	
}
