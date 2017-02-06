package ic.gui.search;

import ic.gui.ImagePanel;
import ic.image.Image;
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

public class SearchEntry extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image img;

	private SearchEntry thisEntry;

	private SearchPanel thisParent;

	private JPanel thumbnail;

	private JLabel pathLabel;
	private JLabel pathField;

	public SearchEntry(SearchPanel thisParent, Image baseImg, Image img) {
		super();

		thisEntry = this;
		this.thisParent = thisParent;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.img = img;

		thumbnail = new ImagePanel(img.getThumbnail());
		thumbnail.setSize(Config.GUI_THUMBNAIL_SIZE);
		thumbnail.setPreferredSize(Config.GUI_THUMBNAIL_SIZE);
		thumbnail.setMinimumSize(Config.GUI_THUMBNAIL_SIZE);
		thumbnail.setMaximumSize(Config.GUI_THUMBNAIL_SIZE);
		thumbnail.setBackground(Color.gray);
		this.add(thumbnail);

		pathLabel = new JLabel("File: ");
		this.add(pathLabel);

		pathField = new JLabel(img.getPath());
		pathField.setPreferredSize(new Dimension(
				Config.GUI_SEARCH_ENTRY_PATH_WIDTH, pathField
						.getPreferredSize().height));
		this.add(pathField);

		HashInfoPanel dHashPanel = new HashInfoPanel(baseImg.getDHash(),
				img.getDHash());
		HashInfoPanel pHashPanel = new HashInfoPanel(baseImg.getPHash(),
				img.getPHash());

		pHashPanel.setPreferredSize(new Dimension(
				Config.GUI_SEARCH_ENTRY_HASH_WIDTH + Config.GUI_THUMBNAIL_WIDTH
						- 10, pHashPanel.getPreferredSize().height));
		dHashPanel.setPreferredSize(new Dimension(
				Config.GUI_SEARCH_ENTRY_HASH_WIDTH + Config.GUI_THUMBNAIL_WIDTH
						- 10, dHashPanel.getPreferredSize().height));

		this.add(GUI.genVerticalSeparator(thumbnail.getPreferredSize().height));
		this.add(dHashPanel);
		this.add(GUI.genVerticalSeparator(thumbnail.getPreferredSize().height));
		this.add(pHashPanel);
		this.add(GUI.genVerticalSeparator(thumbnail.getPreferredSize().height));

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

		this.add(buttonPanel);

	}

	public void trash() {
		thisParent.trashEntry(this);
	}

}
