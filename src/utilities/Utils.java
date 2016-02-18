package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Convenient collection of utilities.
 * 
 * @author Juan J. Alvarez <jalvarez5241997@gmail.com
 *
 */
public class Utils {

	/**
	 * Returns a string representation of the date in a file-safe format.
	 * 
	 * @return String representation of the date in a file-safe format
	 */
	public static String getDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		return df.format(d);
	}

	/**
	 * Returns a string representation of the time in a file-safe format.
	 * 
	 * @return String representation of the time in a file-safe format.
	 */
	public static String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("HH-mm-ss");
		Date d = new Date();
		return df.format(d);
	}

	/**
	 * Retrns a string representation of the date and time in a file-safe
	 * format.
	 * 
	 * @return String representation of the date and time in a file-safe format
	 */
	public static String getDateAndTime() {
		return getDate() + " " + getTime();
	}
}