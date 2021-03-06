package ic.gui.search;

import ic.gui.MainFrame;
import ic.image.Image;
import ic.io.FilesystemCrawler;
import ic.metrics.ImageComparator;
import ic.metrics.hashes.ImageHash;
import ic.util.Config;
import ic.util.GUI;
import ic.util.IO;

import java.awt.BorderLayout;
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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

/**
 * Represents a JPanel which fills a search tab of the main frame.
 * 
 * @author Rwilmes
 * 
 */
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

	private JButton pauseButton;
	private JButton stopButton;

	private JLabel progressLabel;
	private JProgressBar progressBar;

	private FilesystemCrawler fileCrawler;
	
	private ImageComparator comparator;

	public SearchPanel(MainFrame parent, Image baseImg, String imgPath,
			String dir, ImageComparator comparator) {
		super();

		thisPanel = this;
		thisParent = parent;
		this.baseImg = baseImg;

		fileCrawler = new FilesystemCrawler(dir, Config.GUI_SEARCH_RECURSIVE);
		fileCrawler.register(this);

		this.comparator = comparator;
		
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
		pauseButton = new JButton("Continue");
		Dimension d = new Dimension(pauseButton.getPreferredSize().width,
				pauseButton.getPreferredSize().height);
		pauseButton.setText("Pause");
		pauseButton.setPreferredSize(d);
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (fileCrawler.isPaused()) {
					fileCrawler.unpause();
					pauseButton.setText("Pause");
					thisParent.setTabRunning(thisPanel);
				} else {
					fileCrawler.pause();
					pauseButton.setText("Continue");
					thisParent.setTabPaused(thisPanel);
				}
				validate();
			}
		});
		southPanel.add(pauseButton);

		stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pauseButton.setEnabled(false);
				fileCrawler.stop();
			}
		});
		southPanel.add(stopButton);

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fileCrawler.stop();
				thisPanel.close();
			}
		});
		southPanel.add(closeButton);

		dummy = new JPanel();
		dummy.setPreferredSize(new Dimension(20, 10));
		southPanel.add(dummy);
		southPanel.add(new JLabel("Image: "));

		JLabel imgLabel = new JLabel(IO.getFilename(imgPath));
		// System.out.println(imgLabel.getPreferredSize());
		imgLabel.setToolTipText(imgPath);
		imgLabel.setPreferredSize(new Dimension(178, imgLabel
				.getPreferredSize().height));
		southPanel.add(imgLabel);

		JPanel dummy2 = new JPanel();
		dummy2.setPreferredSize(new Dimension(20, 10));
		southPanel.add(dummy2);

		southPanel.add(new JLabel("Directory: "));
		JLabel dirLabel = new JLabel(dir);
		dirLabel.setPreferredSize(new Dimension(300, dirLabel
				.getPreferredSize().height));
		dirLabel.setToolTipText(dir);
		southPanel.add(dirLabel);

		progressLabel = new JLabel("Idle");
		progressLabel.setHorizontalAlignment(JLabel.RIGHT);
		progressLabel.setPreferredSize(new Dimension(100, progressLabel
				.getPreferredSize().height));
		southPanel.add(progressLabel);

		progressBar = new JProgressBar(0, 100);
		progressBar.setString("");
		progressBar.setStringPainted(true);

		southPanel.add(progressBar);

		this.add(southPanel, BorderLayout.SOUTH);
	}

	/** Creates the header panel. **/
	public JPanel createHeader() {
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel imageHeader = new JLabel("Image");
		imageHeader.setPreferredSize(new Dimension(
				Config.GUI_THUMBNAIL_SIZE.width,
				imageHeader.getPreferredSize().height));
		header.add(imageHeader);

		JLabel pathHeader = new JLabel("Path");
		pathHeader.setPreferredSize(new Dimension(
				Config.GUI_SEARCH_ENTRY_PATH_WIDTH, pathHeader
						.getPreferredSize().height));
		header.add(pathHeader);

		header.add(GUI.genVerticalSeparator(imageHeader.getPreferredSize().height));

		JLabel buttonHeader = new JLabel("Misc");
		buttonHeader.setPreferredSize(new Dimension(
				Config.GUI_SEARCH_ENTRY_BUTTON_PANEL_WIDTH, buttonHeader
						.getPreferredSize().height));
		header.add(buttonHeader);

		// create and add hash headers
		for (Class<? extends ImageHash> c : Config.getHashClassesToCompute()) {
			header.add(GUI.genVerticalSeparator(imageHeader.getPreferredSize().height));
			JLabel hashHeader = new JLabel(c.getSimpleName());
			hashHeader.setPreferredSize(new Dimension(
					Config.GUI_SEARCH_ENTRY_HASH_WIDTH, hashHeader
							.getPreferredSize().height));
			header.add(hashHeader);
		}

		// set border
		header.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		return header;
	}

	/** Adds an entry. **/
	public void addEntry(Image img) {
		// check if image is equal enough to be added
		if (comparator.areImagesEqual(baseImg, img)) {
			centerPanel.add(new SearchEntry(this, baseImg, img));
			this.validate();
		}
	}

	/** Adds an entry. **/
	public void addEntry(String path) {
		try {
			Image img = new Image(path);

			if (comparator.areImagesEqual(baseImg, img))
				centerPanel.add(new SearchEntry(this, baseImg, img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.validate();
	}

	/** Trashes an entry. **/
	public void trashEntry(SearchEntry se) {
		centerPanel.remove(se);
		this.revalidate();
		this.repaint();
	}

	/** Closes this tab. **/
	public void close() {
		thisParent.closeTab(this);
	}

	/** Updates the progress. **/
	public void updateProgress(String msg, double progress) {
		progressLabel.setText(msg);
		int v = (int) Math.floor(progress * 100);
		progressBar.setValue(v);
		progressBar.setString("" + v + " %");
		progressBar.setStringPainted(true);
	}

	/** Returns the filesystem crawler. **/
	public FilesystemCrawler getFilesystemCrawler() {
		return fileCrawler;
	}

	/** Sets that the search is done. **/
	public void setDone() {
		pauseButton.setEnabled(false);
		stopButton.setEnabled(false);
		thisParent.setTabDone(this);
	}
}
