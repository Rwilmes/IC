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
	
	public Image(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
}
