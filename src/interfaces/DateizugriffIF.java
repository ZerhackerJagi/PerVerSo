package interfaces;
/*@author: 		Soeren Hebestreit
 *@date: 		21.06.2019
 *@description: gibt an, wie eine Dateizugriffsklasse implementiert sein muss 
 */

public interface DateizugriffIF {
		
	Object laden() throws Exception;
	boolean speichern(Object obj) throws Exception;

}
