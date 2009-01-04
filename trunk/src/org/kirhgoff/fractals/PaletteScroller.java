package org.kirhgoff.fractals;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;

public class PaletteScroller extends KeyAdapter {
	private byte[] reds = new byte[256];
	private byte[] greens = new byte[256];
	private byte[] blues = new byte[256];
	private int[] pixels;
	private int width;
	private int height;

	private final FractalPanel panel;


	public PaletteScroller(FractalPanel panel) {
		this.panel = panel;
		for (int i = 0; i < 255; i++) {
			System.out.println(i);
			reds[i] = (byte) i;
			greens[i] = (byte) i;
			blues[i] = (byte) i;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'q') {
			synchronized (this) {
				reds = rotateForward(reds);
			}
		}
		else if (e.getKeyChar() == 'w') {
			synchronized (this) {
				reds = rotateBackward(reds);
			}
		}
		if (e.getKeyChar() == 'a') {
			synchronized (this) {
				greens = rotateForward(greens);
			}
		}
		else if (e.getKeyChar() == 's') {
			synchronized (this) {
				greens = rotateBackward(greens);
			}
		}
		else if (e.getKeyChar() == 'z') {
			synchronized (this) {
				blues = rotateForward(blues);
			}
		}
		else if (e.getKeyChar() == 'x') {
			synchronized (this) {
				blues = rotateBackward(blues);
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

		IndexColorModel colorModel = new IndexColorModel(8, 256, reds, greens,
				blues);
		Image image = Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(width, height, colorModel, pixels, 0,
						width));
		return image;
	}

}
