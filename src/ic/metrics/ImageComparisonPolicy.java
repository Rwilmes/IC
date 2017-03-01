package ic.metrics;

import ic.metrics.hashes.ImageHash;

/**
 * An ImageComparisonPolicy defines which characteristics have to be met for two
 * ImageHashes to be categorized as being the same hash.
 * 
 * @author Rwilmes
 * 
 */
public abstract class ImageComparisonPolicy {

	/** Returns true if both images are identified as the same image. **/
	public abstract boolean areImagesEqual(ImageHash h1, ImageHash h2);
}
