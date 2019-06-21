package interfaces;

public interface Verwaltung {
	/*@author: 		Jakob Küchler
	 *@date: 		20.06.2019
	 *@description: Gibt an, wie eine Verwaltungsklasse implementiert sein muss. 
	 */
	
	public void start();
	public void create() throws Exception;
	public void edit();
	public void delete();
	public void speichern() throws Exception;
	public void laden() throws Exception;
	public Object suchen(int nummer);
}
