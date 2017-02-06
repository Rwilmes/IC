package ic.util;

import ic.util.Timer.TimerType;
import ic.util.log.Log;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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

	public static ImageIcon readImageIcon(String path) {
		return new ImageIcon(path);
	}

	/** Collects all files found in the dir and below and adds the mto the list. **/
	public static void collectFiles(File dir, ArrayList<File> fileList,
			boolean recursive) {
		if (!dir.exists() || !dir.isDirectory())
			return;

		File[] files = dir.listFiles();

		for (File f : files) {
			if (f.isDirectory()) {
				if (recursive)
					collectFiles(f, fileList, recursive);
			} else
				fileList.add(f);
		}
	}
}
