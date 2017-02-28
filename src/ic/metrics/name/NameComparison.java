package ic.metrics.name;

import ic.image.Image;

/** A simple metric comparing only the filenames of two images. **/
public class NameComparison {

	private String name;

	public NameComparison(String name) {
		this.name = name;
	}

	/** Compares two images. Returns 0 if they are equal, 1 else. **/
	public static int compare(Image i1, Image i2) {
		if (i1.getFilename().equals(i2.getFilename()))
			return 0;
		else
			return 1;
	}
}
