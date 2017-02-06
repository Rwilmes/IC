package ic.gui.search;

import ic.gui.MainFrame;
import ic.image.Image;
import ic.util.Config;
import ic.util.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

public class SearchPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SearchPanel thisPanel;
	private MainFrame thisParent;

	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel southPanel;

	private JPanel dummy;

	private Image baseImg;

	public SearchPanel(MainFrame parent, Image baseImg, String imgPath,
			String dir) {
		super();

		thisPanel = this;
		thisParent = parent;
		this.baseImg = baseImg;

		// set layout
		this.setLayout(new BorderLayout());

		// create header
		northPanel = createHeader();
		this.add(northPanel, BorderLayout.NORTH);

		// create list
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		JScrollPane scrollPane = new JScrollPane(centerPanel);
		this.add(scrollPane, BorderLayout.CENTER);

		// create south panel
		southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addEntry("data/Alyson_Hannigan_200512.jpg");
			}
		});
		southPanel.add(addButton);

		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ok();
			}
		});
		southPanel.add(okButton);

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				thisPanel.close();
			}
		});

		southPanel.add(closeButton);

		dummy = new JPanel();
		dummy.setPreferredSize(new Dimension(20, 10));
		southPanel.add(dummy);
		southPanel.add(new JLabel("Image: "));
		southPanel.add(new JLabel(imgPath));

		JPanel dummy2 = new JPanel();
		dummy2.setPreferredSize(new Dimension(20, 10));
		southPanel.add(dummy2);

		southPanel.add(new JLabel("Directory: "));
		southPanel.add(new JLabel(dir));

		this.add(southPanel, BorderLayout.SOUTH);

	}

	public JPanel createHeader() {
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel imageHeader = new JLabel("Image");
		imageHeader.setPreferredSize(new Dimension(Config.GUI_THUMBNAIL_WIDTH,
				imageHeader.getPreferredSize().height));
		header.add(imageHeader);

		JLabel pathHeader = new JLabel("Path");
		pathHeader.setPreferredSize(new Dimension(
				32 + Config.GUI_SEARCH_ENTRY_PATH_WIDTH, pathHeader
						.getPreferredSize().height));
		header.add(pathHeader);

		header.add(GUI.genVerticalSeparator(imageHeader.getPreferredSize().height));

		JLabel dHeader = new JLabel("dHash");
		dHeader.setPreferredSize(new Dimension(
				Config.GUI_SEARCH_ENTRY_HASH_WIDTH + Config.GUI_THUMBNAIL_WIDTH
						- 10, dHeader.getPreferredSize().height));

		header.add(dHeader);
		header.add(GUI.genVerticalSeparator(imageHeader.getPreferredSize().height));

		JLabel pHeader = new JLabel("pHash");
		pHeader.setPreferredSize(new Dimension(
				Config.GUI_SEARCH_ENTRY_HASH_WIDTH + Config.GUI_THUMBNAIL_WIDTH
						- 10, pHeader.getPreferredSize().height));

		header.add(pHeader);

		header.add(GUI.genVerticalSeparator(imageHeader.getPreferredSize().height));

		header.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		return header;
	}

	public void ok() {
		for (Component c : centerPanel.getComponents()) {
			if (c instanceof SearchEntry) {
				((SearchEntry) c).setVisible(true);
			}
		}
	}

	public void addEntry(Image img) {
		centerPanel.add(new SearchEntry(this, baseImg, img));
		this.validate();
	}

	public void addEntry(String path) {
		try {
			Image img = new Image(path);
			SearchEntry se = new SearchEntry(this, baseImg, img);
			centerPanel.add(se);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.validate();
	}

	public void trashEntry(SearchEntry se) {
		centerPanel.remove(se);
		this.revalidate();
		this.repaint();
	}

	public void close() {
		thisParent.closeTab(this);
	}

}
