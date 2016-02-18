package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * 
 * @author Juan J. Alvarez <jalvarez5241997@gmail.com>
 *
 */
public class Logger {

	/**
	 * The new standard print stream.
	 */
	public static final PrintStream stdout = new PrintStream(new StdOut());

	/**
	 * List of print streams that the standard print stream will distribute to.
	 */
	protected static ArrayList<PrintStream> streamList = new ArrayList<PrintStream>();

	/**
	 * Registers other streams and changes the standard and error print streams.
	 */
	public static void init() {
		File dir = new File("logs");
		if (!dir.exists())
			dir.mkdir();
		File log = new File(String.format("logs/log %s.log", Utils.getDateAndTime()));
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				shutdown();
			}
		});
		streamList.add(System.out);
		try {
			streamList.add(new PrintStream(new FileOutputStream(log)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(stdout);
		System.setErr(null);
	}

	/**
	 * Event handler for when the program closes.
	 */
	public static void shutdown() {
		for (PrintStream ps : streamList)
			ps.close();
	}

	/**
	 * Customized output stream to handle all logging information.
	 * 
	 * @author Juan J. Alvarez <jalvarez5241997@gmail.com>
	 *
	 */
	private static class StdOut extends OutputStream {

		private boolean newline;

		/**
		 * Parameterless constructor that inititializes the stream.
		 */
		public StdOut() {
			newline = true;
		}

		@Override
		public synchronized void write(int b) throws IOException {
			if (newline) {
				newline = false;
				String str = String.format("[%s][STDOUT] ", Utils.getDateAndTime());
				for (PrintStream ps : streamList)
					ps.print(str);
			}
			if (b == '\n')
				newline = true;
			for (PrintStream ps : streamList)
				ps.write(b);
		}
	}
}