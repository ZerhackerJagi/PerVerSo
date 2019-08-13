package logik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import comparatoren.EintragStartComparator;
import extern.Datum;
/*@author: 		Soeren Hebestreit
 *@date: 		19.07.2019
 *@description:	
 */

public class Arbeitszeitkonto implements Serializable {

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int sollstunden;
	private int ueberminuten;
	private int ueberminutenmin;
	private int ueberminutenmax;
	private int urlaubbasis;
	private ArrayList <Eintrag> liste;
				
//******************** KONSTRUKTOR ********************
		
	/*
	 *@description:	Konstruktor Arbeitsbereich Standard
	 */
	public Arbeitszeitkonto() {
				
		sollstunden = 40;
		ueberminuten = 0;
		ueberminutenmin = -sollstunden*30;
		ueberminutenmax = sollstunden*60;
		urlaubbasis = 30;
		liste = new ArrayList <Eintrag>();
	}
	
	/*
	 *@description:	Konstruktor Arbeitsbereich mit Angaben
	 */
	public Arbeitszeitkonto(int sollstunden, int min, int max, int kontingent) {
				
		this.sollstunden = sollstunden;
		ueberminuten = 0;
		if (min < 0) {
			ueberminutenmin = min;
		} else {
			ueberminutenmin = -min;
		}
		ueberminutenmax = max;
		urlaubbasis = kontingent;
		liste = new ArrayList <Eintrag>();
	}

//******************** VERWALTUNG ********************	
	
	/*
	 *@description:	Urlaubseintrag hinzufuegen
	 */
	public void addUrlaub (Datum start, Datum ende, int tage) throws Exception {
			
		if (start.getJahr() == ende.getJahr()) {
			liste.add(new Urlaubseintrag(start, ende, tage));
			sort();
		} else {
			throw new Exception ("Bitte Urlaub nicht jahresübergreifend angeben.");
		}
	}
	
	/*
	 *@description:	Krankheitseintrag hinzufuegen
	 *				Urlaub muss manuell geloescht und neu eingetragen werden, um die Ueberschneidung zu entfernen, sofern der Urlaub erstattet werden soll
	 */
	public void addKrankheit (Datum start, Datum ende, int tage) throws Exception {
		
		if (start.getJahr() == ende.getJahr()) {
			liste.add(new Krankheitseintrag(start, ende, tage));
			sort();
		} else {
			throw new Exception ("Bitte Krankheit nicht jahresübergreifend angeben.");
		}
	}
	
	/*
	 *@description:	Urlaubseintrag loeschen
	 */
	public boolean deleteUrlaub (int eintrag) {
		
		if (liste.size() > eintrag) {
			if(liste.get(eintrag) instanceof Urlaubseintrag) {
				liste.remove(liste.get(eintrag));
				return true;
			}
		}
		return false;
	}
	
	/*
	 *@description:	Krankheitseintrag loeschen
	 */
	public boolean deleteKrankheit (int eintrag) {
		
		if (liste.size() > eintrag) {
			if(liste.get(eintrag) instanceof Krankheitseintrag) {
				liste.remove(liste.get(eintrag));
				return true;
			}
		}
		return false;
	}
	
	/*
	 *@description:	positiven Betrag den Ueberminuten hinzufuegen
	 */
	public void addPlus (int betrag) {
			
		ueberminuten = ueberminuten + betrag;
	}
	
	/*
	 *@description:	negativen Betrag den Ueberminuten hinzufuegen
	 */
	public void addMinus (int betrag) {
		
		ueberminuten = ueberminuten - betrag;
	}

//******************** AUSGABE ********************

	/*
	 *@description:	Textrueckgabe String
	 */
	public String toString() {
			
		return "Sollstunden: "+sollstunden+"\t, Ueberminuten: "+ueberminuten+"\t, Urlaubstage: "+urlaubbasis;
	}
		
	/*
	 *@description:	Textausgabe Konsole
	 */
	public void display() {
			
		System.out.println("Sollstunden: "+sollstunden);
		System.out.println("Überminuten: "+ueberminuten);
		System.out.println("Urlaubstage: \t"+urlaubbasis);
	}
	
	/*
	 *@description:	Urlaubseintraege anzeigen (Konsole) 
	 */
	public void showUrlaub() {
		
		if (liste.size()!=0) {
			for (int i = 0; i < liste.size(); i++) {
				if (liste.get(i) instanceof Urlaubseintrag) {
					liste.get(i).display();
				}
			}	
		} else {
			System.out.println("Empty");
		}
	}
	
	/*
	 *@description:	Urlaubseintraege anzeigen (Konsole), mit Jahreseingrenzung
	 */
	public void showUrlaub(int jahr) {
		
		if (liste.size()!=0) {
			for (int i = 0; i < liste.size(); i++) {
				if (liste.get(i) instanceof Urlaubseintrag) {
					if (liste.get(i).getStart().getJahr() == jahr) {
						liste.get(i).display();
					}
				}
			}	
		} else {
			System.out.println("Empty");
		}
	}
	
	/*
	 *@description:	Urlaubseintraege anzeigen (Konsole) 
	 */
	public void showKrankheit() {
		
		if (liste.size()!=0) {
			for (int i = 0; i < liste.size(); i++) {
				if (liste.get(i) instanceof Krankheitseintrag) {
					liste.get(i).display();
				}
			}	
		} else {
			System.out.println("Empty");
		}
	}
	
//******************** SORTIEREN & SUCHEN ********************
	
	/*
	 *@description:	Mitarbeiterliste nach Name, Vorname sortieren
	 */
	public void sort() {
		
		Collections.sort(liste, new EintragStartComparator());
	}
	
//******************** GETTER & SETTER ********************	
	
	public int getSollstunden() {
		return sollstunden;
	}

	public void setSollstunden(int sollstunden) {
		this.sollstunden = sollstunden;
	}

	public int getUeberminuten() {
		return ueberminuten;
	}

	public int getUeberminutenmin() {
		return ueberminutenmin;
	}

	public void setUeberminutenmin(int ueberminutenmin) {
		this.ueberminutenmin = ueberminutenmin;
	}

	public int getUeberminutenmax() {
		return ueberminutenmax;
	}

	public void setUeberminutenmax(int ueberminutenmax) {
		this.ueberminutenmax = ueberminutenmax;
	}

	public int getUrlaubbasis() {
		return urlaubbasis;
	}

	public void setUrlaubbasis(int urlaubbasis) {
		this.urlaubbasis = urlaubbasis;
	}
	
	public ArrayList<Eintrag> getListe() {
		return liste;
	}

	public void setListe(ArrayList<Eintrag> liste) {
		this.liste = liste;
	}
	
}
