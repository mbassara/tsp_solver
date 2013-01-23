package pl.edu.agh.iet.tsp.Algorithm;

import java.util.ArrayList;
import java.util.Collections;

import pl.edu.agh.iet.tsp.Gui.Data;
import pl.edu.agh.iet.tsp.Model.Converter;
import pl.edu.agh.iet.tsp.Model.TSPData;

public class RunAlgorithm {

	public static TSPData runAlgoThread(OptionsForAlgorithm params, TSPData tspData){
		BCOAlgorithm bcoalgorithm = new BCOAlgorithm(params, tspData);
		bcoalgorithm.runBCO();
		
		
//		ArrayList<Integer> path = new ArrayList<Integer>(tspData.getNodes().size());
//		
//		for(int i = 0; i < tspData.getNodes().size(); i++)
//		{
//			path.add(new Integer(i));
//		}
//		Collections.shuffle(path);
//		Results mock = new Results(10, path, 12);
		
		Result res = bcoalgorithm.getResult();
		return Converter.convert(res, tspData);
	}


}
