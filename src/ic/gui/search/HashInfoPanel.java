package ic.gui.search;

import ic.metrics.hashes.ImageHash;
import ic.util.Config;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HashInfoPanel extends JPanel {

	public HashInfoPanel(ImageHash baseHash, ImageHash hash) {
		super();

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

		JLabel distanceLabel = new JLabel("distance = " + hash.compareTo(baseHash));
		JLabel hashLabel = new JLabel(hash.getHash());

		JPanel imagePanel = new JPanel();
		imagePanel.setPreferredSize(new Dimension(Config.GUI_THUMBNAIL_WIDTH - 10,
				Config.GUI_THUMBNAIL_HEIGHT - 10));
		imagePanel.setBackground(Color.gray);

		
//		System.out.println(hashLabel.getPreferredSize().width);
		hashLabel.setPreferredSize(new Dimension(120, hashLabel.getPreferredSize().height));
		
		textPanel.add(distanceLabel);
		textPanel.add(hashLabel);
		this.add(textPanel);
		this.add(imagePanel);

	}
}
