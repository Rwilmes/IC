package ic.gui;

import ic.image.Image;
import ic.metrics.name.DHash;
import ic.metrics.name.PHash;
import ic.util.Config;
import ic.util.IO;
import ic.util.Processing;
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

		// init preview
		imagePreview = new ImagePanel(null);
		imagePreview.setBackground(Color.gray);
		imagePreview.setMinimumSize(new Dimension(300, 300));
		imagePreview.setMaximumSize(new Dimension(300, 300));

		this.add(imagePanel);
		this.add(imagePreview);

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
		searchButton = new JButton("Search");
		searchButton.setEnabled(false);
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
		Log.log("selecting image: " + image.getAbsolutePath());
		imageText.setText(image.getPath());

		try {
			BufferedImage img = IO.readImage(image.getAbsolutePath());
			this.baseImage = new Image(img, image.getPath(),
					DHash.computeHash(img), PHash.computeHash(img));
			resizedImage = Processing.resize(img, 300, 300);
			imagePreview = new ImagePanel(resizedImage);
			searchButton.setEnabled(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.validate();
	}

	public void selectDirectory(String dir) {
		Log.log("selecting directory: " + dir);
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
