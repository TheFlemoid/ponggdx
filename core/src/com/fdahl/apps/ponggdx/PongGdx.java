package com.fdahl.apps.ponggdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PongGdx extends Game {

	public static PongGdx INSTANCE;
	private int screenWidth;
	private int screenHeight;
	private OrthographicCamera orthographicCamera;

	public PongGdx() {
		INSTANCE = this;
	}

	public static PongGdx getInstance() {
		PongGdx retVal = null;

		if (INSTANCE == null) {
			INSTANCE = new PongGdx();
		}
		return INSTANCE;
	}


	@Override
	public void create () {
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();
		this.orthographicCamera = new OrthographicCamera();
		this.orthographicCamera.setToOrtho(false, screenWidth, screenHeight);
		setScreen(new GameScreen(orthographicCamera));
	}

	public int getScreenWidth() {
		return this.screenWidth;
	}

	public int getScreenHeight() {
		return this.screenHeight;
	}
}
