package com.fdahl.apps.ponggdx.views;

import com.fdahl.apps.ponggdx.PongGdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuScreen implements Screen {
    private Stage stage;
    private Skin skin;
    private Label titleLabel;

    public MenuScreen() {
        skin = new Skin(Gdx.files.internal("skins/pong_skin.json"));
        stage = new Stage(new ScreenViewport());
        titleLabel = new Label("Pong GDX!", skin);
    }

    @Override
    public void show() {
        stage.clear();
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        //Create buttons for the menu
        TextButton play = new TextButton("PLAY", skin);
        TextButton exit = new TextButton("EXIT", skin);

        // Add listeners for all buttons
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PongGdx.getInstance().changeScreen(PongGdx.APPLICATION);
            }
        });

        exit.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
               Gdx.app.exit();
           }
        });

        // Add buttons to the Scene2D table
        table.add(titleLabel);
        table.row().pad(30, 0, 10, 0);
        table.add(play);
        table.row().pad(10, 0, 10, 0);
        table.add(exit);
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
}
