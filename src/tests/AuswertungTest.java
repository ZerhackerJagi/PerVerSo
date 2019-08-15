package tests;
import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import extern.Datum;
import logik.Auswertung;
import logik.Personalverwaltung;


/*@author: 		Charly Spina
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuswertungTest {
	
	public Auswertung ausw;
	
	@Test
	public void Aladen() throws Exception {
		Personalverwaltung.getInstance().setModus("Beispiel");
		Personalverwaltung.getInstance().laden();
	}
	
	@Test
	public void BtestshowDurchschnittsalter() {
		ausw = new Auswertung();
		assertEquals("34 Jahre",ausw.showDurchschnittsalter(-1,new Datum()));
		assertEquals("0 Jahre",ausw.showDurchschnittsalter(0,new Datum()));
		assertEquals("36 Jahre",ausw.showDurchschnittsalter(2,new Datum()));
		assertEquals("42 Jahre",ausw.showDurchschnittsalter(3,new Datum()));
		assertEquals("31 Jahre",ausw.showDurchschnittsalter(4,new Datum()));
		assertEquals("0 Jahre",ausw.showDurchschnittsalter(5,new Datum()));
		assertEquals("25 Jahre",ausw.showDurchschnittsalter(6,new Datum()));
	}
	
	@Test
	public void CtestcalcAlterPercent() {
		ausw = new Auswertung();
		ausw.calcAlterPercent(-1,new Datum());
		assertEquals(47,(int) ausw.getAgeUnder30Allp());
		assertEquals(26,(int) ausw.getAge30to39Allp());
		assertEquals(5,(int) ausw.getAge40to50Allp());
		assertEquals(21,(int) ausw.getAgeOver50Allp());
		
		ausw.calcAlterPercent(0,new Datum());
		assertEquals(0,(int) ausw.getAgeUnder30p());
		assertEquals(0,(int) ausw.getAge30to39p());
		assertEquals(0,(int) ausw.getAge40to50p());
		assertEquals(0,(int) ausw.getAgeOver50p());
		
		ausw.calcAlterPercent(2,new Datum());
		assertEquals(33,(int) ausw.getAgeUnder30p());
		assertEquals(33,(int) ausw.getAge30to39p());
		assertEquals(0,(int) ausw.getAge40to50p());
		assertEquals(33,(int) ausw.getAgeOver50p());
		
		ausw.calcAlterPercent(3,new Datum());
		assertEquals(16,(int) ausw.getAgeUnder30p());
		assertEquals(33,(int) ausw.getAge30to39p());
		assertEquals(16,(int) ausw.getAge40to50p());
		assertEquals(33,(int) ausw.getAgeOver50p());
				
	}
	
	@Test
	public void DtestalcGeschlechtPercent() {
		ausw = new Auswertung();
		ausw.calcGeschlechtPercent(-1,new Datum());
		assertEquals(63,(int) ausw.getCountGenderMAllp());
		assertEquals(31,(int) ausw.getCountGenderWAllp());
		assertEquals(5,(int) ausw.getCountGenderDAllp());
		assertEquals(0,(int) ausw.getCountGenderAllp());
		
		ausw.calcGeschlechtPercent(0,new Datum());
		assertEquals(0,(int) ausw.getCountGenderMp());
		assertEquals(0,(int) ausw.getCountGenderWp());
		assertEquals(0,(int) ausw.getCountGenderDp());
		assertEquals(0,(int) ausw.getCountGenderp());
		
		ausw.calcGeschlechtPercent(2,new Datum());
		assertEquals(66,(int) ausw.getCountGenderMp());
		assertEquals(33,(int) ausw.getCountGenderWp());
		assertEquals(0,(int) ausw.getCountGenderDp()); 
		assertEquals(0,(int) ausw.getCountGenderp());
	}
	
	@Test
	public void EshowGeschlechterverteilung() {
		ausw = new Auswertung();
		ausw.showGeschlechterverteilung(-1,new Datum());
		assertEquals(12,(int) ausw.getCountGenderMAllp());
		assertEquals(6,(int) ausw.getCountGenderWAllp());
		assertEquals(1,(int) ausw.getCountGenderDAllp());
		assertEquals(0,(int) ausw.getCountGenderAllp());
		
		ausw.showGeschlechterverteilung(0,new Datum());
		assertEquals(0,(int) ausw.getCountGenderM());
		assertEquals(0,(int) ausw.getCountGenderW());
		assertEquals(0,(int) ausw.getCountGenderD());
		assertEquals(0,(int) ausw.getCountGender());
		
		ausw.showGeschlechterverteilung(2,new Datum());
		assertEquals(2,(int) ausw.getCountGenderM());
		assertEquals(1,(int) ausw.getCountGenderW());
		assertEquals(0,(int) ausw.getCountGenderD());
		assertEquals(0,(int) ausw.getCountGender());
		
	}
	
	@Test
	public void FshowFluktuationsquote() {
		ausw = new Auswertung();
		assertEquals("0 %",ausw.showFluktuationsquoteAll(new Datum()));
		assertEquals("0 %",ausw.showFluktuationsquote(0,new Datum()));
		assertEquals("0 %",ausw.showFluktuationsquote(2,new Datum()));
		
	}
	
	@Test
	public void GshowUeberstunden() {
		ausw = new Auswertung();
		assertEquals("137,4 Stunden",ausw.showUeberstunden(-1));
		assertEquals("0 Stunden",ausw.showUeberstunden(0));
		assertEquals("73,02 Stunden",ausw.showUeberstunden(2));
		
	}
		
	@Test
	public void HshowUeberstundenSchnitt() {
		ausw = new Auswertung();
		assertEquals("0 Stunden",ausw.showUeberstundenSchnitt(-1));
		ausw.showGeschlechterverteilung(-1,new Datum());
		ausw.showUeberstunden(-1);
		assertEquals("7,23 Stunden",ausw.showUeberstundenSchnitt(-1));
		ausw.showGeschlechterverteilung(0,new Datum());
		ausw.showUeberstunden(0);
		assertEquals("0 Stunden",ausw.showUeberstundenSchnitt(0));
		ausw.showGeschlechterverteilung(2,new Datum());
		ausw.showUeberstunden(2);
		assertEquals("3,32 Stunden",ausw.showUeberstundenSchnitt(2));
		
	}
	
	@Test
	public void IshowFehltage() {
		ausw = new Auswertung();
		assertEquals("40 Tage",ausw.showFehltage(-1));
		assertEquals("0 Tage",ausw.showFehltage(0));
		assertEquals("0 Tage",ausw.showFehltage(2));
		assertEquals("8 Tage",ausw.showFehltage(3));
		assertEquals("17 Tage",ausw.showFehltage(4));
		assertEquals("0 Tage",ausw.showFehltage(5));
		assertEquals("15 Tage",ausw.showFehltage(6));
	
	}
	
	@Test
	public void JshowFehltageMaximal() {
		ausw = new Auswertung();
		assertEquals("23 Tage",ausw.showFehltageMaximal(-1));
		assertEquals("0 Tage",ausw.showFehltageMaximal(0));
		assertEquals("0 Tage",ausw.showFehltageMaximal(2));
		assertEquals("5 Tage",ausw.showFehltageMaximal(3));
		assertEquals("7 Tage",ausw.showFehltageMaximal(4));
		assertEquals("0 Tage",ausw.showFehltageMaximal(5));
		assertEquals("12 Tage",ausw.showFehltageMaximal(6));
	}
	
	@Test
	public void KtestshowAnzahlMitarbeiterImUnternehmen() {
		ausw = new Auswertung();
		assertEquals("19 MitarbeiterInnen",ausw.showAnzahlMitarbeiterImUnternehmen(new Datum()));
	}
	
	@Test
	public void LtestshowAnzahlMitarbeiterInArbeitsbereich() {
		ausw = new Auswertung();
		assertEquals("0 MitarbeiterInnen",ausw.showAnzahlMitarbeiterInArbeitsbereich(0,new Datum()));
		assertEquals("3 MitarbeiterInnen",ausw.showAnzahlMitarbeiterInArbeitsbereich(2,new Datum()));
		assertEquals("6 MitarbeiterInnen",ausw.showAnzahlMitarbeiterInArbeitsbereich(3,new Datum()));
		assertEquals("7 MitarbeiterInnen",ausw.showAnzahlMitarbeiterInArbeitsbereich(4,new Datum()));
		assertEquals("0 MitarbeiterInnen",ausw.showAnzahlMitarbeiterInArbeitsbereich(5,new Datum()));
		assertEquals("3 MitarbeiterInnen",ausw.showAnzahlMitarbeiterInArbeitsbereich(6,new Datum()));
	}

}
