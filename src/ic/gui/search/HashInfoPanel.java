package ic.gui.search;

import ic.gui.ImagePanel;
import ic.metrics.hashes.ImageHash;
import ic.util.Config;
import ic.util.Processing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The HashInfoPanel is a subpanel used in SearchEntries. It displays a hash,
 * its distance to a given base-hash and an image representing the hash.
 * 
 * @author Rwilmes
 * 
 */
public class HashInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HashInfoPanel(ImageHash baseHash, ImageHash hash) {
		super();

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

		JLabel distanceLabel = new JLabel("distance = "
				+ hash.compareTo(baseHash));
		JLabel hashLabel = new JLabel(hash.getHash());

		BufferedImage hashImage = Processing.resize(
				Processing.getImageFromHash(hash),
				Config.GUI_SEARCH_ENTRY_HASH_IMAGE_SIZE.width,
				Config.GUI_SEARCH_ENTRY_HASH_IMAGE_SIZE.height);
		ImagePanel imagePanel = new ImagePanel(hashImage);
		imagePanel.setPreferredSize(Config.GUI_SEARCH_ENTRY_HASH_IMAGE_SIZE);
		imagePanel.setBackground(Color.gray);

		hashLabel.setPreferredSize(new Dimension(
				Config.GUI_SEARCH_ENTRY_HASH_WIDTH
						- Config.GUI_SEARCH_ENTRY_HASH_IMAGE_SIZE.width - 10,
				hashLabel.getPreferredSize().height));

		textPanel.add(distanceLabel);
		textPanel.add(hashLabel);
		this.add(textPanel);
		this.add(imagePanel);

		this.setPreferredSize(new Dimension(Config.GUI_SEARCH_ENTRY_HASH_WIDTH,
				this.getPreferredSize().height));
	}

}
