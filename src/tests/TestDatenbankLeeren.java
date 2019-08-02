package tests;

import org.junit.Test;

import logik.Personalverwaltung;

public class TestDatenbankLeeren {

	public Personalverwaltung pv;
	
	@Test
	public void KLeeredatenBank() throws Exception {
//		pv = Personalverwaltung.getInstance();
//		pv.resetPersonalverwaltung();
		pv = Personalverwaltung.getInstance();
		pv.speichern("Normal");
	}
}
