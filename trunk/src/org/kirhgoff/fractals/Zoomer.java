package org.kirhgoff.fractals;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Stack;

public class Zoomer extends MouseAdapter {
	private static final int GAP = 4;
	private Point start = new Point();
	private Point end = new Point();
	private boolean dragging = false;
	private Stack<Coord> history = new Stack<Coord> ();
	
	private FractalPanel component;

	public Zoomer(FractalPanel component) {
		this.component = component;
	}

	@Override
	public void mousePressed(MouseEvent event) {
		System.out.println("Pressed: " + event);
		synchronized (this) {
			dragging = true;
			start = event.getPoint ();
			end = event.getPoint();
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (history.isEmpty()) return;
		Coord pop = history.pop();
		component.conversion.setCoord (pop);
		component.setImage (null);
		component.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		System.err.println("Released: " + event);
		synchronized (this) {
			dragging = false;
			history.push(component.conversion.getCoord ());
			component.conversion.setNewPoints(start, end);
			component.setImage (null);
			component.repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point position = e.getPoint();
		if (position.x > start.x && position.y > start.y)
			end = e.getPoint();
		component.repaint();
	}

	public void draw(Graphics g) {
		if (dragging) {
			g.setColor(Color.black);
			//g.setFont(new Font ("Courier", Font.BOLD, 10));
			g.drawRect(start.x, start.y, end.x - start.x , end.y - start.y);

			String startTag = point2String(start);
			int stringWidth = g.getFontMetrics().stringWidth(startTag);
			g.drawString(startTag, start.x - stringWidth + GAP, start.y - GAP);

			String endTag = point2String(end);
			g.drawString(endTag, end.x + GAP, end.y + GAP);
		}
	}

	private String point2String(Point p) {
		return "[" + p.x + ", " + p.y + "]";
	}
}
