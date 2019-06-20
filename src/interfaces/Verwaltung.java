package interfaces;

public interface Verwaltung {

	public void start();
	public void create() throws Exception;
	public void edit();
	public void delete();
	public void speichern(String dateiname) throws Exception;
	public void laden(String dateiname) throws Exception;
	public Object suchen(int nummer);
}
