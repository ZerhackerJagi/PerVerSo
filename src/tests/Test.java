package tests;

import java.util.ArrayList;

import logik.*;

class Test {

	public static void main(String[] args) throws Exception {
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.add("Test", "User", 'd', 1, 1, 2000, 1, 1, 2000, 0);
		pv.show();
		ArrayList<Mitarbeiter> liste = pv.getaMA();
		Mitarbeiter eins = liste.get(0);
		Mitarbeiter zwei = liste.get(1);
		Berechtigung test = eins.getBerechtigung();
		if(test instanceof Admin) {
			Admin blub = (Admin) test;
			System.out.println(blub.getPersonalID());
			System.out.println("Aendere Berechtigung von Nummer Zwei...");
			blub.changeBerechtigung(1);
			System.out.println("Geaendert.");
		}
		test = zwei.getBerechtigung();
		if(test instanceof Admin) {
			System.out.println("Bin jetzt Admin :)");
		}
	
	
	}
}
