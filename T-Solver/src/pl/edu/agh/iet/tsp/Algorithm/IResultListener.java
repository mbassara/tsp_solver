package pl.edu.agh.iet.tsp.Algorithm;

import pl.edu.agh.iet.tsp.Model.TSPData;

public interface IResultListener {

	public void receiveResult(TSPData result, int best_iteration,
			double distance);

}
