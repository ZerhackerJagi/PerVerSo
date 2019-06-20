package tests;

import logik.*;

class Test {

	public static void main(String[] args) {
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.show();
		Mitarbeiter[] liste = pv.getAktiveMA();
		Mitarbeiter eins = liste[0];
		Berechtigung test = eins.getBerechtigung();
		if(test instanceof Admin) {
			Admin blub = (Admin) test;
			System.out.println("Ändere Berechtigung...");
			blub.changeBerechtigung(00001);
			System.out.println("Geändert.");
		}
		test = eins.getBerechtigung();
		if(test instanceof User) {
			System.out.println("Bin jetzt User :(");
		}
		
		
	}
}
