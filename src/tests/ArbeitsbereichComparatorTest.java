package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import comparatoren.*;
import logik.Arbeitsbereich;

public class ArbeitsbereichComparatorTest {

	   Arbeitsbereich arb1 = new Arbeitsbereich(1,"Küche","Bla");
	   Arbeitsbereich arb2 = new Arbeitsbereich(2,"NichtKüche","Bla2");
	   ArbeitsbereichNameComparator ArbCompC = new ArbeitsbereichNameComparator();
	   ArbeitsbereichNummerComparator ArbCompINT = new ArbeitsbereichNummerComparator();
	      
	   @Test
	   public void testNameGleich() {		  
	      assertEquals(0,ArbCompC.compare(arb1,arb1));
	   }
	   
//	   @Test
//	   public void testNameUngleich() {		 
//	      assertEquals(1,ArbCompC.compare(arb1,arb2));
//	   }
//	   
	   @Test
	   public void testNummerGleich() {
	      assertEquals(0,ArbCompINT.compare(arb1,arb1));
	   }
	      
	   @Test
	   public void testNummerUngleich() {
	 
	      assertEquals(-1,ArbCompINT.compare(arb1,arb2));
	   }
	   
	   @Test
	   public void testNummerUngleich2() {
	      assertEquals(1,ArbCompINT.compare(arb2,arb1));
	   }
	   
	}
