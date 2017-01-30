package ic.util;

import ic.util.log.Log;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Utility class containing general utility methods.
 * 
 * @author Rwilmes
 * 
 */
public class Utils {

	/** Prints the dimension of a buffered image. **/
	public static void printDimension(BufferedImage i) {
		if (i == null)
			Log.log("dimension: image is null!");
		else
			Log.log("dimension: " + i.getWidth() + "x" + i.getHeight());
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
