package edu.aecc.bulletin.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.aecc.bulletin.core.Application;
import edu.aecc.bulletin.core.Executable;
import edu.aecc.bulletin.io.ResourceLoader;
import edu.aecc.bulletin.io.ResourceManager;
import edu.aecc.bulletin.utilities.ImageUtils;

/**
 * Application that provides a very basic demo of what the final version of this
 * application will look like.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
public class BulletinDemo implements Executable {

	/**
	 * Entry point.
	 * 
	 * @param arguments
	 *            Command line arguments
	 */
	public static void main(String[] arguments) {
		new Application(new BulletinDemo()).start();
	}

	private Application app;
	private ArrayList<BufferedImage> imageList;
	private ResourceManager<BufferedImage> rm;
	private BufferedImage image;
	private long tickTarget;
	private long ticks;

	@Override
	public void init(Application a) {
		app = a;
		rm = new ResourceManager<BufferedImage>(new File("docs"), new ResourceLoader<BufferedImage>() {

			@Override
			public synchronized BufferedImage loadResource(File f) {
				try {
					return ImageIO.read(f);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

		});
		imageList = rm.loadResources();
		image = imageList.get(0);
		imageList.remove(0);
		tickTarget = 0;
	}

	@Override
	public void draw(Graphics g) {
		BufferedImage i = ImageUtils.maxScale(image, 3000, 2000);
		int x = (3000 - i.getWidth()) / 2;
		int y = (2000 - i.getTileHeight()) / 2;
		g.drawImage(i, x, y, null);
		g.setFont(new Font("Arial", Font.PLAIN, 32));
		g.setColor(Color.RED);
		g.drawString("Ticks: " + ticks, 100, 100);
	}

	@Override
	public void update(long t) {
		ticks = t;
		if (t > tickTarget) {
			imageList.add(image);
			image = imageList.get(0);
			imageList.remove(0);
			tickTarget = t + 200;
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent ke) {
		if (ke.getKeyChar() == 'q')
			app.stop();
	}

}
