package org.kirhgoff.fractals.color;

public class Gradient {
	int colors [];
	
	public Gradient() {
	}
	
	public Gradient(int [] colors) {
		this.colors = colors;
	}
	
	public int getColor  (int component) {
		return colors [component / (255/colors.length + 1)];
	} 
}
