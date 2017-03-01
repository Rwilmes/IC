package ic.image;

import ic.metrics.hashes.ImageHash;
import ic.metrics.hashes.perceptual.DHash;
import ic.metrics.hashes.perceptual.PHash;
import ic.util.Config;
import ic.util.IO;
import ic.util.Processing;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * An Image object represents an image(-file). It reads the image once on
 * initialization and performs the hash computations. Afterwards it will only
 * point to the image file by the specified path. However, it stores a thumbnail
 * of the image and several ImageHash objects in an ArrayList. They denote
 * separate hashes computed via separate methods.
 * 
 * @author Rwilmes
 * 
 */
public class Image {

	// image file properties
	protected String path;
	protected String filename;
	protected String dir;

	// thumbanil
	protected BufferedImage thumbnail;

	// collection of hashes
	protected ArrayList<ImageHash> hashes;

	/** Inits an Image object with the default hashes. **/
	public Image(String path) throws IOException {
		this(IO.readImage(path), path, Config.getHashClassesToCompute());
	}

	/**
	 * Constructs an Image object with an already read buffered image and the
	 * default hashes.
	 **/
	public Image(BufferedImage img, String path) {
		this(img, path, Config.getHashClassesToCompute());
	}

	/** Constructs an Image object with an already read buffered image. **/
	@SafeVarargs
	public Image(BufferedImage img, String path,
			Class<? extends ImageHash>... hashClasses) {
		// set path, dir and filename
		this.path = path;

		String[] splits = path.split("\\\\");
		if (splits.length > 1) {
			this.filename = splits[splits.length - 1];
			this.dir = "";
			for (int i = 0; i < splits.length - 1; i++) {
				this.dir += splits[i] + "\\";
			}
		} else {
			this.dir = "";
			this.filename = splits[0];
		}

		// compute hashes
		this.hashes = computeHashes(img, hashClasses);

		// compute and set thumbnail
		this.thumbnail = Processing.resize(img,
				(int) Math.floor(Config.GUI_THUMBNAIL_SIZE.getHeight()),
				(int) Math.floor(Config.GUI_THUMBNAIL_SIZE.getHeight()));
	}

	/**
	 * Computes all the hashes specified in the array over the given image. At
	 * the very least returns an empty list of ImageHash.
	 **/
	protected ArrayList<ImageHash> computeHashes(BufferedImage img,
			Class<? extends ImageHash>[] hashClasses) {
		ArrayList<ImageHash> hashList = new ArrayList<ImageHash>();

		// compute hashes
		for (Class<? extends ImageHash> c : hashClasses) {
			// compute hash
			ImageHash hash = ImageHash.computeHash(img, c);

			// if computation successful add hash to list of hashes
			if (hash != null)
				hashList.add(hash);
		}

		return hashList;
	}

	/** Returns the images thumbnail. **/
	public BufferedImage getThumbnail() {
		return thumbnail;
	}

	/** Returns the image path. **/
	public String getPath() {
		return path;
	}

	/** Returns the images directory. **/
	public String getDir() {
		return dir;
	}

	/** Returns the images filename. **/
	public String getFilename() {
		return filename;
	}

	/** Returns the list of computed hashes. **/
	public ArrayList<ImageHash> getHashes() {
		return hashes;
	}

	/**
	 * Adds a hash to this image. Note: Multiple hashes of the same class will
	 * not be considered.
	 **/
	public void addHash(ImageHash hash) {
		hashes.add(hash);
	}

	/**
	 * Returns a hash of the specified class. Will return null if no hash of
	 * that class is found.
	 **/
	public ImageHash getHash(Class<? extends ImageHash> hashClass) {
		for (ImageHash hash : hashes) {
			if (hashClass.isInstance(hash))
				return hash;
		}

		return null;
	}

	/** Returns a dHash if possible, else null. **/
	public DHash getDHash() {
		return (DHash) getHash(DHash.class);
	}

	/** Returns a pHash if possible, else null. **/
	public PHash getPHash() {
		return (PHash) getHash(PHash.class);
	}
}
