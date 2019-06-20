package interfaces;

public interface Verwaltung {

	public void start();
	public void create();
	public void edit();
	public void delete();
	public void speichern();
	public void laden();
	public Object suchen(int nummer);
}
