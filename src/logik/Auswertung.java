package logik;

import java.util.ArrayList;

public class Auswertung {
	private int ageUnder30;
	private int age30to39;
	private int age40to50;
	private int ageOver50;
	
	private int countGenderM;
	private int countGenderW;
	private int countGenderD;
	private int countGender;
	
	
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
		
		return (""+(Gesamtalter/aktiveMA)+" Jahre");
	}
	
	
	public void showGeschlechtsverteilung(int arbeitsbereichnummer) {
		/* @author: 	Jakob Küchler
		 * @date: 		31.07.2019
		 * @description:Gibt die Geschlechtsverteilung aus
		 */
		
		for(int i = 0;i<Personalverwaltung.getaMA().size();i++) {
			if(Personalverwaltung.getaMA().get(i).getActualAB().getArbeitsbereichnummer() == arbeitsbereichnummer) {
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
	
	public void showFluktuationsquote(int arbeitsbereichnummer) {
		// TO DO
	}
	
	public void showUeberstunden(int arbeitsbereichnummer) {
		/* @author: 	Jakob Küchler
		 * @date: 		31.07.2019
		 * @description:Gibt die gesamten Überstunden im aktuellen Jahr aus 
		 */
		
		
		
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
				System.out.println("aktueller MA: "+Personalverwaltung.getaMA().get(i).getName());
				ArrayList<Eintrag> listFehltage = Personalverwaltung.getaMA().get(i).getAzk().getListe();
				fehltagePerson = 0;
				for(int j = 0;j<listFehltage.size();j++) {
					if(listFehltage.get(j) instanceof Krankheitseintrag) {
						fehltagePerson = fehltagePerson +listFehltage.get(j).getArbeitstage();
						System.out.println("Fehltage jetzt: "+fehltagePerson);
					}
				}
				if(fehltagePerson>fehltageMax) {
					fehltageMax = fehltagePerson;
				}
			}	
		}
		return ""+fehltageMax+" Tage";
		
		
	}
	
	public void showFehltagePerson() {
		// TO DO
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
	
	
	
	
	
	
}
