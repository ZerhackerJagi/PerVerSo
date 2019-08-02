package _programmstart;

import gui.LoginGUI;
import logik.*;

public class Programmstart {

	public static void main(String[] args) throws Exception {		
		/*@author:		Soeren Hebestreit
		 *@date: 		19.07.2019
		 *@description: Programmstart: PV und ABV laden, GUI starten
		 */

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		pv.laden("Normal");
		abv.laden("Normal");
			
		new LoginGUI();
	}
	
}
