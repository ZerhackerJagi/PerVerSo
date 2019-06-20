package logik;
import java.util.Date;

import interfaces.*;

public class Personalverwaltung implements Verwaltung {

	// PRIVAT
	private Mitarbeiter[] aktiveMA;
	
	
	// KONSTRUKTOR
	
	public Personalverwaltung() {
		aktiveMA = new Mitarbeiter[1];
		aktiveMA[0] = new Mitarbeiter("minda","admin","passwort",'d',new Date(),new Admin(),new Status(),new Zugehoerigkeit());
	}
	
	
	// METHODEN
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
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
	public void speichern() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void laden() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object suchen(int nummer) {
		// TODO Auto-generated method stub
		for(int i=0; i<aktiveMA.length;i++) {
			if(aktiveMA[i].getPersonalnummer() == nummer) {
				return aktiveMA[i];
			}
		}
		return null;
	}

}
