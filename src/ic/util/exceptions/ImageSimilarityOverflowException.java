package ic.util.exceptions;

import ic.image.Image;

/**
 * Will be thrown when a similarity score not between 0.0 and 1.0 is being set.
 * 
 * @author Rwilmes
 *
 */
public class ImageSimilarityOverflowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ImageSimilarityOverflowException(Image image1, Image image2, double value) {
		
	}
}
