package ic;

import ic.util.IO;
import ic.util.Processing;
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

	public static void main(String[] args) throws IOException {
		Log.setDebugMode();

		rotate();
	}

	public static void rotate() throws IOException {
		String imagePath1 = "data/P1080493.JPG";
		String imagePath2 = "data/P1080499.JPG";
		String imagePath3 = "data/P1080536.JPG";
		String imagePath4 = "data/P1080538.JPG";

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

		Runtimes.printReport();
	}
}
