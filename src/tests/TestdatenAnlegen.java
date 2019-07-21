package tests;

import extern.Datum;
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
		pv.add("Test", "Test", 'd', new Datum(1, 1, 2000), new Datum(18, 7, 2019), 9);
		pv.show();
		System.out.println("\nZugehörigkeit von Test: "+Personalverwaltung.getaMA().get(7).getActualAB());
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
		
		pv.add("Kuechler", "Jakob", 'm', new Datum(15, 5, 1997), new Datum(18, 6, 2019), 2, "admin", "auchAdmin");
		pv.add("Hebestreit", "Soeren", 'm', new Datum(15, 8, 1984), new Datum(18, 6, 2019), 3);
		pv.add("Spina", "Charly", 'm', new Datum(9, 9, 1996), new Datum(18, 6, 2019), 4);
		pv.add("Ossowski", "Armin", 'm', new Datum(4, 10, 1996), new Datum(18, 6, 2019), 5);
		pv.add("Jones", "Olivia", 'd', new Datum(9, 10, 1984), new Datum(18, 6, 2019), 1);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		pv.add("Bunk", "Sabrina", 'w', new Datum(22, 1, 1985), new Datum(18, 7, 2019), 2);
		
	}
	
	
	public static void anlegenAB() {
		
		Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
		
		av.add("Personal","Personalangelegenheiten");
		av.add("Development", "Entwickler und Designer");
		av.add("Vertrieb", "Kundenbetreuung, Handel");
		av.add("Support", "Kundenbetreuung, Telefondienst, Außendienst");
		
	}
	
	
	
}
