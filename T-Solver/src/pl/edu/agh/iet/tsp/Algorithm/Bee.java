package pl.edu.agh.iet.tsp.Algorithm;

public class Bee {

	public int id;

	public float bee_profitability;
	public float tour_length;
	public float waggle_dance_duration;
	
	private BeeColony bcvalues;
	
	public Bee(int id, BeeColony bcvalues) {
		this.bcvalues = bcvalues;
		this.id = id;
	}

	public void bzzbzz(){
		System.out.println("I'm a bee no. "+ this.id);
	}
	
	public void UpdateTourProfitability(){
		bee_profitability = 1 / tour_length;
	}
	
	public void WaggleDance(){
		
	}

}