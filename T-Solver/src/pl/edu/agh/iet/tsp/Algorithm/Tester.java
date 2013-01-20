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
		tspdata.nodesToGraph();
		
		params = new OptionsForAlgorithm();
		params.setN(10);
		params.setBcmax(200);
		params.setAlpha(1.0F);
		params.setBeta(10.0F);
		params.setGamma(0.8F);
		params.setK(100);

		float t[][] = new float[5][3];
		t[0][0] = 0; t[0][1] = 0.95F; t[0][2] = 0.8F;
		t[1][0] = 0.95F; t[1][1] = 0.975F; t[1][2] = 0.2F;
		t[2][0] = 0.975F; t[2][1] = 0.99F; t[2][2] = 0.02F;
		t[3][0] = 0.99F; t[3][1] = 100.0F; t[3][2] = 0.0F;
		params.setTab(t);


		bcoalgorithm = new BCOAlgorithm(params, tspdata);
		bcoalgorithm.runBCO();
		Result res = bcoalgorithm.getResult();
		
		int index = 0;
		System.out.print("BEST TOUR FOUND: " + index);
		for(int i = 0; i < res.path.size(); i++){
			index = res.path.get(index);
			System.out.print(" " + index);
		}
		System.out.print(" length: " + res.tour_length + "\n");
		//System.out.println("results" +  res.path.toString() + " " + res.tour_length);
	}

}