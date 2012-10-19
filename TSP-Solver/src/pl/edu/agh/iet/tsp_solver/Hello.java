package pl.edu.agh.iet.tsp_solver;

import java.io.File;
import java.io.IOException;

import pl.edu.agh.iet.tsp_solver.Model.TSPData;
import pl.edu.agh.iet.tsp_solver.Model.TSPDataSerialization;

public class Hello {

	public static void main(String[] args) throws IOException {

		File sampleDir = new File("./sample_data");

		if (sampleDir.isDirectory()) {
			for (File file : sampleDir.listFiles()) {
				TSPData data = TSPDataSerialization.deserialize(file);
				System.out.println(data + "\n");
			}
		}
		
		TSPData sample = TSPDataSerialization.generateData("sample", "przyk³adowe wygenerowane dane wejœciowe dla naszego programu", 69, 1500);
		TSPDataSerialization.serialize(sample, new File("./sample_data/sample.tsp"));
	}

}
