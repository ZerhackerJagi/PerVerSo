package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tests.Mocks.MitarbeiterMock;
import comparatoren.*;
/*@author: 		Charly Spina
 */

public class MitarbeiterComparatorTest {
	
	MitarbeiterMock MitM1 = new MitarbeiterMock(12,"Spina", "Charly");
	MitarbeiterMock MitM2 = new MitarbeiterMock(14,"Spina", "Charlene");
	MitarbeiterNameComparator MitCompC = new MitarbeiterNameComparator();
	MitarbeiterNummerComparator MitCompInt = new MitarbeiterNummerComparator();
	
	   @Test
	   public void testNameGleich() {		  
	      assertEquals(0,MitCompC.compare(MitM1,MitM1));
	   }
	      
	   @Test
	   public void testNummerGleich() {
		  
	      assertEquals(0,MitCompInt.compare(MitM1,MitM1));
	   }
	      
	   @Test
	   public void testNummerUngleich() {
	 
	      assertEquals(-1,MitCompInt.compare(MitM1,MitM2));
	   }
	   
	   @Test
	   public void testNummerUngleich2() {
	      assertEquals(1,MitCompInt.compare(MitM2,MitM1));
	   }
}
