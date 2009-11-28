package org.kirhgoff.fractals;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class FractalApplication {
	public static void main(String[] args) {
		JFrame frame = new JFrame ("Fractals");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});

		final FractalPanel panel = new FractalPanel();
		panel.addComponentListener(new ComponentAdapter () {
			@Override
			public void componentResized(ComponentEvent arg0) {
				panel.repaint();
			}
		});
		
		frame.addKeyListener(panel.asKeyListener());
		frame.getContentPane().add (panel);
		frame.pack();
		frame.setVisible(true);
	}
}
