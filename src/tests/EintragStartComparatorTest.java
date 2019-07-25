package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import comparatoren.EintragStartComparator;
import extern.Datum;
import logik.Eintrag;

public class EintragStartComparatorTest {

		Eintrag eintrM = new Eintrag(new Datum(1,2,2019));
		Eintrag eintrM2 = new Eintrag(new Datum(23,5,2016));
		
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
