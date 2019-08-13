package _programmstart;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import filter.FileFilterABV;
import filter.FileFilterPV;
import gui.LoginGUI;
import logik.Arbeitsbereichverwaltung;
import logik.Personalverwaltung;
/*@author:		Soeren Hebestreit
 *@date: 		06.08.2019
 *@description: Initialisierung wenn Daten nicht vorhanden
 *				1. Suche und neuen Pfad speichern
 *				2. Neu anlegen (nur Standardadmin)
 */
public class Init {
	
	public Init() {	
	
		Object[] options = {"Importieren", "Neu anlegen", "Exit"};
		int selected = JOptionPane.showOptionDialog(null, "<html>Daten nicht gefunden.<br>Bitte wählen sie eine Aktion:<br>_______________________ </html>", null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
		//System.out.println(selected);
		switch(selected) {
    		case 0: {
    			JFileChooser fc = new JFileChooser();
    			FileFilterPV fPV = new FileFilterPV();
    			fc.setAcceptAllFileFilterUsed(false);
    			fc.addChoosableFileFilter(fPV);
    			int returnVal = fc.showOpenDialog(null);
    			if (returnVal == 0) {
    				//System.out.println("öffnen");
    				System.out.println(fc.getSelectedFile().getAbsolutePath());
    				try {
    					Personalverwaltung.setPath(fc.getSelectedFile().getAbsolutePath());
						Personalverwaltung.getInstance().laden();
						fc = new JFileChooser();
						fc.setAcceptAllFileFilterUsed(false);
						fc.addChoosableFileFilter(new FileFilterABV());
		    			returnVal = fc.showOpenDialog(null);
		    			if (returnVal == 0) {
		    				//System.out.println("öffnen");
		    				System.out.println(fc.getSelectedFile().getAbsolutePath());
		    				try {
		    					Arbeitsbereichverwaltung.setPath(fc.getSelectedFile().getAbsolutePath());
		    					Arbeitsbereichverwaltung.getInstance().laden();
		    					Personalverwaltung.getInstance().speichern();
		    					Arbeitsbereichverwaltung.getInstance().speichern();
								new LoginGUI();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		    			} else if (returnVal == 1) {
		    				//System.out.println("abbrechen");
		    				new Init();
		    			}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			} else if (returnVal == 1) {
    				//System.out.println("abbrechen");
    				new Init();
    			}
    			break;
    		}
    		case 1: {
    			try {
    				Personalverwaltung.getInstance().speichern();
    				Arbeitsbereichverwaltung.getInstance().speichern();
    				JOptionPane.showMessageDialog(null, "<html>Datenbank angelegt. <br>Das Passwort für den Standardadmin entnehmen Sie bitte dem Handbuch.</html>", null, JOptionPane.INFORMATION_MESSAGE);
    				new LoginGUI();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			break;
    		}
    		case 2: {
    			System.exit(0);	
    		}
		}
	}

}
