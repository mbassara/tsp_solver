package pl.edu.agh.iet.tsp.Model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class TSPDataSerialization {
	
	public static boolean serialize(TSPData data, File file){
		if(data == null || file == null)
			return false;
		
		try {
			BufferedWriter writer = new BufferedWriter(
										new OutputStreamWriter(
											new FileOutputStream(file),
											Charset.forName("ASCII")));
			
			if(data.getName() != null)
				writer.write("NAME: " + data.getName() + System.getProperty("line.separator"));
			
			writer.write("TYPE: TSP");
			writer.newLine();
			
			if(data.getComment() != null)
				writer.write("COMMENT: " + data.getComment() + System.getProperty("line.separator"));
			
			if(data.getDimension() != -1)
				writer.write("DIMENSION: " + data.getDimension() + System.getProperty("line.separator"));
			
			writer.write("EDGE_WEIGHT_TYPE: EUC_2D");
			writer.newLine();
			writer.write("NODE_COORD_SECTION");
			writer.newLine();
			
			int index = 1;
			if(data.getNodes() != null){
				for(Field field : data.getNodes())
					writer.write(index++ + " " + field.getX() + " " + field.getY() + System.getProperty("line.separator"));
			}
			
			writer.write("EOF");
			writer.newLine();
			writer.newLine();
			
			writer.flush();
			writer.close();
			
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public static TSPData deserialize(File tsvFile) {
		String name = null, comment = null;
		int dimension = -1;
		ArrayList<Field> nodes = new ArrayList<Field>();

		try {
			BufferedReader reader = new BufferedReader(
										new InputStreamReader(
											new FileInputStream(tsvFile),
											Charset.forName("ASCII")));

			boolean coordinates_section_reached = false;
			String line = reader.readLine();
			while (line != null) {

				if (line.contains("EOF"))
					coordinates_section_reached = false;

				if (coordinates_section_reached) {
					String[] lineSplit = line.split(" ");
					nodes.add(new Field(Double.parseDouble(lineSplit[1]),
							Double.parseDouble(lineSplit[2])));
				}

				if (line.contains("NAME: "))
					name = line.split("NAME: ")[1];
				else if (line.contains("COMMENT: "))
					comment = line.split("COMMENT: ")[1];
				else if (line.contains("DIMENSION: "))
					dimension = Integer.parseInt(line.split("DIMENSION: ")[1]);
				else if (line.contains("NODE_COORD_SECTION"))
					coordinates_section_reached = true;
				// Unsupported formats
				else if (line.matches("TYPE: .*")){
					if(!(line.split("TYPE: ")[1].equals("TSP"))){
						return null;	// NOT a TSP file type!!!
					}
				}
				else if (line.matches("EDGE_WEIGHT_TYPE: .*")){
					if(!(line.split("EDGE_WEIGHT_TYPE: ")[1].equals("EUC_2D"))){
						return null;	// NOT a 2D euclidean coordinates in this file 
					}
				}

				line = reader.readLine();
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new TSPData(name, comment, dimension, nodes);
	}

}
