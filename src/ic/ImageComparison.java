package ic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ic.image.Image;

/**
 * Main class of the ImageComparison project.
 * 
 * @author Rwilmes
 *
 */
public class ImageComparison {

	public static void main(String[] args) throws IOException {
		// TODO: IMPORT ARGLIST
		//
		// Image i2 = new Image("C://test//bla.png");
		// System.out.println(i2.getDir() + "\t" + i2.getFilename());
		// Image i3 = new Image("C:/test/bla.png");
		// System.out.println(i3.getDir() + "\t" + i3.getFilename());
		// Image i4 = new Image("bla.png");
		// System.out.println(i4.getDir() + "\t" + i4.getFilename());

		Image i1 = new Image("data/Alyson_Hannigan_200512.jpg");
		System.out.println(i1.getDir() + "\t" + i1.getFilename());

		BufferedImage bi1 = ImageIO.read(new File(i1.getPath()));

		
		System.out.println(bi1.getWidth() + "\t" + bi1.getHeight());
		
		int scaledWidth = 9;
		int scaledHeight = 8;

		BufferedImage bi2 = new BufferedImage(scaledWidth, scaledHeight, bi1.getType());

		System.out.println(bi2.getWidth() + "\t" + bi2.getHeight());
		
		Graphics2D g2d = bi2.createGraphics();

		g2d.drawImage(bi1, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();

		System.out.println(bi2.getWidth() + "\t" + bi2.getHeight());
		
		String outputFormat = "jpg";
		String outputPath = "data/Alyson_Hannigan_200512_rezised " + "." + outputFormat;
		
		
		boolean blub = ImageIO.write(bi2, outputFormat, new File(outputPath));
		System.out.println(blub);
		
	}
}
