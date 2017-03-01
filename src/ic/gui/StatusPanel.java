package ic.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class StatusPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 922616361758527614L;

	public JLabel statusText;
	private JProgressBar progressBar;

	public StatusPanel() {
		super();

		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(new JLabel("Status:"));
		statusText = new JLabel("Idle..");
		statusText.setMinimumSize(new Dimension(120, 16));
		statusText.setPreferredSize(new Dimension(120, 16));
		statusText.setMaximumSize(new Dimension(120, 16));
		statusText.setHorizontalAlignment(JLabel.RIGHT);
		this.add(statusText);

		// this.add(new JPanel());

		progressBar = new JProgressBar(0, 100);
		progressBar.setString("");
		progressBar.setStringPainted(true);
		this.add(progressBar);
	}

	public void setStatus(String text) {
		statusText.setText(text);
	}

	/** Value should be between 0 and 1, where 1.0 = 100%. **/
	public void setProgress(double value) {
		int v = (int) Math.floor(value * 100);
		progressBar.setValue(v);
		progressBar.setString("" + v + " %");
		progressBar.setStringPainted(true);
	}
}
