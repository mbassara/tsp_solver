package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;

import pl.edu.agh.iet.tsp.Model.TSPData;



public class BCOAlgorithm {

	
	public OptionsForAlgorithm 	params;
	public TSPData 				tspdata;
	public BeeColony 			colony;
	
	public int 					iteration;
	
	public BCOAlgorithm(OptionsForAlgorithm params, TSPData tspdata) {
		this.tspdata  = tspdata;
		this.params = params;
		
		this.iteration = 0;
	}

	
	public void initializePopulation() {
		colony = new BeeColony(params.getN(), this);
	}
	
	
	
	public void runBCO() {

		initializePopulation();
		
		for(int i = 0; i < params.getN(); i++){
			colony.bees.get(i).bzzbzz();
		}
		
		
		/* iteration loop*/
		while(iteration < params.getBcmax()) {
			iteration++;
			System.out.println("Iteration "+ iteration);
			
			for(int i = 0; i < params.getN(); i++){
				colony.bees.get(i).observeDance();
				colony.bees.get(i).findPath();
				System.out.println("	Bee no. "+ colony.bees.get(i).id + " performs opt");	
				colony.bees.get(i).performDance();
			}
			
			System.out.println("All bees completed tasks");
			colony.updateDancers();
			colony.updateProfitability();
		}
		
		
	}
	
	
}

