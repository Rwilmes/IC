package ic.gui;

import ic.gui.search.SearchPanel;
import ic.io.FilesystemCrawler;
import ic.util.Config;
import ic.util.GUI;
import ic.util.log.Log;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * The MainFrame is the outer JFrame in which the GUI is embedded.
 * 
 * @author Rwilmes
 * 
 */
public class MainFrame extends JFrame {

	/** Main method used to open a MainFrame. **/
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// init MainFrame
		MainFrame frame = new MainFrame();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String title = "ImageComparison";

	public MainFrame thisMainFrame;

	private JPanel mainPanel;
	private JTabbedPane tabPane;
	private SetupPanel setupPanel;

	// constructors
	public MainFrame() {
		this(new Dimension(Config.GUI_MIN_WIDTH, Config.GUI_MIN_HEIGHT),
				new Dimension(Config.GUI_MIN_WIDTH, Config.GUI_MIN_HEIGHT));
	}

	public MainFrame(Dimension size, Dimension minSize) {
		super();

		init(MainFrame.title, size, minSize);

		this.validate();
		this.setupPanel.initFileChoosers();
	}

	/** Used for initialization. **/
	private void init(String title, Dimension size, Dimension minSize) {
		// use WebLAF
		GUI.installWebLAF();

		// some settings
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(size);
		this.setMinimumSize(minSize);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		// init main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.add(mainPanel);

		// init setup panel
		setupPanel = new SetupPanel(this);

		// init tabbed pane
		tabPane = new JTabbedPane(JTabbedPane.TOP);
		tabPane.insertTab("Setup", Config.ICON_SETTINGS, this.setupPanel, null,
				0);

		mainPanel.add(tabPane, BorderLayout.CENTER);
	}

	/** Starts a search for the image at the given path in the given dir. **/
	public void search(String path, String dir) {
		if (tabPane.getTabCount() < Config.GUI_TABS_MAX) {
			SearchPanel searchPanel = new SearchPanel(this,
					setupPanel.getBaseImage(), path, dir);
			FilesystemCrawler fc = searchPanel.getFilesystemCrawler();
			fc.register(this);

			tabPane.insertTab("Search", Config.ICON_PROCESSING, searchPanel,
					null, tabPane.getTabCount());

			tabPane.setSelectedIndex(tabPane.getTabCount() - 1);
			this.validate();

			setTabRunning(searchPanel);
			fc.start();
		} else
			Log.error("maximum number of tabs reached");
	}

	/** Can be used to set the progress. **/
	public void setProgress(double value) {
		// NOTHING TO DO
	}

	/** Can be used to update the progress. **/
	public void updateProgress(String msg, double progress) {
		// NOTHING TO DO
	}

	/** Closes the given tab. **/
	public void closeTab(SearchPanel panel) {
		tabPane.remove(panel);
	}

	/** Sets that the search in a tab is done. **/
	public void setTabDone(SearchPanel panel) {
		for (int i = 0; i < tabPane.getTabCount(); i++) {
			if (tabPane.getComponent(i) == panel) {
				tabPane.setIconAt(i, Config.ICON_DONE);
			}
		}
	}

	/** Sets that a tab is paused. **/
	public void setTabPaused(SearchPanel panel) {
		for (int i = 0; i < tabPane.getTabCount(); i++) {
			if (tabPane.getComponent(i) == panel) {
				tabPane.setIconAt(i, Config.ICON_QUEUE);
			}
		}
	}

	/** Sets that a tab is running. **/
	public void setTabRunning(SearchPanel panel) {
		for (int i = 0; i < tabPane.getTabCount(); i++) {
			if (tabPane.getComponent(i) == panel) {
				tabPane.setIconAt(i, Config.ICON_PROCESSING);
			}
		}
	}
}
