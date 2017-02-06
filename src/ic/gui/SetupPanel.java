package ic.gui;

import ic.image.Image;
import ic.metrics.name.DHash;
import ic.metrics.name.PHash;
import ic.util.Config;
import ic.util.IO;
import ic.util.Processing;
import ic.util.gui.PreviewPane;
import ic.util.log.Log;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SetupPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Component parentComponent;
	private SetupPanel thisSetupPanel;

	// source image components
	private Image baseImage;
	private JPanel imagePanel;
	private JLabel imageLabel;
	private JTextField imageText;
	private JButton imageButton;
	private JFileChooser imageChooser;

	private BufferedImage resizedImage;
	private ImagePanel imagePreview;

	// directory components
	private JPanel directoryPanel;
	private JLabel directoryLabel;
	private JTextField directoryText;
	private JButton directoryButton;
	private JFileChooser directoryChooser;

	// search button components
	private JPanel searchPanel;
	private JButton searchButton;

	public SetupPanel(Component parent) {
		super();
		this.parentComponent = parent;

		thisSetupPanel = this;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// setup image components
		imageLabel = new JLabel("Source Image:");
		imageText = new JTextField(Config.GUI_IMAGE_DEFAULT_DIR);
		imageText.setHorizontalAlignment(JTextField.LEFT);
		imageText.setMinimumSize(new Dimension(280, 25));
		imageText.setPreferredSize(new Dimension(280, 25));
		imageText.setMaximumSize(new Dimension(280, 25));
		imageText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectImage(imageText.getText());
				imageButton.requestFocus();
			}
		});

		imageButton = new JButton("...");
		imageButton.setEnabled(false);
		imageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == imageButton) {
					int returnVal = imageChooser.showOpenDialog(thisSetupPanel);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = imageChooser.getSelectedFile();
						selectImage(file);
					}
				}
			}
		});

		imagePanel = new JPanel();
		imagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		imagePanel.add(imageLabel);
		imagePanel.add(imageText);
		imagePanel.add(imageButton);
		this.add(imagePanel);

		// init preview
		JPanel imagePreviewPanel = new JPanel();
		imagePreviewPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		imagePreview = new ImagePanel(null);
		imagePreview.setBackground(Color.LIGHT_GRAY);
		imagePreview.setSize(Config.GUI_PREVIEW_IMAGE_SIZE);
		imagePreview.setPreferredSize(Config.GUI_PREVIEW_IMAGE_SIZE);
		imagePreview.setMinimumSize(Config.GUI_PREVIEW_IMAGE_SIZE);
		imagePreview.setMaximumSize(Config.GUI_PREVIEW_IMAGE_SIZE);
		imagePreviewPanel.add(imagePreview);
		this.add(imagePreviewPanel);

		// setup directory components
		directoryLabel = new JLabel("Directory:");
		directoryLabel.setMinimumSize(new Dimension(80, 25));
		directoryLabel.setPreferredSize(new Dimension(80, 25));
		directoryLabel.setMaximumSize(new Dimension(80, 25));
		directoryText = new JTextField(Config.GUI_DIRECTORY_DEFAULT_DIR);
		directoryText.setHorizontalAlignment(JTextField.LEFT);
		directoryText.setMinimumSize(new Dimension(280, 25));
		directoryText.setPreferredSize(new Dimension(280, 25));
		directoryText.setMaximumSize(new Dimension(280, 25));
		directoryText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectDirectory(directoryText.getText());
				directoryButton.requestFocus();
			}
		});

		directoryButton = new JButton("...");
		directoryButton.setEnabled(false);
		directoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == directoryButton) {
					int returnVal = directoryChooser
							.showOpenDialog(thisSetupPanel);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = directoryChooser.getSelectedFile();
						selectDirectory(file);
					}
				}
			}
		});

		directoryPanel = new JPanel();
		directoryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		directoryPanel.add(directoryLabel);
		directoryPanel.add(directoryText);
		directoryPanel.add(directoryButton);
		this.add(directoryPanel);

		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchButton = new JButton("Search");
		searchButton.setEnabled(false);
		searchButton.setPreferredSize(new Dimension(410, 55));
		searchButton.setMinimumSize(new Dimension(410, 55));
		searchButton.setMaximumSize(new Dimension(410, 55));
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((MainFrame) parentComponent).search(imageText.getText(),
						directoryText.getText());
				((MainFrame) parentComponent).setProgress(Math.random());

			}
		});
		searchPanel.add(searchButton);
		this.add(searchPanel);

		// add glue to fill up vertical space
		Component glue = Box.createVerticalGlue();
		((Box.Filler) glue).changeShape(glue.getMinimumSize(), new Dimension(0,
				Short.MAX_VALUE), // make
									// glue
									// greedy
				glue.getMaximumSize());
		this.add(glue);

	}

	public void initFileChoosers() {
		if (imageChooser == null) {
			imageChooser = new JFileChooser(new File(
					Config.GUI_IMAGE_DEFAULT_DIR));
			imageChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			imageButton.setEnabled(true);

			PreviewPane previewPane = new PreviewPane();
			imageChooser.setAccessory(previewPane);
			imageChooser.addPropertyChangeListener(previewPane);
		}
		if (directoryChooser == null) {
			directoryChooser = new JFileChooser(new File(
					Config.GUI_DIRECTORY_DEFAULT_DIR));
			directoryChooser
					.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			directoryButton.setEnabled(true);
		}
	}

	public void selectImage(String path) {
		File f = new File(path);
		if (f.exists() && f.isFile())
			selectImage(f);
	}

	public void selectImage(File image) {
		imageText.setText(image.getPath());

		try {
			if (IO.isFormatSupported(image)) {
				BufferedImage img = IO.readImage(image.getAbsolutePath());
				this.baseImage = new Image(img, image.getPath(),
						DHash.computeHash(img), PHash.computeHash(img));
				resizedImage = Processing.resize(img,
						Config.GUI_PREVIEW_IMAGE_SIZE.width,
						Config.GUI_PREVIEW_IMAGE_SIZE.height);
				imagePreview.setImage(resizedImage);

				searchButton.setEnabled(true);

				revalidate();
				repaint();
			} else {
				Log.error("file format not supported '" + image.getPath() + "'");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void selectDirectory(String dir) {
		directoryText.setText(dir);
		this.validate();
	}

	public void selectDirectory(File dir) {
		selectDirectory(dir.getPath());
	}

	public Image getBaseImage() {
		return baseImage;
	}
}
