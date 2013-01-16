package pl.edu.agh.iet.tsp.Algorithm;

import pl.edu.agh.iet.tsp.Model.TSPData;

public class Bee {

	private BeeColony colony;
	private TSPData   tspdata;
	private OptionsForAlgorithm algparams;
	
	public int id;
	public float profitability;
	public float tour_length;
	public float waggle_dance_duration;
	
	
	
	public Bee(int id, BeeColony colony, OptionsForAlgorithm algparams, TSPData tspdata) {
		
		this.id = id;
		this.colony = colony;
		this.tspdata = tspdata;
		this.algparams = algparams;
				
	}

	public void bzzbzz(){
		System.out.println("I'm a bee no. "+ this.id);
	}
	
	public void UpdateProfitability(){
		profitability = 1 / tour_length;
	}
	
	public void WaggleDance(){
		
	}

}