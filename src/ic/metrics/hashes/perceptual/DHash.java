package ic.metrics.hashes.perceptual;

import ic.metrics.hashes.ImageHash;
import ic.util.Processing;
import ic.util.Timer;
import ic.util.Timer.TimerType;
import ic.util.Utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * A DHash object represents a dHash which stands for "difference hash". The
 * dHash algorithm scales the images down to 9x8 grayscale image. Then it
 * computes for each row the differences between adjacent pixels and maps them
 * onto a single bit (0 if the first bit is brighter, 1 if the second bit is
 * brighter). This results in the 64 bit hash. Because the hash represents the
 * gradient it is supposed to be robust to rescaling (even with changing aspect
 * ratios), increasing or decreasing of brightness or contrast or altering of
 * colors. Even gamma corrections will not impact the results to harshly.
 * 
 * The hamming distance between two dHash values illustrates their distance. It
 * can be interpreted as follows:
 * 
 * d(h1, h2) <br>
 * 0 <=> identical image <br>
 * 1-10 <=> variant of the same image <br>
 * 10 <=> different image
 * 
 * 
 * An implementation of the dHash algorithm.
 * 
 * @see http://www.hackerfactor.com/blog/?/archives/529-Kind-of-Like-That.html
 * @author Rwilmes
 * 
 */
public class DHash extends ImageHash {

	public DHash(String hash) {
		super(hash);
	}

	/**
	 * Computes the hamming distance between this DHash object and the given
	 * DHash object.
	 * 
	 * Results should be interpreted as follows: <br>
	 * 0 <=> identical image <br>
	 * 1-10 <=> variant of the same image <br>
	 * 10 <=> different image
	 **/
	@Override
	public int compareTo(ImageHash hash) {
		return Utils.computeHammingDistance(getHash(), hash.getHash());
	}

	/**
	 * Computes the hamming distance between the two DHashes.
	 * 
	 * Results should be interpreted as follows: <br>
	 * 0 <=> identical image <br>
	 * 1-10 <=> variant of the same image <br>
	 * 10 <=> different image
	 * **/
	public static int compare(DHash hash1, DHash hash2) {
		return Utils.computeHammingDistance(hash1.getHash(), hash2.getHash());
	}

	/** Computes a DHash on the given image. **/
	public static DHash computeHash(BufferedImage img) {
		// start timer
		Timer timer = new Timer(TimerType.TIMER_HASHING_DHASH);

		// resize and grayscale image
		BufferedImage i2 = Processing.resizeAndGrayScale(img, 9, 8);

		// get data pixels
		byte[] pixels = ((DataBufferByte) i2.getRaster().getDataBuffer())
				.getData();

		// init hash bit array
		int[] hash = new int[64];

		int counter = 0;

		// iterate over all rows
		for (int i = 0; i < 8; i++) {
			// iterate over all pixels of the row
			for (int j = 0; j < 8; j++) {
				// index of the specific pixel
				int index = i * 9 + j;

				// convert signed byte into unsigned int
				int leftPixel = (int) pixels[index] & 0xFF;
				int rightPixel = (int) pixels[index + 1] & 0xFF;

				// compare P[X] and P[X+1] and store gradient
				if (leftPixel < rightPixel)
					hash[counter] = 1;
				else
					hash[counter] = 0;

				counter++;
			}
		}

		// init byte array - will be filled with 4 bits each from the hash array
		byte[] bytes = new byte[16];
		counter = 0;

		// parse every four bits as one byte
		for (int i = 0; i < hash.length; i += 4) {
			bytes[counter] = Byte.parseByte("" + hash[i + 3] + hash[i + 2]
					+ hash[i + 1] + hash[i], 2);
			counter++;
		}

		// build hash-string based on the parsed bytes
		StringBuilder sb2 = new StringBuilder();
		for (byte b : bytes) {
			sb2.append(String.format("%2X", b));
		}
		String dHash = sb2.toString().replace(" ", "").toLowerCase();

		// stop timer
		timer.stop();

		// return new DHash object
		return new DHash(dHash);
	}

}
