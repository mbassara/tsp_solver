package pl.edu.agh.iet.tsp.Algorithm;

import pl.edu.agh.iet.tsp.Gui.ProgressBar;
import pl.edu.agh.iet.tsp.Model.Converter;
import pl.edu.agh.iet.tsp.Model.TSPData;

public class RunAlgorithm {

	public static void runAlgoThread(OptionsForAlgorithm params,
			TSPData tspData, ProgressBar progressBar,
			IResultListener resultListener) {

		final OptionsForAlgorithm options = params;
		final TSPData data = tspData;
		final ProgressBar bar = progressBar;
		final IResultListener listener = resultListener;

		new Thread(new Runnable() {

			@Override
			public void run() {
				BCOAlgorithm bcoalgorithm = new BCOAlgorithm(options, data);
				bcoalgorithm.runBCO(bar);
				Result res = bcoalgorithm.getResult();

				listener.receiveResult(Converter.convert(res, data),
						res.tour_length);

			}
		}).start();
	}
}
