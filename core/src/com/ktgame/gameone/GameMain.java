package com.ktgame.gameone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

public class GameMain extends ApplicationAdapter {
	private static final float SCALE = 3.0f;
	//private static final String MAP_PATH = "maps/GameMap.tmx";
	private static final String MAP_PATH = "maps/ONE.tmx";
	private OrthographicCamera orthographicCamera;
	private SpriteBatch batch;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private TiledMap tiledMap;


	@Override
	public void create () {

		orthographicCamera = new OrthographicCamera();
		orthographicCamera.setToOrtho(false, Gdx.graphics.getWidth() / SCALE, Gdx.graphics.getHeight() / SCALE);

		batch = new SpriteBatch();

		tiledMap = new TmxMapLoader().load(MAP_PATH);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

	}

	@Override
	public void render() {
		update();

		Gdx.gl.glClearColor(0.5f, 0.8f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		tiledMapRenderer.render();

	}

	private void update() {

		cameraUpdate();
		tiledMapRenderer.setView(orthographicCamera);
		batch.setProjectionMatrix(orthographicCamera.combined);
	}


	private void cameraUpdate() {
		orthographicCamera.position.x = orthographicCamera.position.x + 2;

		Vector3 position = orthographicCamera.position;
		orthographicCamera.position.set(position);
		orthographicCamera.update();
	}

	@Override
	public void resize(int width, int height) {
		orthographicCamera.setToOrtho(false, width / SCALE, height / SCALE);
	}

	@Override
	public void dispose() {
		batch.dispose();
		tiledMapRenderer.dispose();
		tiledMap.dispose();
	}


}
