package pl.edu.agh.iet.tsp_solver.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GraphPanel implements ActionListener {
	DataPanel datapanel;
	JFrame frame;
	GraphicPanel panel;

	GraphPanel(JFrame newFrame) {
		frame = newFrame;
		panel = new GraphicPanel();
		panel.setDisplayPlot(false);
		datapanel = new DataPanel(frame);
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
		frame.setSize(700, 600);
		frame.setVisible(true);
		frame.pack();
	}

	ActionListener getDataPanel() {
		return datapanel;
	}
}