package ic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import ic.gui.MainFrame;
import ic.image.Image;
import ic.metrics.name.DHash;
import ic.metrics.name.PHash;
import ic.util.IO;
import ic.util.Processing;
import ic.util.log.Log;

/**
 * Main class of the ImageComparison project.
 * 
 * @author Rwilmes
 * 
 */
public class ImageComparison {

	// public static String imagePath = "data/images/1/P1080541.JPG";

	public static String imagePath = "data/images/1/P1080579.JPG";

	public static int counter = 0;

	public static ArrayList<Image> hits = new ArrayList<Image>();
	public static ArrayList<Image> similars = new ArrayList<Image>();

	public static void main(String[] args) throws IOException {
//		imagePath = "data/images/Irland/DSC_5649.jpg";
//
//		BufferedImage img = IO.readImage(imagePath);
//		BufferedImage img_small = Processing.resize(img, 0.3, 0.3);
//		IO.writeImage(img_small, "data/similars/base.jpg");
//
//		String dir = "data/images/";
//		String dir2 = "C://files/bilder/";
//		boolean recursive = true;
//
////		searchDuplicates(img, dir, recursive);
////		searchDuplicates(img, dir2, recursive);
//
//
//		//
//		// BufferedImage i1 = IO.readImage("data/images/1/P1080579.JPG");
//		// i1 = Processing.resize(i1, 0.3, 0.3);
//		// IO.writeImage(i1, "data/images/small/P1080579_small.JPG");
//		//
//		// BufferedImage i2 = IO.readImage("data/images/1/P1080578.JPG");
//		// i2 = Processing.resize(i2, 0.3, 0.3);
//		// IO.writeImage(i2, "data/images/small/P1080578_small.JPG");
//
//		Log.sep();
//		Log.log("probable hits for '" + imagePath + "' :");
//		for (int i = 0; i < hits.size(); i++) {
//			Log.log(i + "\t" + hits.get(i).getPath());
//		}
//
//		Log.sep();
//		Log.log("similar images for '" + imagePath + "' :");
//		for (int i = 0; i < similars.size(); i++) {
//			Log.log(i + "\t" + similars.get(i).getPath());
//		}
//
//		Runtimes.printReport();


		guiTest();
	}
	
	public static void guiTest() {
		Log.log("GUI TEST");

		MainFrame x = new MainFrame();
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

				if (dhDistance <= 11) {
					if (phDistance <= 11) {
						line += "\t" + "HIT";
						hits.add(new Image(p, dh, ph));
					} else {
						line += "\t" + "SIMILAR";
						Image imageTemp = new Image(p, dh, ph);
						similars.add(imageTemp);
						IO.writeImage(Processing.resize(image, 0.3, 0.3),
								"data/similars/" + imageTemp.getFilename());
					}
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
