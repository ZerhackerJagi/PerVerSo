package _programmstart;

import gui.LoginGUI;
import logik.Arbeitsbereichverwaltung;
import logik.Personalverwaltung;

public class Programmstart {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Programmstart: PV und ABV laden, GUI starten
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		pv.laden();
		abv.laden();
		
		LoginGUI start = new LoginGUI();
	}

}
