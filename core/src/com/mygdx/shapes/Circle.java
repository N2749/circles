package com.mygdx.shapes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Circle {

	private Set<Point> points;

	public void setPoints(Set<Point> points) {
		this.points = points;
	}

	private Point center;
	private int radius;

	public Circle() {
		this.center = new Point(0, 0);
		this.radius = 10;
		generatePoints();
	}

	public Circle(int radius) {
		this.center = new Point(0, 0);
		this.radius = radius;
		generatePoints();
	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
		generatePoints();
	}

	public void printPoints() {
		Comparator<Point> comparator = new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o1 == null && o2 == null || o1.equals(o2))
					return 0;
				if (o1.getX() > o2.getX())
					return 1;
				if (o1.getX() == o2.getX() && o1.getY() > o2.getY())
					return 1;
				return -1;
			}
		};
		System.out.println("size of points: " + points.size());
		ArrayList<Point> pr = new ArrayList<>(points);
		pr.sort(comparator);
		pr.forEach((Point p) -> System.out.println(p));
	}

	// this method creates an r+1 circle TODO: refactor
	// TODO: generate circle looking to diameter, when it is odd, add point in
	// center, remove otherwise.
	private void generatePoints() {

		points = new HashSet<>();
		int cx = center.getX();
		int cy = center.getY();
		int tx = cx;
		int ty = cy;

		// generating quarter
		while (tx <= radius) {
			Point t = new Point(tx, 0);
			int base = (int) Math.round(Math.sqrt(Math.pow(radius, 2) - Math.pow(tx - cx, 2)));

			t.setY(base + cy);

			points.add(t);

			tx++;
		}
		while (ty <= radius) {
			Point t = new Point(0, ty);
			int base = (int) Math.round(Math.sqrt(Math.pow(radius, 2) - Math.pow(ty - cy, 2)));

			t.setX(base + cx);
			points.add(t);

			ty++;
		}

		// inverting it two times
		Iterator<Point> it = points.iterator();
		HashSet<Point> copy = new HashSet<>();

		while (it.hasNext())
			copy.add(new Point(it.next()).invertX());
		points.addAll(copy);

		it = points.iterator();
		while (it.hasNext())
			copy.add(new Point(it.next()).invertY());
		points.addAll(copy);
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Set<Point> getPoints() {
		return points;
	}

	public void draw(ShapeRenderer shape) {
		for (Point p : points) {
			p.draw(shape);
		}
	}
}
