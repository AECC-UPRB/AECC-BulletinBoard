package edu.aecc.bulletin.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Provides a convinient wrapper to the window and keyboard.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
@SuppressWarnings("serial")
public class Screen extends JPanel implements KeyListener {

	private Executable exec;
	private JFrame frame;

	/**
	 * Initializes the screen with the given executable.
	 * 
	 * @param e
	 *            Executable that is using the screen
	 */
	public Screen(Executable e) {
		System.out.println("Creating new Screen");
		exec = e;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setUndecorated(true);
	}

	/**
	 * Returns the JFrame responsible for the screen.
	 * 
	 * @return JFrame responsible for the screen
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Changes the visibility of the window.
	 */
	public void setVisible(boolean b) {
		frame.setVisible(b);
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
		exec.keyPressed(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		exec.keyReleased(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		exec.keyTyped(arg0);
	}
}