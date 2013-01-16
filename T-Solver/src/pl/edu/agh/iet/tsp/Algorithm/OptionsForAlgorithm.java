package pl.edu.agh.iet.tsp.Algorithm;

public class OptionsForAlgorithm {

	private int n;
	private int bcmax;
	private float gamma;
	private float alpha;
	private float beta;
	private float k;
	private float[][] tab;

	public OptionsForAlgorithm() {
		tab = new float[3][5];
	}

	public int getBcmax() {
		return bcmax;
	}

	public void setBcmax(int bcmax) {
		this.bcmax = bcmax;
	}

	public float getGamma() {
		return gamma;
	}

	public void setGamma(float gamma) {
		this.gamma = gamma;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public float getBeta() {
		return beta;
	}

	public void setBeta(float beta) {
		this.beta = beta;
	}

	public float getK() {
		return k;
	}

	public void setK(float k) {
		this.k = k;
	}

	public float[][] getTab() {
		return tab;
	}

	public void setTab(float[][] t) {
		tab = t;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
}