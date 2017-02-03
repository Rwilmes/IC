package ic.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ic.util.GUI;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String title = "ImageComparison";

	public MainFrame() {
		super();

		init(MainFrame.title);

		JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);

		JPanel p1 = new JPanel();
		p1.setToolTipText("p1");

		JLabel l1 = new JLabel("Label 1");
		p1.add(l1);

		JPanel p2 = new JPanel();
		p2.setToolTipText("p2");
		JLabel l2 = new JLabel("Label 2");
		p2.add(l2);

		pane.insertTab("Tab1", null, p1, "p1 tab tooltip", 0);
		pane.insertTab("Tab1", null, p2, "p2 tab tooltip", 1);

		// this.setLayout(new BoxLayout(arg0, arg1));

		System.out.println("ADDED PANE");

		// this.add(new JLabel("Label 3"));

		JPanel mainPanel = new JPanel();
		this.add(mainPanel);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(pane, BorderLayout.NORTH);

		this.validate();
	}

	private void init(String title) {
		// use WebLAF
		GUI.installWebLAF();
		GUI.enableWebLAF();

		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(500, 300));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
