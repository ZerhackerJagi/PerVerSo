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
	private String DateiNameAb;
	
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
	public Object laden(String modus) throws Exception {
	
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		//System.out.println(stackTraceElements[2].getClassName().substring(stackTraceElements[2].getClassName().lastIndexOf(".")+1,stackTraceElements[2].getClassName().length()));
		String caller = stackTraceElements[2].getClassName().substring(stackTraceElements[2].getClassName().lastIndexOf(".")+1,stackTraceElements[2].getClassName().length());
		
		switch (caller) {
			case "Personalverwaltung": return loadPV(modus);
			case "Arbeitsbereichverwaltung": return loadABV(modus);
			default: return null;
		}
	}
	
	/*laedt Daten aus der PV-Datei und uebergibt diese */
	private Object loadPV(String modus) throws Exception {
		
		if(modus == "Test") {
			DateiNamePv = "TestPV.dat";
		} else if(modus == "Beispiel") {
			DateiNamePv = "BeispielPV.dat";
		} else {
			DateiNamePv = "DataPV.dat";
		}
		
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
	private Object loadABV(String modus) throws Exception {
		if(modus == "Test") {
			DateiNameAb = "TestABV.dat";
		} else if(modus == "Beispiel") {
			DateiNameAb = "BeispielABV.dat";
		} else {
			DateiNameAb = "DataABV.dat";
		}
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(DateiNameAb));
			ArrayList <Arbeitsbereich> AL = (ArrayList <Arbeitsbereich>)in.readObject();
			in.close();		
			return AL;
		} catch (Exception e) {
			throw new Exception("Datei nicht gefunden! "+e);
		}
	}
	
//******************** SPEICHERN ********************	
	
	//ermittelt die aufrufende Klasse und fuehrt die fallspeziefische speichern-Methode aus
	public boolean speichern(Object obj, String modus) throws Exception {
		
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		//System.out.println(stackTraceElements[2].getClassName().substring(stackTraceElements[2].getClassName().lastIndexOf(".")+1,stackTraceElements[2].getClassName().length()));
		String caller = stackTraceElements[2].getClassName().substring(stackTraceElements[2].getClassName().lastIndexOf(".")+1,stackTraceElements[2].getClassName().length());
		
		switch (caller) {
			case "Personalverwaltung": return savePV(obj, modus);
			case "Arbeitsbereichverwaltung": return saveABV(obj, modus);
			default: return false;
		}
	}
	
	//legt Backup an und speichert uebergebene Daten in der PV-Datei
	private boolean savePV(Object obj, String modus) throws Exception {
		if(modus == "Test") {
			DateiNamePv = "TestPV.dat";
			backup(modus);
		} else if(modus == "Beispiel") {
			DateiNamePv = "BeispielPV.dat";
			backup(modus);
		} else {
			DateiNamePv = "DataPV.dat";
			backup("DataPV");
		}
		
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
	private boolean saveABV(Object obj, String modus) throws Exception {
		if(modus == "Test") {
			DateiNamePv = "TestABV.dat";
			backup(modus);
		} else if(modus == "Beispiel") {
			DateiNamePv = "BeispielABV.dat";
			backup(modus);
		} else {
			DateiNamePv = "DataABV.dat";
			backup("DataABV");
		}

		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DateiNameAb));
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
