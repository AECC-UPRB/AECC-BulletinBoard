package edu.aecc.bulletin.core;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 * Provides an abstraction interface to the application.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
public interface Executable {

	public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	/**
	 * Allows preallocated time to load and resources and other computational
	 * tasks.
	 * 
	 * @param a
	 *            Application that will handle the executable
	 */
	public abstract void init(Application a);

	/**
	 * Draws components onto the screen.
	 * 
	 * @param g
	 *            Graphics interface to the screen
	 */
	public abstract void draw(Graphics g);

	/**
	 * Performs a controlled tick-based update.
	 * 
	 * @param ticks
	 *            Number of ticks passed since the executable was first ran
	 */
	public abstract void update(long ticks);

	/**
	 * Code that will execute when the application stops running.
	 */
	public abstract void stop();

	/**
	 * Handles key press events.
	 * 
	 * @param ke
	 *            KeyEvent that was triggered
	 */
	public abstract void keyPressed(KeyEvent ke);

	/**
	 * Handles key release events.
	 * 
	 * @param ke
	 *            KeyEvent that was triggered
	 */
	public abstract void keyReleased(KeyEvent ke);

	/**
	 * Handles key type events.
	 * 
	 * @param ke
	 *            KeyEvent that was triggered
	 */
	public abstract void keyTyped(KeyEvent ke);
}