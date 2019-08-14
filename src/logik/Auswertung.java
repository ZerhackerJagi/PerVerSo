package logik;

import java.text.NumberFormat;
import java.util.ArrayList;

import extern.Datum;
/* @author: 	Jakob Küchler
 * @date: 		31.07.2019
 * @description:
 */

public class Auswertung {
	
	// Alterswerte einzelner Arbeitsbereich
	private int ageUnder30;
	private int age30to39;
	private int age40to50;
	private int ageOver50;
	
	// Alterswerte Gesamtunternehmen
	private int ageUnder30All;
	private int age30to39All;
	private int age40to50All;
	private int ageOver50All;

	// Alterswerte Gesamtunternehmen prozentual
	private double ageUnder30Allp;
	private double age30to39Allp;
	private double age40to50Allp;
	private double ageOver50Allp;
	
	// Alterswerte einzelner Arbeitsbereich prozentual
	private double ageUnder30p;
	private double age30to39p;
	private double age40to50p;
	private double ageOver50p;
	
	// Anzahl Geschlechter einzelner Arbeitsbereich
	private int countGenderM;
	private int countGenderW;
	private int countGenderD;
	private int countGender;
	
	// Anzahl Geschlechter gesamtes Unternehmen prozentual
	private double countGenderMAllp;
	private double countGenderWAllp;
	private double countGenderDAllp;
	private double countGenderAllp;
	
	// Anzahl Geschlechter einzelner Arbeitsbereich prozentual
	private double countGenderMp;
	private double countGenderWp;
	private double countGenderDp;
	private double countGenderp;
	
	// Anzahl MA in Arbeitsbereich arbeitend
	private int aktiveMA;
	
	// Gesamte Überstunden eines Arbeitsbereiches
	private double gesamtUeberstunden;
	private double gesamtUeberstundenU;
	
	// Nummer des Arbeitsbereiches "Ausgeschieden"
	private int abNrAusgeschieden = 1;
	
	//Nummer des GesamtUnternehmens als Arbeitsbereichnummer
	private int GesamtUnternehmen = -1;
	

	public void resetAgeValues() {
		ageUnder30All=0; 
		age30to39All=0; 
		age40to50All=0;
		ageOver50All=0;
		
		ageUnder30Allp=0; 
		age30to39Allp=0; 
		age40to50Allp=0;
		ageOver50Allp=0;
		
		ageUnder30All=0; 
		age30to39All=0; 
		age40to50All=0;
		ageOver50All=0;
		
		ageUnder30=0; 
		age30to39=0; 
		age40to50=0;
		ageOver50=0;
		
		ageUnder30p=0; 
		age30to39p=0; 
		age40to50p=0;
		ageOver50p=0;
	}
	
	public void resetGenderValues() {
		countGenderM=0;
		countGenderW=0;
		countGenderD=0;
		countGender=0;
		
		// Anzahl Geschlechter gesamtes Unternehmen prozentual
		countGenderMAllp=0;
		countGenderWAllp=0;
		countGenderDAllp=0;
		countGenderAllp=0;
		
		// Anzahl Geschlechter einzelner Arbeitsbereich prozentual
		countGenderMp=0;
		countGenderWp=0;
		countGenderDp=0;
		countGenderp=0;
	}
	
		
	/* 
	 * @description:Gibt das Durchschnittsalter der aktuellen Mitarbeiter aus
	 */
	public String showDurchschnittsalter(int arbeitsbereichnummer, Datum selectedDate) {
			
		int Gesamtalter = 0;int aktiveMA = 0;
		
		resetAgeValues();
			
		for(int i = 0; i<Personalverwaltung.getaMA().size();i++) {
			Mitarbeiter ma = Personalverwaltung.getaMA().get(i);
			if(arbeitsbereichnummer == GesamtUnternehmen) {
				if(checkArbeitsbereichZeitraum(i, GesamtUnternehmen, selectedDate)) {
					Gesamtalter = Gesamtalter + ma.getAlter();
					aktiveMA++;
					if(ma.getAlter()<30) 							{ageUnder30All++;} 
					else if(ma.getAlter()>=30&&ma.getAlter()<40)	{age30to39All++;} 
					else if(ma.getAlter()>=40&&ma.getAlter()<=50) 	{age40to50All++;}
					else 											{ageOver50All++;}
				}
			} else {
				if(checkArbeitsbereichZeitraum(i, arbeitsbereichnummer, selectedDate)) {
					Gesamtalter = Gesamtalter + ma.getAlter();
					aktiveMA++;
					if(ma.getAlter()<30) 							{ageUnder30++;} 
					else if(ma.getAlter()>=30&&ma.getAlter()<40) 	{age30to39++;} 
					else if(ma.getAlter()>=40&&ma.getAlter()<=50) 	{age40to50++;} 
					else 											{ageOver50++;}
				}
			}
			
		}
		
		this.aktiveMA = aktiveMA;
		if(aktiveMA == 0) {
			aktiveMA++;
		}
		return (""+(Gesamtalter/aktiveMA)+" Jahre");
	}
	
