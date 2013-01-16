package pl.edu.agh.iet.tsp.Algorithm;

public class Bee {

	public int id;
	public BeeColony colony;

	public Bee(BeeColony colony, int id) {
		this.id = id;
		this.colony = colony;
	}

	public void bzzbzz()
	{
		System.out.println("I'm a bee no. "+ this.id);
	}

}