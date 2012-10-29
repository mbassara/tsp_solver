package pl.edu.agh.iet.tsp_solver.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.edu.agh.iet.tsp_solver.Model.TSPData;
import pl.edu.agh.iet.tsp_solver.Model.TSPDataSerialization;

public class TSPDataPanel extends JPanel {

	private static final long serialVersionUID = 6870364996122438389L;
	private boolean initialized;

	private enum INDEX {
		NAME, COMMENT, DIM, MIN_X, MAX_X, MIN_Y, MAX_Y, EDGE
	}

	private JLabel[] paramLabels;
	private JTextField[] paramFields;
	private JPanel northPanel; // North panel in order not to let MainWindow
								// stretch content of this panel vertically
	private TSPData dataObject = null;

	public TSPDataPanel() {
		initialized = false;
		paramLabels = new JLabel[7];
		paramLabels[INDEX.NAME.ordinal()] = new JLabel("Name");
		paramLabels[INDEX.COMMENT.ordinal()] = new JLabel("Comment");
		paramLabels[INDEX.DIM.ordinal()] = new JLabel("Dimension");
		paramLabels[INDEX.MAX_X.ordinal()] = new JLabel("X Max");
		paramLabels[INDEX.MIN_X.ordinal()] = new JLabel("X Min");
		paramLabels[INDEX.MIN_Y.ordinal()] = new JLabel("Y Min");
		paramLabels[INDEX.MAX_Y.ordinal()] = new JLabel("Y Max");

		paramFields = new JTextField[7];

		northPanel = new JPanel(new GridLayout(8, 2));
		this.add(northPanel, BorderLayout.NORTH);

		for (int i = 0; i < 7; i++) {
			paramFields[i] = new JTextField();
			paramFields[i].setEnabled(false);
			paramFields[i].setPreferredSize(new Dimension(100, 30));
			paramLabels[i].setPreferredSize(new Dimension(100, 30));
			northPanel.add(paramLabels[i]);
			northPanel.add(paramFields[i]);
		}
	}

	public TSPData fillWithRandomData() {
		TSPData data = TSPData
				.generateData("random data", "comment", 100, 1500);
		fillWithData(data);

		return data;
	}

	public void fillWithData(File file) {
		TSPData data = TSPDataSerialization.deserialize(file);
		fillWithData(data);
	}

	// The data file contains two sections.
	// The first section contains parameters used for configuring the graph.
	// Each line starts with a single word identifier followed by a space.
	// The rest of the line is the value.
	// It is terminated by a line containing the word "Data".
	// The second section contains the data pairs that will be graphed.
	public void fillWithData(TSPData data) {

		dataObject = data;

		paramFields[INDEX.NAME.ordinal()].setText(data.getName());
		paramFields[INDEX.COMMENT.ordinal()].setText(data.getComment());
		paramFields[INDEX.DIM.ordinal()].setText(new Integer(data
				.getDimension()).toString());
		paramFields[INDEX.MAX_X.ordinal()].setText(new Double(data.getMaxX())
				.toString());
		paramFields[INDEX.MIN_X.ordinal()].setText(new Double(data.getMinX())
				.toString());
		paramFields[INDEX.MIN_Y.ordinal()].setText(new Double(data.getMinY())
				.toString());
		paramFields[INDEX.MAX_Y.ordinal()].setText(new Double(data.getMaxY())
				.toString());

		initialized = true;

	}

	public boolean isInitialized() {
		return initialized;
	}

	public TSPData getData() {
		return dataObject;
	}

	public void reset() {
		paramFields[INDEX.NAME.ordinal()].setText("");
		paramFields[INDEX.COMMENT.ordinal()].setText("");
		paramFields[INDEX.DIM.ordinal()].setText("");
		paramFields[INDEX.MAX_X.ordinal()].setText("");
		paramFields[INDEX.MIN_X.ordinal()].setText("");
		paramFields[INDEX.MIN_Y.ordinal()].setText("");
		paramFields[INDEX.MAX_Y.ordinal()].setText("");

		dataObject = null;
	}

}