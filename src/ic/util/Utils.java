package ic.util;

import ic.util.log.Log;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Utility class containing general utility methods.
 * 
 * @author Rwilmes
 * 
 */
public class Utils {

	/** Prints the dimension of a buffered image. **/
	public static void printDimension(BufferedImage i) {
		if (i == null)
			Log.log("dimension: image is null!");
		else
			Log.log("dimension: " + i.getWidth() + "x" + i.getHeight());
	}

	/** Converts an Image into BufferedImage. **/
	public static BufferedImage toBufferedImage(Image i) {
		if (i instanceof BufferedImage)
			return (BufferedImage) i;

		// create buffered image
		BufferedImage bi = new BufferedImage(i.getWidth(null),
				i.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// draw into buffered image
		Graphics2D bGr = bi.createGraphics();
		bGr.drawImage(i, 0, 0, null);
		bGr.dispose();

		return bi;
	}

	/** Computes the byte-wise hamming distance between the two strings. **/
	public static int computeHammingDistance(String s1, String s2) {
		if (s1.length() != s2.length())
			return -1;

		int counter = 0;

		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i))
				counter++;
		}

		return counter;
	}

	/** Compute the bitwise hamming distance on two hex-strings. **/
	public static int computeHammingDistanceBitwise(String s1, String s2) {
		if (s1.length() != s2.length())
			return -1;

		int similarities = 0;

		// iterate over hash string
		int counter = 0;
		for (int i = 0; i < s1.length(); i++) {
			int v1 = Integer.parseInt(s1.substring(i, i + 1), 16);
			int v2 = Integer.parseInt(s2.substring(i, i + 1), 16);

			String b1 = Integer.toBinaryString(v1);
			String b2 = Integer.toBinaryString(v2);

			// add padding
			while (b1.length() < 4)
				b1 = "0" + b1;

			while (b2.length() < 4)
				b2 = "0" + b2;

			for (int j = 0; j < b1.length(); j++) {
				counter++;

				if (b1.charAt(j) == b2.charAt(j))
					similarities++;
			}

		}

		// return number of unsimilar bits
		return (counter - similarities);
	}

	/** Prints the given int matrix. **/
	public static void printMatrix2D(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			String temp = "";
			for (int j = 0; j < matrix[i].length; j++) {
				temp += " " + matrix[i][j];
			}
			System.out.println(temp);
		}
	}

	/** Prints the given double matrix. **/
	public static void printMatrix2D(double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			String temp = "";
			for (int j = 0; j < matrix[i].length; j++) {
				temp += " " + matrix[i][j];
			}
			System.out.println(temp);
		}
	}

	/** Prints the given char matrix. **/
	public static void printMatrix2D(char[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			String temp = "";
			for (int j = 0; j < matrix[i].length; j++) {
				temp += " " + matrix[i][j];
			}
			System.out.println(temp);
		}
	}

	/** Computes the median of the given array. **/
	public static int computeMedian(int[] array) {
		int[] temp = new int[array.length];
		System.arraycopy(array, 0, temp, 0, array.length);
		Arrays.sort(temp);
		return temp[temp.length / 2];
	}

	/** Compute the average over the given array. **/
	public static double computeAverage(int[] array) {
		int sum = 0;
		for (int i : array)
			sum += i;

		return 1.0 * sum / array.length;

	}

}
