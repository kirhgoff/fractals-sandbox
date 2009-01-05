package org.kirhgoff.fractals;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class FractalPanel extends JComponent {

	private static final long serialVersionUID = 1L;
	protected Conversion conversion = new Conversion (getSize ());
	protected Zoomer zoomer;
	protected PaletteScroller paletteScroller = new PaletteScroller (this);
	private Image image = null;
	
	public FractalPanel() {
		zoomer = new Zoomer (this);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame ("Fractals");
		final FractalPanel panel = new FractalPanel();
		frame.getContentPane().add (panel);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		panel.addComponentListener(new ComponentAdapter () {
			@Override
			public void componentResized(ComponentEvent arg0) {
				panel.repaint();
			}
		});
		panel.addMouseListener(panel.zoomer);
		panel.addMouseMotionListener(panel.zoomer);
		panel.addMouseWheelListener(panel.zoomer);
		frame.addKeyListener(panel.paletteScroller);
		frame.pack();
		frame.setVisible(true);
	}

	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}

	public void paint(Graphics g) {
		Dimension size = getSize ();
		if (image == null || !conversion.getSize ().equals (size)) {
			conversion.setSize(getSize());
			System.out.println(conversion);
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
				int colorIndex = conversion.getColorIndex(x, y);
				pixels [index++] = (colorIndex << 24) | (colorIndex << 16) | (colorIndex);
			}
		}
		return pixels;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public Image getImage () {
		return image;
	}
}
