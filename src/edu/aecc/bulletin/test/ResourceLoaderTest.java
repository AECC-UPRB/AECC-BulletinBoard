package edu.aecc.bulletin.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.aecc.bulletin.io.ResourceLoader;
import edu.aecc.bulletin.io.ResourceManager;

/**
 * Application that tests the resource loading system.
 * 
 * @author Juan J. Alvarez <jalvarez52419972@gmail.com>
 *
 */
public class ResourceLoaderTest {

	/**
	 * Entry point
	 * 
	 * @param arguments
	 *            Command line arguments
	 */
	public static void main(String[] arguments) {
		long start = System.currentTimeMillis();
		ResourceManager<BufferedImage> rm = new ResourceManager<BufferedImage>(new File("docs"),
				new ResourceLoader<BufferedImage>() {

					@Override
					public BufferedImage loadResource(File f) {
						try {
							return ImageIO.read(f);
						} catch (IOException e) {
							e.printStackTrace();
						}
						return null;
					}
				});
		rm.loadResources();
		System.out.println(
				String.format("It took %d milliseconds to load all resources", System.currentTimeMillis() - start));
	}
}