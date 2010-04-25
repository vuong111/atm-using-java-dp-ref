package atm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	/** get current date time **/
	public static String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
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
	public static int parseInt(String s) {
		try {
			return Integer.parseInt(s);
		}
		catch (NumberFormatException nfe) {
			return -1;
		}
	}
	
	/** parse a string to a double **/
	public static double parseDouble(String s) {
		try {
			return Double.parseDouble(s);
		}
		catch (NumberFormatException nfe) {
			return -1;
		}
	}
}
