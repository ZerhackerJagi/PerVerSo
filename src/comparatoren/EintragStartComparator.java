package comparatoren;

import java.util.Comparator;
import logik.Eintrag;

public class EintragStartComparator implements Comparator <Eintrag> {
	/*@author: 		Soeren Hebestreit
	 *@date: 		19.07.2019
	 *@description:	Comparator Eintrag nach Startdatum
	 */
	
	public EintragStartComparator () {
		
		super();
	}
	
	public int compare(Eintrag e1, Eintrag e2) {

    	return e1.getStart().compareTo(e2.getStart());
    }

}

