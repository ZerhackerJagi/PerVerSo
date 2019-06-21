package logik;

import java.io.Serializable;
import java.util.Date;

public class Zugehoerigkeit implements Serializable {
	private Date startDatum;
	private int ma;
	private int ab;
	
	public Zugehoerigkeit(Date startDatum, int ma, int ab) {
		this.startDatum = startDatum;
		this.ma = ma;
		this.ab = ab;
	}
}
