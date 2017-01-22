package ic.image;

import ic.util.exceptions.ImageSimilarityOverflowException;

/**
 * The WeihgtedImageSimilarity class extends the ImageSimilarity class by
 * introducing a double weight value. It can be used to aggregate the similarity
 * of two images based on multiple ImageSimilarities, whose importance can be
 * controled via the weight.
 * 
 * @author Rwilmes
 * 
 */
public class WeightedImageSimilarity extends ImageSimilarity {

	private double weight;

	public WeightedImageSimilarity(Image baselineImage, Image comparedImage,
			double similarity, double weight)
			throws ImageSimilarityOverflowException {
		super(baselineImage, comparedImage, similarity);
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}
}
