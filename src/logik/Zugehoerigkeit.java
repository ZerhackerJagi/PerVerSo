package logik;

import java.io.Serializable;
import java.util.Date;

public class Zugehoerigkeit implements Serializable {
	private Date startDatum;
<<<<<<< HEAD
	private int arbeitsbereichnummer;
	
	public Zugehoerigkeit(Date start, int abteilung) {
		this.startDatum = start;
		this.arbeitsbereichnummer = abteilung;

	}
	
	public Zugehoerigkeit() {
		this.startDatum = new Date();
		this.arbeitsbereichnummer = 0;

	}

	public Date getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}

	
	
	
	
=======
	private int ma;
	private int ab;
	
	public Zugehoerigkeit(Date startDatum, int ma, int ab) {
		this.startDatum = startDatum;
		this.ma = ma;
		this.ab = ab;
	}
>>>>>>> branch 'master' of https://github.com/ZerhackerJagi/PerVerSo.git
}
