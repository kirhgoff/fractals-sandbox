package org.kirhgoff.fractals;

public class MonoGradient extends Gradient {

	public MonoGradient(int steps) {
		colors = new int [steps];
		for (int i = 0; i < steps; i++) {
			colors [i] = 255/steps*i;
		}
	}

}
