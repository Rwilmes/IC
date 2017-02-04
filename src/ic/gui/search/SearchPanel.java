package ic.gui.search;

import ic.image.Image;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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

	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel southPanel;

	private JPanel dummy;

	public SearchPanel(String imgPath, String dir) {
		super();

		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		northPanel.add(new JLabel("Image"));
		northPanel.add(new JLabel("PHash"));
		northPanel.add(new JLabel("DHash"));
		northPanel.add(new JLabel("Path"));
		northPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));

		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);

		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		// centerPanel.setLayout(new BoxLayout);
		// this.add(centerPanel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane(centerPanel);
		// scrollPane.setPreferredSize();
		this.add(scrollPane, BorderLayout.CENTER);

		

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
		
		
		
		centerPanel.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("shown\t" + arg0.getComponent());
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("resized\t" + arg0.getComponent());
				ok();
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("moved\t" + arg0.getComponent());
				ok();
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("hidden\t" + arg0.getComponent());
			}
		});
	}

	public void ok() {
//		for (Component c : centerPanel.getComponents()) {
//			System.out.println(c);
//
//			if (c instanceof SearchEntry) {
//				((SearchEntry) c).drawImage();
//			}
//		}
	}

	public void addEntry(String path) {
		try {
			Image img = new Image(path);
			SearchEntry se = new SearchEntry(img);
			centerPanel.add(se);
//			centerPanel.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.validate();
	}
}
