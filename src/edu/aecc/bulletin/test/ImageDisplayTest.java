package edu.aecc.bulletin.test;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.aecc.bulletin.Application;
import edu.aecc.bulletin.Executable;
import edu.aecc.bulletin.io.ResourceLoader;
import edu.aecc.bulletin.io.ResourceManager;

public class ImageDisplayTest implements Executable {

	ResourceManager<BufferedImage> rm;
	BufferedImage image;

	public static void main(String[] arguments) {
		new Application(new ImageDisplayTest()).start();
	}

	@Override
	public void init(Application a) {
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
		image = rm.loadResources().get(0);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

	@Override
	public void update(long ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent ke) {

	}

}
