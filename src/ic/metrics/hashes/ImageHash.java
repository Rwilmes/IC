package ic.metrics.hashes;

/**
 * Abstract class for hash objects over images. Each ImageHash holds a String,
 * which represents the actual hash value.
 * 
 * All implementations have to implement a compute(..) method and a compare(..)
 * method.
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

	public abstract int compareTo(ImageHash h);
}
