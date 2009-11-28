package org.kirhgoff.fractals.math;

public class Coord implements Cloneable{
	private double minX;
	private double maxX;
	private double minY;
	private double maxY;
	
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
		return "Coords: [" + minX + ", " + maxX + ", " + minY + ", " + maxY + "]";
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
