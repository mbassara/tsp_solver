package pl.edu.agh.iet.tsp.Gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProgressBar extends JPanel implements ActionListener,
		PropertyChangeListener {

	private final JProgressBar progressBar;
	private final JButton calculate;
	private Task task;

	public ProgressBar(JButton _calculate) {
		super(new BorderLayout());

		calculate = _calculate;

		// Create the demo's UI.

		calculate.setActionCommand("start");
		calculate.addActionListener(this);

		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);

		JPanel panel = new JPanel();
		panel.add(calculate);
		panel.add(progressBar);

		add(panel, BorderLayout.PAGE_START);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		calculate.setEnabled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		task = new Task(calculate, this);
		task.addPropertyChangeListener(this);
		task.execute();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress".equals(evt.getPropertyName())) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
		}
	}

}