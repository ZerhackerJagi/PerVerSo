package speicher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import interfaces.DateizugriffIF;
import logik.Arbeitsbereich;
import logik.Mitarbeiter;
/*
 *@author: 		Soeren Hebestreit
 *@date: 		21.06.2019
 *@description:	
 */
public class Dateizugriff implements DateizugriffIF{
	
//******************** PARAMETER ********************
	
	private static Dateizugriff uniqueInstance;
	private String DateiNamePv;
	private String DateiNameAbv;
	private String NamePv;
	private String NameAbv;
	
//******************** KONSTRUKTOR ********************
	//gibt die einzige Instanz von Dateizugriff aus (Singleton)
	public static Dateizugriff getInstance() {
		
			
		if(uniqueInstance == null) {
			uniqueInstance = new Dateizugriff();
		}
		return uniqueInstance;
	}
	
//******************** LADEN ********************
	
	//ermittelt die aufrufende Klasse und fuehrt die fallspeziefische laden-Methode aus
	public Object laden() throws Exception {
	
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		//System.out.println(stackTraceElements[2].getClassName().substring(stackTraceElements[2].getClassName().lastIndexOf(".")+1,stackTraceElements[2].getClassName().length()));
		String caller = stackTraceElements[2].getClassName().substring(stackTraceElements[2].getClassName().lastIndexOf(".")+1,stackTraceElements[2].getClassName().length());
		
		switch (caller) {
			case "Personalverwaltung": return loadPV();
			case "Arbeitsbereichverwaltung": return loadABV();
			default: return null;
		}
	}
	
	public void setDatenBank(String modus) {
		if(modus == "Test") {
			DateiNamePv = "TestPV.dat";
			NamePv = "TestPV";
			DateiNameAbv = "TestABV.dat";
			NameAbv = "TestABV";
		} else if(modus == "Beispiel") {
			DateiNamePv = "BeispielPV.dat";
			NamePv = "BeispielPV";
			DateiNameAbv = "BeispielABV.dat";
			NameAbv = "BeispielABV";
		} else if(modus == "Normal"){
			DateiNamePv = "DataPV.dat";
			NamePv = "DataPV";
			DateiNameAbv = "DataABV.dat";
			NameAbv = "DataABV";
		}
	}
	
	/*laedt Daten aus der PV-Datei und uebergibt diese */
	private Object loadPV() throws Exception {
				
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(DateiNamePv));
			ArrayList <Mitarbeiter> AL = (ArrayList <Mitarbeiter>)in.readObject();
			in.close();		
			return AL;
		} catch (Exception e) {
			throw new Exception("Datei nicht gefunden! "+e);
		}
	}
	
	//laedt Daten aus der ABV-Datei und uebergibt diese
	private Object loadABV() throws Exception {
	
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(DateiNameAbv));
			ArrayList <Arbeitsbereich> AL = (ArrayList <Arbeitsbereich>)in.readObject();
			in.close();		
			return AL;
		} catch (Exception e) {
			throw new Exception("Datei nicht gefunden! "+e);
		}
	}
	
//******************** SPEICHERN ********************	
	
	//ermittelt die aufrufende Klasse und fuehrt die fallspeziefische speichern-Methode aus
	public boolean speichern(Object obj) throws Exception {
		
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		//System.out.println(stackTraceElements[2].getClassName().substring(stackTraceElements[2].getClassName().lastIndexOf(".")+1,stackTraceElements[2].getClassName().length()));
		String caller = stackTraceElements[2].getClassName().substring(stackTraceElements[2].getClassName().lastIndexOf(".")+1,stackTraceElements[2].getClassName().length());
		
		switch (caller) {
			case "Personalverwaltung": return savePV(obj);
			case "Arbeitsbereichverwaltung": return saveABV(obj);
			default: return false;
		}
	}
	
	//legt Backup an und speichert uebergebene Daten in der PV-Datei
	private boolean savePV(Object obj) throws Exception {
		backup(NamePv);
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DateiNamePv));
			out.writeObject((ArrayList<Mitarbeiter>) obj);
			out.close();
			return true;
		} catch (Exception e) {
			throw new Exception("Personalverwaltungsdaten konnten nicht gespeichert werden! "+e);
		}	
	}
	
	//legt Backup an und speichert uebergebene Daten in der ABV-Datei 
	private boolean saveABV(Object obj) throws Exception {
		backup(NameAbv);
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DateiNameAbv));
			out.writeObject((ArrayList<Arbeitsbereich>) obj);
			out.close();
			return true;
		} catch (Exception e) {
			throw new Exception("Arbeitsbereichsdaten konnten nicht gespeichert werden! "+e);
		}	
	}
	
	//legt ein Backup an
	private boolean backup(String name) {
		
		File alt = new File(name+".dat");
		File neu = new File(name+"_old.dat");
		neu.delete();
		return alt.renameTo(neu);
	}
	
}
