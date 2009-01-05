package org.kirhgoff.fractals;

public class MandelbrotFunctor implements Functor {

	public Complex getNext(Complex complex) {
        double newReal = complex.real*complex.real - complex.imagine*complex.imagine + complex.real;
        double newImagine = 2*complex.real*complex.imagine + complex.imagine;
        return new Complex (newReal, newImagine);
	}

}
