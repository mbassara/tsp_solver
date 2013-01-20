package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;

public class Results {

	public int iterations;
	public ArrayList<Integer> path = new ArrayList<Integer>();
	public float tour_length;
	
	public Results(int iterations, ArrayList<Integer> path, float tour_length) {
		this.iterations = iterations;
		this.path = path;
		this.tour_length = tour_length;
	}
	
}
