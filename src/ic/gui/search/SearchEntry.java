package ic.gui.search;

import ic.gui.ImagePanel;
import ic.image.Image;
import ic.metrics.hashes.ImageHash;
import ic.util.Config;
import ic.util.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A search entry represenets a single search result in the searchpanel.
 * 
 * @author Rwilmes
 * 
 */
public class SearchEntry extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SearchEntry thisEntry;

	private SearchPanel thisParent;

	private JPanel thumbnail;

	private JLabel pathLabel;
	private JLabel pathField;

	public SearchEntry(SearchPanel thisParent, Image baseImg, Image img) {
		this.thisEntry = this;
		this.thisParent = thisParent;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		thumbnail = new ImagePanel(img.getThumbnail());
		thumbnail.setSize(Config.GUI_THUMBNAIL_SIZE);
		thumbnail.setPreferredSize(Config.GUI_THUMBNAIL_SIZE);
		thumbnail.setMinimumSize(Config.GUI_THUMBNAIL_SIZE);
		thumbnail.setMaximumSize(Config.GUI_THUMBNAIL_SIZE);
		thumbnail.setBackground(Color.gray);
		this.add(thumbnail);

		pathLabel = new JLabel("File: ");
		pathLabel.setToolTipText(img.getPath());
		this.add(pathLabel);

		pathField = new JLabel(img.getFilename());
		pathField.setToolTipText(img.getPath());
		pathField.setPreferredSize(new Dimension(
				Config.GUI_SEARCH_ENTRY_PATH_WIDTH, pathField
						.getPreferredSize().height));
		this.add(pathField);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		JButton copyButton = new JButton("Copy");
		copyButton.setToolTipText("Copy the image path to clipboard");
		copyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(pathField
						.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
			}
		});
		buttonPanel.add(copyButton);

		JButton trashButton = new JButton("Trash");
		trashButton.setToolTipText("Removes this entry from the results.");
		trashButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				thisEntry.trash();
			}
		});

		buttonPanel.add(trashButton);

		this.add(GUI.genVerticalSeparator(thumbnail.getPreferredSize().height));
		this.add(buttonPanel);

		// create and add hash panels
		for (Class<? extends ImageHash> c : Config.getHashClassesToCompute()) {
			this.add(GUI.genVerticalSeparator(thumbnail.getPreferredSize().height));
			this.add(new HashInfoPanel(baseImg.getHash(c), img.getHash(c)));
		}
	}

	/** Trashes this entry. **/
	public void trash() {
		thisParent.trashEntry(this);
	}

}
