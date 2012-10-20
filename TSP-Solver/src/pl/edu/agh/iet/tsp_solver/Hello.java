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

		TSPData sample = TSPData.generateData("sample",
				"przykladowe wygenerowane dane wejsciowe dla naszego programu",
				69, 3000);
		TSPDataSerialization.serialize(sample, new File(
				"./sample_data/sample.tsp"));
		System.out.println(":D");
	}

}
