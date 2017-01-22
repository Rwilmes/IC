package ic.comparator;

import ic.image.Image;

/**
 * The abstract ImageComparator class defines all the methods an image
 * comparator has to supply.
 * 
 * @author Rwilmes
 * 
 */
public abstract class ImageComparator {

	private String name;
	private double weight;

	public ImageComparator(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public double getWeight() {
		return weight;
	}

	/**
	 * The core method for image comparison. It compares both images and returns
	 * a double value representing how similar the two images are. By convention
	 * the value ranges from 0.0 to 1.0, where 1.0 denotes two identical images
	 * and 0.0 denotes no similarities at all. <br>
	 * <br>
	 * 
	 * A comparator may report a value based on several available
	 * characteristics, such as the actual given image data (i.e. pixels,
	 * colors, shapes, etc.) or meta data (i.e. ex-tiff tags, coordinates etc.)
	 * 
	 * @param image1
	 *            The first image to be compared.
	 * @param image2
	 *            The second image to be compared.
	 * 
	 * @return The similarity of the two images measured by a double ranging
	 *         from 0.0 (no similarity) up to 1.0 (identical).
	 */
	public abstract double compare(Image image1, Image image2);

}
