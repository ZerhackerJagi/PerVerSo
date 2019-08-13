package comparatoren;

import java.util.Comparator;
import logik.Mitarbeiter;
/*@author: 		Soeren Hebestreit
 *@date: 		21.06.2019
 *@description:	Comparator Mitarbeiter nach Personalnummer
 */

public class MitarbeiterNummerComparator implements Comparator <Mitarbeiter> {
	
	
	public MitarbeiterNummerComparator () {
		
		super();
	}
	
	@Override
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

