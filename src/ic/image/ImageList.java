package ic.image;

import java.util.ArrayList;

/**
 * An ImageList is a list of images.
 * 
 * @author Rwilmes
 *
 */
public class ImageList {

	private ArrayList<Image> list;
	
	public ImageList() {
		this.list = new ArrayList<Image>();
	}
	public ImageList(int size) {
		this.list = new ArrayList<Image>(size);
	}
}
