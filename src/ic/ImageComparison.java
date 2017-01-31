package ic;

import ic.image.Image;
import ic.metrics.name.DHash;
import ic.metrics.name.PHash;
import ic.util.IO;
import ic.util.log.Log;
import ic.util.runtime.Runtimes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class of the ImageComparison project.
 * 
 * @author Rwilmes
 * 
 */
public class ImageComparison {

//	public static String imagePath = "data/images/1/P1080541.JPG";
	
	public static String imagePath = "data/images/1/P1080597.JPG";

	public static int counter = 0;

	public static ArrayList<Image> hits = new ArrayList<Image>();

	public static void main(String[] args) throws IOException {
		BufferedImage img = IO.readImage(imagePath);

		String dir = "data/images/";
		String dir2 = "C://files/bilder/";
		boolean recursive = true;

		 searchDuplicates(img, dir, recursive);
		searchDuplicates(img, dir2, recursive);

		Log.sep();
		Log.log("probable hits for '" + imagePath + "' :");
		for (int i = 0; i < hits.size(); i++) {
			Log.log(i + "\t" + hits.get(i).getPath());
		}

		Runtimes.printReport();

	}

	public static void searchDuplicates(BufferedImage img, String dir,
			boolean recursive) throws IOException {
		DHash dHash = DHash.computeHash(img);
		PHash pHash = PHash.computeHash(img);
		searchDuplicates(dHash, pHash, dir, recursive);
	}

	public static void searchDuplicates(DHash dHash, PHash pHash, String dir,
			boolean recursive) throws IOException {
		Log.log("checking '" + dir + "'");
		File dirFile = new File(dir);

		File[] files = dirFile.listFiles();

		ArrayList<String> dirQueue = new ArrayList<String>();

		for (int i = 0; i < files.length; i++) {
			String p = files[i].getPath();
			File file = new File(p);

			if (file.isFile()) {
				BufferedImage image = IO.readImage(p);
				DHash dh = DHash.computeHash(image);
				PHash ph = PHash.computeHash(image);

				int dhDistance = dHash.compareTo(dh);
				int phDistance = pHash.compareTo(ph);

				String line = "\t" + counter + "\t" + dhDistance + "\t"
						+ phDistance + "\t" + files[i];

				if (dhDistance <= 11 && phDistance <= 11) {
					line += "\t" + "HIT";
					hits.add(new Image(p, dh, ph));
				}

				Log.log(line);
				counter++;
			}

			if (file.isDirectory()) {
				dirQueue.add(p);
			}
		}

		// check subdirs recursively
		if (recursive)
			for (String s : dirQueue)
				searchDuplicates(dHash, pHash, s, recursive);
	}
}
