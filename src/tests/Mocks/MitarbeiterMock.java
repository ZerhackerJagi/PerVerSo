package tests.Mocks;

import logik.*;
/*@author: 		Charly Spina
 */

public class MitarbeiterMock extends Mitarbeiter{
	private static final long serialVersionUID = 1L;
	private int persnr;
	private String name;
	private String vorname;
	
	public MitarbeiterMock(int persnr, String name, String vorname) {
		super();
		this.persnr = persnr;
		this.name = name;
		this.vorname = vorname;
	}
	
	public int getPersonalnummer() {
		return persnr;
	}
	
	public String getName() {
		return name;
	}
	
	public String getVorname() {
		return vorname;
	}
}