	/* 
	 * @description:Berechnet die Altersverteilung in Prozent
	 */
	public void calcAlterPercent(int arbeitsbereichnummer, Datum selectedDate) {
	
		// aktuelle Werte bekommen
		showDurchschnittsalter(arbeitsbereichnummer, selectedDate);
		
		double anzahlMA=this.aktiveMA;
			
		if(arbeitsbereichnummer == GesamtUnternehmen) {
			// Arbeitsbereich -1 = gesamtes Unternehmen
			double ageUnder30Alld = (double) ageUnder30All;
			double age30to39Alld = (double) age30to39All;
			double age40to50Alld = (double) age40to50All;
			double ageOver50Alld = (double) ageOver50All;
						
			ageUnder30Allp = (ageUnder30Alld/anzahlMA)*100;
			age30to39Allp = (age30to39Alld/anzahlMA)*100;
			age40to50Allp = (age40to50Alld/anzahlMA)*100;
			ageOver50Allp = (ageOver50Alld/anzahlMA)*100;
				
		} else {
			
			double ageUnder30d = (double) ageUnder30;
			double age30to39d = (double) age30to39;
			double age40to50d = (double) age40to50;
			double ageOver50d = (double) ageOver50;
			
			ageUnder30p = (ageUnder30d/aktiveMA)*100;
			age30to39p = (age30to39d/aktiveMA)*100;
			age40to50p = (age40to50d/aktiveMA)*100;
			ageOver50p = (ageOver50d/aktiveMA)*100;
					
		}
			
	}
	
	/*
	 * @description:Berechnet die Geschlechtsverteilung in Prozent
	 */
	public void calcGeschlechtPercent(int arbeitsbereichnummer, Datum selectedDate) {
	
		resetGenderValues();
		aktiveMA=0;
		
		showGeschlechterverteilung(arbeitsbereichnummer, selectedDate);
		
		double countGenderMd = (double) countGenderM;
		double countGenderWd = (double) countGenderW;
		double countGenderDd = (double) countGenderD;
		double countGenderd = (double) countGender;
			
		if(arbeitsbereichnummer == GesamtUnternehmen) {
			countGenderMAllp = countGenderMAllp/aktiveMA*100;
			countGenderWAllp = countGenderWAllp/aktiveMA*100;
			countGenderDAllp = countGenderDAllp/aktiveMA*100;
			countGenderAllp = countGenderAllp/aktiveMA*100;
		} else {
			countGenderMp = countGenderMd/aktiveMA*100;
			countGenderWp = countGenderWd/aktiveMA*100;
			countGenderDp = countGenderDd/aktiveMA*100;
			countGenderp = countGenderd/aktiveMA*100;
		}	
	}
	
