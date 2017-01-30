package ic.util;

import ic.util.log.Log;

import java.awt.image.BufferedImage;

/**
 * Utility class containing general utility methods.
 * 
 * @author Rwilmes
 * 
 */
public class Utils {

	public static void printDimension(BufferedImage i) {
		if (i == null)
			Log.log("dimension: image is null!");
		else
			Log.log("dimension: " + i.getWidth() + " x " + i.getHeight());
	}
}
