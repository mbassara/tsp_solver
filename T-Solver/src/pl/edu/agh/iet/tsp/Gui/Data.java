package pl.edu.agh.iet.tsp.Gui;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;

import pl.edu.agh.iet.tsp.Model.Field;
import pl.edu.agh.iet.tsp.Model.TSPData;

public class Data {
	JFrame mainFrame;

	float dimension;
	float xMax, xMin;
	float yMin, yMax;
	Point2D.Float[] points;
	TSPData tspdata;

	Data(JFrame mainframe) {

		mainFrame = mainframe;
	}

	boolean readData(TSPData data) {

		tspdata = data;

		points = new Point2D.Float[data.getDimension()];

		ArrayList<Field> tmp = (ArrayList<Field>) data.getNodes();

		xMax = (float) data.getMaxX();
		dimension = data.getDimension();
		xMin = (float) data.getMinX();
		yMin = (float) data.getMinY();
		yMax = (float) data.getMaxY();

		for (int i = 0; i < data.getDimension(); i++) {
			Float ex = new Float(tmp.get(i).getX());
			Float ey = new Float(tmp.get(i).getY());
			points[i] = new Point2D.Float(ex, ey);
		}

		return true;
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

	public TSPData getTspdata() {
		return tspdata;
	}
}