package ic.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Extends the basic JPanel and overwrites the paint method to enable painting
 * of images.
 * 
 * @author Rwilmes
 * 
 */
public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage img;

	public ImagePanel(BufferedImage img) {
		this.img = img;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}

	public void setImage(BufferedImage img) {
		this.img = img;
	}

}
