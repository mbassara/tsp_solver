package pl.edu.agh.iet.tsp_solver.Model;

import java.awt.geom.Point2D;

public class Node extends Point2D.Double {
	
	private static final long serialVersionUID = -7345685506139788789L;
	
	public Node(double x, double y){
		super(x, y);
	}
	
	@Override
	public String toString() {
		return "(" + x + " ; " + y + ")";
	}
}
