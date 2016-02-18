package edu.aecc.bulletin.core;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import utilities.Logger;

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
	private boolean running;
	private long ticks;

	/**
	 * Initializes the application.
	 * 
	 * @param e
	 *            Executable to handle
	 */
	public Application(Executable e) {
		Logger.init();
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		exec = e;
		screen = new Screen(e);
	}

	/**
	 * Starts the executable, internal update thread and the screen.
	 */
	public void start() {
		System.out.println("Starting new application");
		exec.init(this);
		thread = new Thread(this);
		running = true;
		gd.setFullScreenWindow(screen.getFrame());
		screen.getFrame().setVisible(true);
		thread.start();
	}

	/**
	 * Stops the internal update thread.
	 */
	public void stop() {
		System.out.println("Stopping application");
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
				Thread.sleep(15);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		screen.getFrame().dispose();
		exec.stop();
	}
}