	/* 
	 * @description:Gibt die Geschlechterverteilung aus
	 */
	public void showGeschlechterverteilung(int arbeitsbereichnummer, Datum selectedDate) {
		
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++) {
			char gender = Personalverwaltung.getaMA().get(i).getGeschlecht();
			
			if(arbeitsbereichnummer == GesamtUnternehmen) {
				if(checkArbeitsbereichZeitraum(i, arbeitsbereichnummer, selectedDate)) {
					aktiveMA++;
					if(gender == 'm') 			{countGenderMAllp++;
					} else if (gender == 'w') 	{countGenderWAllp++;
					} else if (gender == 'd') 	{countGenderDAllp++;
					} else 						{countGenderAllp++;
					}	
				}
			} else {
				if(checkArbeitsbereichZeitraum(i, arbeitsbereichnummer, selectedDate)) {
					aktiveMA++;
					if(gender == 'm') 			{countGenderM++;
					} else if (gender == 'w')	{countGenderW++;
					} else if (gender == 'd') 	{countGenderD++;
					} else 						{countGender++;
					}
				}
			}			
		}
	}
	
	/* 
	 * @description:Gibt die Fluktuationsquote nach Schlüter für das aktuelle Jahr aus
	 */
	public String showFluktuationsquoteAll(Datum selectedDate) {
			
		int year = selectedDate.getJahr();
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
			
			} catch (NullPointerException e){ }
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
	
	/*
	 * @description:Gibt die Fluktuationsquote nach Schlüter für das aktuelle Jahr aus
	 */
	public String showFluktuationsquote(int arbeitsbereichnummer, Datum selectedDate) {
			
		int year = selectedDate.getJahr();	
		double zugaenge = 0;
		double abgaenge = 0;
		double aktiveMA = 0;
		
		// Personalabgaenge & Zugaenge der Periode zaehlen
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++){
			Mitarbeiter ma = Personalverwaltung.getaMA().get(i);
			
			if(checkArbeitsbereichZeitraum(i, arbeitsbereichnummer, (new Datum()))){
				aktiveMA++;
			}
			
			 //Test ob MA vorher in entsprechendem AB war
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
				} catch (NullPointerException e){ }	
				if(ma.getEinstellungsdatum().getJahr() == year) {
					zugaenge++;
				}
			}
		}
		
		System.out.println("aktuelle MA: "+ aktiveMA+"\nZugaenge: "+zugaenge+"\nAbgaenge: "+abgaenge);
		double bestandBeginnPeriode = aktiveMA-zugaenge+abgaenge;
		
		// Berechnung nach Schlüter	
		double fluktuationsquote = (double) ((abgaenge/(zugaenge+bestandBeginnPeriode))*100);
		
		NumberFormat n = NumberFormat.getInstance();
		n.setMaximumFractionDigits(2); // max. 2 stellen hinter komma
		
		return ""+(n.format(fluktuationsquote))+" %";
			
	}
	
	/*
	 * @description:Gibt die gesamten Überstunden im aktuellen Jahr aus 
	 */
	public String showUeberstunden(int arbeitsbereichnummer) {
	
		double ueberminuten = 0;
		NumberFormat n = NumberFormat.getInstance();
		n.setMaximumFractionDigits(2); // max. 2 stellen hinter komma
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++) {
			Mitarbeiter ma = Personalverwaltung.getaMA().get(i);
			Zugehoerigkeit MaAb = ma.getActualAB();
			if(arbeitsbereichnummer != GesamtUnternehmen) {
				if(MaAb.getArbeitsbereichnummer() == arbeitsbereichnummer) {
					ueberminuten = ueberminuten + ma.getAzk().getUeberminuten();
					this.gesamtUeberstunden = (ueberminuten/60);
				}
			} else {
				if(arbeitsbereichnummer!=abNrAusgeschieden) {
					ueberminuten = ueberminuten + ma.getAzk().getUeberminuten();
					this.gesamtUeberstundenU = (ueberminuten/60);
				}
			}
		}	
		return ""+(n.format((ueberminuten/60)))+" Stunden";	
	}
	
	/* 
	 * @description:Gibt die gesamten Überstunden im aktuellen Jahr pro Person aus 
	 */
	public String showUeberstundenSchnitt(int arbeitsbereichnummer) {

		double MA = (double) aktiveMA;
		NumberFormat n = NumberFormat.getInstance();
		n.setMaximumFractionDigits(2); // max. 2 stellen hinter komma
		
		if(gesamtUeberstundenU == 0 || aktiveMA == 0) {
			return ""+0+" Stunden";
		}
		if(arbeitsbereichnummer == GesamtUnternehmen) {
			return ""+n.format((gesamtUeberstundenU/MA))+" Stunden";
		} 
		if(gesamtUeberstunden == 0 || aktiveMA == 0) {
			return ""+0+" Stunden";
		}
		return ""+n.format((gesamtUeberstunden/MA))+" Stunden";	
	}
	
	/*
	 * @description:Gibt die gesamten Fehltage im aktuellen Jahr aus 
	 */
	public String showFehltage(int arbeitsbereichnummer) {
		
		int fehltage = 0;
		
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++) {
			
			if(checkArbeitsbereichZeitraum(i, arbeitsbereichnummer, (new Datum()))) {
				ArrayList<Eintrag> listFehltage = Personalverwaltung.getaMA().get(i).getAzk().getListe();
				for(int j = 0;j<listFehltage.size();j++) {
					if(listFehltage.get(j) instanceof Krankheitseintrag) {
						fehltage = fehltage +(listFehltage.get(j).getArbeitstage());
						
					}
				}
			}
		}
		return ""+fehltage+" Tage";	
	}
	
	/* 
	 * @description:Gibt die maximalen Fehltage einer Person im aktuellen Jahr aus 
	 */
	public String showFehltageMaximal(int arbeitsbereichnummer) {

		int fehltageMax = 0;
		int fehltagePerson = 0;
		
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++) {
			Mitarbeiter ma = Personalverwaltung.getaMA().get(i);
			Zugehoerigkeit MaAb = ma.getActualAB();
			if(arbeitsbereichnummer != GesamtUnternehmen) {	
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
			} else {
				ArrayList<Eintrag> listFehltage = Personalverwaltung.getaMA().get(i).getAzk().getListe();
				fehltagePerson = 0;
				if(arbeitsbereichnummer!=abNrAusgeschieden) {
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
		}
		return ""+fehltageMax+" Tage";	
	}
	
	/* 
	 * @description:Ermittelt die Anzahl der MA im Unternehmen zu einem Zeitpunkt
	 */
	public String showAnzahlMitarbeiterImUnternehmen(Datum selectedDate) {
		
		int countAllMA = 0;
		
		for(int i = 0; i< Personalverwaltung.getaMA().size();i++) {
			
				if(checkArbeitsbereichZeitraum(i, -1, selectedDate)) {
					countAllMA++;
				}		
		}
		return ""+countAllMA+" MitarbeiterInnen";	
	}
	
	/*
	 * @description:Ermittelt die Anzahl der MA in einem Arbeitsbereich zu einem Zeitpunkt
	 */
	public String showAnzahlMitarbeiterInArbeitsbereich(int arbeitsbereichnummer, Datum selectedDate) {
		
		int countAllMA = 0;
		
		for(int i = 0; i< Personalverwaltung.getaMA().size();i++) {
			
				if(checkArbeitsbereichZeitraum(i, arbeitsbereichnummer, selectedDate)) {
					countAllMA++;
				}
		}
		return ""+countAllMA+" MitarbeiterInnen";
	}
	
	/* 
	 * @description:Ermittelt, ob ein Mitarbeiter zu einem bestimmten Zeitraum in einem bestimmten Arbeitsbereich war
	 */
	private boolean checkArbeitsbereichZeitraum(int i,int arbeitsbereichnummer, Datum selectedDate) {
	
		Mitarbeiter ma = Personalverwaltung.getaMA().get(i);
		ArrayList<Zugehoerigkeit> zugListe = ma.getZugehoerigkeit();	
		if(selectedDate.compareTo((new Datum()))==0){
			if(arbeitsbereichnummer == GesamtUnternehmen) {
				
				if(ma.getActualAB().getArbeitsbereichnummer() != abNrAusgeschieden) {
					return true;
				}
				
			} else if (ma.getActualAB().getArbeitsbereichnummer() == arbeitsbereichnummer){
				return true;
			}
			
		} else if (selectedDate.compareTo((new Datum()))==1){
			System.out.println("Datum liegt in der Zukunft");
		} else {
			
			// Datum liegt in der Vergangenheit
			if(Personalverwaltung.getaMA().get(i).getEinstellungsdatum().compareTo(selectedDate)==-1||Personalverwaltung.getaMA().get(i).getEinstellungsdatum().compareTo(selectedDate)==0) {
				
				// Mitarbeiter wurde am ausgewählten Tag oder davor eingestellt
				if(zugListe.size()>1) {
					// War in mehr als einer Abteilung
					int j = 0;
					int verbleibend = zugListe.size();
					int counter = 0;
					
					try {
						if(ma.getAusscheidungsdatum().compareTo(selectedDate)==1||ma.getAusscheidungsdatum()==null) {
						
							System.out.println(ma.getVorname()+" "+ma.getName()+" - "+ma.getEinstellungsdatum().toString()+" - "+ma.getActualAB().getStart()+" - "+ma.getActualAB().getArbeitsbereichnummer());
							
							// Suche das Startdatum, welches am Nächsten am gewählten Datum liegt.
							while((zugListe.get(j).getStart().compareTo(selectedDate)==-1) && verbleibend>1) {
								j++;
								verbleibend = zugListe.size()-j;
								counter++;
								
							}
							if(counter==1) {
								j--;
							}
							
							if(arbeitsbereichnummer == GesamtUnternehmen) {
								if(zugListe.get(j).getArbeitsbereichnummer() != abNrAusgeschieden) {
									return true;
								}
								
							} else if (zugListe.get(j).getArbeitsbereichnummer()==arbeitsbereichnummer) {
								
								return true;
								
							}
						}
					} catch (NullPointerException e){
						while((zugListe.get(j).getStart().compareTo(selectedDate)==-1 || zugListe.get(j).getStart().compareTo(selectedDate)==0) && verbleibend>1) {
							j++;
							verbleibend = zugListe.size()-j;
							counter++;
						}
		
						if(counter==1) {
							j--;
						}
						
						if(arbeitsbereichnummer == GesamtUnternehmen) {
							if(zugListe.get(j).getArbeitsbereichnummer() != abNrAusgeschieden) {
								return true;
							}
							
						} else if (zugListe.get(j).getArbeitsbereichnummer()==arbeitsbereichnummer) {
							
							return true;
							
						}
					}
					
					
				} else {
					// MA nur in einem Arbeitsbereich
					try {
						if(arbeitsbereichnummer == GesamtUnternehmen) {
							
							if(ma.getAusscheidungsdatum().compareTo(selectedDate)==1||ma.getAusscheidungsdatum()==null) {
								return true;
							}
							
						} else if (ma.getActualAB().getArbeitsbereichnummer() == arbeitsbereichnummer){
							if(ma.getAusscheidungsdatum().compareTo(selectedDate)==1||ma.getAusscheidungsdatum()==null) {
								return true;
							}
						}
					} catch(NullPointerException e) {
						if(arbeitsbereichnummer == GesamtUnternehmen) {
								return true;
						} else if (ma.getActualAB().getArbeitsbereichnummer() == arbeitsbereichnummer){
								return true;
						}
					}
				}
			}
		}
		return false;		
		
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
	
	public double getAgeUnder30Allp() {
		return ageUnder30Allp;
	}

	public double getAge30to39Allp() {
		return age30to39Allp;
	}

	public double getAge40to50Allp() {
		return age40to50Allp;
	}

	public double getAgeOver50Allp() {
		return ageOver50Allp;
	}

	public double getAgeUnder30p() {
		return ageUnder30p;
	}

	public double getAge30to39p() {
		return age30to39p;
	}

	public double getAge40to50p() {
		return age40to50p;
	}

	public double getAgeOver50p() {
		return ageOver50p;
	}

	public int getAktiveMA() {
		return aktiveMA;
	}

	public double getGesamtUeberstunden() {
		return gesamtUeberstunden;
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

	public double getCountGenderMAllp() {
		return countGenderMAllp;
	}

	public double getCountGenderWAllp() {
		return countGenderWAllp;
	}

	public double getCountGenderDAllp() {
		return countGenderDAllp;
	}

	public double getCountGenderAllp() {
		return countGenderAllp;
	}

	public double getCountGenderMp() {
		return countGenderMp;
	}

	public double getCountGenderWp() {
		return countGenderWp;
	}

	public double getCountGenderDp() {
		return countGenderDp;
	}

	public double getCountGenderp() {
		return countGenderp;
	}
}
