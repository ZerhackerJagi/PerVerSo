package comparatoren;

import java.util.Comparator;
import logik.Mitarbeiter;

public class MitarbeiterNummerComparator implements Comparator <Mitarbeiter> {
	/*@author: 		Soeren Hebestreit
	 *@date: 		21.06.2019
	 *@description:	Comparator Mitarbeiter nach Personalnummer
	 */
	
	public MitarbeiterNummerComparator () {
		
		super();
	}
	
	public int compare(Mitarbeiter m1, Mitarbeiter m2) {

	   if(m1.getPersonalnummer()>m2.getPersonalnummer()) {
			return 1;
		} else if(m1.getPersonalnummer()<m2.getPersonalnummer()) {
			return -1;
		} else {
			return 0;
		}
    }

}

