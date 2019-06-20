package logik;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import interfaces.*;

// SINGLETON!!!
public class Personalverwaltung implements Verwaltung {

	// PRIVAT
	private static Mitarbeiter[] aktiveMA; //MA Liste
	private static Personalverwaltung uniqueInstance; //Einzigartige Instanz
	private static int personalnummer;
	//public static Personalverwaltung getInstance; 
	
	public static Personalverwaltung getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Personalverwaltung();
		}
		return uniqueInstance;
	}
	
	
	// KONSTRUKTOR
	private Personalverwaltung() {
		aktiveMA = new Mitarbeiter[1];
		aktiveMA[0] = new Mitarbeiter("minda","admin","passwort",'d',new Date(),new Admin(),new Default(),new Zugehoerigkeit(), createPersonalnummer());
	}
	
	// PRIVATE METHODEN (HILFSMITTEL)
	
	private void extendListAktiveMA() {
		Mitarbeiter[] newList =	new Mitarbeiter[(aktiveMA.length+1)];
		System.arraycopy(aktiveMA, 0, newList, 0, aktiveMA.length);
		this.aktiveMA = newList;
	}
	
	private int createPersonalnummer() {
		return personalnummer+1;
	}
	
	
	// ÖFFENTLICHE METHODEN
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() throws Exception {
		// 
		createMAonTerminal();
		
	}
	
	private void createMAonTerminal() throws Exception {
		// für Terminal
		
		// Liste um 1 Mitarbeiter erweitern
		extendListAktiveMA();
		
		// Abfrage essentieller Infos für Konstruktor
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Nachname: ");
		String name = br.readLine();
		System.out.print("Vorname: ");
		String vorname = br.readLine();
		System.out.print("Passwort eingeben: ");
		String passwort = br.readLine();
		System.out.print("Geschlecht (m/w/d): ");
		String eingabe = br.readLine();
		char gender = eingabe.charAt(0);
		System.out.print("Geburtsjahr: ");
		int bdayyear = Integer.parseInt(br.readLine());
		System.out.print("Geburtsmonat: ");
		int bdaymonth = Integer.parseInt(br.readLine());
		System.out.print("Geburtstag: ");
		int bdayday = Integer.parseInt(br.readLine());
		Date bday = new Date(bdayyear,bdaymonth,bdayday);
		
		//String name, String vorname, String passwort, char geschlecht, Date geburtstag, Berechtigung berechtigung, Status status, Zugehoerigkeit zugehoerigkeit)		
		aktiveMA[aktiveMA.length-1] = new Mitarbeiter(name, vorname, passwort, gender, bday, new User(),new Default(), new Zugehoerigkeit(), createPersonalnummer());
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void speichern(String dateiname) throws Exception{
		// TODO Auto-generated method stub
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dateiname));
		out.writeObject(uniqueInstance);
		out.close();
		
	}

	@Override
	public void laden(String dateiname) throws Exception {
		// TODO Auto-generated method stub
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(dateiname));
		this.uniqueInstance = (Personalverwaltung) in.readObject();
		in.close();
	}

	@Override
	public Object suchen(int nummer) {
		// TODO Auto-generated method stub
		for(int i=0; i<aktiveMA.length;i++) {
			if(aktiveMA[i].getPersonalnummer() == nummer) {
				return aktiveMA[i];
			}
		}
		return null;
	}
	
	public void show() {
		for(int i = 0; i<aktiveMA.length;i++) {
			System.out.println("Mitarbeiter: "+aktiveMA[i]);
		}
	}

// GETTER & SETTER
	public static Mitarbeiter[] getAktiveMA() {
		return aktiveMA;
	}


	public static void setAktiveMA(Mitarbeiter[] aktiveMA) {
		Personalverwaltung.aktiveMA = aktiveMA;
	}
	
	
}
