package ic.comparator;

import java.util.ArrayList;

import ic.image.Image;
import ic.metrics.ImageComparisonMetric;

/**
 * The abstract ImageComparator class defines all the methods an image
 * comparator has to supply. An ImageComparator uses multiple
 * ImageComparisonMetrics in order to determine how similar to images are.
 * 
 * @author Rwilmes
 * 
 */
public abstract class ImageComparator {

	private String name;
	private ArrayList<ImageComparisonMetric> metrics;

	public ImageComparator(String name, ArrayList<ImageComparisonMetric> metrics) {
		this.name = name;
		this.metrics = metrics;
	}

	public String getName() {
		return name;
	}

	public ArrayList<ImageComparisonMetric> getMetrics() {
		return metrics;
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
