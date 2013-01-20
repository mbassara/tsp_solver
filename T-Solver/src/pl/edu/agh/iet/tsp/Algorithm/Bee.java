package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;
import java.util.Random;

import pl.edu.agh.iet.tsp.Model.TSPData;

public class Bee {

	private BeeColony 				colony;
	private TSPData   				tspdata;
	private OptionsForAlgorithm 	algparams;
	
	public int 						id;
	public float 					profitability;
	public float 					max_profitability;
	
	private ArrayList<Integer>  	path_found; 
	public float 					tour_length;
	public float 					dance_duration;
	
	private boolean					follow_dance;
	private ArrayList<Integer>  	prefered_path;
	
	public float					max_tour_length;
	public ArrayList<Integer>  		max_tour_found;
	
	public int 						iteration;
	
	public Bee(int id, BeeColony colony, OptionsForAlgorithm algparams, TSPData tspdata) {
		
		this.id = id;
		this.colony = colony;
		this.tspdata = tspdata;
		this.algparams = algparams;
		
		this.follow_dance = false;
		
		this.iteration = 0;
	}

	public void bzzbzz(){
		System.out.println("I'm a bee no. "+ this.id);
	}
	
	public void updateProfitability(){
		
		profitability = 1 / tour_length;
		if (profitability > max_profitability){
			max_profitability = profitability;
		}	

	}
	
	public void observeDance(){
		
		System.out.println("	Bee no. "+ id + " observes dance");
		
		float pfollow_index =  profitability / colony.profitability;
		float pfollow;
		Dance dance;
		
		if(algparams.tab[0][0] < pfollow_index && pfollow_index < algparams.tab[0][1]){
			pfollow = algparams.tab[0][1];
		} else if(algparams.tab[1][0] < pfollow_index && pfollow_index < algparams.tab[1][1]){
			pfollow = algparams.tab[1][1];
		} else if(algparams.tab[2][0] < pfollow_index && pfollow_index < algparams.tab[2][1]){
			pfollow = algparams.tab[2][1];
		} else if(algparams.tab[3][0] < pfollow_index && pfollow_index < algparams.tab[3][1]){
			pfollow = algparams.tab[3][1];
		} else{
			pfollow = 0;
		}

				
		Random rand = new Random();
		
		if (rand.nextFloat() < pfollow){
			this.follow_dance = true;
		}
		else{
			this.follow_dance = false;
		}
		
		if(this.follow_dance){
			dance = colony.dancers.get( rand.nextInt(colony.dancers.size()) );
			this.prefered_path = dance.path;
		}

	}
	

	
	public void performDance(){
		System.out.println("	Bee no. "+ id + " performs dance"); 
		
		if (this.profitability >= this.max_profitability){
			
			dance_duration = algparams.k * this.profitability / colony.profitability;
			Dance dance = new Dance(this.path_found, this.dance_duration);
			colony.dancers.add(dance);
			
		}
	}
	
	
	
	
	public void findPath(){
		this.iteration++;
		
		int current_city = 0;
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		ArrayList<Integer> A_set = new ArrayList<Integer>();
		ArrayList<Integer> F_set = new ArrayList<Integer>();
		
		for(int n = 0; n < algparams.n; n++)
		{
			A_set.add(n+1);  		
		}
		
		for(int n = 0; n < algparams.n; n++)
		{
						
		}
		
		
	
		
		System.out.println("	Bee no. "+ id + " looking for path, iteration " + iteration); 
		
	}
	
	
	
	
	

}


