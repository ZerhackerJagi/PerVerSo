package _programmstart;

import gui.LoginGUI;
import logik.Arbeitsbereichverwaltung;
import logik.Personalverwaltung;


public class Programmstart {

	public static void main(String[] args) throws Exception {		
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Programmstart: PV und ABV laden, GUI starten | Initialisieren falls Daten nicht gefunden
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		try {
			pv.laden();
			abv.laden();
			new LoginGUI();
		} catch (Exception e) {
			new Init();
		}	
	}
}
