package ic.image;

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

	public Image(String dir, String filename) {
		this.dir = dir;
		this.filename = filename;
		this.path = dir + filename;
	}

	public Image(String path) {
		this.path = path;
		String[] splits = path.split("/");
		if (splits.length > 1) {
			this.filename = splits[splits.length - 1];
			this.dir = splits[0];
			for (int i = 1; i < splits.length - 1; i++) {
				this.dir += "/" + splits[i];
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
}
