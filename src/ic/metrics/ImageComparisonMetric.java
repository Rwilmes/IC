package ic.metrics;

import ic.image.Image;
import ic.image.ImageSimilarity;
import ic.util.exceptions.ImageSimilarityOverflowException;

/** Abstract class for metrics to compare images. **/
public abstract class ImageComparisonMetric {

	private String name;

	public ImageComparisonMetric(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract ImageSimilarity compare(Image i1, Image i2) throws ImageSimilarityOverflowException;

}
