package ic.metrics.hashes.perceptual;

import ic.metrics.hashes.ImageHash;
import ic.util.DCT;
import ic.util.Processing;
import ic.util.Timer;
import ic.util.Timer.TimerType;
import ic.util.Utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * The PHash class is used to compute the perceptual hash of a given image. The
 * hash will be a string containing 16 bytes.
 * 
 * The algorithm used first resizes the image in an 32x32 grayscale matrix. A
 * discrete cosine transformation (DCT) will be performed on the matrix,
 * resulting in 32x32 DCT coefficients. (Note: The DCT implementation is taken
 * from http://www.nyx.net/~smanley/dct/DCT.html. It has all the DCT Matrix
 * values precomputed.) We take the top left 8x8 frequencies (which are the low
 * frequencies) and compute their average A (while leaving out the very first
 * one, which might throw off the average). All coefficients C_i will be
 * compared to the average A over the 64 coefficients. A hash of 64 bits H_i is
 * computed given the following ruling: <br>
 * H_i := 0 if C_i < A <br>
 * H_i := 1 if C_i >= A
 * 
 * The 64 bits will then be converted in a 16 byte hash value (in form of a
 * String).
 * 
 * 
 * The hamming distance between two pHash values illustrates their distance. It
 * can be interpreted as follows:
 * 
 * d(h1, h2) <br>
 * 0 <=> identical image <br>
 * 1-10 <=> variant of the same image <br>
 * 10 <=> different image
 * 
 * @see www.phash.org
 * @see Zauner, Christoph.
 *      "Implementation and benchmarking of perceptual image hash functions."
 *      (2010)
 * @see Manley, Steve. http://www.nyx.net/~smanley/dct/DCT.html
 * 
 * @author Rwilmes
 * 
 */
public class PHash32 extends ImageHash {

	// static DCT transformation instance
	private static final DCT transformation = new DCT(0);

	public PHash32(String hash) {
		super(hash);
	}

	@Override
	/**
	 * Computes the hamming distance between this DHash object and the given
	 * DHash object.
	 * 
	 * Results should be interpreted as follows: <br>
	 * 0 <=> identical image <br>
	 * 1-10 <=> variant of the same image <br>
	 * 10 <=> different image
	 **/
	public int compareTo(ImageHash hash) {
		return Utils.computeHammingDistance(getHash(), hash.getHash());
	}

	/** Computes the pHash of a given image. **/
	public static PHash32 computeHash(BufferedImage img) {
		Timer timer = new Timer(TimerType.TIMER_HASHING_PHASH);

		// resize and grayscale the image
		BufferedImage i2 = Processing.resizeAndGrayScale(img, 32, 32);

		// get data pixels from image
		byte[] pixels = ((DataBufferByte) i2.getRaster().getDataBuffer())
				.getData();

		// transform pixels array to char matrix
		char[][] pixelsCharMatrix = new char[32][];

		for (int i = 0; i < 32; i++) {
			char[] tempChar = new char[32];
			for (int j = 0; j < 8; j++) {
				int index = i * 8 + j;
				tempChar[j] = (char) (pixels[index] & 0xFF);
			}
			pixelsCharMatrix[i] = tempChar;
		}

		// compute DCT
		int[][] dctCoefficients = transformation.forwardDCT(pixelsCharMatrix);

		// only take the low frequency information (upper left 8x8 block)
		int[][] lowFrequency = new int[8][8];

		int sum = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				lowFrequency[i][j] = dctCoefficients[i][j + 1];

				// leave out point 0,0 in the matrix, as it will throw off the
				// average
				if (i > 0 || j > 0)
					sum += dctCoefficients[i][j];
			}
		}

		// compute average frequency
		double avg = sum / 63;

		// copy matrix in single array
		int[] hash = new int[64];

		// compare frequencies to the average
		for (int i = 0; i < lowFrequency.length; i++) {
			for (int j = 0; j < lowFrequency[i].length; j++) {
				if (lowFrequency[i][j] < avg)
					hash[i * 8 + j] = 1;
				else
					hash[i * 8 + j] = 0;
			}
		}

		// init byte array - will be filled with 4 bits each from the hash array
		byte[] bytes = new byte[16];
		int counter = 0;

		// parse every four bits as one byte
		for (int j = 0; j < hash.length; j += 4) {
			bytes[counter] = Byte.parseByte("" + hash[j + 3] + hash[j + 2]
					+ hash[j + 1] + hash[j], 2);
			counter++;
		}

		// build hash-string based on the parsed bytes
		StringBuilder sb2 = new StringBuilder();
		for (byte b : bytes) {
			sb2.append(String.format("%2X", b));
		}
		String pHash = sb2.toString().replace(" ", "").toLowerCase();

		// stop timer
		timer.stop();

		// return pHash
		return new PHash32(pHash);
	}
}
