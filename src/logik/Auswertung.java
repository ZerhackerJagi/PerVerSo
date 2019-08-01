package logik;

import java.text.NumberFormat;
import java.util.ArrayList;

import extern.Datum;

public class Auswertung {
	private int ageUnder30;
	private int age30to39;
	private int age40to50;
	private int ageOver50;
	
	private int ageUnder30All;
	private int age30to39All;
	private int age40to50All;
	private int ageOver50All;
	
	private int countGenderM;
	private int countGenderW;
	private int countGenderD;
	private int countGender;
	
	private int aktiveMA;
	
	private int gesamtUeberstunden;
	


	public String showDurchschnittsalter(int arbeitsbereichnummer) {
		/* @author: 	Jakob Küchler
		 * @date: 		31.07.2019
		 * @description:Gibt das Durchschnittsalter der aktuellen Mitarbeiter aus
		 */
		
		
		int Gesamtalter = 0;
		int aktiveMA = 0;
		
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++) {
			
			Mitarbeiter ma = Personalverwaltung.getaMA().get(i);
			Zugehoerigkeit MaAb = ma.getActualAB();
			if(ma.getAlter()<30) {
				ageUnder30All++;
			} else if(ma.getAlter()>=30&&ma.getAlter()<40) {
				age30to39All++;
			} else if(ma.getAlter()>=40&&ma.getAlter()<=50) {
				age40to50All++;
			} else {
				ageOver50All++;
			}
			
			if(MaAb.getArbeitsbereichnummer() == arbeitsbereichnummer) {
				Gesamtalter = Gesamtalter + ma.getAlter();
				aktiveMA++;
				if(ma.getAlter()<30) {
					ageUnder30++;
				} else if(ma.getAlter()>=30&&ma.getAlter()<40) {
					age30to39++;
				} else if(ma.getAlter()>=40&&ma.getAlter()<=50) {
					age40to50++;
				} else {
					ageOver50++;
				}
			}
		}
		
