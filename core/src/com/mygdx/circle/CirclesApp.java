package com.mygdx.circle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.input.CameraInputHandler;
import com.mygdx.input.CircleInteractionHandler;
import com.mygdx.shapes.Circle;

import space.earlygrey.shapedrawer.ShapeDrawer;

/* TODO:
 * add start screen
 * change shapeRenderer to shapeDrawer
 * add input fields
 * add text output of points
 */

public class CirclesApp extends ApplicationAdapter {

	// Constants
	public final static int MIN_WORLD_WIDTH = 100;
	public final static int MIN_WORLD_HEIGHT = 100;
	public final static int BASE_VIEWPORT_WIDTH = 30;
	public final static int BASE_BLOCK_SIZE = 1;

	// View variables
	private OrthographicCamera camera;
	private ExtendViewport worldViewport;
	Texture pixelGrid;

	// Draw Variables
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private BitmapFont font;

	// Input variables
	InputMultiplexer multiplexer;

	// Shapes Variables
	private Circle circle;

	@Override
	public void create() {
		// TODO: change camera initial position depending on size of circle
		camera = new OrthographicCamera(BASE_VIEWPORT_WIDTH, BASE_VIEWPORT_WIDTH);
		worldViewport = new ExtendViewport(BASE_VIEWPORT_WIDTH, BASE_VIEWPORT_WIDTH);
		worldViewport.setCamera(camera);

		shape = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(1f);

		circle = new Circle();

		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new CameraInputHandler(camera));
		multiplexer.addProcessor(new CircleInteractionHandler(camera, circle));
		Gdx.input.setInputProcessor(multiplexer);

		pixelGrid = new Texture("pixelgrid.png");
	}

	// TODO: add grid
	@Override
	public void render() {
		camera.update();

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1f);

//		batch.setProjectionMatrix(camera.combined);
//	    batch.begin();
////	    font.draw(batch, "Hello World!", 10, 10);
//	    batch.draw(pixelGrid, 0, 0);
//	    batch.end();

		shape.setProjectionMatrix(camera.combined);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		circle.draw(shape);
		shape.end();
	}

	@Override
	public void resize(int width, int height) {
		worldViewport.update(width, height);
	}

	@Override
	public void dispose() {
		batch.dispose();
		shape.dispose();
	}
}
