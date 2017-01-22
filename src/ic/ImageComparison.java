package ic;

import ic.image.Image;

/** 
 * Main class of the ImageComparison project.
 * 
 * @author Rwilmes
 *
 */
public class ImageComparison {

	public static void main(String[] args) {
		// TODO: IMPORT ARGLIST
//		
//		Image i2 = new Image("C://test//bla.png");
//		System.out.println(i2.getDir() + "\t" + i2.getFilename());
		Image i3 = new Image("C:/test/bla.png");
		System.out.println(i3.getDir() + "\t" + i3.getFilename());
		Image i4 = new Image("bla.png");
		System.out.println(i4.getDir() + "\t" + i4.getFilename());
	}
}
