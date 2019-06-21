package interfaces;

public interface VerwaltungIF {
	/*@author: 		Jakob Kuechler
	 *@date: 		20.06.2019
	 *@description: gibt an, wie eine Verwaltungsklasse implementiert sein muss. 
	 */
	
	public void start();
	public void create();
	public void edit();
	public void delete();
	public void speichern() throws Exception;
	public void laden() throws Exception;
	public Object suchen(int nummer);
	public void sortName();
	public void sortNumber();
}
