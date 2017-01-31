package ic.image;

import ic.util.exceptions.ImageSimilarityOverflowException;

import java.util.ArrayList;

/**
 * A ComparedImage object is an extension to the regular Image object. It
 * represents an Image, which has already been compared to a given baseline
 * image. Thus, it holds a list of ImageSimilarity objects as well as a
 * reference to the baseline image.
 * 
 * @author Rwilmes
 * 
 */
public class ComparedImage extends Image {

	protected Image baselineImage;
	protected ArrayList<ImageSimilarity> similarityList;

	public ComparedImage(String path, Image baselineImage) {
		super(path, null, null);
		this.baselineImage = baselineImage;
		this.similarityList = new ArrayList<ImageSimilarity>();
	}

	public void addImageSimilarity(ImageSimilarity similarity) {
		this.similarityList.add(similarity);
	}

	public Image getBaselineImage() {
		return baselineImage;
	}

	/**
	 * Computes the aggregated image similartiy of this image and the baseline
	 * image based on all the given similarities.
	 **/
	public ImageSimilarity getAggregatedSimilarity()
			throws ImageSimilarityOverflowException {
		double score = 0.0;
		for (ImageSimilarity s : this.similarityList) {
			if (s instanceof WeightedImageSimilarity)
				score += ((WeightedImageSimilarity) s).getWeight()
						* s.getSimilarity();
			else
				score += s.getSimilarity();
		}

		score = score / this.similarityList.size();

		return new ImageSimilarity(baselineImage, this, score);
	}

}
