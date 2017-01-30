package ic.util;

import ic.util.Timer.TimerType;
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
		// start timer
		Timer timer = new Timer(TimerType.TIMER_IO_READ);

		// read image
		BufferedImage i = ImageIO.read(new File(path));

		// stop timer
		timer.stop();
		Log.io("reading image from: '" + path + "'\tt="
				+ timer.getTotalAsString());

		// return
		return i;
	}

	public static void writeImage(BufferedImage i, String path)
			throws IOException {
		// start timer
		Timer timer = new Timer(TimerType.TIMER_IO_WRITE);

		// get format string
		String[] splits = path.split("\\.");
		String format = splits[splits.length - 1];

		// write image
		ImageIO.write(i, format, new File(path));

		// stop timer
		timer.stop();
		Log.io("writing to: '" + path + "' with format. '" + format + "'\tt="
				+ timer.getTotalAsString());
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
