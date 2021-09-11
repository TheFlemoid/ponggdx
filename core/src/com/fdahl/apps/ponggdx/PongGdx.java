package com.fdahl.apps.ponggdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.fdahl.apps.ponggdx.views.GameScreen;
import com.fdahl.apps.ponggdx.views.MenuScreen;

public class PongGdx extends Game {
	// These are enumerations for describing each game screen
	public static final int MENU = 0;
	public static final int DIFFICULTY_SELECT = 1;
	public static final int APPLICATION = 2;

	public static PongGdx INSTANCE;
	private int screenWidth;
	private int screenHeight;
	private OrthographicCamera orthographicCamera;
	private GameScreen gameScreen;
	private MenuScreen menuScreen;

	public PongGdx() {
		INSTANCE = this;
	}

	public static PongGdx getInstance() {
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

		menuScreen = new MenuScreen();
		gameScreen = new GameScreen(orthographicCamera);
		setScreen(menuScreen);
	}

	public void changeScreen(int screen) {
		switch(screen) {
			case MENU:
				if(menuScreen == null) {
					menuScreen = new MenuScreen();
				}
				this.setScreen(menuScreen);
				break;
			case APPLICATION:
				if(gameScreen == null) {
					gameScreen = new GameScreen(orthographicCamera);
				}
				this.setScreen(gameScreen);
				break;
			default:
				break;
		}
	}

	public int getScreenWidth() {
		return this.screenWidth;
	}

	public int getScreenHeight() {
		return this.screenHeight;
	}
}
