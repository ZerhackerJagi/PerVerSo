package arbeitszeitplaene;

import java.util.*;

import logik.Mitarbeiter;

public class Schicht {
	private Date date;
	private Mitarbeiter ma;
	private Date startTime;
	private Date endTime;
	
	public Schicht(Date datum, Mitarbeiter mitarbeiter, Date startzeit, Date endzeit) {
		this.date = datum;
		this.ma = mitarbeiter;
		this.startTime = startzeit;
		this.endTime = endzeit;
	}
}
