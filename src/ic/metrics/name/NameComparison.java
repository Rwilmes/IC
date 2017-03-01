package ic.metrics.name;

import ic.image.Image;

/** A simple metric comparing only the filenames of two images. **/
public class NameComparison {

	/** Compares two images. Returns 0 if they are equal, 1 else. **/
	public static int compare(Image i1, Image i2) {
		return NameComparison.compare(i1.getFilename(), i2.getFilename());
	}

	/** Compares two filenames. Returns 0 if they are equal, 1 else. **/
	public static int compare(String filename1, String filename2) {
		if (filename1.equals(filename2))
			return 0;
		else
			return 1;
	}
}
