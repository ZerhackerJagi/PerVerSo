package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import comparatoren.EintragStartComparator;
import extern.Datum;
import logik.*;
/*@author: 		Charly Spina
 */

public class EintragStartComparatorTest {

		Krankheitseintrag eintrM = new Krankheitseintrag(new Datum(1,2,2019), new Datum(3,2,2019),2);
		Krankheitseintrag eintrM2 = new Krankheitseintrag(new Datum(23,5,2016), new Datum(30,5,2016),7);
		
		@Test
		public void testStartGleich() {
			EintragStartComparator eintrComp = new EintragStartComparator();
			assertEquals(0,eintrComp.compare(eintrM,eintrM));
		}
		
		@Test
		public void testStartUnterschiedlich() {
			EintragStartComparator eintrComp = new EintragStartComparator();
			assertEquals(1,eintrComp.compare(eintrM,eintrM2));
		}
}
