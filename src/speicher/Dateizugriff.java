package speicher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import interfaces.DateizugriffIF;
import logik.Arbeitsbereich;
import logik.Arbeitsbereichverwaltung;
import logik.Mitarbeiter;
import logik.Personalverwaltung;

public class Dateizugriff implements DateizugriffIF{
	
//******************** PARAMETER ********************
	
	private static Dateizugriff uniqueInstance;
	private String DateiNamePv;
	private String DateiNameAbv;
	private String NamePv;
	private String NameAbv;
	
//******************** KONSTRUKTOR ********************
	
	public static Dateizugriff getInstance() {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	gibt die einzige Instanz von Dateizugriff aus (Singleton)
		 */
			
		if(uniqueInstance == null) {
			uniqueInstance = new Dateizugriff();
		}
		return uniqueInstance;
	}
	
//******************** LADEN ********************
	
	/*@author: 		Charly Spina
	 *@date: 		07.08.2019
	 *@description:	setzt den laden und speicher modus
	 */
	public void setDatenBank(String modus) {
		if(modus == "Test") {
			DateiNamePv = "TestPV.pvd";	
			NamePv = "TestPV";
			DateiNameAbv = "TestABV.avd";
			NameAbv = "TestABV";
		} else if(modus == "Beispiel") {
			DateiNamePv = "BeispielPV.pvd";
			NamePv = "BeispielPV";
			DateiNameAbv = "BeispielABV.avd";
			NameAbv = "BeispielABV";
		} else if(modus == "Normal"){
			DateiNamePv = "DataPV.pvd";
			NamePv = "DataPV";
			DateiNameAbv = "DataABV.avd";
			NameAbv = "DataABV";
		}
		Personalverwaltung.getInstance();
		Personalverwaltung.setPath(DateiNamePv);
		Arbeitsbereichverwaltung.getInstance();
		Arbeitsbereichverwaltung.setPath(DateiNameAbv);
	}
	
	@Override
	public Object laden() throws Exception {
		// TODO Auto-generated method stub
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	ermittelt die aufrufende Klasse und fuehrt die fallspeziefische laden-Methode aus 
		 */
		
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		//System.out.println(stackTraceElements[2].getClassName().substring(stackTraceElements[2].getClassName().lastIndexOf(".")+1,stackTraceElements[2].getClassName().length()));
		String caller = stackTraceElements[2].getClassName().substring(stackTraceElements[2].getClassName().lastIndexOf(".")+1,stackTraceElements[2].getClassName().length());
		
		switch (caller) {
			case "Personalverwaltung": return loadPV();
			case "Arbeitsbereichverwaltung": return loadABV();
			default: return null;
		}
	}
	
	private Object loadPV() throws Exception {
		// TODO Auto-generated method stub
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	laedt Daten aus der PV-Datei und uebergibt diese 
		 */
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(Personalverwaltung.getPath()));
			ArrayList <Mitarbeiter> AL = (ArrayList <Mitarbeiter>)in.readObject();
			in.close();		
			return AL;
		} catch (Exception e) {
			throw new Exception("Datei nicht gefunden! "+e);
		}
	}
	
	private Object loadABV() throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	laedt Daten aus der ABV-Datei und uebergibt diese 
		 */
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(Arbeitsbereichverwaltung.getPath()));
			ArrayList <Arbeitsbereich> AL = (ArrayList <Arbeitsbereich>)in.readObject();
			in.close();		
			return AL;
		} catch (Exception e) {
			throw new Exception("Datei nicht gefunden! "+e);
		}
	}
	
//******************** SPEICHERN ********************	
	
	@Override
	public boolean speichern(Object obj) throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	ermittelt die aufrufende Klasse und fuehrt die fallspeziefische speichern-Methode aus  
		 */
		
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		//System.out.println(stackTraceElements[2].getClassName().substring(stackTraceElements[2].getClassName().lastIndexOf(".")+1,stackTraceElements[2].getClassName().length()));
		String caller = stackTraceElements[2].getClassName().substring(stackTraceElements[2].getClassName().lastIndexOf(".")+1,stackTraceElements[2].getClassName().length());
		
		switch (caller) {
			case "Personalverwaltung": return savePV(obj);
			case "Arbeitsbereichverwaltung": return saveABV(obj);
			default: return false;
		}
	}
	
	private boolean savePV(Object obj) throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	legt Backup an und speichert uebergebene Daten in der PV-Datei 
		 */
		
		backup(NamePv,"pvd");
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DateiNamePv));
			out.writeObject((ArrayList<Mitarbeiter>) obj);
			out.close();
			return true;
		} catch (Exception e) {
			throw new Exception("Personalverwaltungsdaten konnten nicht gespeichert werden! "+e);
		}	
	}
		
	private boolean saveABV(Object obj) throws Exception {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	legt Backup an und speichert uebergebene Daten in der ABV-Datei 
		 */
		
		backup(NameAbv,"avd");
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DateiNameAbv));
			out.writeObject((ArrayList<Arbeitsbereich>) obj);
			out.close();
			return true;
		} catch (Exception e) {
			throw new Exception("Arbeitsbereichsdaten konnten nicht gespeichert werden! "+e);
		}	
	}
		
	private boolean backup(String name, String expansion) {
		/*@author: 		Soeren Hebestreit
		 *@date: 		21.06.2019
		 *@description:	legt ein Backup an 
		 */
		
		File alt = new File(name+"."+expansion);
		File neu = new File(name+"_old."+expansion);
		neu.delete();
		return alt.renameTo(neu);
	}
	
}
