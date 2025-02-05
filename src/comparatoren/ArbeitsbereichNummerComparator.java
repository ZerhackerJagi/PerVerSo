package comparatoren;

import java.util.Comparator;
import logik.Arbeitsbereich;
/*@author: 		Soeren Hebestreit
 *@date: 		21.06.2019
 *@description:	Comparator Arbeitsbereich nach Arbeitsbereichnummer
 */
public class ArbeitsbereichNummerComparator implements Comparator <Arbeitsbereich> {
	
	public ArbeitsbereichNummerComparator () {
		
		super();
	}
	
	@Override
	public int compare(Arbeitsbereich a1, Arbeitsbereich a2) {

	   if(a1.getArbeitsbereichnummer()>a2.getArbeitsbereichnummer()) {
			return 1;
		} else if(a1.getArbeitsbereichnummer()<a2.getArbeitsbereichnummer()) {
			return -1;
		} else {
			return 0;
		}
    }
	
}

