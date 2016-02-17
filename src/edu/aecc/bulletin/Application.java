package edu.aecc.bulletin;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

/**
 * A controller that will handle the given executable.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
public class Application implements Runnable {

	private Executable exec;
	private Thread thread;
	private GraphicsDevice gd;
	private Screen screen;
	private JFrame frame;
	private boolean running;
	private long ticks;

	/**
	 * Initializes the application.
	 * 
	 * @param e
	 *            Executable to handle
	 */
	public Application(Executable e) {
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		exec = e;
		screen = new Screen(e);
	}

	/**
	 * Starts the executable, internal update thread and the screen.
	 */
	public void start() {
		exec.init(this);
		thread = new Thread(this);
		running = true;
		frame = new JFrame();
		frame.add(screen);
		frame.addKeyListener(screen);
		frame.setUndecorated(true);
		gd.setFullScreenWindow(frame);
		frame.setVisible(true);
		thread.start();
	}

	/**
	 * Stops the internal update thread.
	 */
	public void stop() {
		running = false;
	}

	/**
	 * Returns whether or not the internal update thread is active.
	 * 
	 * @return Whether or not the internal update thread is active
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Returns the Graphics Device that is in charge of the screen.
	 * 
	 * @return Graphics Device that is in charge of the screen
	 */
	public GraphicsDevice getGraphicsDevice() {
		return gd;
	}

	/**
	 * Code to be executed in the internal update thread.
	 */
	@Override
	public void run() {
		ticks = 0;
		while (running) {
			ticks++;
			exec.update(ticks);
			screen.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		frame.dispose();
		exec.stop();
	}
}
