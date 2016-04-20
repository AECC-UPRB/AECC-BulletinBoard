package edu.aecc.bulletin.test;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import edu.aecc.bulletin.application.App;
import edu.aecc.bulletin.application.Executable;
import edu.aecc.bulletin.io.ResourceLoader;
import edu.aecc.bulletin.io.ResourceManager;
import edu.aecc.bulletin.utilities.ImageUtils;

/**
 * Application that tests the rendering engine and some image manipulation
 * utilities.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
public class ImageDisplayTest implements Executable {

	ResourceManager<BufferedImage> rm;
	BufferedImage background;
	BufferedImage image;
	private int width;
	private int height;

	/**
	 * Entry point.
	 * 
	 * @param arguments
	 *            Command line arguments
	 */
	public static void main(String[] arguments) {
		new App(new ImageDisplayTest()).start();
	}

	@Override
	public void init(App a) {
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
		ArrayList<BufferedImage> list = rm.loadResources();
		image = list.get(new Random().nextInt(list.size()));
		background = list.get(new Random().nextInt(list.size()));
		width = image.getWidth();
		height = image.getHeight();
	}

	@Override
	public void draw(Graphics g) {
		BufferedImage b = ImageUtils.maxScale(background, 3000, 2000);
		BufferedImage f = ImageUtils.scaleImage(image, width, height);
		g.drawImage(b, 0, 0, null);
		int x = (3000 - f.getWidth()) / 2;
		int y = (2000 - f.getHeight()) / 2;
		g.drawImage(f, x, y, null);
	}

	@Override
	public void update(long ticks) {
	}

	@Override
	public void stop() {
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
			width++;
		if (ke.getKeyCode() == KeyEvent.VK_LEFT)
			width--;
		if (ke.getKeyCode() == KeyEvent.VK_UP)
			height--;
		if (ke.getKeyCode() == KeyEvent.VK_DOWN)
			height++;
	}

	@Override
	public void keyReleased(KeyEvent ke) {
	}

}
