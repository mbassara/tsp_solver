package pl.edu.agh.iet.tsp.Gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProgressBar extends JPanel {

	private static final long serialVersionUID = -1588798219506287543L;
	private final JProgressBar progressBar;

	public ProgressBar() {
		super(new BorderLayout());

		// Create the demo's UI.

		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);

		JPanel panel = new JPanel();
		panel.add(progressBar);

		add(panel, BorderLayout.PAGE_START);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	}

	public void setProgress(int current, int max) {
		progressBar.setValue((100 * current) / max);
		// System.out.println("progress: " + (100 * current / max));
	}

	public void setMax() {
		progressBar.setValue(0);
	}

	public void setMin() {
		progressBar.setValue(100);
	}

}