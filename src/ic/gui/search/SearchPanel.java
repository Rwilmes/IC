package ic.gui.search;

import ic.image.Image;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class SearchPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel southPanel;

	private JPanel dummy;

	public SearchPanel(String imgPath, String dir) {
		super();

		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		northPanel.add(new JLabel("Image: "));
		northPanel.add(new JLabel(imgPath));
		northPanel.add(new JLabel("       "));
		northPanel.add(new JLabel("Directory: "));
		northPanel.add(new JLabel(dir));
		northPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));

		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);

		centerPanel = new JPanel();
		centerPanel.add(new JLabel("TEST"));

		this.add(centerPanel, BorderLayout.CENTER);

		southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(dummy.getSize());
				addEntry("data/Alyson_Hannigan_200512.jpg");
			}
		});
		southPanel.add(addButton);

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

	public void addEntry(String path) {
		Image img;
		try {
			img = new Image(path);
			centerPanel.add(new SearchEntry(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
