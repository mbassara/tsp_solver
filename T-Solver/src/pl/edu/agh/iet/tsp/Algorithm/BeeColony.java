package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;

public class BeeColony {

	public BCOAlgorithm 		algorithm;
	
	public ArrayList<Bee> 		bees;
	public ArrayList<Dance> 	dancers;
	
	public int 					count;
	public static float 		profitability;
	
	
	public BeeColony(int count, BCOAlgorithm algorithm) {
		
		this.count = count;
		this.algorithm = algorithm;
		
		bees = new ArrayList<Bee>();
		for (int i = 0; i < count; i++) {
			bees.add( new Bee(i, this, algorithm.params, algorithm.tspdata) );
		}
		
		dancers = new ArrayList<Dance>();
	}
	
	
	public void updateDancers(){
		
		ArrayList<Dance> dancing = new ArrayList<Dance>();
		for (Dance dance : dancers) {
			if (dance.duration > 0 ){
				dance.duration -= 1;
				dancing.add(dance);
			}
		}
		dancers = dancing;
	}
	
	
	public void updateProfitability()	{
		
		profitability = 0;
		for(int i = 0; i < count; i++){
			profitability += bees.get(i).profitability;
		}
		profitability = profitability / count;
	}
	
}

