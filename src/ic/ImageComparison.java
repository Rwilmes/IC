package ic;

import ic.gui.MainFrame;
import ic.image.Image;
import ic.metrics.hashes.perceptual.DHash;
import ic.metrics.hashes.perceptual.PHash;
import ic.util.IO;
import ic.util.Processing;
import ic.util.log.Log;

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

	// public static String imagePath = "data/images/1/P1080541.JPG";

	public static String imagePath = "data/images/1/P1080579.JPG";

	public static int counter = 0;

	public static ArrayList<Image> hits = new ArrayList<Image>();
	public static ArrayList<Image> similars = new ArrayList<Image>();

	public static void main(String[] args) throws IOException {

//		guiTest();
		
		String alysonPath = "data/Alyson_Hannigan_200512.jpg";

		BufferedImage img = IO.readImage(alysonPath);
		
		PHash p = (PHash) PHash.computeHash(img);

		
		Image i = new Image(alysonPath);
		
		
		System.out.println(i.getPHash());
		System.out.println(i.getPHash().getHash());

	}

	public static void guiTest() {
		MainFrame x = new MainFrame();
	}

	public static void searchDuplicates(BufferedImage img, String dir,
			boolean recursive) throws IOException {
		DHash dHash = DHash.computeHash(img);
//		PHash pHash = PHash.computeHash(img);
		PHash pHash32 = PHash.computeHash(img);
		searchDuplicates(dHash, pHash32, dir, recursive);
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
						hits.add(new Image(p));
					} else {
						line += "\t" + "SIMILAR";
						Image imageTemp = new Image(p);
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
