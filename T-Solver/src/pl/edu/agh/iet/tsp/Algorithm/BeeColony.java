package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;

public class BeeColony {

	public BCOAlgorithm algorithm;

	public ArrayList<Bee> bees;
	public ArrayList<Dance> dancers;

	public int count;
	public float profitability;

	public Dance min_dance;
	public int min_dance_iteration;
	
	public BeeColony(int count, BCOAlgorithm algorithm) {

		this.count = count;
		this.algorithm = algorithm;

		bees = new ArrayList<Bee>();
		for (int i = 0; i < count; i++) {
			bees.add(new Bee(i, this, algorithm.params, algorithm.tspdata));
		}

		dancers = new ArrayList<Dance>();
		min_dance = new Dance(new ArrayList<Integer>(), Double.MAX_VALUE, 0.0);
	}

	public void updateDancers() {

		ArrayList<Dance> dancing = new ArrayList<Dance>();
		for (Dance dance : dancers) {
			if (dance.duration > 0) {
				dance.duration -= 1;
				dancing.add(dance);
			}
			if (dance.tour_length < min_dance.tour_length) {
				min_dance = dance;
				min_dance_iteration = algorithm.iteration;
			}
		}
		dancers = dancing;
	}

	public void updateProfitability() {

		profitability = 0;
		for (int i = 0; i < count; i++) {
			profitability += bees.get(i).profitability;
		}
		profitability = profitability / count;
	}

}
