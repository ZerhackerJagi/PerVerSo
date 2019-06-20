package logik;

public class Arbeitsplan {
	private int kw;
	private Arbeitsbereich ab;
	private Schicht[] arbeitszeiten;
	
	public Arbeitsplan(int kw, Arbeitsbereich arbeitsbereich, Schicht[] arbeitszeiten) {
		this.kw = kw;
		this.ab = arbeitsbereich;
		this.arbeitszeiten = arbeitszeiten;
	}

	public void addMA(Schicht schicht) {
		// TO DO
	}
	
	public void rmMA(Schicht schicht) {
		// TO DO
	}
	
	public void showPlan() {
		// TO DO
	}

}
