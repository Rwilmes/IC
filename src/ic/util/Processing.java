package ic.util;

import ic.util.log.Log;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * A class containing basic image processing methods.
 * 
 * @author Rwilmes
 * 
 */
public class Processing {

	/** Resizes (stretches) an image to the given size. **/
	public static BufferedImage resize(BufferedImage i, int width, int height) {
		Log.proc("rezising\t" + i.getWidth() + "x" + i.getHeight() + "\t->\t"
				+ width + "x" + height);

		BufferedImage i2 = new BufferedImage(width, height, i.getType());
		Graphics2D g2d = i2.createGraphics();
		g2d.drawImage(i, 0, 0, width, height, null);
		g2d.dispose();

		return i2;
	}

	/** Reizes (stretches) an image using the given percentages. **/
	public static BufferedImage resize(BufferedImage i, double widthPercent,
			double heightPercent) {
		double scaledWidth = i.getWidth() * widthPercent;
		double scaledHeight = i.getHeight() * heightPercent;

		return resize(i, (int) Math.floor(scaledWidth),
				(int) Math.floor(scaledHeight));
	}

	/** Converts the image into grayscale. **/
	public static BufferedImage grayScale(BufferedImage i) {
		Log.proc("converting image to gray-scale");

		BufferedImage image = new BufferedImage(i.getWidth(), i.getHeight(),
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = image.getGraphics();
		g.drawImage(i, 0, 0, null);
		g.dispose();
		return image;
	}

	/** Converts an Image into BufferedImage. **/
	public static BufferedImage toBufferedImage(Image i) {
		if (i instanceof BufferedImage)
			return (BufferedImage) i;

		// create buffered image
		BufferedImage bi = new BufferedImage(i.getWidth(null),
				i.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// draw into buffered image
		Graphics2D bGr = bi.createGraphics();
		bGr.drawImage(i, 0, 0, null);
		bGr.dispose();

		return bi;
	}

}
