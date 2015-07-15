package mwatsonIMS;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * IMSmain is created to create a single instance of the Inventory Management
 * System
 * 
 * @author Matt Watson
 * 
 *
 */

public class IMSmain {

	public static void main(String args[]) throws SQLException {

		/**
		 * @throws Exception
		 *             This is here just in case the application cannot connect
		 *             to mySQL
		 * 
		 */
		try {

			String className = getLookAndFeelClassName("Nimbus");
			UIManager.setLookAndFeel(className);
			// Creates an instance of the Inventory Management System
			IMS ims = new IMS();
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getLookAndFeelClassName(String name) {
		LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo info : plafs) {
			if (info.getName().contains(name)) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UIDefaults defaults = UIManager.getLookAndFeelDefaults();
				defaults.put("Table.gridColor", new Color(214, 217, 223));
				defaults.put("Table.disabled", false);
				defaults.put("Table.showGrid", true);
				return info.getClassName();
			}
		}

		return null;

	}
}