package atm.utils;

import javax.swing.ImageIcon;

public class ATMUtils {
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	public static ImageIcon createImageIcon(Class targetClass, String path, String description) {
	    java.net.URL imgURL = targetClass.getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } else {
	        System.err.println("Không tìm thấy file: " + path);
	        return null;
	    }
	}
	
	/** Check if a string can be parsed to an integer number **/
	public static boolean isNumberKey(String i)
	{
		try {
			Integer.parseInt(i);
			return true;
		}
		catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	/** parse a string to an integer **/
	public static int parseInt(String i) {
		try {
			return Integer.parseInt(i);
		}
		catch (NumberFormatException nfe) {
			return 0;
		}
	}
}
