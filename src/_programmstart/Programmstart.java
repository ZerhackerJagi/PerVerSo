package _programmstart;

import javax.swing.JOptionPane;

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
		try {
			pv.laden();
			abv.laden();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Datenbank angelegt.", null, JOptionPane.INFORMATION_MESSAGE);
			pv.speichern();
			abv.speichern();
		}	
		
		new LoginGUI();
	}
	
}
