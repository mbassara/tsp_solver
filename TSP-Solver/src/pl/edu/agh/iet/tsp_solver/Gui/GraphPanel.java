package pl.edu.agh.iet.tsp_solver.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GraphPanel implements ActionListener {
	TSPDataPanel datapanel;
	JFrame frame;
	MapPanel panel;

	GraphPanel(JFrame newFrame) {
		frame = newFrame;
		panel = new MapPanel();
		panel.setDisplayPlot(false);
		datapanel = new TSPDataPanel(frame);
		panel.setDataPanel(datapanel);
		frame.getContentPane().add(panel, "Center");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!datapanel.isInitialized()) {
			return;
		}
		datapanel.refreshData();
		panel.setDisplayPlot(true);
		panel.update(panel.getGraphics());
		// frame.setSize(700, 600);
		frame.setVisible(true);
		// frame.pack();
	}

	ActionListener getTSPDataPanel() {
		return datapanel;
	}
}