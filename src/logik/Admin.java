package logik;

public class Admin extends Berechtigung{

	public Admin() {
		super();
	}
	
	public boolean changeBerechtigung(int personalnummer) {
		// MA raussuchen
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = (Mitarbeiter) pv.suchen(personalnummer);
		// MA auswählen
		try {
		// MA Berechtigung neu setzen
			if(ma.getBerechtigung() instanceof User) {
				ma.setBerechtigung(new Admin());
			} else {
				ma.setBerechtigung(new User());
			}
		} catch(NullPointerException e) {
			System.out.println("Mitarbeiternummer nicht vergeben");
			return false;
		}
		return true;
	}
	
}
