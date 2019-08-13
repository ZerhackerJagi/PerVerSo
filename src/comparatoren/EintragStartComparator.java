package comparatoren;

import java.util.Comparator;
import logik.Eintrag;
/*@author: 		Soeren Hebestreit
 *@date: 		19.07.2019
 *@description:	Comparator Eintrag nach Startdatum
 */

public class EintragStartComparator implements Comparator <Eintrag> {
	
	
	public EintragStartComparator () {
		
		super();
	}
	
	@Override
	public int compare(Eintrag e1, Eintrag e2) {

    	return e1.getStart().compareTo(e2.getStart());
    }

}

