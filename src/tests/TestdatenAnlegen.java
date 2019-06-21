package tests;

import logik.Arbeitsbereichverwaltung;
import logik.Personalverwaltung;

public class TestdatenAnlegen {
	
	public static void main(String[] args) throws Exception {
		
		anlegenMA();
		anlegenAB();
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.display();
		System.out.println("--------");
		pv.speichern();
		System.out.println("--------");
		pv.add("Test", "Test", "x", 'd', 00, 1, 1);
		pv.display();
		System.out.println("--------");
		pv.laden();
		pv.display();
		
		System.out.println("--------");
		System.out.println("--------");
		
		Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
		av.display();
		System.out.println("--------");
		av.speichern();
		System.out.println("--------");
		av.add("Test", "Testabteilung");
		av.display();
		System.out.println("--------");
		av.laden();
		av.display();
	}

	
	public static void anlegenMA() throws Exception {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		
		pv.add("Mitarbeiter", "A", "maA83", 'm', 83, 8, 15);
		pv.add("Mitarbeiter", "B", "maB85", 'm', 85, 5, 9);
		pv.add("Mitarbeiter", "C", "maC84", 'w', 84, 7, 29);
		pv.add("Mitarbeiter", "D", "maC85", 'w', 85, 2, 4);
		pv.add("Mitarbeiter", "E", "maC84", 'w', 84, 10, 9);
		pv.add("Mitarbeiter", "F", "maC85", 'm', 85, 1, 22);
		pv.add("Mitarbeiter", "G", "maC92", 'd', 92, 3, 14);
		pv.add("Mitarbeiter", "H", "maC97", 'w', 97, 7, 21);
		
	}
	
	public static void anlegenAB() {
		
		Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
		
		av.add("Personal","Personalangelegenheiten");
		av.add("Development", "Entwickler und Designer");
		av.add("Vertrieb", "Kundenbetreuung, Handel");
		av.add("Support", "Kundenbetreuung, Telefondienst, Auﬂendienst");
		av.add("Ausgeschieden", "ehemalige Mitarbeiter");
		
	}
	
	
	
}
