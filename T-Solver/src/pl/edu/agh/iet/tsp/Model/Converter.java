package pl.edu.agh.iet.tsp.Model;

import java.util.ArrayList;

import pl.edu.agh.iet.tsp.Algorithm.Result;

public class Converter {
	public static TSPData convert(Result result, TSPData tspData){
		ArrayList<Field> nodes = tspData.getNodes();
		ArrayList<Field> converted = new ArrayList<Field>(nodes.size());
		ArrayList<Integer> path = result.path;
		int lastElement;
		converted.add( nodes.get(path.get(0)));
		lastElement = path.get(0);
		for(int i = 1; i < path.size(); i++)
		{
			
			
			//converted.set(i , nodes.get(path.get(path.get(i-1)) -1));
			int index = path.get(lastElement);//index of next element
			lastElement = path.get(lastElement);
			int nodeIndex = path.get(index); //next element in nodes
			converted.add( nodes.get(nodeIndex));
			
			
		}
		nodes = converted;
		
		return tspData;
	}
}
