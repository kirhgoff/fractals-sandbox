package org.kirhgoff.fractals;
import java.awt.Dimension;
import java.awt.Point;

public class Conversion {
	private Dimension size;
	private Coord coords = new Coord (-2.0, 2.0, -2.0, 2.0);
	
	public Conversion(Dimension size2) {
		this.size = size2;
	}

	public int getColorIndex(int x, int y) {
		double xReal = convertX(x);
		double yReal = convertY(y);
		int colorComponent = ComplexProcessor.timeToInfinity(
				new SimpleFunctor(), new Complex(xReal, yReal));
		return colorComponent;
	}


	public double convertY(int y) {
		return - y *  getHeightReal()/ size.height + coords.getMaxY ();
	}

	public double convertX(int x) {
		return coords.getMinX () + x * getWidthReal() / size.width;
	}

	private double getWidthReal() {
		return Math.abs(coords.getMaxX () - coords.getMinX ());
	}

	private double getHeightReal() {
		return Math.abs(coords.getMaxY () - coords.getMinY ());
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public void setNewPoints(Point start, Point end) {
		double newMinX = convertX (start.x);
		double newMaxX = convertX(end.x);
		double newMinY = convertY(end.y);
		double newMaxY = convertY (start.y);
	
		coords = new Coord (newMinX, newMaxX, newMinY, newMaxY);
		System.out.println("new points start=" + start + " end=" + end);
		System.out.println(coords);
	}

	public Dimension getSize() {
		return size;
	}
	
	@Override
	public String toString() {
		return "Size: " + getSize() + "\n" + coords;
	}

	public Coord getCoord() {
		try {
			return (Coord) coords.clone ();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setCoord(Coord pop) {
		coords = pop;
	}
}
