package pl.edu.agh.iet.tsp.Algorithm;

import pl.edu.agh.iet.tsp.Gui.ProgressBar;
import pl.edu.agh.iet.tsp.Model.Converter;
import pl.edu.agh.iet.tsp.Model.TSPData;

public class RunAlgorithm {

	public static TSPData runAlgoThread(OptionsForAlgorithm params,
			TSPData tspData, ProgressBar progressBar) {
		// System.out.println(tspData.getNodes().size());
		// System.out.println(params.tab.length);
		BCOAlgorithm bcoalgorithm = new BCOAlgorithm(params, tspData);
		bcoalgorithm.runBCO(progressBar);
		Result res = bcoalgorithm.getResult();

		// ArrayList<Integer> path = new
		// ArrayList<Integer>(tspData.getNodes().size());
		//
		// for(int i = 0; i < tspData.getNodes().size(); i++)
		// {
		// path.add(new Integer(i));
		// }
		// Collections.shuffle(path);
		// Results mock = new Results(10, path, 12);

		return Converter.convert(res, tspData);
	}

}
