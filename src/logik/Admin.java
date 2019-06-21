package logik;

import java.util.ArrayList;
import java.util.Date;

public class Admin extends Berechtigung{

	public Admin() {
		super();
	}
	
	public boolean changeBerechtigung(int personalnummer) {
		/* 
		 @author:		Jakob Küchler
		 @date: 		20.06.2019
		 @description: 	Bekommt Personalnummer (Integer) und ändert die Berechtigung des Mitarbeiters
		 				in die bisher nicht vorhandene (Admin -> User und User -> Admin)
		*/
		
		// MA raussuchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
		// MA auswählen
		try {
		// MA Berechtigung neu setzen
			if(ma.getBerechtigung() instanceof User) {
				ma.setBerechtigung(new Admin());
			} else {
				ma.setBerechtigung(new User());
			}
		} catch(NullPointerException e) {
			System.out.println("Mitarbeiternummer nicht vergeben");
			return false;
		}
		return true;
	}
	
	public void addMA() throws Exception{
		/*@author:		Jakob Küchler
		 *@date: 		20.06.2019
		 *@description: Fügt neuen Mitarbeiter hinzu.
		 */
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.create();
	}
	
	public void editMA(int personalnummer) {
		// TO DO
	}
	
	public void rmMA(int personalnummer, Date ausscheidungsdatum) {
		// TO DO
	}
	
	public void addAB(String abteilungsname) {
		// TO DO
	}
	
	public void editAB(int abteilungsnummer) {
		// TO DO
	}
	
	public void rmAB(int abteilungsnummer) {
		// TO DO
	}
	
	public void lnkMAtoAB(int personalnummer, int abteilungsnummer) {
		//@author:		Jakob Küchler
		//@date: 		21.06.2019
		//@description: 
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
		
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
		Arbeitsbereich ab = (Arbeitsbereich) av.suchen(abteilungsnummer);
		
		Zugehoerigkeit z = new Zugehoerigkeit(new Date(), personalnummer, abteilungsnummer);
		
		ArrayList<Zugehoerigkeit> listMA = ma.getZugehoerigkeit();
		ArrayList<Zugehoerigkeit> listAB = ab.getInabteilung();
		
		listAB.add(z);
		ab.setInabteilung(listAB);
		
		listMA.add(z);
		ma.setZugehoerigkeit(listMA);
		
	}
	
	public void showStatistics(int position, int abteilungsnummer) {
		// TO DO
	}
	
	public void addArbeitsplan(int kw, int abteilungsnummer) {
		// TO DO
	}
	
	public void rmArbeitsplan(int kw, int abteilungsnummer) {
		// TO DO
	}
	
	public void addMAtoSchicht(int kw, int abteilungsnummer, Schicht schicht) {
		// TO DO
	}
	
	public void rmMA(int kw, int abteilungsnummer, Schicht schicht) {
		// TO DO
	}
	
	public void addUrlaub(int personalnummer, Date startDatum, Date endDatum) {
		// TO DO
	}
	
	public void rmUrlaub(int personalnummer, int auswahl) {
		// TO DO
	}
	
	public void changeSOLL(int personalnummer, int newSOLL) {
		// TO DO
	}
	
	public void changeDARF_Urlaub(int personalnummer, int newDARF) {
		// TO DO
	}
	
}
