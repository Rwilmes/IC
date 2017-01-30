package ic.metrics.name;

import ic.util.Processing;
import ic.util.Timer;
import ic.util.Timer.TimerType;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

/**
 * An implementation of the dHash algorithm.
 * 
 * @see http://www.hackerfactor.com/blog/?/archives/529-Kind-of-Like-That.html
 * @author Rwilmes
 * 
 */
public class DHash {

	public static String getDHash2(BufferedImage i) throws IOException {
		// start timer
		Timer timer = new Timer(TimerType.TIMER_HASHING_DHASH);

		// resize and grayscale image
		BufferedImage i2 = Processing.resizeAndGrayScale(i, 9, 8);

		byte[] pixels = ((DataBufferByte) i2.getRaster().getDataBuffer())
				.getData();

		int[] hash = new int[64];

		int counter = 0;

		// iterate over all rows
		for (int row = 0; row < 8; row++) {
			// iterate over all pixels of the row
			for (int j = 0; j < 8; j++) {
				// index of the specific pixel
				int index = row * 9 + j;

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

		byte[] bytes = new byte[16];
		counter = 0;

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
		String dHash = sb2.toString().replace(" ", "").toLowerCase();

		// stop timer
		timer.stop();

		// return hash
		return dHash;
	}

	private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

		final byte[] pixels = ((DataBufferByte) image.getRaster()
				.getDataBuffer()).getData();
		final int width = image.getWidth();
		final int height = image.getHeight();
		final boolean hasAlphaChannel = image.getAlphaRaster() != null;

		int[][] result = new int[height][width];
		if (hasAlphaChannel) {
			final int pixelLength = 4;
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
				argb += ((int) pixels[pixel + 1] & 0xff); // blue
				argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
				argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
				result[row][col] = argb;
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		} else {
			final int pixelLength = 3;
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += -16777216; // 255 alpha
				argb += ((int) pixels[pixel] & 0xff); // blue
				argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
				argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
				result[row][col] = argb;
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		}

		return result;
	}

}
