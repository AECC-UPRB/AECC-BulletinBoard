package edu.aecc.bulletin.test;

import edu.aecc.bulletin.utilities.Logger;

/**
 * Application that tests the Logger utility.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
public class LoggerTest {

	/**
	 * Entry point.
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String[] args) {
		Logger.init();
		System.out.println("potato");
		System.out.println("tomato");
	}

}
