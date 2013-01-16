package pl.edu.agh.iet.tsp.Algorithm;

import pl.edu.agh.iet.tsp.Model.TSPData;

public class Tester {

	static BCOAlgorithm 		bcoalgorithm;
	static OptionsForAlgorithm 	params;
	static TSPData				tspdata;

	public Tester() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*sample options*/

		tspdata = TSPData.generateData("test", null, 10, 100);

		params = new OptionsForAlgorithm();
		params.setN(10);
		params.setBcmax(13);
		params.setAlpha((float) 0.3);
		params.setBeta((float) 0.9);
		params.setGamma((float) 1.0);




		bcoalgorithm = new BCOAlgorithm(params, tspdata);
		bcoalgorithm.runBCO();
	}

}