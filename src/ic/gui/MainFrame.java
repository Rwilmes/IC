package ic.gui;

import ic.image.Image;
import ic.util.Config;
import ic.util.GUI;
import ic.util.log.Log;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String title = "ImageComparison";

	public MainFrame thisMainFrame;

	private JPanel mainPanel;
	private JTabbedPane tabPane;
	private SetupPanel setupPanel;
	private StatusPanel statusPanel;

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

		// init status panel
		statusPanel = new StatusPanel();
		mainPanel.add(statusPanel, BorderLayout.SOUTH);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw();
	}

	public void draw() {
		if (setupPanel != null)
			setupPanel.draw();
	}

	public void search(Image img, String dir) {
		for (int i = 1; i < tabPane.getTabCount() - 1; i++) {
			tabPane.setIconAt(i, Config.ICON_DONE);
		}

		if (tabPane.getTabCount() > 1)
			tabPane.setIconAt(tabPane.getTabCount() - 1, Config.ICON_QUEUE);

		if (tabPane.getTabCount() < Config.GUI_TABS_MAX)
			tabPane.addTab("Search", Config.ICON_PROCESSING, new JLabel(dir));
		else
			Log.error("maximum number of tabs reached");

		this.validate();
	}

	public void setProgress(double value) {
		statusPanel.setProgress(value);
		statusPanel.statusText.setText(statusPanel.statusText.getText() + "a");
		this.repaint();
	}

}
