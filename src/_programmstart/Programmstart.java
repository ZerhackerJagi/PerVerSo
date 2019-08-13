package _programmstart;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import gui.LoginGUI;
import logik.Arbeitsbereichverwaltung;
import logik.Personalverwaltung;
/*@author:		Soeren Hebestreit
 *@date: 		19.07.2019
 *@description: Programmstart: PV und ABV laden, GUI starten | Initialisieren falls Daten nicht gefunden
 */

public class Programmstart {
	
	public static ArrayList<Image> iconlist;
	
	public static void main(String[] args) throws Exception {		
		

		Personalverwaltung pv = Personalverwaltung.getInstance();
		Arbeitsbereichverwaltung abv = Arbeitsbereichverwaltung.getInstance();
		try {
			pv.setModus("Normal");
			pv.laden();
			abv.laden();
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			ArrayList<Image> icons = new ArrayList<Image>();
			Image bild16 = toolkit.getImage("pictures/PerVerSo16x16.png");
			Image bild32 = toolkit.getImage("pictures/PerVerSo32x32.png");
			icons.add(bild16);
			icons.add(bild32);
			iconlist = icons;
			
			
			new LoginGUI();
		} catch (Exception e) {
			new Init();
		}	
	}
	
}
