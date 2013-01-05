package pl.edu.agh.iet.tsp.Model;

import java.awt.geom.Point2D;

public class Field extends Point2D.Double {
	
	private static final long serialVersionUID = -7345685506139788789L;
	
	public Field(double x, double y){
		super(x, y);
	}
	
	@Override
	public String toString() {
		return "(" + x + " ; " + y + ")";
	}
}
