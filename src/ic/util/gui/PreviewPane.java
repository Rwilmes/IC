package ic.util.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Implementation of a PreviewPane used as an accessory for a JFileChooser.
 * Source was found at
 * http://www.javalobby.org/forums/thread.jspa?messageID=91844740
 * 
 * @author R.J. Lorimer
 * 
 */
public class PreviewPane extends JPanel implements PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -31042263633701560L;

	private JLabel label;
	private int maxImgWidth;

	public PreviewPane() {
		setLayout(new BorderLayout(5, 5));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(new JLabel("Preview:"), BorderLayout.NORTH);
		label = new JLabel();
		label.setBackground(Color.WHITE);
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(400, 400));
		maxImgWidth = 395;
		label.setBorder(BorderFactory.createEtchedBorder());
		add(label, BorderLayout.CENTER);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		Icon icon = null;
		if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(evt
				.getPropertyName())) {
			File newFile = (File) evt.getNewValue();
			if (newFile != null) {
				String path = newFile.getAbsolutePath();
				if (path.endsWith(".gif") || path.endsWith(".jpg")
						|| path.endsWith(".png") || path.endsWith(".bmp")) {
					try {
						BufferedImage img = ImageIO.read(newFile);
						float width = img.getWidth();
						float height = img.getHeight();
						float scale = height / width;
						width = maxImgWidth;
						height = (width * scale); // height should be scaled
													// from new width
						icon = new ImageIcon(img.getScaledInstance(
								Math.max(1, (int) width),
								Math.max(1, (int) height), Image.SCALE_SMOOTH));
					} catch (IOException e) {
						// couldn't read image.
					}
				}
			}

			label.setIcon(icon);
			this.repaint();

		}
	}

}
