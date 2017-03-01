package ic.metrics.hashes;

import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Abstract class for hash objects over images. Each ImageHash holds a String,
 * which represents the actual hash value.
 * 
 * All implementations have to implement a compareTo(..) method and a
 * computeHash(..) method.
 * 
 * The static computeHash method can be used to compute hashes of arbitrary
 * ImageHash implementations.
 * 
 * @author Rwilmes
 * 
 */
public abstract class ImageHash {

	private String hash;

	public ImageHash(String hash) {
		this.hash = hash;
	}

	public String getHash() {
		return hash;
	}

	/** Compares one hash to another hash. Returns a distance value. **/
	public abstract int compareTo(ImageHash h);

	/**
	 * This method computes the a hash of the given hashClass over the given
	 * BufferedImage.
	 **/
	public static ImageHash computeHash(BufferedImage img,
			Class<? extends ImageHash> hashClass) {
		try {
			// get computeHash method
			Method m = hashClass.getMethod("computeHash", BufferedImage.class);

			// invoke method and return computed hash
			return (ImageHash) m.invoke(null, img);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

		// return null if something went wrong
		return null;
	}
}
