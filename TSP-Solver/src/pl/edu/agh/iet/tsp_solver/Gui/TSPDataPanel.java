package pl.edu.agh.iet.tsp_solver.Gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import pl.edu.agh.iet.tsp_solver.Model.Field;
import pl.edu.agh.iet.tsp_solver.Model.TSPData;
import pl.edu.agh.iet.tsp_solver.Model.TSPDataSerialization;

public class TSPDataPanel implements ActionListener {
	boolean initialized;
	int nameIndex, commentIndex, xMaxIndex;
	int dimensionIndex, edgeIndex, xMinIndex;
	int yMinIndex, yMaxIndex;
	JFrame frame;
	JPanel panel;
	JScrollPane scrollPane;
	JLabel msg;
	String name;
	String comment;
	float dimension;
	float xMax, xMin;
	float yMin, yMax;
	Point2D.Float[] points;
	JLabel[] paramLabels;
	JTextField[] paramFields;
	JTextField[] dataFields;

	TSPDataPanel(JFrame newFrame) {
		initialized = false;

		nameIndex = 0;
		commentIndex = 1;
		dimensionIndex = 2;
		xMinIndex = 3;
		xMaxIndex = 4;
		yMinIndex = 5;
		yMaxIndex = 6;
		paramLabels = new JLabel[7];
		paramLabels[nameIndex] = new JLabel("Name");
		paramLabels[commentIndex] = new JLabel("Comment");
		paramLabels[xMaxIndex] = new JLabel("X Max");
		paramLabels[dimensionIndex] = new JLabel("Dimension");
		paramLabels[xMinIndex] = new JLabel("X Min");
		paramLabels[yMinIndex] = new JLabel("Y Min");
		paramLabels[yMaxIndex] = new JLabel("Y Max");

		paramFields = new JTextField[7];
		paramFields[nameIndex] = new JTextField("Test Title");
		paramFields[commentIndex] = new JTextField("Comment");
		paramFields[xMaxIndex] = new JTextField("10");
		paramFields[dimensionIndex] = new JTextField("10");
		paramFields[xMinIndex] = new JTextField("0");
		paramFields[yMinIndex] = new JTextField("0");
		paramFields[yMaxIndex] = new JTextField("10");

		frame = newFrame;
		panel = new JPanel(new FlowLayout());
		frame.getContentPane().add(panel, "West");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame fileFrame = new JFrame();
		JPanel filePanel = new JPanel();
		JFileChooser fileChooser = new JFileChooser(".");
		fileFrame.getContentPane().add(filePanel);
		filePanel.add(fileChooser);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fileChooser.showOpenDialog(filePanel);
		if (result != JFileChooser.APPROVE_OPTION) {
			msg = new JLabel("No file selected");
			panel.add(msg);
			return;
		}

		File datafile = fileChooser.getSelectedFile();
		TSPData data = TSPDataSerialization.deserialize(datafile);
		initialized = readData(data);
		panel.update(panel.getGraphics());
		// frame.pack();
		frame.setVisible(true);
	}

	// The data file contains two sections.
	// The first section contains parameters used for configuring the graph.
	// Each line starts with a single word identifier followed by a space.
	// The rest of the line is the value.
	// It is terminated by a line containing the word "Data".
	// The second section contains the data pairs that will be graphed.
	boolean readData(TSPData data) {

		dataFields = new JTextField[2 * data.getDimension()];
		points = new Point2D.Float[data.getDimension()];

		ArrayList<Field> tmp = (ArrayList<Field>) data.getNodes();

		paramFields[nameIndex] = new JTextField(data.getName());
		paramFields[commentIndex] = new JTextField(data.getComment());
		paramFields[xMaxIndex] = new JTextField(
				new Double(data.getMaxX()).toString());
		paramFields[dimensionIndex] = new JTextField(new Integer(
				data.getDimension()).toString());
		paramFields[xMinIndex] = new JTextField(
				new Double(data.getMinX()).toString());
		paramFields[yMinIndex] = new JTextField(
				new Double(data.getMinY()).toString());
		paramFields[yMaxIndex] = new JTextField(
				new Double(data.getMaxY()).toString());

		for (int i = 0; i < data.getDimension(); i++) {
			Double ex = new Double(tmp.get(i).getX());
			Double ey = new Double(tmp.get(i).getY());
			dataFields[2 * i] = new JTextField(ex.toString());
			dataFields[2 * i + 1] = new JTextField(ey.toString());
		}

		if (scrollPane != null)
			frame.getContentPane().remove(scrollPane);
		panel = new JPanel(new GridLayout(8 + data.getDimension(), 2));
		for (int i = 0; i < 7; i++) {
			panel.add(paramLabels[i]);
			panel.add(paramFields[i]);
		}
		for (int i = 0; i < data.getDimension(); i++) {
			panel.add(dataFields[2 * i]);
			panel.add(dataFields[2 * i + 1]);
		}
		scrollPane = new JScrollPane(panel);
		frame.getContentPane().add(scrollPane, "West");

		return true;
	}

	// Read data from panel in case user made any changes
	void refreshData() {
		if (!initialized) {
			return;
		}
		name = paramFields[nameIndex].getText();
		comment = paramFields[commentIndex].getText();
		xMax = Float.parseFloat(paramFields[xMaxIndex].getText());
		dimension = Float.parseFloat(paramFields[dimensionIndex].getText());
		xMin = Float.parseFloat(paramFields[xMinIndex].getText());
		yMin = Float.parseFloat(paramFields[yMinIndex].getText());
		yMax = Float.parseFloat(paramFields[yMaxIndex].getText());
		for (int i = 0; i < points.length; i++) {
			Float x = Float.parseFloat(dataFields[2 * i].getText());
			Float y = Float.parseFloat(dataFields[2 * i + 1].getText());
			points[i] = new Point2D.Float(x, y);
		}
	}

	boolean isInitialized() {
		return initialized;
	}

	String getName() {
		return name;
	}

	String getComment() {
		return comment;
	}

	float getXMax() {
		return xMax;
	}

	float getDimension() {
		return dimension;
	}

	float getXMin() {
		return xMin;
	}

	float getYMin() {
		return yMin;
	}

	float getYMax() {
		return yMax;
	}

	int getNumberOfPoints() {
		return points.length;
	}

	Point2D.Float getPoint(int i) {
		if (i < 0) {
			i = 0;
		} else if (i >= points.length) {
			i = points.length - 1;
		}
		return points[i];
	}
}