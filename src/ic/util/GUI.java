package ic.util;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.alee.laf.WebLookAndFeel;

import ic.util.log.Log;

/**
 * Utility class for everything involving the gui.
 * 
 * @author Rwilmes
 *
 */
public class GUI {

	/** Lists the available java look and feels. **/
	public static void listLookAndFeels() {
		Log.sep();
		Log.log("Available Java Swing Look and Feels:");
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
			Log.log("\t" + info.getName());
	}

	/** Installs WebLAF. **/
	public static void installWebLAF() {
		WebLookAndFeel.install();
	}

	/** Enables WebLAF. **/
	public static void enableWebLAF() {
		try {
			UIManager.setLookAndFeel(new WebLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			Log.error("WebLAF not found, using default look and feel.");
			e.printStackTrace();
		}
	}

}
