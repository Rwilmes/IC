package ic.metrics;

import ic.metrics.hashes.ImageHash;
import ic.util.Config;

/**
 * A basic implementation of an ImageComparisonPolicy. It checks the distances
 * of between two hashes. Images are equal if the distances between their hashes
 * is below the upper bound.
 * 
 * @author Rwilmes
 *
 */
public class BasicDistancePolicy extends ImageComparisonPolicy {

	private int upperBound;

	public BasicDistancePolicy() {
		this(Config.IMAGE_COMPARISON_BASIC_DISTANCE_UPPER_BOUND);
	}

	public BasicDistancePolicy(int upperBound) {
		this.upperBound = upperBound;
	}

	@Override
	public boolean areImagesEqual(ImageHash i1, ImageHash i2) {
		if (i1.compareTo(i2) < upperBound)
			return true;
		else
			return false;
	}

}
