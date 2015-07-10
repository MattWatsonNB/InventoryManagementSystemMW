package mwatsonIMS;

import java.sql.SQLException;

/**IMSmain is created to create a single instance of the Inventory Management System
 * @author Matt Watson 
 * 
 *
 */

public class IMSmain {

	public static void main(String args[]) throws SQLException{
		
		
		/**
		 * @throws Exception This is here just in case the application cannot connect to mySQL
		 * 
		 */
		try {
			//Creates an instance of the Inventory Management System
			IMS ims = new IMS();
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}