package ic.util;

import ic.metrics.hashes.ImageHash;
import ic.util.Timer.TimerType;
import ic.util.log.Log;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * A class containing basic image processing methods.
 * 
 * @author Rwilmes
 * 
 */
public class Processing {

	public static final int white = Color.WHITE.getRGB();
	public static final int black = Color.BLACK.getRGB();

	/** Resizes (stretches) an image to the given size. **/
	public static BufferedImage resize(BufferedImage i, int width, int height) {
		// start timer
		Timer timer = new Timer(TimerType.TIMER_PROCESSING_RESIZE);

		// resize
		BufferedImage i2 = new BufferedImage(width, height, i.getType());
		Graphics2D g2d = i2.createGraphics();
		g2d.drawImage(i, 0, 0, width, height, null);
		g2d.dispose();

		// stop timer
		timer.stop();
		Log.proc("resizing\t" + i.getWidth() + "x" + i.getHeight() + "\t->\t"
				+ width + "x" + height + "\tt=" + timer.getTotalAsString());

		// return
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

	public static BufferedImage resizeAndGrayScale(BufferedImage i, int width,
			int height) {
		// start timer
		Timer timer = new Timer(TimerType.TIMER_PROCESSING_RESIZE_GRAYSCALE);

		// resize and grayscale
		BufferedImage i2 = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g2d = i2.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2d.drawImage(i, 0, 0, width, height, null);
		g2d.dispose();

		// stop timer
		timer.stop();
		Log.proc("resizing and gray-scaling " + width + "x" + height + "\tt="
				+ timer.getTotalAsString());

		// return
		return i2;
	}

	/** Converts the image into grayscale. **/
	public static BufferedImage grayScale(BufferedImage i) {
		// start timer
		Timer timer = new Timer(TimerType.TIMER_PROCESSING_COLORING);

		// convert into gray image
		BufferedImage image = new BufferedImage(i.getWidth(), i.getHeight(),
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = image.getGraphics();
		g.drawImage(i, 0, 0, null);
		g.dispose();

		// stop timer
		timer.stop();
		Log.proc("converting image to gray-scale\tt="
				+ timer.getTotalAsString());

		// return
		return image;
	}

	/**
	 * Rotates an image by the given angle (e.g.: angle = 45 will rotate by 45
	 * degrees).
	 **/
	public static BufferedImage rotate(BufferedImage i, double angle) {
		// start timer
		Timer timer = new Timer(TimerType.TIMER_PROCESSING_ROTATE);

		// create affine transformation
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(angle), i.getWidth() / 2,
				i.getHeight() / 2);

		// create image
		BufferedImage image = new BufferedImage(i.getWidth(), i.getHeight(),
				i.getType());

		// transform and draw into graphics
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.drawImage(i, transform, null);
		g2d.dispose();

		// stop timer
		timer.stop();
		Log.proc("rotating image by " + angle + " degrees\tt="
				+ timer.getTotalAsString());

		// return
		return image;
	}

	/** Computes an image representing the given hash. **/
	public static BufferedImage getImageFromHash(ImageHash hash) {
		String h = hash.getHash();

		int length = h.length();
		int pixelsPerCharacter = 4;
		int dimension = (int) Math.sqrt(length * pixelsPerCharacter);

		// init buffered image
		BufferedImage img = new BufferedImage(dimension, dimension,
				BufferedImage.TYPE_BYTE_BINARY);

		// iterate over hash string
		for (int i = 0; i < h.length(); i++) {
			// parse char to binary
			int v = Integer.parseInt(h.substring(i, i + 1), 16);
			String bin = Integer.toBinaryString(v);

			// add padding
			while (bin.length() < 4)
				bin = "0" + bin;

			// compute y-position
			int yPos = i / 2;

			// x-position base
			int xPosBase = 0;
			if (i % 2 != 0)
				xPosBase = 4;

			for (int j = 0; j < 4; j++) {
				// compute x-position
				int xPos = xPosBase + j;

				// set white if 1, else set black
				if (bin.charAt(j) == '1')
					img.setRGB(xPos, yPos, white);
				else
					img.setRGB(xPos, yPos, black);
			}
		}

		// return
		return img;
	}
}
