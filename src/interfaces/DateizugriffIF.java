package interfaces;

public interface DateizugriffIF {
	/*@author: 		Soeren Hebestreit
	 *@date: 		21.06.2019
	 *@description: gibt an, wie eine Dateizugriffsklasse implementiert sein muss 
	 */
	
	Object laden() throws Exception;
	boolean speichern(Object obj) throws Exception;

}
