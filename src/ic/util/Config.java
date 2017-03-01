package ic.util;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import ic.metrics.BasicDistancePolicy;
import ic.metrics.ImageComparator;
import ic.metrics.hashes.ImageHash;
import ic.metrics.hashes.perceptual.DHash;
import ic.metrics.hashes.perceptual.PHash;

/**
 * This class is used to configure the ImageComparison program.
 * 
 * @author Rwilmes
 * 
 */
public class Config {

	/*
	 * HASH COMPUTATIONS CONFIG
	 */
	@SuppressWarnings("unchecked")
	private static Class<? extends ImageHash>[] hashClassesArray = new Class[] { DHash.class, PHash.class };

	@SuppressWarnings("unchecked")
	/** Sets new hashes to be computed. **/
	public static void setHashesToCompute(Class<? extends ImageHash>... hashClasses) {
		hashClassesArray = hashClasses;
	}

	/** Returns the classes of the currently set hashes to be computed. **/
	public static Class<? extends ImageHash>[] getHashClassesToCompute() {
		return hashClassesArray;
	}

	// default ImageComparator
	public static ImageComparator IMAGE_COMPARISON_DEFAULT_COMPARATOR = new ImageComparator(new BasicDistancePolicy());

	// default value for the upper bound policy
	public static final int IMAGE_COMPARISON_BASIC_DISTANCE_UPPER_BOUND = 12;

	/*
	 * GUI CONFIG
	 */
	public static final int GUI_MIN_WIDTH = 1200;
	public static final int GUI_MIN_HEIGHT = 800;

	public static final String GUI_IMAGE_DEFAULT_DIR = "./";
	public static final String GUI_DIRECTORY_DEFAULT_DIR = "./";

	public static final Dimension GUI_PREVIEW_IMAGE_SIZE = new Dimension(410, 410);

	public static final int GUI_TABS_MAX = 20;

	public static final ImageIcon ICON_SETTINGS = IO.readImageIcon("img/settings_24x.png");
	public static final ImageIcon ICON_PROCESSING = IO.readImageIcon("img/processing_24x.png");
	public static final ImageIcon ICON_QUEUE = IO.readImageIcon("img/queue_24x.png");
	public static final ImageIcon ICON_DONE = IO.readImageIcon("img/done_24x.png");

	public static final Dimension GUI_THUMBNAIL_SIZE = new Dimension(150, 150);

	public static final int GUI_SEARCH_ENTRY_PATH_WIDTH = 200;
	public static final int GUI_SEARCH_ENTRY_HASH_WIDTH = 175;
	public static final Dimension GUI_SEARCH_ENTRY_HASH_IMAGE_SIZE = new Dimension(50, 50);

	public static final int GUI_SEARCH_ENTRY_BUTTON_PANEL_WIDTH = 50;

	public static final boolean GUI_SEARCH_RECURSIVE = true;

}
