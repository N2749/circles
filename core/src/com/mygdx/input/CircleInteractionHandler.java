package com.mygdx.input;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.shapes.Circle;
import com.mygdx.shapes.Point;

public class CircleInteractionHandler extends InputAdapter {

	private Circle circle;
	private OrthographicCamera camera;

	public CircleInteractionHandler(OrthographicCamera camera, Circle circle) {
		this.camera = camera;
		this.circle = circle;
	}
	
	// TODO: add hopping to next, and highlighting the previous point
	// TODO: implement clearing and undoing clearing
	// TODO: implement command pattern
	public boolean keyDown(int keycode) {
//			if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) &&
//					Gdx.input.isKeyPressed(Keys.ALT_LEFT) &&
//					Gdx.input.isKeyPressed(Keys.HOME))
//				
		return true;
	}

	// TODO: implement filling several sections of circle
	// TODO: add pop up when touching point
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (button != Buttons.LEFT)
			return true;
		Vector3 position = camera.unproject(new Vector3(x, y, 0));
		for (Point p : circle.getPoints())
			if (p.isTouched(position.x, position.y))
				p.toggleFilled();
		return true;
	}
	// TODO: collision detection with point
	// TODO: pop up of coordinates of point in world units
	// TODO: change color of point that was placed, maybe move perspective to the
	// next
	// TODO: say how many blocks in row / column
}
