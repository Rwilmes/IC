package ic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ic.image.Image;
import ic.util.IO;
import ic.util.Processing;
import ic.util.Utils;
import ic.util.log.Log;

/**
 * Main class of the ImageComparison project.
 * 
 * @author Rwilmes
 *
 */
public class ImageComparison {

	public static void main(String[] args) throws IOException {
		Log.setDebugMode();
		// TODO: IMPORT ARGLIST
		//
		// Image i2 = new Image("C://test//bla.png");
		// System.out.println(i2.getDir() + "\t" + i2.getFilename());
		// Image i3 = new Image("C:/test/bla.png");
		// System.out.println(i3.getDir() + "\t" + i3.getFilename());
		// Image i4 = new Image("bla.png");
		// System.out.println(i4.getDir() + "\t" + i4.getFilename());

//		Image i1 = new Image("data/Alyson_Hannigan_200512.jpg");
//		System.out.println(i1.getDir() + "\t" + i1.getFilename());
//
//		BufferedImage bi1 = ImageIO.read(new File(i1.getPath()));
//
//		
//		System.out.println(bi1.getWidth() + "\t" + bi1.getHeight());
//		
//		int scaledWidth = 9;
//		int scaledHeight = 8;
//
//		BufferedImage bi2 = new BufferedImage(scaledWidth, scaledHeight, bi1.getType());
//
//		System.out.println(bi2.getWidth() + "\t" + bi2.getHeight());
//		
//		Graphics2D g2d = bi2.createGraphics();
//
//		g2d.drawImage(bi1, 0, 0, scaledWidth, scaledHeight, null);
//		g2d.dispose();
//
//		System.out.println(bi2.getWidth() + "\t" + bi2.getHeight());
//		
//		String outputFormat = "jpg";
//		String outputPath = "data/Alyson_Hannigan_200512_rezised " + "." + outputFormat;
//		
//		
//		boolean blub = ImageIO.write(bi2, outputFormat, new File(outputPath));
//		System.out.println(blub);
//		
		
		String imagePath1 = "data/P1080493.JPG";
		String imagePath2 = "data/P1080499.JPG";
		String imagePath3 = "data/P1080536.JPG";
		String imagePath4 = "data/P1080538.JPG";
		
		BufferedImage i1 = IO.readImage(imagePath1);
		Utils.printDimension(i1);

		IO.writeImage(i1, "data/copy1.JPG");
		
		String blub = IO.changeImageFormat(imagePath1, "png");
		System.out.println(blub);
		
		Log.sep();
//		IO.writeImage(i1, blub);
		
//		
//		BufferedImage i2 = Processing.resize(i1, 1.20, 1.0);
//		IO.writeImage(i2, "data/i1_w20.jpg");
//		
//		BufferedImage i3 = Processing.resize(i1, 1.40, 1.0);
//		IO.writeImage(i3, "data/i1_w40.jpg");
//		
		BufferedImage i4 = Processing.resize(i1, 0.5, 0.5);
		IO.writeImage(i4, "data/processing/i1_w50_h50.jpg");
		
//		BufferedImage i5 = Processing.grayScale(i4);
//		IO.writeImage(i5, "data/i1_w200_h200_gray.jpg");
		
		BufferedImage i6 = Processing.rotate(i4, 45);
		IO.writeImage(i6, "data/processing/i1_w200_h200_r45.jpg");
		
		BufferedImage i7 = Processing.rotate(i4, 90);
		IO.writeImage(i7, "data/processing/i1_w200_h200_r90.jpg");
		
		BufferedImage i8 = Processing.rotate(i4, 135);
		IO.writeImage(i8, "data/processing/i1_w200_h200_r135.jpg");
		
		BufferedImage i9 = Processing.rotate(i4, 180);
		IO.writeImage(i9, "data/processing/i1_w200_h200_r180.jpg");
		
		BufferedImage i10 = Processing.rotate(i4, 225);
		IO.writeImage(i10, "data/processing/i1_w200_h200_r225.jpg");
		
		BufferedImage i11 = Processing.rotate(i4, 270);
		IO.writeImage(i11, "data/processing/i1_w200_h200_r270.jpg");
	}
}
