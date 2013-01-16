package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;

import pl.edu.agh.iet.tsp.Model.TSPData;



public class BCOAlgorithm {

	public OptionsForAlgorithm Params;
	public TSPData Data;
	public BeeColony Colony;
	
	
	public BCOAlgorithm(OptionsForAlgorithm params, TSPData data) {
		this.Data  = data;
		this.Params = params;
	}

	
	public void InitializePopulation()
	{
		Colony = new BeeColony(Params.getN(), this);
	}
	
	
	
	public void RunBCO() {

		InitializePopulation();
		
		for(int i = 0; i < Params.getN(); i++){
			Colony.Bees.get(i).bzzbzz();
		}
		
		int iterations = 0;
		
		/* iteration loop*/
		while( iterations < Params.getBcmax() ){
			iterations++;
			System.out.println("Iteration "+ iterations);
			
			
			
			for(int i = 0; i < Params.getN(); i++){
				System.out.println("	Bee no. "+ Colony.Bees.get(i).id + " observes dance");
				System.out.println("	Bee no. "+ Colony.Bees.get(i).id + " finds path");		
				System.out.println("	Bee no. "+ Colony.Bees.get(i).id + " performs opt");	
				System.out.println("	Bee no. "+ Colony.Bees.get(i).id + " performs dance");
			}
			
			System.out.println("All bees completed tasks");
				
		}
		
		
	}
	
	
}

