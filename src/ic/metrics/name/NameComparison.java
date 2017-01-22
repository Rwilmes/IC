package ic.metrics.name;

import ic.image.Image;
import ic.image.ImageSimilarity;
import ic.metrics.ImageComparisonMetric;
import ic.util.exceptions.ImageSimilarityOverflowException;

/** A simple metric comparing only the filenames of two images. **/
public class NameComparison extends ImageComparisonMetric {

	public NameComparison(String name) {
		super(name);
	}

	@Override
	public ImageSimilarity compare(Image i1, Image i2) throws ImageSimilarityOverflowException {
		if (i1.getFilename().equals(i2.getFilename()))
			return new ImageSimilarity(i1, i2, 1.0);
		else
			return new ImageSimilarity(i1, i2, 0.0);
	}
}
