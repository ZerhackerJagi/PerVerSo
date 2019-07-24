package tests;

import java.util.ArrayList;

import extern.Datum;
import logik.Admin;
import logik.Berechtigung;
import logik.Mitarbeiter;
import logik.Personalverwaltung;

class TestBerechtigung {

	public static void main(String[] args) throws Exception {
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.add("Test", "User", 'd', new Datum(1, 1, 2000), new Datum(1, 1, 2000), 0);
		pv.show();
		ArrayList<Mitarbeiter> liste = Personalverwaltung.getaMA();
		Mitarbeiter eins = liste.get(0);
		Mitarbeiter zwei = liste.get(2);
		Berechtigung test = eins.getBerechtigung();
		if(test instanceof Admin) {
			Admin blub = (Admin) test;
			System.out.println(blub.getPersonalID());
			System.out.println("Aendere Berechtigung von Nummer Zwei...");
			blub.changeMABerechtigung(2, new Admin(2));
			System.out.println("Geaendert.");
		}
		test = zwei.getBerechtigung();
		if(test instanceof Admin) {
			System.out.println("Bin jetzt Admin :)");
		}
	}
}
