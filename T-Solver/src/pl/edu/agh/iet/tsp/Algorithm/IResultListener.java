package pl.edu.agh.iet.tsp.Algorithm;

import javax.naming.spi.DirStateFactory.Result;

import pl.edu.agh.iet.tsp.Model.TSPData;

public interface IResultListener {

	public void receiveResult(TSPData result, double distance);

}
