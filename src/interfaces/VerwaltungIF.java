package interfaces;

public interface VerwaltungIF {
	/*@author: 		Jakob Kuechler
	 *@date: 		20.06.2019
	 *@description: gibt an, wie eine Verwaltungsklasse implementiert sein muss. 
	 */
	
	public void start();
	public boolean delete(int nummer);
	public void show();
	public void speichern(String modus) throws Exception;
	public void laden(String modus) throws Exception;
	public Object suchen(int nummer);
	public void sortName();
	public void sortNumber();
}
