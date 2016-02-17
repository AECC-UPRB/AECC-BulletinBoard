package edu.aecc.bulletin.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import edu.aecc.bulletin.Application;
import edu.aecc.bulletin.Executable;

/**
 * Basic test that illustrates the tick counter.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
public class TickTest implements Executable {

	private long t;

	/**
	 * Entry point
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String[] args) {
		Executable e = new TickTest();
		Application a = new Application(e);
		a.start();
	}

	@Override
	public void init(Application a) {
	}

	@Override
	public void draw(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 32));
		g.setColor(Color.WHITE);
		g.drawString("Ticks: " + t, 100, 100);
	}

	@Override
	public void update(long ticks) {
		t = ticks;

	}

	@Override
	public void stop() {
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}
}