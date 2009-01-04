package org.kirhgoff.fractals;

public class Coord implements Cloneable{
	private double minX = -2.0;
	private double maxX = 2.0;
	private double minY = -2.0;
	private double maxY = 2.0;
	
	public Coord(double minX, double maxX, double minY, double maxY) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}


	public double getMinX() {
		return minX;
	}

	public double getMaxX() {
		return maxX;
	}


	public double getMinY() {
		return minY;
	}

	public double getMaxY() {
		return maxY;
	}
	
	@Override
	public String toString() {
		return "Coords: [" + minX + ", " + minX + ", " + maxX + ", " + maxY + "]";
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
