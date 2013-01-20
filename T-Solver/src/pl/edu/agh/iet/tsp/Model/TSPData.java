package pl.edu.agh.iet.tsp.Model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class TSPData {

	private final String name;
	private final String comment;
	private final int dimension;
	private double minX = 0.0;
	private double minY = 0.0;
	private double maxX = 0.0;
	private double maxY = 0.0;
	private final ArrayList<Field> nodes;
	public ArrayList<ArrayList<Float>> graph;
	

	public TSPData(String name, String comment, int dimension,
			Collection<Field> nodes) {
		this.name = name;
		this.comment = comment;
		this.dimension = dimension;
		this.nodes = new ArrayList<Field>(nodes);
		this.graph = new ArrayList<ArrayList<Float>>();
		
		if (nodes.size() > 0) {
			ArrayList<Double> x = new ArrayList<Double>();
			ArrayList<Double> y = new ArrayList<Double>();
			for (Field field : nodes) {
				x.add(field.getX());
				y.add(field.getY());
			}

			minX = Collections.min(x);
			maxX = Collections.max(x);
			minY = Collections.min(y);
			maxY = Collections.max(y);
		}
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	public int getDimension() {
		return dimension;
	}
	
	public double getMaxX() {
		return maxX;
	}
	
	public double getMaxY() {
		return maxY;
	}
	
	public double getMinX() {
		return minX;
	}
	
	public double getMinY() {
		return minY;
	}

	public Collection<Field> getNodes() {
		return nodes;
	}

	@Override
	public String toString() {
		String result = "TSV Data Object:";

		if (name != null)
			result += "\n\tname:\t\t" + name;
		if (comment != null)
			result += "\n\tcomment:\t" + comment;
		if (dimension != -1)
			result += "\n\tdimmen:\t\t" + dimension;
		if (nodes != null && nodes.size() > 0) {
			result += "\n\tX range:\t" + minX + "\t<=>\t" + maxX;
			result += "\n\tY range:\t" + minY + "\t<=>\t" + maxY;
			result += "\n\tpoints:\t\t";
			if (nodes != null)
				for (Field field : nodes)
					result += field + " ";
			result.substring(0, result.length() - 2);
		}

		return result;
	}

	public static TSPData generateData(String name, String comment,
			int dimension, int mapWidth) {

		ArrayList<Field> fields = new ArrayList<Field>();

		Random rand = new Random();
		for (int i = 0; i < dimension; i++) {
			Field field = new Field((double) rand.nextInt(mapWidth) - mapWidth
					/ 2, (double) rand.nextInt(mapWidth) - mapWidth / 2);
			fields.add(field);
		}

		return new TSPData(name, comment, dimension, fields);
	}
	
	public void nodesToGraph(){
		
		for(int i = 0; i < dimension; i++){
			graph.add(new ArrayList<Float>());
			for(int j = 0; j < dimension; j++){
				double dist = nodes.get(i).distance(nodes.get(j));
				graph.get(i).add( new Float(dist));
			}
			
		}
		
	}

}


