package com.ktgame.gameone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class GameMain extends ApplicationAdapter implements InputProcessor {

	private static final float SCALE = 2.5f;
	private static final String MAP_PATH = "maps/one1.tmx";
	private static final String SPRITE_PATH = "sprites/axd.png";
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private TiledMap tiledMap;
	private SpriteBatch sbatch;
	private Texture texture;
	private Sprite sprite;


	@Override
	public void create () {

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w/SCALE, h/SCALE);
		camera.update();
		tiledMap = new TmxMapLoader().load(MAP_PATH);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);

		sbatch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal(SPRITE_PATH));
		sprite = new Sprite(texture);

	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		sbatch.begin();
		sprite.draw(sbatch);
		sbatch.end();

		/*
		Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
		Vector3 position = camera.unproject(clickCoordinates);
		sprite.setPosition(position.x, position.y);
		*/

	}

	/*
	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width / SCALE, height / SCALE);
	}
	*/

	@Override
	public void dispose() {
		tiledMapRenderer.dispose();
		tiledMap.dispose();
		sbatch.dispose();
		texture.dispose();

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.LEFT)
			camera.translate(-8,0); // x was 32
		if(keycode == Input.Keys.RIGHT)
			camera.translate(8,0);

		if(keycode == Input.Keys.NUM_1)
			tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
		if(keycode == Input.Keys.NUM_2)
			tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
		if(keycode == Input.Keys.NUM_3)
			tiledMap.getLayers().get(2).setVisible(!tiledMap.getLayers().get(2).isVisible());
		if(keycode == Input.Keys.NUM_4)
			tiledMap.getLayers().get(3).setVisible(!tiledMap.getLayers().get(3).isVisible());

		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}



}
