package logik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import comparatoren.EintragStartComparator;

public class Arbeitsplan implements Serializable {
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int kw;
	private Arbeitsbereich ab;
	private ArrayList<Schicht> schichten;
	
	
//******************** KONSTRUKTOR ********************		
	
	public Arbeitsplan(int kw, Arbeitsbereich arbeitsbereich) {
		this.kw = kw;
		this.ab = arbeitsbereich;
		this.schichten = new ArrayList<Schicht>();
	}
	
	
//******************** VERWALTUNG ********************		

	public void addMA(Schicht schicht) {
		// TO DO
	}
	
	public void rmMA(Schicht schicht) {
		// TO DO
	}
	
	
//******************** AUSGABE ********************	
	
	public void showPlan() {
		// TO DO
	}


//******************** SORTIEREN & SUCHEN ********************
	
	public void sort() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Mitarbeiterliste nach Name, Vorname sortieren
		 */
			
		//Collections.sort(schichten, new EintragStartComparator());
	}

	
//******************** GETTER & SETTER ********************	

	
	
	
	
}

