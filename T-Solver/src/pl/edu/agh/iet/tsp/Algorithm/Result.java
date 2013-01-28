package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;

public class Result {

	public int iterations;
	public int best_iteration;
	public ArrayList<Integer> path = new ArrayList<Integer>();
	public double tour_length;
	
	public Result(int iterations, int best_iteration, ArrayList<Integer> path, double length) {
		this.iterations = iterations;
		this.best_iteration = best_iteration;
		this.path = path;
		this.tour_length = length;
	}
	
	public String toString(){
		String result = "lista intow z result:\n";
		for(Integer i : path)
			result += i.toString() + " ";
		
		result += " IT: " + best_iteration;
		return result;
	}
	
}
