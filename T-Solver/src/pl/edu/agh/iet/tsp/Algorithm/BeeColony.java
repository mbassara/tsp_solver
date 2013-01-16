package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;

public class BeeColony {

	private BCOAlgorithm algorithm;
	
	public ArrayList<Bee> Bees;
	
	public int count;
	public static float profitability;
	
	
	public BeeColony(int count, BCOAlgorithm algorithm) {
		this.count = count;
		this.algorithm = algorithm;
		
		Bees = new ArrayList<Bee>();
		for (int i = 0; i < count; i++) {
			Bees.add( new Bee(i, this, algorithm.Params, algorithm.Data) );
		}
	}
	
	
	public void UpdateValues()	{
		
		profitability = 0;
		for(int i = 0; i < count; i++){
			profitability += Bees.get(i).profitability;
		}
	}
	
	
}
