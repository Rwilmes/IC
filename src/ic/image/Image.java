package ic.image;

import ic.metrics.name.DHash;
import ic.metrics.name.PHash;

/**
 * An Image object represents an image(-file). It does not contain the actual
 * image file but rather points to it by the specified path.
 * 
 * @author Rwilmes
 * 
 */
public class Image {

	protected String path;

	protected String filename;
	protected String dir;

	protected DHash dHash;
	protected PHash pHash;

	public Image(String dir, String filename, DHash dHash, PHash pHash) {
		this.dir = dir;
		this.filename = filename;
		this.path = dir + filename;
		this.dHash = dHash;
		this.pHash = pHash;
	}

	public Image(String path, DHash dHash, PHash pHash) {
		this.path = path;
		this.dHash = dHash;
		this.pHash = pHash;

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
	}

	public String getPath() {
		return path;
	}

	public String getDir() {
		return dir;
	}

	public String getFilename() {
		return filename;
	}

	public DHash getDHash() {
		return dHash;
	}

	public PHash getPHash() {
		return pHash;
	}
}
