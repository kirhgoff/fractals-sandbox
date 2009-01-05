package org.kirhgoff.fractals;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
	private Point cursor = new Point ();
	private Stack<Coord> history = new Stack<Coord> ();
	
	private FractalPanel component;

	public Zoomer(FractalPanel component) {
		this.component = component;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		cursor = e.getPoint();
		component.repaint ();
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
			System.out.println("setting new coords: " + start + ", " + end);
			component.conversion.setNewPoints(start, end);
			component.setImage (null);
			component.repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		end = e.getPoint();
		Component source = (Component) e.getSource();
		Dimension size = source.getSize();
		
		if (end.x < start.x + 10) end.x = start.x + 10;
		if (end.y < start.y + 10) end.y = start.y + 10;

		end = adjustToAspectRatio(size, start, end);
		component.repaint();
	}

	public static Point adjustToAspectRatio(Dimension size, Point start, Point end) {
		float aspectRatio = ((float)size.width)/size.height;		
		int width = end.x - start.x;
		int height = end.y - start.y;
		if (((float)width/height > aspectRatio)) {
			end.x = start.x + Math.round((float)height*aspectRatio);
		}
		else {
			end.y = start.y + Math.round((float)width/aspectRatio);
		}
		return end;
	}

	public void draw(Graphics g) {
		g.setXORMode(Color.white);
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
		else {
			Dimension size = component.getSize();
			g.setColor(Color.black);
			String cursorTag = point2String(cursor);
			int stringWidth = g.getFontMetrics().stringWidth(cursorTag);
			int stringHeight = g.getFontMetrics().getHeight();
			
			int dx = (cursor.x > size.width/2 ? - stringWidth - GAP : GAP);
			int dy = (cursor.y < size.height/2 ? stringHeight + 2*GAP : - GAP);
			g.drawString(cursorTag, cursor.x + dx, cursor.y + dy);
		}
		g.setPaintMode();
	}

	private String point2String(Point p) {
		return "[" + p.x + ", " + p.y + "] " +
			"[" + component.conversion.convertX(p.x) + ", " + component.conversion.convertY(p.y);
	}
}
