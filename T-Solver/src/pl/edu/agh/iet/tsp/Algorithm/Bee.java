package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;

import pl.edu.agh.iet.tsp.Model.TSPData;

public class Bee {

	private BeeColony 				colony;
	private TSPData   				tspdata;
	private OptionsForAlgorithm 	algparams;
	
	public int 						id;
	public float 					profitability;
	public float 					tour_length;
	public int 						dance_duration;
	
	
	
	public Bee(int id, BeeColony colony, OptionsForAlgorithm algparams, TSPData tspdata) {
		
		this.id = id;
		this.colony = colony;
		this.tspdata = tspdata;
		this.algparams = algparams;
				
	}

	public void bzzbzz(){
		System.out.println("I'm a bee no. "+ this.id);
	}
	
	public void updateProfitability(){
		profitability = 1 / tour_length;
	}
	
	public void performDance(){
		dance_duration = 1;
		ArrayList<Integer> path = new ArrayList<Integer>();
		Dance dance = new Dance(path, dance_duration);
		
		colony.dancers.add(dance);
	}

}


