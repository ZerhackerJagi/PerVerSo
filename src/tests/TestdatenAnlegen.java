package tests;

import java.util.Random;

import extern.Datum;
import logik.Arbeitsbereichverwaltung;
import logik.Personalverwaltung;

public class TestdatenAnlegen {
	
	public static void main(String[] args) throws Exception {
		
		anlegenAB();
		anlegenMA();
		
		Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
		av.setModus("Normal");
		System.out.println("Arbeitsbereiche");
		System.out.println("---------------\n");
		av.show();
		av.speichern();
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		System.out.println("\n\nMitarbeiter");
		System.out.println("-----------\n");	
		pv.show();
		pv.speichern();	
	}

	
	public static void anlegenMA() throws Exception {
		
		Personalverwaltung pv = Personalverwaltung.getInstance();	
		
		String[] nachnamen = {"Müller", "Schmidt", "Schneider", "Fischer", "Meyer", "Weber", "Hofmann", 
				"Wagner", "Becker", "Schulz", "Schäfer", "Koch", "Bauer", "Richter", "Klein", "Schröder", 
				"Wolf", "Neumann", "Schwarz", "Schmitz", "Krüger", "Braun", "Zimmermann", "Schmitt", "Lange"
		};
		
		String[] vornamenM = {"Alexander", "Maximilian", "Lukas", "Philipp", "Daniel", "Jan", "Florian", "Niklas", 
				"Felix", "Domenik", "Christian", "Daniel", "Michael", "Sebastian", "Tobias", "Stefan", "Andreas",
				"Markus", "Thomas", "Matthias"
		};
		
		String[] vornamenW = {"Maria", "Sophie", "Julia", "Laura", "Anna", "Sarah", "Lisa", "Katharina", "Vanessa", 
				"Michelle", "Stefanie", "Christine", "Jennifer", "Sabrina", "Kathrin", "Nadine", "Sandra", "Daniela",
				"Melanie", "Kerstin"								
		};
		
		char[] genderAll = {'m','w','d'};
		char gender = 'd';
		Datum geburtstag;
		Datum einstellung;
		int bereiche = Arbeitsbereichverwaltung.getBereiche().size();
		
		int anzahl = 50;
		
		Random ran = new Random();
		
		for (int i = 0; i < anzahl; i++) {
			gender = genderAll[ran.nextInt(2)];
			geburtstag = new Datum(1+ran.nextInt(28),1+ran.nextInt(12),1970+ran.nextInt(32));
			einstellung = new Datum(1,1+ran.nextInt(12),2015+ran.nextInt(4));
			if (gender == 'm') {
				pv.add(nachnamen[ran.nextInt(25)], vornamenM[ran.nextInt(20)], gender, geburtstag, einstellung, 2+ran.nextInt(bereiche-2));
			} else {
				pv.add(nachnamen[ran.nextInt(25)], vornamenW[ran.nextInt(20)], gender, geburtstag, einstellung, 2+ran.nextInt(bereiche-2));
			}	
		}
		pv.add("Jones", "Olivia", 'd', new Datum(15, 5, 1987), new Datum(18, 6, 2019), 2);
	
	}
	
	public static void anlegenAB() {
		
		Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
		av.add("Personal","Personalangelegenheiten");
		av.add("Development", "Entwickler und Designer");
		av.add("Vertrieb", "Kundenbetreuung, Handel");
		av.add("Support", "Kundenbetreuung, Telefondienst, Außendienst");
	}	
}
