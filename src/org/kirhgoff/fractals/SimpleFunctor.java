package org.kirhgoff.fractals;

public class SimpleFunctor implements Functor {

	public Complex getNext(Complex value) {
		return new Complex (value.real*value.real - value.imagine*value.imagine - 1, 2*value.imagine*value.real);
	}

}
