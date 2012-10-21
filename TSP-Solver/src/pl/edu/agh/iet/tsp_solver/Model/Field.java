package pl.edu.agh.iet.tsp_solver.Model;

import java.awt.geom.Point2D;

public class Field extends Point2D.Double {

	private double x;
	private double y;

	public Field(double x, double y) {
		super(x, y);

	}
	@Override
	public String toString() {
		return "(" + x + " ; " + y + ")";
	}
}
