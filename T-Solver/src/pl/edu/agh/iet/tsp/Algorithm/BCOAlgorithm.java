package pl.edu.agh.iet.tsp.Algorithm;

import pl.edu.agh.iet.tsp.Model.TSPData;


public class BCOAlgorithm {

	private OptionsForAlgorithm Params;
	private TSPData Data;
	
	private BeeColony Colony;
	
	
	
	public BCOAlgorithm(OptionsForAlgorithm params, TSPData data) {
		
		this.Data  = data;
		this.Params = params;
		this.Colony = new BeeColony(params.getN());

	}

	public void InitializePopulation()
	{
		for (int i = 0; i < Params.getN(); i++) {
			Colony.Bees.add( new Bee(Colony, i) );
		}
		
	}
	
	
	public void RunBCO() {

		InitializePopulation();
		
		for(int i = 0; i < Params.getN(); i++){
			Colony.Bees.get(i).bzzbzz();
		}
		
		int iterations = 0;
		
		while( iterations < Params.getBcmax() ){
			iterations++;
			System.out.println("Iteration "+ iterations);
			
			
			
			for(int i = 0; i < Colony.count; i++){
				System.out.println("	Bee no. "+ Colony.Bees.get(i).id + " observes dance");
				System.out.println("	Bee no. "+ Colony.Bees.get(i).id + " finds path");		
				System.out.println("	Bee no. "+ Colony.Bees.get(i).id + " performs opt");	
				System.out.println("	Bee no. "+ Colony.Bees.get(i).id + " performs dance");
			}
			
			System.out.println("All bees completed tasks");
				
		}
		
		
		
		
		
	}
	
	
}

