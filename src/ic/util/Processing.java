package ic.util;

import ic.util.Timer.TimerType;
import ic.util.log.Log;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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
		// start timer
		Timer timer = new Timer(TimerType.TIMER_PROCESSING_RESIZE);

		// resize
		BufferedImage i2 = new BufferedImage(width, height, i.getType());
		Graphics2D g2d = i2.createGraphics();
		g2d.drawImage(i, 0, 0, width, height, null);
		g2d.dispose();

		// stop timer
		timer.stop();
		Log.proc("rezising\t" + i.getWidth() + "x" + i.getHeight() + "\t->\t"
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
}
