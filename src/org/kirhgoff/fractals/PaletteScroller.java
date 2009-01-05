package org.kirhgoff.fractals;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;

public class PaletteScroller extends KeyAdapter {
	private byte[] saturation = new byte[256];
	private int[] pixels;
	private int width;
	private int height;

	private final FractalPanel panel;

	public PaletteScroller(FractalPanel panel) {
		this.panel = panel;
		for (int i = 0; i < 255; i++) {
			saturation[i] = (byte) (i);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'q') {
			synchronized (this) {
				saturation = rotateForward(saturation);
			}
		} else if (e.getKeyChar() == 'w') {
			synchronized (this) {
				saturation = rotateBackward(saturation);
			}
		}

		panel.setImage(getImage(pixels, width, height));
		panel.repaint();

	}

	public static byte[] rotateForward(byte[] array) {
		byte last = array[array.length - 1];
		for (int i = array.length - 1; i >= 1; i--) {
			array[i] = array[i - 1];
		}
		array[0] = last;
		return array;
	}

	public static byte[] rotateBackward(byte[] array) {
		byte first = array[0];
		for (int i = 0; i < array.length - 1; i++) {
			array[i] = array[i + 1];
		}
		array[array.length - 1] = first;
		return array;
	}

	public Image getImage(int[] pixels, int width, int height) {
		this.pixels = pixels;
		this.width = width;
		this.height = height;
		
		byte newReds [] = new byte [saturation.length];
		byte newGreens [] = new byte [saturation.length];
		byte newBlues [] = new byte [saturation.length];
		
		for (int i = 0; i < saturation.length; i++) {
			Color color = Color.getHSBColor(saturation [i]/255.0F,1F,1F);
			newReds [i] = (byte) color.getRed();
			newGreens [i] = (byte) color.getGreen();
			newBlues [i] = (byte) color.getBlue();
		}
		IndexColorModel colorModel = new IndexColorModel(8, 256, newReds, newGreens, newBlues);
		Image image = Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(width, height, colorModel, pixels, 0, width));
		return image;
	}

}
