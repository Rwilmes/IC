package ic.metrics;

import ic.image.Image;
import ic.metrics.hashes.ImageHash;
import ic.util.Config;

/**
 * The ImageComparator compares two images based on a given
 * ImageComparisonPolicy and returns if their distance is sufficiently small.
 * 
 * @author Rwilmes
 * 
 */
public class ImageComparator {

	private ImageComparisonPolicy policy;

	public ImageComparator(ImageComparisonPolicy policy) {
		this.policy = policy;
	}
	
	public boolean compare(Image i1, Image i2) {
		for (Class<? extends ImageHash> c : Config.getHashClassesToCompute()) {
			int distance = i1.getHash(c).compareTo(i2.getHash(c));
			
		}
		
		return true;
	}
}
