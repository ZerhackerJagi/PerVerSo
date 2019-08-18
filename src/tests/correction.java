package tests;

import logik.Mitarbeiter;
import logik.Personalverwaltung;

public class correction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.setModus("Beispiel");
		try {
			pv.laden();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(100006);
		ma.display();
		System.out.println(ma.getAusscheidungsdatum());
		ma.setAusscheidungsdatum(null);
		System.out.println(ma.getAusscheidungsdatum());
		try {
			pv.speichern();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
