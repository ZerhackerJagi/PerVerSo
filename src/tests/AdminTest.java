package tests;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;

import extern.Datum;
import logik.Admin;
import logik.Berechtigung;

import org.junit.FixMethodOrder;
/*@author: 		Charly Spina
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminTest {
	
	@Test
	public void AtestEditMAStammdaten() {
		Admin ad1 = new Admin(0);
		assertTrue(ad1.editMAStammdaten(100000,"Admin","Nimda",'w',new Datum(9,9,1996)));
		assertFalse(ad1.editMAStammdaten(100001,"Charly","Spina",'m',new Datum(9,9,1996)));
	}
	
	@Test
	public void CtestEditMAPasswort() {
		Admin ad1 = new Admin(0);
		assertTrue(ad1.editMAPasswort(100000,"Neues Passwort"));
		assertFalse(ad1.editMAPasswort(100001,"Neues Passwort"));
	}
	
	@Test
	public void DtestChangeMABerechtigung() throws Exception {
		Admin ad1 = new Admin(100000);
		Admin ad2 = new Admin(100001);
		Berechtigung be = (Berechtigung) ad2;
		assertFalse(ad1.changeMABerechtigung(100000, be ));
		assertFalse(ad1.changeMABerechtigung(100001 ,be));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //1
		assertTrue(ad1.changeMABerechtigung(100001,be));
	}	
	
	@Test
	public void FEditMAEinstellungsdatum() throws Exception {
		Admin ad1 = new Admin(100000);
		assertFalse(ad1.editMAEinstellungsdatum(100002,new Datum(10,2,2019)));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //2
		assertTrue(ad1.editMAEinstellungsdatum(100002,new Datum(10,2,2019)));
	}
	
	@Test
	public void GAusscheidenMA() {
		Admin ad1 = new Admin(100000);
		assertFalse(ad1.ausscheidenMA(100000,new Datum(20,2,2017)));
		assertFalse(ad1.ausscheidenMA(100002,new Datum(20,2,2020)));
		assertTrue(ad1.ausscheidenMA(100002,new Datum(20,2,2017)));
		assertFalse(ad1.ausscheidenMA(100003,new Datum(23,5,2017)));			
	}
	
	
	@Test
	public void JtestremoveMA() {
		Admin ad1 = new Admin(100000);
		assertFalse(ad1.removeMA(100000));
		assertTrue(ad1.removeMA(100002)); // 2 weg 
	}
	
	@Test
	public void KtestEditAZKLimit() throws Exception {
		Admin ad1 = new Admin(100000);
		assertFalse(ad1.editAZKLimit(100003,30,100));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //3
		assertTrue(ad1.editAZKLimit(100003,30,100));
	}
		
		
	@Test
	public void KtestEditAZKVertragsdaten() throws Exception {	
		Admin ad1 = new Admin(100000);
		assertFalse(ad1.editAZKVertragsdaten(100004,40,25));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //4
		assertTrue(ad1.editAZKVertragsdaten(100004,40,25));
	}
	
	@Test
	public void LtestAddAZKUeberminuten() throws Exception {
		Admin ad1 = new Admin(100000);
		assertFalse(ad1.addAZKUeberminuten(100005,40));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //5
		assertTrue(ad1.addAZKUeberminuten(100005,40));
		assertTrue(ad1.addAZKUeberminuten(100005,-20));
	}
	
	@Test
	public void OtestAddAZKUrlaub() throws Exception {
		Admin ad1 = new Admin(100000);
		assertFalse(ad1.addAZKUrlaub(100006,new Datum(12,3,2018),new Datum(10,4,2018),20));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //6
		assertTrue(ad1.addAZKUrlaub(100006,new Datum(12,3,2018),new Datum(10,4,2018),20));
	}
	
	@Test
	public void PtestAZKKrankheit() throws Exception {
		Admin ad1 = new Admin(100000);
		assertFalse(ad1.addAZKKrankheit(100007,new Datum(12,3,2018),new Datum(10,4,2018),20));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //7
		assertTrue(ad1.addAZKKrankheit(100007,new Datum(12,3,2018),new Datum(10,4,2018),20));
	}
	
	@Test
	public void QtestRemoveAZKUrlaub() throws Exception {
		Admin ad1 = new Admin(100000);
		assertFalse(ad1.removeAZKUrlaub(100008,1));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //8
		assertTrue(ad1.removeAZKUrlaub(100008,1));
	}
	
	@Test
	public void RtestRemoveAZKKrankheit() throws Exception {
		Admin ad1 = new Admin(100000);
		assertFalse(ad1.removeAZKKrankheit(100009,1));
		ad1.addMA("Spina","Charly",'d',new Datum(9,9,1996), new Datum(12,2,2019),1); //9
		assertTrue(ad1.removeAZKKrankheit(100009,1));
	}
	
	@Test
	public void StestArbeitsbereiche() {
		Admin ad1 = new Admin(100000);
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
