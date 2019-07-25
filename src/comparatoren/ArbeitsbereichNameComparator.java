package comparatoren;

import java.util.Comparator;
import logik.Arbeitsbereich;

public class ArbeitsbereichNameComparator implements Comparator <Arbeitsbereich> {
	/*@author: 		Soeren Hebestreit
	 *@date: 		21.06.2019
	 *@description:	Comparator Arbeitsbereich nach Name
	 */
	
	public ArbeitsbereichNameComparator () {
		
		super();
	}
	
	@Override
	public int compare(Arbeitsbereich a1, Arbeitsbereich a2) {

    	return a1.getName().compareTo(a2.getName());
    }
   
}

