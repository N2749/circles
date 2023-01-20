package com.mygdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraInputHandler extends InputAdapter {

	private OrthographicCamera camera;
	Vector2 touchPos = Vector2.Zero;
	private Vector2 touchPosPrev = touchPos;
	private Vector3 change = Vector3.Zero;
	private boolean justTouched = true;

	public CameraInputHandler(OrthographicCamera camera) {
		this.camera = camera;
	}

	// TODO: think do I need to move camera with WASD?
	// TODO: implement continuous moving
	public boolean keyDown(int keycode) {
		switch (keycode) {
		// Moving camera
		case Keys.A:
			camera.translate(-1, 0, 0);
			break;
		case Keys.D:
			camera.translate(1, 0, 0);
			break;
		case Keys.W:
			camera.translate(0, 1, 0);
			break;
		case Keys.S:
			camera.translate(0, -1, 0);
			break;
		// If Camera lost
		case Keys.F:
			camera.position.set(0, 0, 0);
			break;
		}
		camera.update();
		return true;
	}

	public boolean scrolled(float amountX, float amountY) {

		boolean shift = Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT);
		boolean control = Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_LEFT);

		if (shift && !control) {
			camera.zoom += amountY * 0.1;
			return true;
		}
		if (control && !shift) {
			camera.translate(amountY, 0);
			return true;
		}
		camera.translate(0, amountY);
		camera.update();
		return true;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {

		touchPos = new Vector2(screenX, screenY);
		if (!justTouched) {
			change.set(camera.project(camera.position).add(new Vector3(touchPosPrev.sub(touchPos), 0)));
			change.set(camera.unproject(change));
			camera.position.set(change);
		}
		else {
			justTouched = false;
		}
		touchPosPrev.set(touchPos);

		camera.update();
		return true;
	}

	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		justTouched = true;
		return false;
	}

	public boolean touchUp(int x, int y, int pointer, int button) {
		justTouched = false;
		return true;
	}

}