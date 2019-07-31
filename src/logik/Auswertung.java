package logik;



public class Auswertung {
	private int ageUnder30;
	private int age30to39;
	private int age40to50;
	private int ageOver50;
	
	
	public String showDurchschnittsalter(int arbeitsbereichnummer) {
		/* @author: 	Jakob Küchler
		 * @date: 		31.07.2019
		 * @description:Gibt das Durchschnittsalter der aktuellen Mitarbeiter aus
		 */
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		
		int Gesamtalter = 0;
		int aktiveMA = 0;
		
		for(int i = 0;i<pv.getaMA().size();i++) {
			
			Mitarbeiter ma = pv.getaMA().get(i);
			int laengeZugehoerigkeitsliste = ma.getZugehoerigkeit().size();
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
		// TO DO
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
	
	public void showFehltage(int arbeitsbereichnummer) {
		/* @author: 	Jakob Küchler
		 * @date: 		31.07.2019
		 * @description:Gibt die gesamten Fehltage im aktuellen Jahr aus 
		 */
		Personalverwaltung pv = Personalverwaltung.getInstance();
		int fehltage = 0;
		
		for(int i = 0;i<pv.getaMA().size();i++) {
//			pv.getaMA().get(i).get
		}
		
		
	}
	
	public void showFehltageDurchschnitt() {
		// TO DO
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
