package tests;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import logik.Arbeitsbereichverwaltung;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArbeitsbereichverwaltungTest {

public Arbeitsbereichverwaltung abv;
	
	@Test
	public void AtestInstance() {
		abv = Arbeitsbereichverwaltung.getInstance();
		abv.setModus("Test");
		assertNotNull(abv);
	}
	
	@Test
	public void Btestadd() {
		abv = Arbeitsbereichverwaltung.getInstance();
		assertNull(abv.suchen(2));
		abv.add("kueche","bla"); //2
		assertNotNull(abv.suchen(2));	
	}
	
	@Test
	public void Ctestdelte() {
		abv = Arbeitsbereichverwaltung.getInstance();
		assertTrue(abv.delete(2));
		assertFalse(abv.delete(3));
		assertNull(abv.suchen(2));
	}
	
	@Test
	public void DtestSortierenName() {
		abv = Arbeitsbereichverwaltung.getInstance();
		abv.add("Abstellraum","balbla"); //3
		assertEquals("Undefined", abv.getBereiche().get(0).getName());
		abv.sortName();
		assertEquals("Abstellraum", abv.getBereiche().get(0).getName());
	}
	
	@Test
	public void EtestSortierenNummer() {
		abv = Arbeitsbereichverwaltung.getInstance();
		assertEquals("Abstellraum", abv.getBereiche().get(0).getName());
		abv.sortNumber();
		assertEquals("Undefined", abv.getBereiche().get(0).getName());
	}
	
	@Test
	public void FtestSpeichernLaden() throws Exception {
		abv = Arbeitsbereichverwaltung.getInstance();
		assertNotNull(abv.suchen(3));
		assertNull(abv.suchen(4));
		abv.speichern();
		abv.delete(3); // 3 weg
		abv.add("Aldi","Supermarkt"); //4
		abv.laden();
		assertNotNull(abv.suchen(3));
		assertNull(abv.suchen(4));	
	}
	
	@Test
	public void GresetAB() {
		abv = Arbeitsbereichverwaltung.getInstance();
		abv.resetArbeitsbereichverwaltung();
	}
	
}
