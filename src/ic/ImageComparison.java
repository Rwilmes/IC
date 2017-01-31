package ic;

import ic.metrics.name.DHash;
import ic.metrics.name.PHash;
import ic.util.IO;
import ic.util.Processing;
import ic.util.Timer;
import ic.util.Timer.TimerType;
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

	// public static String imagePath5 = "data/P1080565.JPG";
	// public static String imagePath6 = "data/P1080566.JPG";

	// public static String imagePath5 = "data/P1080578.JPG";
	// public static String imagePath6 = "data/P1080579.JPG";

	// public static String imagePath5 = "data/P1080581.JPG"; 4
	// public static String imagePath6 = "data/P1080582.JPG";

	// public static String imagePath5 = "data/P1080587.JPG"; 14
	// public static String imagePath6 = "data/P1080588.JPG";

	// public static String imagePath5 = "data/P1080589.JPG";
	// public static String imagePath6 = "data/P1080590.JPG";

//	public static String imagePath5 = "data/P1080596.JPG"; 11 
//	public static String imagePath6 = "data/P1080597.JPG";
	
//	public static String imagePath5 = "data/P1080598.JPG"; 13
//	public static String imagePath6 = "data/P1080599.JPG";

	public static String imagePath5 = "data/P1080724.JPG"; 
	public static String imagePath6 = "data/P1080733.JPG";
	
	
	public static String alysonPath = "data/Alyson_Hannigan_200512.jpg";

	public static void main(String[] args) throws IOException {
		// Log.setDebugMode();
		// Log.setQuiet();
		// rotate();
		// dHash();

//		dHash2();
		pHash();
//		Runtimes.printReport();
	}

	public static void pHash() throws IOException {
		BufferedImage i1 = IO.readImage(alysonPath);
		PHash p1 = PHash.getPHash(i1);
		
		BufferedImage i2 = IO.readImage("data/P1080581.JPG");
		PHash p2 = PHash.getPHash(i2);
		
		BufferedImage i3 = IO.readImage("data/P1080582.JPG");
		PHash p3 = PHash.getPHash(i3);
		
		System.out.println("1: " + p1.getHash());
		System.out.println("2: " + p2.getHash());
		System.out.println("3: " + p3.getHash());
		
		System.out.println("1 vs 2: " + p1.compareTo(p2));
		System.out.println("1 vs 3: " + p1.compareTo(p3));
		
		System.out.println("2 vs 3: " + p2.compareTo(p3));
		
		
		BufferedImage i4 = Processing.resize(i3, 1.3, 1.9);
		

		PHash p4 = PHash.getPHash(i4);
		System.out.println("4: " + p4.getHash());
		System.out.println("3 vs 4: " + p4.compareTo(p3));
		
		
		Runtimes.printReport();
	}
	
	public static void dHash2() throws IOException {
//		BufferedImage image1 = IO.readImage(imagePath1);
//		BufferedImage image2 = IO.readImage(imagePath2);
//		BufferedImage image3 = IO.readImage(imagePath3);
//		BufferedImage image4 = IO.readImage(imagePath4);
		BufferedImage image5 = IO.readImage(imagePath5);
		BufferedImage image6 = IO.readImage(imagePath6);
//
//		DHash hash1 = DHash.getDHash(image1);
//		DHash hash2 = DHash.getDHash(image2);
//		DHash hash3 = DHash.getDHash(image3);
//		DHash hash4 = DHash.getDHash(image4);
		DHash hash5 = DHash.getDHash(image5);
		DHash hash6 = DHash.getDHash(image6);

//		Log.log("1 vs all");
//		System.out.println(hash1.compareTo(hash2));
//		System.out.println(hash1.compareTo(hash3));
//		System.out.println(hash1.compareTo(hash4));
//
//		Log.log("2 vs all");
//		System.out.println(hash2.compareTo(hash1));
//		System.out.println(hash2.compareTo(hash3));
//		System.out.println(hash2.compareTo(hash4));
//
//		Log.log("3 vs all");
//		System.out.println(hash3.compareTo(hash1));
//		System.out.println(hash3.compareTo(hash2));
//		System.out.println(hash3.compareTo(hash4));
//
//		Log.log("4 vs all");
//		System.out.println(hash4.compareTo(hash1));
//		System.out.println(hash4.compareTo(hash2));
//		System.out.println(hash4.compareTo(hash3));

		Log.log("5 vs 6");
		System.out.println(hash5.compareTo(hash6));
	}

	public static void dHash() throws IOException {
		BufferedImage i = IO.readImage(alysonPath);

		DHash hash = DHash.getDHash(i);

		BufferedImage i2 = Processing.resize(i, 1.2, 1.2);
		DHash hash2 = DHash.getDHash(i2);

		BufferedImage i3 = Processing.resize(i, 1.5, 1.0);
		DHash hash3 = DHash.getDHash(i3);

		BufferedImage i4 = Processing.grayScale(i);
		DHash hash4 = DHash.getDHash(i4);

		BufferedImage i5 = Processing.rotate(i, 90);
		DHash hash5 = DHash.getDHash(i5);

		System.out.println(hash.compareTo(hash2));
		System.out.println(hash.compareTo(hash3));
		System.out.println(hash.compareTo(hash4));
		System.out.println(hash.compareTo(hash5));

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
