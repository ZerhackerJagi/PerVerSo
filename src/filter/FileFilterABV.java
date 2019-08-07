package filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileFilterABV extends FileFilter {
	/*@author: 		Soeren Hebestreit
	 *@date: 		06.08.2019
	 *@description:	File Filter für die Datei der Arbeitsbereichverwaltung
	 */
	
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String extension = f.getName().substring(f.getName().lastIndexOf("."));
		if (extension != null) {
			if (extension.equals(".apvs")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Daten zur Arbeitsbereichverwaltung";
	}

}
