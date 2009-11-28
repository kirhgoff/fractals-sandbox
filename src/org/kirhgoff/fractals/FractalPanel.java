package org.kirhgoff.fractals;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

import org.kirhgoff.fractals.controllers.PaletteScroller;
import org.kirhgoff.fractals.controllers.Zoomer;
import org.kirhgoff.fractals.math.Conversion;

public class FractalPanel extends JComponent {

	private static final long serialVersionUID = 1L;
	private Conversion conversion = new Conversion (getSize ());
	private Zoomer zoomer  = new Zoomer (this);
	private PaletteScroller paletteScroller = new PaletteScroller (this);
	private Image image = null;

	public FractalPanel() {
		
		addMouseListener(zoomer);
		addMouseMotionListener(zoomer);
		addMouseWheelListener(zoomer);
		//TODO why doesnt work???
		addKeyListener(paletteScroller);
	}

	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}

	public void paint(Graphics g) {
		Dimension size = getSize ();
		if (image == null || !getConversion().getSize ().equals (size)) {
			getConversion().setSize(getSize());
			System.out.println(getConversion());
			int[] pixels = buildPixelsMatrix(getSize().width, getSize().height);
			image = paletteScroller.getImage (pixels, getSize().width, getSize().height);
		}
		g.drawImage(image, 0, 0, null);
		zoomer.draw (g);
	}

	private int[] buildPixelsMatrix(int width, int height) {
		int [] pixels = new int [width*height];
		int index = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++){
				int colorIndex = getConversion().getColorIndex(x, y);
				pixels [index++] = (colorIndex << 24) | (colorIndex << 16) | (colorIndex);
			}
		}
		return pixels;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setConversion(Conversion conversion) {
		this.conversion = conversion;
	}

	public Conversion getConversion() {
		return conversion;
	}
	
	public KeyListener asKeyListener () {
		return paletteScroller;
	}
	
	@Override
	public boolean isFocusTraversable() {
		return true;
	}
}
