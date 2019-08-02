package interfaces;

public interface DateizugriffIF {
	/*@author: 		Soeren Hebestreit
	 *@date: 		21.06.2019
	 *@description: gibt an, wie eine Dateizugriffsklasse implementiert sein muss 
	 */
	
	Object laden(String modus) throws Exception;
	boolean speichern(Object obj, String modus) throws Exception;

}
