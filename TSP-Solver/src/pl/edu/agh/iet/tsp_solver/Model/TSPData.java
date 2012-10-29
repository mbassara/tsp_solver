package pl.edu.agh.iet.tsp_solver.Model;

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
	private final ArrayList<Node> nodes;

	public TSPData(String name, String comment, int dimension,
			Collection<Node> nodes) {
		this.name = name;
		this.comment = comment;
		this.dimension = dimension;
		this.nodes = new ArrayList<Node>(nodes);

		if (nodes.size() > 0) {
			ArrayList<Double> x = new ArrayList<Double>();
			ArrayList<Double> y = new ArrayList<Double>();
			for (Node node : nodes) {
				x.add(node.getX());
				y.add(node.getY());
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

	public Collection<Node> getNodes() {
		return nodes;
	}

	public Node getNode(int i) {
		if (nodes == null || nodes.size() <= i)
			return null;

		return nodes.get(i);
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
				for (Node field : nodes)
					result += field + " ";
			result.substring(0, result.length() - 2);
		}

		return result;
	}

	public static TSPData generateData(String name, String comment,
			int dimension, int mapWidth) {

		ArrayList<Node> fields = new ArrayList<Node>();

		Random rand = new Random();
		for (int i = 0; i < dimension; i++) {
			Node field = new Node((double) rand.nextInt(mapWidth) - mapWidth
					/ 2, (double) rand.nextInt(mapWidth) - mapWidth / 2);
			fields.add(field);
		}

		return new TSPData(name, comment, dimension, fields);
	}

}
