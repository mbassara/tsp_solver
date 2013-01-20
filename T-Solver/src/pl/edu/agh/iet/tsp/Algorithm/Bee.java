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
	private int withoutdance;
	
	public Bee(int id, BeeColony colony, OptionsForAlgorithm algparams, TSPData tspdata) {
		
		this.id = id;
		this.colony = colony;
		this.tspdata = tspdata;
		this.algparams = algparams;
		
		this.follow_dance = false;
		this.max_tour_length = 0.0;
		this.iteration = 0;
		this.withoutdance = 0;
	}

	public void bzzbzz(){
		System.out.println("I'm a bee no. "+ this.id);
	}
	
	public void updateProfitability(){
		
		profitability = 1 / tour_length;
		//System.out.println("curr prof:"+ profitability + " max_prof" + max_profitability);
		if (profitability >= max_profitability){
			max_profitability = profitability;
			max_path_found = path_found;
			max_tour_length = tour_length;
		}else
		{
			this.withoutdance++;
			if(withoutdance > 10)
			{
				max_profitability = 0.9 * max_profitability;
			}
		}

	}
	
	public void observeDance(){
		
		//System.out.print("	Bee no. "+ id + " observes dance ");
		
		double pfollow_index =  profitability / colony.profitability;
		double pfollow;
		Dance dance;
		//System.out.print("profitability: " + profitability + " colony.profitability: " + colony.profitability + " pfollow_index: " + pfollow_index );
		
		if(algparams.tab[0][0] < pfollow_index && pfollow_index <= algparams.tab[0][1]){
			pfollow = algparams.tab[0][2];
		} else if(algparams.tab[1][0] < pfollow_index && pfollow_index <= algparams.tab[1][1]){
			pfollow = algparams.tab[1][2];
		} else if(algparams.tab[2][0] < pfollow_index && pfollow_index <= algparams.tab[2][1]){
			pfollow = algparams.tab[2][2];
		} else if(algparams.tab[3][0] < pfollow_index && pfollow_index <= algparams.tab[3][1]){
			pfollow = algparams.tab[3][2];
		} else{
			pfollow = 0.0;
		}

		//System.out.println(" pfollow: " + pfollow);		
		
		Random rand = new Random();
		
		if (rand.nextFloat() < pfollow){
			this.follow_dance = true;
		}
		else{
			this.follow_dance = false;
		}
		
		if(this.follow_dance && colony.dancers.size() > 0){
			dance = colony.dancers.get( rand.nextInt(colony.dancers.size()) );
			this.prefered_path = dance.path;
		}
		else{
			this.follow_dance = false;
		}

	}
	

	
	public void performDance(){
		
		
		if (this.profitability >= this.max_profitability){
			//System.out.print("perform dance ");
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
		for(int n = 0; n <= algparams.n; n++)
		{
			A_set.add(n);  		
		}
			
		//System.out.println("PATH SEARCHING\n");
		
		//System.out.println("prefered_path: " + this.prefered_path.toString());
		
		for(int n = 0; n < algparams.n; n++)
		{
			//System.out.println("current city: " + current_city);
			A_set.remove(new Integer(current_city));
			//System.out.println("allowed cities: " + A_set.toString());
			
			
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
			//System.out.println("Phi_set :" + Phi_set.toString());
			
			for(int j = 0; j < A_set.size(); j++){
				//Phi_set.get(j);
				double dist = 1.0;
				
				if (current_city == 0){
					dist = 1.0;
				}else{
					dist = tspdata.graph.get(current_city-1).get(A_set.get(j)-1);
				}
				//System.out.println("dist current_city to A_set.get(" +j+ ") " + current_city +" " +A_set.get(j) + " " + dist);
				P_set.add( Math.pow(Phi_set.get(j), algparams.alpha) * Math.pow(1.0 / dist , algparams.beta));
					
			}
			double psetsum = 0.0;
			for(int i = 0; i < A_set.size(); i++){
				psetsum += P_set.get(i);		
			}
			for(int i = 0; i < A_set.size(); i++){
				 P_set.set(i, P_set.get(i) / psetsum );	
			}
			//System.out.println("P_set: " + P_set.toString());	
				
			int next_city_index = choose_next_city(P_set);
			
			next_city = A_set.get(next_city_index);
			//System.out.println("next city:" + next_city);
			
			A_set.remove(next_city_index);
			
			path.set(current_city, next_city);
			if (current_city * next_city != 0)
			{
				tour_length += tspdata.graph.get(current_city-1).get(next_city-1);
			}
			
			
			//System.out.println(path.toString() + " next: " + next_city + " curr: " + current_city + " length " + tour_length);
			
			current_city = next_city;
			//System.out.println(" \n ");
			
		}
		
		this.path_found = path;
		this.tour_length = tour_length;
		
	
		
		//System.out.print("	Bee no. "+ id + " looking for path, iteration " + iteration); 
		//System.out.print(" " + path.toString());
		
		int index = 0;
		//System.out.print("	path found: " + index);
		for(int i = 0; i < path_found.size(); i++){
			index = path_found.get(index);
			//System.out.print(" " + index);
		}
		//System.out.print(" length: " + tour_length);
		//System.out.print(" \n" + index);
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


