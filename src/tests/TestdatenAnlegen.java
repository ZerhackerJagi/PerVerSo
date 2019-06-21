package tests;

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
		
	}

	
	public static void anlegenMA() throws Exception {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		
		pv.add("Heinrich", "Ralf", "rh17", 'm', 83, 8, 15);
		pv.add("Gülzow", "Klaus", "kg08", 'm', 85, 5, 9);
		pv.add("Bunk", "Sabrina", "sb09", 'w', 84, 7, 29);
		pv.add("Nowak", "Heike", "hn29", 'w', 85, 2, 4);
		pv.add("Ludewig", "Julia", "jl21", 'w', 84, 10, 9);
		pv.add("Rheis", "Richard", "rr07", 'm', 85, 1, 22);
		pv.add("Möller", "Dieta", "dm01", 'd', 92, 3, 14);
		pv.add("Jeske", "Ulrike", "uj28", 'w', 97, 7, 21);
		pv.add("Rucks", "Paul", "pr09", 'm', 93, 1, 21);
		pv.add("Giese", "Wolfgang", "wg18", 'm', 98, 8, 15);
		
	}
	
	public static void anlegenAB() {
		
	}
	
	
	
}
