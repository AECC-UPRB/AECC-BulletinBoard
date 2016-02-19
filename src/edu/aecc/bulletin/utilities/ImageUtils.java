package edu.aecc.bulletin.utilities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Collection of image manipulation utilities.
 * 
 * @author Juan J. Alvarez <jalvarez5241997@gmail.com,>
 *
 */
public class ImageUtils {

	/**
	 * Generates a new image scaled to the given resolution based on the given
	 * original image.
	 * 
	 * @param original
	 *            Original image to be transformed
	 * @param newWidth
	 *            Width of the scaled image
	 * @param newHeight
	 *            Height of the scaled image
	 * @return A copy of the given original image scaled to the given resolution
	 */
	public static BufferedImage scaleImage(BufferedImage original, int newWidth, int newHeight) {
		BufferedImage scaled = new BufferedImage(newWidth, newHeight, original.getType());
		Graphics g = scaled.getGraphics();
		g.drawImage(original, 0, 0, newWidth, newHeight, null);
		return scaled;
	}

	/**
	 * Generates a new image scaled to its maximum possible size in the given
	 * resolution without altering the width-height ratio.
	 * 
	 * @param original
	 *            Original image
	 * @param maxWidth
	 *            Maximum width for the scaled image
	 * @param maxHeight
	 *            Maximum height for the scaled image
	 * @return New image scaled to its maximum possible size in the given
	 *         resolution without altering the width-height ratio
	 */
	public static BufferedImage maxScale(BufferedImage original, int maxWidth, int maxHeight) {
		float scale = (float) maxWidth / original.getWidth();
		if (original.getHeight() * scale > maxHeight)
			scale = (float) maxHeight / original.getHeight();
		int newWidth = (int) (original.getWidth() * scale);
		int newHeight = (int) (original.getHeight() * scale);
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, original.getType());
		Graphics g = newImage.getGraphics();
		g.drawImage(original, 0, 0, newWidth, newHeight, null);
		return newImage;
	}
}