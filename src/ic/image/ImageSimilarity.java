package ic.image;

import ic.util.exceptions.ImageSimilarityOverflowException;

/**
 * An ImageSimilarity-object represents the similarity of two images. The
 * similarity is measured by a double ranging from 0.0 to 1.0. <br>
 * <br>
 * 
 * Note: Supplying a double outside of the specified range [0.0 : 1.0] will
 * result in an ImageSimilarityOverflowException.
 * 
 * @author Rwilmes
 * 
 */
public class ImageSimilarity {

	private Image image1;
	private Image image2;

	private double similarity;

	public ImageSimilarity(Image image1, Image image2, double similarity)
			throws ImageSimilarityOverflowException {
		this.image1 = image1;
		this.image2 = image2;
		this.similarity = similarity;
		if (similarity < 0.0 || similarity > 1.0) {
			throw new ImageSimilarityOverflowException(image1, image2,
					similarity);
		}
	}

	public Image getImage1() {
		return image1;
	}

	public Image getImage2() {
		return image2;
	}

	/**
	 * Returns the similarity hold by this object. Note: A similarity-score
	 * between 0.0 and 1.0 is garantueed!
	 **/
	public double getSimilarity() {
		return similarity;
	}
}
