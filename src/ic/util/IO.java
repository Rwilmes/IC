package ic.util;

import ic.util.log.Log;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class containing utility I/O methods.
 * 
 * @author Rwilmes
 * 
 */
public class IO {

	public static BufferedImage readImage(String path) throws IOException {
		BufferedImage i = ImageIO.read(new File(path));
		return i;
	}

	public static void writeImage(BufferedImage i, String path)
			throws IOException {
		String[] splits = path.split("\\.");
		String format = splits[splits.length - 1];

		Log.io("writing to: '" + path + "' with format. '" + format + "'");
		ImageIO.write(i, format, new File(path));
	}

	public static String changeImageFormat(String path, String format) {
		String[] splits = path.split("\\.");
		String temp = "";
		for (int i = 0; i < splits.length - 1; i++) {
			temp += splits[i] + ".";
		}

		return temp + format;
	}
}
