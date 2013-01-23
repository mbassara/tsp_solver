package pl.edu.agh.iet.tsp.Algorithm;

import pl.edu.agh.iet.tsp.Gui.ProgressBar;
import pl.edu.agh.iet.tsp.Model.TSPData;

public class BCOAlgorithm {

	public OptionsForAlgorithm params;
	public TSPData tspdata;
	public BeeColony colony;

	public int iteration;

	public BCOAlgorithm(OptionsForAlgorithm params, TSPData tspdata) {
		this.tspdata = tspdata;
		this.params = params;

		this.iteration = 0;

	}

	public void initializePopulation() {
		colony = new BeeColony(params.n, this);
	}

	public void runBCO(ProgressBar progressBar) {

		initializePopulation();

		iteration++;
		for (int i = 0; i < params.n; i++) {
			colony.bees.get(i).findPath();
			colony.bees.get(i).updateProfitability();
		}
		colony.updateDancers();
		colony.updateProfitability();

		while (iteration < params.getBcmax()) {
			iteration++;

			for (int i = 0; i < params.n; i++) {
				colony.bees.get(i).observeDance();
				colony.bees.get(i).findPath();
				colony.bees.get(i).updateProfitabilityAndPerformDance();
			}

			colony.updateDancers();
			colony.updateProfitability();

			if (progressBar != null) {
				progressBar.setProgress(iteration, params.getBcmax());
			}
		}
	}

	public Result getResult() {
		return new Result(iteration, colony.min_dance.path,
				colony.min_dance.tour_length);
	}

}
