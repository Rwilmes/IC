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

	public static final ImageIcon ICON_SETTINGS = IO
			.readImageIcon("img/settings_24x.png");

}
