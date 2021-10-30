package com.fdahl.apps.ponggdx;

import com.fdahl.apps.ponggdx.helper.GameType;
import com.fdahl.apps.ponggdx.views.DifficultyScreen;
import com.fdahl.apps.ponggdx.views.GameScreen;
import com.fdahl.apps.ponggdx.views.MenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class PongGdx extends Game {
	// These are enumerations for describing each game screen
	public static final int MENU = 0;
	public static final int DIFFICULTY_SELECT = 1;
	public static final int APPLICATION = 2;

	// These are enumerations for setting the difficulty of the game
	public static final int EASY = 0;
	public static final int MID = 1;
	public static final int HARD = 2;

	public static PongGdx INSTANCE;
	private GameType gameType;
	private int screenWidth;
	private int screenHeight;
	private OrthographicCamera orthographicCamera;
	private GameScreen gameScreen;
	private MenuScreen menuScreen;
	private DifficultyScreen difficultyScreen;

	public PongGdx(GameType gameType) {
		INSTANCE = this;
		this.gameType = gameType;
	}

	public static PongGdx getInstance() {
		// Not null checking here, as we don't know the GameType to use to make a new instance.
		// If this returns null, something serious has gone wrong.
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
		difficultyScreen = new DifficultyScreen();
		setScreen(menuScreen);
	}

	public void changeScreen(final int screen) {
		switch(screen) {
			case MENU:
				if(menuScreen == null) {
					menuScreen = new MenuScreen();
				}
				Gdx.input.setInputProcessor(menuScreen);
				this.setScreen(menuScreen);
				break;
			case APPLICATION:
				if(gameScreen == null) {
					gameScreen = new GameScreen(orthographicCamera);
				}
				Gdx.input.setInputProcessor(gameScreen);
				this.setScreen(gameScreen);
				break;
			case DIFFICULTY_SELECT:
				if(difficultyScreen == null) {
					difficultyScreen = new DifficultyScreen();
				}
				Gdx.input.setInputProcessor(difficultyScreen);
				this.setScreen(difficultyScreen);
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

	public void setGameDifficulty(final int newDifficultyLevel) {
		gameScreen.setGameDifficulty(newDifficultyLevel);
	}

	public GameType getGameType() {
		return gameType;
	}
}
