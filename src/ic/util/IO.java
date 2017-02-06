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

	public static String[] supportedFileFormats = ImageIO
			.getReaderFormatNames();

	/** Reads an image from the given path and returns it as a BufferedImage. **/
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

	/** Writes a BufferedImage to the specified path. **/
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

	/** Changes the file format of a given path. **/
	public static String changeImageFormat(String path, String format) {
		String[] splits = path.split("\\.");
		String temp = "";
		for (int i = 0; i < splits.length - 1; i++) {
			temp += splits[i] + ".";
		}

		return temp + format;
	}

	/** Reads an ImageIcon from the given path. **/
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
			} else {
				String path = f.getPath();
				String[] splits = path.split("\\.");

				// check if file format is supported
				if (IO.isFormatSupported(splits[splits.length - 1]))
					fileList.add(f);
			}
		}
	}

	/** Returns whether or not the given file-format is supported. **/
	public static boolean isFormatSupported(String format) {
		for (String s : supportedFileFormats) {
			if (s.equals(format))
				return true;
		}

		return false;
	}

}
