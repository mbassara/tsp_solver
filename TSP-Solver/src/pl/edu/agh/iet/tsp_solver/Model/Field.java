package pl.edu.agh.iet.tsp_solver.Model;

public class Field {
	
	private double x;
	private double y;
	
	public Field(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	@Override
	public String toString() {
		return "(" + x + " ; " + y + ")";
	}
}
