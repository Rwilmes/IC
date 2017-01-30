package ic;

import ic.metrics.name.DHash;
import ic.util.IO;
import ic.util.Processing;
import ic.util.Utils;
import ic.util.log.Log;
import ic.util.runtime.Runtimes;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Main class of the ImageComparison project.
 * 
 * @author Rwilmes
 * 
 */
public class ImageComparison {

	public static String imagePath1 = "data/P1080493.JPG";
	public static String imagePath2 = "data/P1080499.JPG";
	public static String imagePath3 = "data/P1080536.JPG";
	public static String imagePath4 = "data/P1080538.JPG";

	public static String alysonPath = "data/Alyson_Hannigan_200512.jpg";

	public static void main(String[] args) throws IOException {
//		Log.setDebugMode();
		// Log.setQuiet();
		// rotate();
		dHash();
		Runtimes.printReport();
	}

	public static void dHash() throws IOException {
		BufferedImage i = IO.readImage(alysonPath);

		String hash = DHash.getDHash2(i);

		BufferedImage i2 = Processing.resize(i, 1.2, 1.2);
		String hash2 = DHash.getDHash2(i2);

		BufferedImage i3 = Processing.resize(i, 1.5, 1.0);
		String hash3 = DHash.getDHash2(i3);

		BufferedImage i4 = Processing.grayScale(i);
		String hash4 = DHash.getDHash2(i4);

		BufferedImage i5 = Processing.rotate(i, 90);
		String hash5 = DHash.getDHash2(i5);

		System.out.println(Utils.computeHammingDistance(hash, hash2));
		System.out.println(Utils.computeHammingDistance(hash, hash3));
		System.out.println(Utils.computeHammingDistance(hash, hash4));
		System.out.println(Utils.computeHammingDistance(hash, hash5));

	}

	public static void rotate() throws IOException {
		BufferedImage i1 = IO.readImage(imagePath1);

		BufferedImage i4 = Processing.resize(i1, 0.5, 0.5);
		IO.writeImage(i4, "data/processing/i1_w50_h50.jpg");

		BufferedImage i6 = Processing.rotate(i4, 45);
		IO.writeImage(i6, "data/processing/i1_w50_h50_r45.jpg");

		BufferedImage i7 = Processing.rotate(i4, 90);
		IO.writeImage(i7, "data/processing/i1_w50_h50_r90.jpg");

		BufferedImage i8 = Processing.rotate(i4, 135);
		IO.writeImage(i8, "data/processing/i1_w50_h50_r135.jpg");

		BufferedImage i9 = Processing.rotate(i4, 180);
		IO.writeImage(i9, "data/processing/i1_w50_h50_r180.jpg");

		BufferedImage i10 = Processing.rotate(i4, 225);
		IO.writeImage(i10, "data/processing/i1_w50_h50_r225.jpg");

		BufferedImage i11 = Processing.rotate(i4, 270);
		IO.writeImage(i11, "data/processing/i1_w50_h50_r270.jpg");

	}
}
