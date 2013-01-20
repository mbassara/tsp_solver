package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;

public class Result {

	public int iterations;
	public ArrayList<Integer> path = new ArrayList<Integer>();
	public double tour_length;
	
	public Result(int iterations, ArrayList<Integer> path, double length) {
		this.iterations = iterations;
		this.path = path;
		this.tour_length = length;
	}
	
}
