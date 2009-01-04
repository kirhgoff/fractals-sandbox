package org.kirhgoff.fractals;

public class ComplexProcessor {
	private static final int ITERATIONS = 255;
	private static final double INFINITY = Double.MAX_VALUE;
	
	public static int timeToInfinity (Functor functor, Complex complex) {
		for (int i = 0; i < ITERATIONS; i++) {
			Complex newComplex = functor.getNext(complex);
			double distance = newComplex.distance();
			//System.out.println("Distance: " + distance);
			if (distance > INFINITY || Double.NaN == distance) return i;
			complex = newComplex;
		}
		return ITERATIONS;
	}
	
}
