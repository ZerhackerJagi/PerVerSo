package logik;

import java.io.Serializable;

public class User extends Berechtigung implements Serializable {

//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private int personalID;

	
//******************** KONSTRUKTOR ********************	
	
	public User(int personalnummer) {
		personalID = personalnummer;
	}
	
	
//******************** GETTER & SETTER ********************
	
	public int getPersonalID() {
		return personalID;
	}


//	public void setPersonalID(int personalID) {
//		this.personalID = personalID;
//	}
		
	
}
