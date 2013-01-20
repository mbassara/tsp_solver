package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;
import java.util.Random;

import pl.edu.agh.iet.tsp.Model.TSPData;

public class Bee {

	private BeeColony 				colony;
	private TSPData   				tspdata;
	private OptionsForAlgorithm 	algparams;
	
	public int 						id;
	public double 					profitability;
	public double 					max_profitability;
	
	private ArrayList<Integer>  	path_found; 
	public double 					tour_length;
	public double 					dance_duration;
	
	private boolean					follow_dance;
	private ArrayList<Integer>  	prefered_path;
	
	public double					max_tour_length;
	public ArrayList<Integer>  		max_path_found;
	
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
			max_path_found = path_found;
			max_tour_length = tour_length;
		}	

	}
	
	public void observeDance(){
		
		//System.out.println("	Bee no. "+ id + " observes dance");
		
		double pfollow_index =  profitability / colony.profitability;
		double pfollow;
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
		//System.out.println("	Bee no. "+ id + " performs dance"); 
		
		if (this.profitability >= this.max_profitability){
			
			dance_duration = algparams.k * this.profitability / colony.profitability;
			Dance dance = new Dance(this.path_found, this.dance_duration);
			colony.dancers.add(dance);
			
		}
	}
	
	
	
	
	public void findPath(){
		this.iteration++;
		
		int current_city = 0;
		int next_city = 0;
		double tour_length = 0;
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		for(int i = 0; i <= algparams.n ;i++){
			path.add(0);
		}
		
		ArrayList<Integer> A_set = new ArrayList<Integer>();
		ArrayList<Integer> F_set = new ArrayList<Integer>();
		
		
		/* set A F set*/
		for(int n = 0; n < algparams.n; n++)
		{
			A_set.add(n+1);  		
		}
		
		if(this.follow_dance){
			F_set = prefered_path;
		}
		
		
		
		for(int n = 0; n < algparams.n; n++)
		{
			int pref_city;
			int allowed_and_prefered;
			
			ArrayList<Double> Phi_set = new ArrayList<Double>();
			ArrayList<Double> P_set = new ArrayList<Double>();
			
			if(follow_dance){
				pref_city = this.prefered_path.get(current_city);
			}else{
				pref_city = -1;
			}
			
			if(A_set.contains(pref_city)){
				allowed_and_prefered = pref_city;
			}else{
				allowed_and_prefered = 0;
			}
			
			
			/* calculate Pij_n*/
			for (int i = 0; i < A_set.size(); i++) {
				
				double ro_ijn;
				
				if(A_set.size() == 1){
					ro_ijn = 1.0;
				}else{
					
					if (allowed_and_prefered != 0){
						
						if(A_set.get(i) == pref_city){
							ro_ijn = algparams.gamma; 
						}else{
							ro_ijn = (1.0 - algparams.gamma) / A_set.size();
						}
						
					}else{
						ro_ijn = 1.0 / A_set.size();
					}				
				}
				Phi_set.add(ro_ijn);
				
			}
			
			//System.out.print(tspdata.graph.toString());
			//System.out.println(Phi_set.size());
			
			for(int j = 0; j < A_set.size(); j++){
				//Phi_set.get(j);
				 P_set.add( Math.pow(Phi_set.get(j), algparams.alpha) * Math.pow((1.0 / tspdata.graph.get(current_city).get(j)), algparams.beta));
					
			}
			double psetsum = 0.0;
			for(int i = 0; i < A_set.size(); i++){
				psetsum += P_set.get(i);		
			}
			for(int i = 0; i < A_set.size(); i++){
				 P_set.set(i, P_set.get(i) / psetsum );	
			}
				
				
			int next_city_index = choose_next_city(P_set);
			
			next_city = A_set.get(next_city_index);
			
			
			path.set(current_city, next_city);
			if (current_city * next_city != 0)
			{
				tour_length += tspdata.graph.get(current_city-1).get(next_city-1);
			}
			
			
			//System.out.println(path.toString() + " next: " + next_city + " curr: " + current_city + " length " + tour_length);
			
			current_city = next_city;
		}
		
		this.path_found = path;
		this.tour_length = tour_length;
		
	
		
		System.out.print("	Bee no. "+ id + " looking for path, iteration " + iteration); 
		System.out.println(" path found" + path.toString());
	}
	
	
	private int choose_next_city(ArrayList<Double> p_set){
		Random rand = new Random();
		double r = rand.nextDouble();
		ArrayList<Double> prob = new ArrayList<Double>();
		
		double sum = 0;
		for(int i = 0; i < p_set.size(); i++)
		{
			sum += p_set.get(i);
			prob.add(sum);
		}
		int i = 0;
		while(r < prob.size() * 1.0  &&  prob.get(i) < r){
			//System.out.println(prob.get(i));
			i++;
		}
		
		return i;
	}
	

}


