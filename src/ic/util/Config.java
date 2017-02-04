package ic.util;

import javax.swing.ImageIcon;

/**
 * This class is used to configure the ImageComparison program.
 * 
 * @author Rwilmes
 * 
 */
public class Config {

	/*
	 * GUI CONFIG
	 */
	public static final int GUI_MIN_WIDTH = 840;
	public static final int GUI_MIN_HEIGHT = 560;

	public static final String GUI_IMAGE_DEFAULT_DIR = "data/";
	public static final String GUI_DIRECTORY_DEFAULT_DIR = "data/";

	public static final int GUI_TABS_MAX = 20;

	public static final ImageIcon ICON_SETTINGS = IO
			.readImageIcon("img/settings_24x.png");
	public static final ImageIcon ICON_PROCESSING = IO
			.readImageIcon("img/processing_24x.png");
	public static final ImageIcon ICON_QUEUE = IO
			.readImageIcon("img/queue_24x.png");
	public static final ImageIcon ICON_DONE = IO
			.readImageIcon("img/done_24x.png");

}
