package org.kirhgoff.fractals;

public class Complex {
	private static final double EPSILON = 1E-14;
	protected double real;
	protected double imagine; 
	
	public Complex(double real, double imagine) {
		this.imagine = imagine;
		this.real = real;
	}
	
	@Override
	public String toString() {
		return real + " + i*" + imagine;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Complex)) return false;
		Complex other = (Complex) obj;
		return Math.abs(real - other.real) < EPSILON && Math.abs(imagine - other.imagine) < EPSILON;
	}
	
	public boolean isInfinite () {
		return real > 100 && imagine > 100;
	}

}
