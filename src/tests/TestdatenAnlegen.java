package tests;

import java.util.Collections;

import comparatoren.*;
import logik.Arbeitsbereichverwaltung;
import logik.Personalverwaltung;

public class TestdatenAnlegen {
	
	public static void main(String[] args) throws Exception {
		
		anlegenMA();
		anlegenAB();
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		pv.show();
		System.out.println("--------");
		pv.speichern();
		System.out.println("--------");
		pv.add("Test", "Test", 'd', 2000, 1, 1);
		pv.show();
		System.out.println("--------");
		pv.laden();
		pv.show();
		System.out.println("--------");
		pv.sortName();
		pv.show();
		System.out.println("--------");
		pv.sortNumber();
		pv.show();
		
		System.out.println("--------");
		System.out.println("--------");
		
		Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
		av.show();
		System.out.println("--------");
		av.speichern();
		System.out.println("--------");
		av.add("Test", "Testabteilung");
		av.show();
		System.out.println("--------");
		av.laden();
		av.show();
		System.out.println("--------");
		av.sortName();
		av.show();
		System.out.println("--------");
		av.sortNumber();
		av.show();
	}

	
	public static void anlegenMA() throws Exception {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		
		pv.add("Mueller", "Michael", 'm', 15, 8, 1984);
		pv.add("Maier", "Boris", 'm', 9, 5, 1985);
		pv.add("Mueller", "Katrin", 'w', 29, 7, 1984);
		pv.add("Runge", "Dieter", 'm', 4, 2, 1985);
		pv.add("Guelzow", "Klaus", 'm', 9, 10, 1984);
		pv.add("Bunk", "Sabrina", 'w', 22, 1, 1985);
		pv.add("Bechler", "Dietmar", 'd', 14, 3, 1992);
		pv.add("Ludewig", "Julia", 'w', 21, 7, 1997);
		
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
