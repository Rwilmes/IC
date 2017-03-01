package ic.metrics;

import ic.image.Image;
import ic.metrics.hashes.ImageHash;
import ic.util.Config;

/**
 * An ImageComparisonPolicy defines which characteristics have to be met for two
 * images to be categorized as being the same image.
 * 
 * @author Rwilmes
 * 
 */
public class ImageComparisonPolicy {

	public ImageComparisonPolicy() {

	}

	/** Returns true if both images are identified as the same image. **/
	public boolean are(Image i1, Image i2) {


		return true;
	}
}
