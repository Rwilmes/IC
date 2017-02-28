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
