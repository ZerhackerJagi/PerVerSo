package interfaces;
/*@author: 		Jakob Kuechler
 *@date: 		20.06.2019
 *@description: gibt an, wie eine Verwaltungsklasse implementiert sein muss. 
 */

public interface VerwaltungIF {
		
	public void start();
	public boolean delete(int nummer);
	public void show();
	public void speichern() throws Exception;
	public void laden() throws Exception;
	public Object suchen(int nummer);
	public void sortName();
	public void sortNumber();
}
