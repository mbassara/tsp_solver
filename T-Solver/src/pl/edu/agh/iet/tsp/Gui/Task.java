package pl.edu.agh.iet.tsp.Gui;

import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.SwingWorker;

class Task extends SwingWorker<Void, Void> {

	ProgressBar progress;
	JButton calculate;
	boolean done;

	/*
	 * Main task. Executed in background thread.
	 */
	public Task(JButton _calculate, ProgressBar _progress) {
		calculate = _calculate;
		progress = _progress;
	}

	@Override
	public Void doInBackground() {
		Random random = new Random();
		int progress = 0;
		// Initialize progress property.
		setProgress(0);
		while (progress < 100) {
			// Sleep for up to one second.
			try {
				Thread.sleep(random.nextInt(100));
			} catch (InterruptedException ignore) {
			}
			// Make random progress.
			progress += random.nextInt(10);
			setProgress(Math.min(progress, 100));
		}
		return null;
	}

	/*
	 * Executed in event dispatching thread
	 */
	@Override
	public void done() {
		Toolkit.getDefaultToolkit().beep();
		calculate.setEnabled(true);
		progress.setCursor(null); // turn off the wait cursor
	}
}