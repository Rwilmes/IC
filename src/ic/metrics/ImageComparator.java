package ic.metrics;

import ic.image.Image;
import ic.metrics.hashes.ImageHash;
import ic.util.Config;

/**
 * The ImageComparator compares two images based on a given
 * ImageComparisonPolicy and returns if their are identified as equal.
 * 
 * @author Rwilmes
 * 
 */
public class ImageComparator {

	private ImageComparisonPolicy policy;

	public ImageComparator(ImageComparisonPolicy policy) {
		this.policy = policy;
	}

	public void setImageComparisonPolicy(ImageComparisonPolicy policy) {
		this.policy = policy;
	}

	public ImageComparisonPolicy getImageComparisonPolicy() {
		return policy;
	}

	/**
	 * The compare methods compares two images and returns true if they are
	 * equal enough.
	 **/
	public boolean areImagesEqual(Image i1, Image i2) {
		// iterate over all hashes and compare them
		for (Class<? extends ImageHash> c : Config.getHashClassesToCompute()) {
			policy.areImagesEqual(i1.getHash(c), i2.getHash(c));
		}
		return false;
	}
}
