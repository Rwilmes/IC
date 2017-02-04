package ic.gui.search;

import ic.gui.ImagePanel;
import ic.image.Image;
import ic.util.Config;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchEntry extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image img;

	private JPanel thumbnail;

	public SearchEntry(Image img) {
		super();

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		System.out.println("init search entry: " + img.getPath());

		this.img = img;
//		final BufferedImage thumbnailImage = img.getThumbnail();
		thumbnail = new ImagePanel(img.getThumbnail());
		thumbnail.setSize(Config.GUI_THUMBNAIL_SIZE);
		thumbnail.setPreferredSize(Config.GUI_THUMBNAIL_SIZE);
		thumbnail.setMinimumSize(Config.GUI_THUMBNAIL_SIZE);
		thumbnail.setMaximumSize(Config.GUI_THUMBNAIL_SIZE);
		thumbnail.setBackground(Color.gray);
		this.add(thumbnail);
		this.add(new JLabel("File: " + img.getFilename()));

	}

}
