package _programmstart;

import gui.LoginGUI;
import logik.*;
/*@author:		Soeren Hebestreit
 *@date: 		19.07.2019
 *@description: Programmstart: PV und ABV laden, GUI starten
 */
public class Programmstart {

	public static void main(String[] args) throws Exception {		
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		pv.setDatenBank("Normal");
		pv.laden();
		abv.laden();
			
		new LoginGUI();
	}
	
}
