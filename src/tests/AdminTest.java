package tests;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;

import extern.Datum;
import logik.Admin;
import logik.Berechtigung;

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
	public void DtestEditMABerechtigung() throws Exception {
		Admin ad1 = new Admin(0);
		Admin ad2 = new Admin(2);
		Berechtigung be = (Berechtigung) ad2;
		assertFalse(ad1.editMABerechtigung(0, be ));
		assertFalse(ad1.editMABerechtigung(2 ,be));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //2
		assertTrue(ad1.editMABerechtigung(2,be));
	}	
	
	@Test
	public void FEditMAEinstellungsdatum() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.editMAEinstellungsdatum(3,new Datum(10,2,2019)));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //3
		assertTrue(ad1.editMAEinstellungsdatum(3,new Datum(10,2,2019)));
	}
	
	@Test
	public void GAusscheidenMA() {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.ausscheidenMA(0,new Datum(20,2,2017)));
		assertFalse(ad1.ausscheidenMA(3,new Datum(20,2,2020)));
		assertTrue(ad1.ausscheidenMA(3,new Datum(20,2,2017)));
		assertFalse(ad1.ausscheidenMA(4,new Datum(23,5,2017)));			
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
		
		assertFalse(ad1.editAZKVertragsdaten(5,40,25));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //5
		assertTrue(ad1.editAZKVertragsdaten(5,40,25));
	}
	
	@Test
	public void LtestAddAZKUeberminuten() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.addAZKUeberminuten(6,40));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //6
		assertTrue(ad1.addAZKUeberminuten(6,40));
		assertTrue(ad1.addAZKUeberminuten(6,-20));
	}
	
	@Test
	public void MteststarteAZKJahr() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.starteAZKJahr(7));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //7
		assertTrue(ad1.starteAZKJahr(7));
	}
	
	@Test
	public void NtestAZKUrlaubsberechnung() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.editAZKUrlaubsberechnung(8,25,10));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //8
		assertTrue(ad1.editAZKUrlaubsberechnung(8,25,10));
	}
	
	@Test
	public void OtestAddAZKUrlaub() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.addAZKUrlaub(9,new Datum(12,3,2018),new Datum(10,4,2018),20));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //9
		assertTrue(ad1.addAZKUrlaub(9,new Datum(12,3,2018),new Datum(10,4,2018),20));
	}
	
	@Test
	public void PtestAZKKrankheit() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.addAZKKrankheit(10,new Datum(12,3,2018),new Datum(10,4,2018),20));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //10
		assertTrue(ad1.addAZKKrankheit(10,new Datum(12,3,2018),new Datum(10,4,2018),20));
	}
	
	@Test
	public void QtestRemoveAZKUrlaub() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.removeAZKUrlaub(11,1));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //11
		assertTrue(ad1.removeAZKUrlaub(11,1));
	}
	
	@Test
	public void RtestRemoveAZKKrankheit() throws Exception {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.removeAZKKrankheit(12,1));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //12
		assertTrue(ad1.removeAZKKrankheit(12,1));
	}
	
	@Test
	public void StestArbeitsbereiche() {
		Admin ad1 = new Admin(0);
		assertFalse(ad1.editABName(2,"Kueche"));
		ad1.addAB("flur","bal"); // 2 AB 
		assertTrue(ad1.editABName(2,"Kueche"));
		
		assertFalse(ad1.editABBeschreibung(3,"sollte nicht da sein"));
		assertTrue(ad1.editABBeschreibung(2,"schöne Kueche"));
		
		assertFalse(ad1.delAB(0));
		assertFalse(ad1.delAB(3));
		assertTrue(ad1.delAB(2)); // 2 AB Weg
	}
	
	
}
