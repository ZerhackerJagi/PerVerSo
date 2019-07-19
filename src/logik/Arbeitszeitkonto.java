package logik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import comparatoren.EintragStartComparator;
import extern.Datum;

public class Arbeitszeitkonto implements Serializable {

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int sollstunden;
	private int ueberminuten;
	private int ueberminutenmin;
	private int ueberminutenmax;
	private int urlaubbasis;
	private int urlaubskontingent;
	private int urlaubgenommen;
	private ArrayList <Eintrag> liste;
		
		
//******************** KONSTRUKTOR ********************
		
	public Arbeitszeitkonto() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Konstruktor Arbeitsbereich Standard
		 */
			
		sollstunden = 40;
		ueberminuten = 0;
		ueberminutenmin = -2000;
		ueberminutenmax = 5000;
		urlaubbasis = 30;
		urlaubskontingent = 30;
		urlaubgenommen = 0;
		liste = new ArrayList <Eintrag>();
	}
	
	
	public Arbeitszeitkonto(int sollstunden, int min, int max, int kontingent) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Konstruktor Arbeitsbereich mit Angaben
		 */
			
		this.sollstunden = sollstunden;
		ueberminuten = 0;
		if (min < 0) {
			ueberminutenmin = min;
		} else {
			ueberminutenmin = -min;
		}
		ueberminutenmin = -min;
		ueberminutenmax = max;
		urlaubbasis = kontingent;
		urlaubskontingent = kontingent;
		urlaubgenommen = 0;
		liste = new ArrayList <Eintrag>();
	}

	
//******************** VERWALTUNG ********************	
	
	public void addUrlaub (Datum start, Datum ende, int tage) throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Urlaubseintrag hinzufuegen
		 */
		
		if (start.getJahr() == ende.getJahr()) {
			if (urlaubskontingent >= urlaubgenommen+tage) {
				liste.add(new Urlaubseintrag(start, ende, tage));
				sort();
				urlaubgenommen = urlaubgenommen + tage;
			} else  {
				throw new Exception ("Urlaubskontingent nicht ausreichend!");
			}
		} else {
			throw new Exception ("Bitte Urlaub nicht jahresübergreifend angeben.");
		}
	}
	
	
	public void addKrankheit (Datum start, Datum ende, int tage) throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Krankheitseintrag hinzufuegen
		 *				Urlaub muss manuell geloescht und neu eingetragen werden, um die Ueberschneidung zu entfernen, sofern der Urlaub erstattet werden soll
		 */
		
		if (start.getJahr() == ende.getJahr()) {
			liste.add(new Krankheitseintrag(start, ende, tage));
			sort();
		} else {
			throw new Exception ("Bitte Krankheit nicht jahresübergreifend angeben.");
		}
	}
	
	
	public boolean deleteUrlaub (int eintrag) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Urlaubseintrag loeschen
		 */

		if (liste.size() > eintrag) {
			if(liste.get(eintrag) instanceof Urlaubseintrag) {
				// Urlaubstage zurueckgeben
				urlaubgenommen = urlaubgenommen - liste.get(eintrag).getArbeitstage();
				liste.remove(liste.get(eintrag));
				return true;
			}
		}
		return false;
	}
	
	
	public boolean deleteKrankheit (int eintrag) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Krankheitseintrag loeschen
		 */

		if (liste.size() > eintrag) {
			if(liste.get(eintrag) instanceof Krankheitseintrag) {
				liste.remove(liste.get(eintrag));
				return true;
			}
		}
		return false;
	}
	
	
	public void addPlus (int betrag) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	positiven Betrag den Ueberminuten hinzufuegen
		 */
		
		ueberminuten = ueberminuten + betrag;
	}
	
	
	public void addMinus (int betrag) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	negativen Betrag den Ueberminuten hinzufuegen
		 */
		
		ueberminuten = ueberminuten - betrag;
	}
	
	
	public void neuesJahr () {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Ruecksetzen/Aendern von Parametern fuer ein neues Kalenderjahr
		 */
		
		urlaubskontingent = urlaubbasis + urlaubskontingent - urlaubgenommen;
		urlaubgenommen = 0;
	}
	

//******************** AUSGABE ********************

	public String toString() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Textrueckgabe String
		 */
			
		return "Soll: "+sollstunden+"\tUeberminuten: "+ueberminuten+"\tUrlaub: "+urlaubskontingent+"\tResturlaub: "+(urlaubskontingent-urlaubgenommen);
	}
		
		
	public void display() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Textausgabe Konsole
		 */
		
		System.out.println("Sollstunden: "+sollstunden);
		System.out.println("Überminuten: "+ueberminuten);
		System.out.println("Urlaub: \t"+urlaubskontingent);
		System.out.println("Resturlaub: "+(urlaubskontingent-urlaubgenommen));
	}
	
	
	public void showUrlaub() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Urlaubseintraege anzeigen (Konsole) 
		 */
		
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
	
	
	public void showKrankheit() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description:	Urlaubseintraege anzeigen (Konsole) 
		 */
		
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
	
	public void sort() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	Mitarbeiterliste nach Name, Vorname sortieren
		 */
		
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


//	public void setUeberminuten(int ueberminuten) {
//		this.ueberminuten = ueberminuten;
//	}


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
	
	
	public int getUrlaubskontingent() {
		return urlaubskontingent;
	}


	public void setUrlaubskontingent(int urlaubskontingent) {
		this.urlaubskontingent = urlaubskontingent;
	}


	public int getUrlaubgenommen() {
		return urlaubgenommen;
	}


	public void setUrlaubgenommen(int urlaubgenommen) {
		this.urlaubgenommen = urlaubgenommen;
	}


	public ArrayList<Eintrag> getListe() {
		return liste;
	}


	public void setListe(ArrayList<Eintrag> liste) {
		this.liste = liste;
	}
	
}
