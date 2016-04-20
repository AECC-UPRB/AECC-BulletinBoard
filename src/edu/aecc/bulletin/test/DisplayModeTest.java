package edu.aecc.bulletin.test;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

import edu.aecc.bulletin.application.App;
import edu.aecc.bulletin.application.Executable;

/**
 * Basic test that illustrates all possible display modes.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
public class DisplayModeTest implements Executable {

	private GraphicsDevice gd;
	private App app;

	/**
	 * Entry point to this test
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String[] args) {
		Executable exec = new DisplayModeTest();
		App a = new App(exec);
		a.start();
	}

	@Override
	public void init(App a) {
		gd = a.getGraphicsDevice();
		app = a;
	}

	@Override
	public void draw(Graphics g) {
		Queue<Color> q = new LinkedList<Color>();
		Color[] colorList = { Color.DARK_GRAY, Color.RED, Color.GRAY, Color.BLUE };
		for (Color c : colorList)
			q.add(c);
		DisplayMode[] dm = gd.getDisplayModes();
		for (int x = dm.length - 1; x > 0; x--) {
			DisplayMode d = dm[x];
			Color cur = q.remove();
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, d.getWidth(), d.getHeight());
			g.setColor(cur);
			q.add(cur);
			g.fillRect(0, 0, d.getWidth(), d.getHeight());
			g.setColor(Color.WHITE);
			g.drawString(String.format("%dx%d", d.getWidth(), d.getHeight()), d.getWidth() - 75, d.getHeight() - 10);
		}
	}

	@Override
	public void update(long ticks) {
	}

	@Override
	public void stop() {
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		if (ke.getKeyChar() == 'q') {
			app.stop();
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
	}

	@Override
	public void keyReleased(KeyEvent ke) {
	}

}
