package logik;

import interfaces.*;

public class Arbeitsbereichverwaltung implements Verwaltung {

	private Arbeitsbereich[] bereiche;
	private static Arbeitsbereichverwaltung uniqueInstance;
	private static int arbeitsbereichnummer;
		
	public static Arbeitsbereichverwaltung getInstance() {
		/*@author: 		Jakob Küchler
		 *@date: 		20.06.2019
		 *@description:	Gibt die einzige Instanz von Arbeitsbereichverwaltung aus (Singleton)
		 */
			
		if(uniqueInstance == null) {
			uniqueInstance = new Arbeitsbereichverwaltung();
		}
		return uniqueInstance;
	}
	
	
	
	
	
	
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void speichern(String dateiname) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void laden(String dateiname) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object suchen(int nummer) {
		// TODO Auto-generated method stub
		return null;
	}

}
