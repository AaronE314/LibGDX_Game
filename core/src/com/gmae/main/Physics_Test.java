package com.gmae.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gmae.main.screen.GameScreen;
import com.gmae.main.screen.ScreenManager;

public class Physics_Test extends ApplicationAdapter {
	SpriteBatch batch;
	public static int WIDTH = 800, HEIGHT = 480;


	@Override
	public void create () {
		batch = new SpriteBatch();
		ScreenManager.setScreen(new GameScreen());

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().update();

		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().render(batch);

	}

	@Override
	public void dispose () {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().dispose();
		batch.dispose();
	}
}
