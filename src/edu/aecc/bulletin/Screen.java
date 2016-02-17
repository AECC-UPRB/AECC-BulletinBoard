package edu.aecc.bulletin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

/**
 * Provides a convinient wrapper to the window and keyboard.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
@SuppressWarnings("serial")
public class Screen extends JPanel implements KeyListener {

	Executable exec;

	/**
	 * Initializes the screen with the given executable.
	 * 
	 * @param e
	 *            Executable that is using the screen
	 */
	public Screen(Executable e) {
		exec = e;
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		exec.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		exec.keyTyped(arg0);
	}
}