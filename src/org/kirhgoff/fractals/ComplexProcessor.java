package org.kirhgoff.fractals;

public class ComplexProcessor {
	private static final int ITERATIONS = 255;


	public static int timeToInfinity(Functor functor, Complex complex) {
		int count = 0;
		double cx = complex.real;
		double cy = complex.imagine;
		while (count < ITERATIONS && Math.abs(complex.real) < 100
				&& Math.abs(complex.imagine) < 100) {
			double newReal = complex.real * complex.real - complex.imagine
					* complex.imagine + cx;
			double newImagine = 2 * complex.real * complex.imagine
					+ cy;
			complex = new Complex(newReal, newImagine);
			count++;
		}
		return count;
	}
}
