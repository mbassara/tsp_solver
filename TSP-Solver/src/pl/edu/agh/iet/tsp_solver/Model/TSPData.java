package pl.edu.agh.iet.tsp_solver.Model;
import java.util.ArrayList;
import java.util.Collection;

public class TSPData {

	private String name;
	private String comment;
	private int dimension;
	private ArrayList<Field> nodes;

	public TSPData(String name, String comment, int dimension,
			Collection<Field> nodes) {
		this.name = name;
		this.comment = comment;
		this.dimension = dimension;
		this.nodes = new ArrayList<Field>(nodes);
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

	public Collection<Field> getNodes() {
		return nodes;
	}

	@Override
	public String toString() {
		String result = "TSV Data Object:";
		if(name != null) result += "\n\tname:\t\t" + name;
		if(comment != null) result += "\n\tcomment:\t" + comment;
		if(dimension != -1) result += "\n\tdimmen:\t\t" + dimension;
		if(nodes != null && nodes.size() > 0){
			result += "\n\tpoints:\t\t";
			if (nodes != null)
				for (Field field : nodes)
					result += field + " ";
			result.substring(0, result.length() - 2);
		}

		return result;
	}

}
