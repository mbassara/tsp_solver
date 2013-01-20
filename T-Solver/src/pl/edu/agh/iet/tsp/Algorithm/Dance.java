package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;


public class Dance {
	
	public ArrayList<Integer> path;
	double duration;
	double tour_length;
	
	public Dance(ArrayList<Integer> path, double length, double dance_duration) {
		this.path = path;
		this.duration = dance_duration;
		this.tour_length = length;
	}

}
