package com.fdahl.apps.ponggdx.views;

import com.fdahl.apps.ponggdx.PongGdx;
import com.fdahl.apps.ponggdx.helper.GameType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuScreen implements Screen, InputProcessor {
    private Stage stage;
    private Skin skin;
    private Label titleLabel;
    private Sound clickSound;

    public MenuScreen() {
        skin = new Skin(Gdx.files.internal("skins/pong_skin.json"));
        stage = new Stage(new ScreenViewport());
        titleLabel = new Label("Pong GDX!", skin);
        this.clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/button_clicked.wav"));
    }

    @Override
    public void show() {
        stage.clear();
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        //Create buttons for the menu
        TextButton play = new TextButton("PLAY", skin);
        TextButton exit = new TextButton("EXIT", skin);

        // Add listeners for all buttons
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
                PongGdx.getInstance().changeScreen(PongGdx.DIFFICULTY_SELECT);
            }
        });

        exit.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
               clickSound.play();
               Gdx.app.exit();
           }
        });

        // Add buttons to the Scene2D table
        table.add(titleLabel);
        table.row().pad(30, 0, 10, 0);
        table.add(play);
        table.row().pad(10, 0, 10, 0);
        // Don't add the exit button if this is an HTTP game session, as the exit call won't do anything
        if(PongGdx.getInstance().getGameType() != GameType.HTTP) {
            table.add(exit);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }

    // The below methods are Overrides needed for the implementation of InputProcessor
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
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
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
