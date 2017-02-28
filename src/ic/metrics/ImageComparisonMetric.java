package ic.metrics;

import ic.image.Image;

/** Interface for metrics to compare images. **/
public interface ImageComparisonMetric {

	public abstract String getName();

	public abstract ImageSimilarity compare(Image i1, Image i2);

}