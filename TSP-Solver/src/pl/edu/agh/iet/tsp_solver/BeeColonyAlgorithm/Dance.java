package pl.edu.agh.iet.tsp_solver.BeeColonyAlgorithm;

import java.lang.reflect.Array;


public class Dance{
	
	public Array tour;
	public float length;
	public int duration;
	
	public Dance(Array tour, float length, int duration) {
		this.tour = tour;
		this.length = length;
		this.duration = duration;
	}
}
