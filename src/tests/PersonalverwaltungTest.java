package tests;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import java.util.ArrayList;

import extern.Datum;
import logik.Mitarbeiter;
import logik.Personalverwaltung;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonalverwaltungTest {

	public Personalverwaltung pv;
	
	@Test
	public void AtestInstance() {
		pv = Personalverwaltung.getInstance();
		assertNotNull(pv);
	}
		
	@Test
	public void BtestAddAuto() throws Exception {
		pv = Personalverwaltung.getInstance();
		pv.add("Spina","Charly",'A', new Datum(9,9,1996), new Datum(10,9,2019), 1); // 2
		assertNotNull(pv.suchen(2));
	}
	
	@Test
	public void CtestAddAuto2() throws Exception {
		pv = Personalverwaltung.getInstance();
		pv.add("Spina","Charly",'A', new Datum(9,9,1996), new Datum(10,9,2019), 1); //3
		pv.add("Wolf","Burki",'B',new Datum(12,8,1970), new Datum(10,9,2019),1); //4
		assertNotNull(pv.suchen(3));
		assertNotNull(pv.suchen(4));
	}
	
	@Test
	public void DtestAddManuell() throws Exception {
		pv = Personalverwaltung.getInstance();
		pv.add("Spina","Charly",'A', new Datum(9,9,1996), new Datum(10,9,2019), 1,"Charlynator","ABC"); // 5
		assertNotNull(pv.suchen(5));
	}
		
	@Test
	public void EtestDelete() throws Exception {
		pv = Personalverwaltung.getInstance();
		pv.add("Spina","Charly",'A', new Datum(9,9,1996), new Datum(10,9,2019), 1); //6
		assertTrue(pv.delete(6));
		assertNull(pv.suchen(6));
	}
	
	@Test
	public void FtestDeleteFalse() {
		pv = Personalverwaltung.getInstance();
		assertFalse(pv.delete(10));
	}
	
	@Test
	public void GtestSpeichernLaden() throws Exception {
		pv = Personalverwaltung.getInstance();
		pv.add("Spina","Charly",'A', new Datum(9,9,1996), new Datum(10,9,2019), 1); // 7
		pv.add("Wolf","Burki",'B',new Datum(12,8,1970), new Datum(10,9,2019),1); // 8
		assertNotNull(pv.suchen(7));
		assertNotNull(pv.suchen(8));
		assertNull(pv.suchen(9));
		pv.speichern();
		pv.add("Wolf","Burki",'B',new Datum(12,8,1970), new Datum(10,9,2019),1); // 8
		pv.laden();
		assertNull(pv.suchen(9));
	}
	
	@Test
	public void HtestSortierenName() throws Exception {
		pv = Personalverwaltung.getInstance();
		pv.add("Aldi","Anni",'B',new Datum(12,8,1970), new Datum(10,9,2019),1); //9
		assertEquals("admin",pv.getaMA().get(0).getName());
		pv.sortName();
		assertEquals("Aldi",pv.getaMA().get(0).getName());		
	}
	
	@Test
	public void ItestSortierenNummer() throws Exception {
		pv = Personalverwaltung.getInstance();
		assertEquals("Aldi",pv.getaMA().get(0).getName());
		pv.sortNumber();
		assertEquals("admin",pv.getaMA().get(0).getName());
	}
	
	
	
	
}
