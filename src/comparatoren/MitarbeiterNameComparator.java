package comparatoren;

import java.util.Comparator;
import logik.Mitarbeiter;


public class MitarbeiterNameComparator implements Comparator <Mitarbeiter> {
	/*@author: 		Soeren Hebestreit
	 *@date: 		21.06.2019
	 *@description:	Comparator Mitarbeiter nach Namen, Vorname
	 */
	
	public MitarbeiterNameComparator () {
		// Konstruktor
		
		super();
	}
	
	
   public int compare(Mitarbeiter m1, Mitarbeiter m2) {

	   		
    		int x = m1.getName().compareTo(m2.getName());
    		if (x == 0) {
    			return m1.getVorname().compareTo(m2.getVorname());
    		} else {
    			return x;
    		}
    }
}