		this.aktiveMA = aktiveMA;
		return (""+(Gesamtalter/aktiveMA)+" Jahre");
	}
	
	
	


	public void showGeschlechtsverteilung(int arbeitsbereichnummer) {
		/* @author: 	Jakob Küchler
		 * @date: 		31.07.2019
		 * @description:Gibt die Geschlechtsverteilung aus
		 */
		
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++) {
			if(Personalverwaltung.getaMA().get(i).getActualAB().getArbeitsbereichnummer() == arbeitsbereichnummer) { //|| (Personalverwaltung.getaMA().get(i).getActualAB().getArbeitsbereichnummer() == 1&&(Personalverwaltung.getaMA().get(i).getZugehoerigkeit().get(Personalverwaltung.getaMA().get(i).getZugehoerigkeit().size()-2).getArbeitsbereichnummer() == arbeitsbereichnummer))){
				char gender = Personalverwaltung.getaMA().get(i).getGeschlecht();
				if(gender == 'm') {
					countGenderM++;
				} else if (gender == 'w') {
					countGenderW++;
				} else if (gender == 'd') {
					countGenderD++;
				} else {
					countGender++;
				}
			}
		}
	}
	
	public String showFluktuationsquoteAll() {
		/* @author: 	Jakob Küchler
		 * @date: 		01.08.2019
		 * @description:Gibt die Fluktuationsquote nach Schlüter für das aktuelle Jahr aus
		 */
		
		Datum date = new Datum();
		int year = date.getJahr();
		int zugaenge = 0;
		int abgaenge = 0;
		int gesamteMA = 0;
		
		// Personalabgaenge & Zugaenge der Periode zaehlen
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++){
			Mitarbeiter ma = Personalverwaltung.getaMA().get(i);
			gesamteMA++;
			try {
			if(ma.getAusscheidungsdatum().getJahr() == year) {
				abgaenge++;
			} 
			
			} catch (NullPointerException e){
			}
			if(ma.getEinstellungsdatum().getJahr() == year) {
				zugaenge++;
			}
		}
		
		int bestandBeginnPeriode = gesamteMA-zugaenge+abgaenge;
		
		// Berechnung nach Schlüter
		double dabgaenge = (double) abgaenge;
		double dzugaenge = (double) zugaenge;
		double dbestandBeginnPeriode = (double) bestandBeginnPeriode;
		
		double fluktuationsquote = (double) ((dabgaenge/(dzugaenge+dbestandBeginnPeriode))*100);
		
		NumberFormat n = NumberFormat.getInstance();
		n.setMaximumFractionDigits(2); // max. 2 stellen hinter komma
		
		
		return ""+(n.format(fluktuationsquote))+" %";
			
	}
	
	public String showFluktuationsquote(int arbeitsbereichnummer) {
		/* @author: 	Jakob Küchler
		 * @date: 		01.08.2019
		 * @description:Gibt die Fluktuationsquote nach Schlüter für das aktuelle Jahr aus
		 */
		
		Datum date = new Datum();
		int year = date.getJahr();
		
		int zugaenge = 0;
		int abgaenge = 0;
		
		
		// Personalabgaenge & Zugaenge der Periode zaehlen
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++){
			Mitarbeiter ma = Personalverwaltung.getaMA().get(i);
			
			// Test ob MA vorher in entsprechendem AB war
			boolean boolAB;
			if(Personalverwaltung.getaMA().get(i).getZugehoerigkeit().size()==1&&Personalverwaltung.getaMA().get(i).getActualAB().getArbeitsbereichnummer()!=arbeitsbereichnummer) {
				boolAB = false;
			} else if (Personalverwaltung.getaMA().get(i).getZugehoerigkeit().size()==1){
				boolAB = true;
			} else {
				boolAB = Personalverwaltung.getaMA().get(i).getZugehoerigkeit().get(Personalverwaltung.getaMA().get(i).getZugehoerigkeit().size()-2).getArbeitsbereichnummer() == arbeitsbereichnummer;
			}
			// Test ob Jahr stimmt
			boolean boolJahr = Personalverwaltung.getaMA().get(i).getZugehoerigkeit().get(Personalverwaltung.getaMA().get(i).getZugehoerigkeit().size()-1).getStart().getJahr() == year;
			
			
			if(ma.getActualAB().getArbeitsbereichnummer() == arbeitsbereichnummer || (boolAB && boolJahr)) {
				
				try {
					if(ma.getAusscheidungsdatum().getJahr() == year) {
						abgaenge++;
					}
				} catch (NullPointerException e){
				}
				
				if(ma.getEinstellungsdatum().getJahr() == year) {
					zugaenge++;
				}
			}
		}
		
		
		int bestandBeginnPeriode = this.aktiveMA-zugaenge+abgaenge;
		
		// Berechnung nach Schlüter
		
		double dabgaenge = (double) abgaenge;
		double dzugaenge = (double) zugaenge;
		double dbestandBeginnPeriode = (double) bestandBeginnPeriode;
		double fluktuationsquote = (double) ((dabgaenge/(dzugaenge+dbestandBeginnPeriode))*100);
		
		NumberFormat n = NumberFormat.getInstance();
		n.setMaximumFractionDigits(2); // max. 2 stellen hinter komma
		
		
		return ""+(n.format(fluktuationsquote))+" %";
			
	}
	
	public String showUeberstunden(int arbeitsbereichnummer) {
		/* @author: 	Jakob Küchler
		 * @date: 		31.07.2019
		 * @description:Gibt die gesamten Überstunden im aktuellen Jahr aus 
		 */
		int ueberstunden = 0;
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++) {
			Mitarbeiter ma = Personalverwaltung.getaMA().get(i);
			Zugehoerigkeit MaAb = ma.getActualAB();
			if(MaAb.getArbeitsbereichnummer() == arbeitsbereichnummer) {
				ueberstunden = ueberstunden + ma.getAzk().getUeberminuten();
			}
		}
		this.gesamtUeberstunden = ueberstunden;
		return ""+ueberstunden+" Stunden";
		
	}
	
	public String showUeberstundenSchnitt() {
		/* @author: 	Jakob Küchler
		 * @date: 		31.07.2019
		 * @description:Gibt die gesamten Überstunden im aktuellen Jahr pro Person aus 
		 */
		if(gesamtUeberstunden == 0 || aktiveMA == 0) {
			return ""+0+" Stunden";
		}
		return ""+(gesamtUeberstunden/aktiveMA)+" Stunden";
		
		
	}
	
	public String showFehltage(int arbeitsbereichnummer) {
		/* @author: 	Jakob Küchler
		 * @date: 		31.07.2019
		 * @description:Gibt die gesamten Fehltage im aktuellen Jahr aus 
		 */
		int fehltage = 0;
		
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++) {
			Mitarbeiter ma = Personalverwaltung.getaMA().get(i);
			Zugehoerigkeit MaAb = ma.getActualAB();
			if(MaAb.getArbeitsbereichnummer() == arbeitsbereichnummer) {
				ArrayList<Eintrag> listFehltage = Personalverwaltung.getaMA().get(i).getAzk().getListe();
				for(int j = 0;j<listFehltage.size();j++) {
					System.out.println(listFehltage.get(j));
					if(listFehltage.get(j) instanceof Krankheitseintrag) {
						fehltage = fehltage +(listFehltage.get(j).getArbeitstage());
						
					}
				}
			}	
		}
		return ""+fehltage+" Tage";
		
	}
	
	public String showFehltageMaximal(int arbeitsbereichnummer) {
		/* @author: 	Jakob Küchler
		 * @date: 		31.07.2019
		 * @description:Gibt die maximalen Fehltage einer Person im aktuellen Jahr aus 
		 */
		
		int fehltageMax = 0;
		int fehltagePerson = 0;
		
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++) {
			Mitarbeiter ma = Personalverwaltung.getaMA().get(i);
			Zugehoerigkeit MaAb = ma.getActualAB();
			if(MaAb.getArbeitsbereichnummer() == arbeitsbereichnummer) {
				ArrayList<Eintrag> listFehltage = Personalverwaltung.getaMA().get(i).getAzk().getListe();
				fehltagePerson = 0;
				for(int j = 0;j<listFehltage.size();j++) {
					if(listFehltage.get(j) instanceof Krankheitseintrag) {
						fehltagePerson = fehltagePerson +listFehltage.get(j).getArbeitstage();
					}
				}
				if(fehltagePerson>fehltageMax) {
					fehltageMax = fehltagePerson;
				}
			}	
		}
		return ""+fehltageMax+" Tage";
		
		
	}
	


	public int getAgeUnder30() {
		return ageUnder30;
	}


	public int getAge30to39() {
		return age30to39;
	}


	public int getAge40to50() {
		return age40to50;
	}


	public int getAgeOver50() {
		return ageOver50;
	}
	
	public int getAgeUnder30All() {
		return ageUnder30All;
	}


	public int getAge30to39All() {
		return age30to39All;
	}


	public int getAge40to50All() {
		return age40to50All;
	}


	public int getAgeOver50All() {
		return ageOver50All;
	}
	
	
	public int getCountGenderM() {
		return countGenderM;
	}


	public int getCountGenderW() {
		return countGenderW;
	}


	public int getCountGenderD() {
		return countGenderD;
	}


	public int getCountGender() {
		return countGender;
	}
	
}
