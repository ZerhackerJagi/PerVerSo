package tests;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;

import extern.Datum;
import logik.Admin;

import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminTest {

	@Test
	public void AtestEditMAStammdaten() {
		Admin ad1 = new Admin(0);
		assertTrue(ad1.editMAStammdaten(0,"Admin","Nimda",'w',new Datum(9,9,1996)));
		assertFalse(ad1.editMAStammdaten(2,"Charly","Spina",'m',new Datum(9,9,1996)));
	}
	
	@Test
	public void BtestEditMABenutzername() {
		Admin ad1 = new Admin(0);
		assertTrue(ad1.editMABenutzername(0,"SuperUser"));
		assertFalse(ad1.editMABenutzername(2,"Charlynator"));
	}
	
	
	@Test
	public void CtestEditMAPasswort() {
		Admin ad1 = new Admin(0);
		assertTrue(ad1.editMAPasswort(0,"Neues Passwort"));
		assertFalse(ad1.editMAPasswort(2,"Neues Passwort"));
	}
	
	@Test
	public void DtestResetMABerechtigung() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.resetMABerechtigung(0));
		assertFalse(ad1.resetMABerechtigung(2));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //2
		assertTrue(ad1.resetMABerechtigung(2));
	}
	
	@Test
	public void EChangeMABerechtigung() {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.changeMABerechtigung(0));
		
	}
	
	@Test
	public void FEditMAEinstellungsdatum() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.editMAEinstellungsdatum(3,new Datum(10,2,2019)));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //3
		assertTrue(ad1.editMAEinstellungsdatum(3,new Datum(10,2,2019)));
	}
	
	@Test
	public void GEditMAAusscheidungsdatum() {
		Admin ad1 = new Admin(0);
		assertTrue(ad1.editMAAusscheidungsdatum(3,new Datum(20,2,2020)));
		assertFalse(ad1.editMAAusscheidungsdatum(4,new Datum(23,5,2020)));	
	}
	
	@Test
	public void HtestresetMAAzk() {
		Admin ad1 = new Admin(0);
		assertTrue(ad1.resetMAAzk(2));
		assertFalse(ad1.resetMAAzk(4));
	}
	
	@Test
	public void ItestdeleteMA() {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.deleteMA(0,new Datum(12,3,2029)));
		assertFalse(ad1.deleteMA(2, new Datum(12,3,2020)));
		assertFalse(ad1.deleteMA(4, new Datum(1,2,2018)));
		assertTrue(ad1.deleteMA(2,new Datum(1,2,2017))); // 2 weg
	}
	
	@Test
	public void JtestremoveMA() {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.removeMA(0));
		assertTrue(ad1.removeMA(3)); // 3 weg 
	}
	
	@Test
	public void KtestEditAZKVertragsdaten() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.editAZKVertragsdaten(4,40,25,30,100));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //4
		assertTrue(ad1.editAZKVertragsdaten(4,40,25,30,100));
	}
	
	@Test
	public void LtestaddAZKUeberminuten() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.addAZKUeberminuten(5,40));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //5
		assertTrue(ad1.addAZKUeberminuten(5,40));
		assertTrue(ad1.addAZKUeberminuten(5,-20));
	}
	
	@Test
	public void MteststarteAZKJahr() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.starteAZKJahr(6));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //6
		assertTrue(ad1.starteAZKJahr(6));
	}
	
	
	
}
