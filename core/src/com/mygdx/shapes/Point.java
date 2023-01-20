package com.mygdx.shapes;

import java.util.Objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Point {

	private static final short SIZE_IN_WORLD_UNITS = 1;
	private static Color FILL_COLOR = Color.RED;
	private static Color DEFAULT_COLOR = Color.WHITE;
	private Vector2 position;
	private Color color;

	public Point() {
		position = new Vector2(0, 0);
		color = DEFAULT_COLOR;
	}

	public Point(float x, float y) {
		position = new Vector2(x, y);
		color = DEFAULT_COLOR;
	}

	public Point(Vector2 position) {
		this.position = position;
		color = DEFAULT_COLOR;
	}

	public Point(Point p) {
		position = new Vector2(p.position);
		color = DEFAULT_COLOR;
	}

	public Point(Vector2 position, Color color) {
		super();
		this.position = position;
		this.color = color;
	}

	public Point invertX() {
		if (position.x != 0)
			position.x = -position.x;
		return this;
	}

	public Point invertY() {
		if (position.y != 0)
			position.y = -position.y;
		return this;
	}

	public void draw(ShapeRenderer shape) {
		if (shape.getColor() != color)
			shape.setColor(color);
		shape.rect(position.x, position.y, SIZE_IN_WORLD_UNITS, SIZE_IN_WORLD_UNITS);
	}

	public boolean isTouched(float x, float y) {
		x -= position.x;
		y -= position.y;
		boolean inXrange = x >= 0 && x <= 1;
		boolean inYrange = y >= 0 && y <= 1;
		return inXrange && inYrange;
	}

	public void toggleFilled() {
		color = color == FILL_COLOR ? DEFAULT_COLOR : FILL_COLOR;
	}

	@Override
	public String toString() {
		return position.toString();
	}

	@Override
	public int hashCode() {
		int r = 1;
		r = (int) (r * 3 + Double.doubleToLongBits(position.x));
		r = (int) (r * 5 + Double.doubleToLongBits(position.y));
		return r;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return Objects.equals(position, other.position);
	}

	public int getX() {
		return (int) this.position.x;
	}

	public int getY() {
		return (int) this.position.y;
	}

	public void setX(int x) {
		this.position.set(x, this.position.y);
	}

	public void setY(int y) {
		this.position.set(this.position.x, y);
	}

	public void setPosition(Vector2 v) {
		position.set(v);
	}

	public Vector2 getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
