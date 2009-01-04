package org.kirhgoff.fractals;
import java.awt.Color;


public class ColorMapper {
	private Gradient redGradient = new MonoGradient (3);
	private Gradient greenGradient = new MonoGradient (10);
	private Gradient blueGradient = new MonoGradient (10);
	
	public Color getColor(int colorComponent) {
		return new Color (red(colorComponent), green(colorComponent), blue(colorComponent));
	}
	
	protected int red (int component) {
		return redGradient.getColor(component);
	}
	
	protected int blue (int component) {
		return blueGradient.getColor(component);
	}

	protected int green (int component) {
		return greenGradient.getColor(component);
	}

}
