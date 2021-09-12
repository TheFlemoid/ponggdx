package com.fdahl.apps.ponggdx.views;

import com.fdahl.apps.ponggdx.PongGdx;

import com.badlogic.gdx.Gdx;
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

public class DifficultyScreen implements Screen {
    private Stage stage;
    private Skin skin;
    private Label difficultyLabel;
    private Sound clickSound;

    public DifficultyScreen() {
        skin = new Skin(Gdx.files.internal("skins/pong_skin.json"));
        stage = new Stage(new ScreenViewport());
        difficultyLabel = new Label("Difficulty", skin);
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

        // Create buttons for the menu
        TextButton easy = new TextButton("EASY", skin);
        TextButton mid = new TextButton("MID", skin);
        TextButton hard = new TextButton("HARD", skin);
        TextButton back = new TextButton("BACK", skin);

        easy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
                PongGdx.getInstance().setGameDifficulty(PongGdx.EASY);
                PongGdx.getInstance().changeScreen(PongGdx.APPLICATION);
            }
        });

        mid.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
                PongGdx.getInstance().setGameDifficulty(PongGdx.MID);
                PongGdx.getInstance().changeScreen(PongGdx.APPLICATION);
            }
        });

        hard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
                PongGdx.getInstance().setGameDifficulty(PongGdx.HARD);
                PongGdx.getInstance().changeScreen(PongGdx.APPLICATION);
            }
        });

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
                PongGdx.getInstance().changeScreen(PongGdx.MENU);
            }
        });

        table.add(difficultyLabel);
        table.row().pad(30, 0, 10, 0);
        table.add(easy);
        table.row().pad(10, 0, 10, 0);
        table.add(mid);
        table.row().pad(10, 0, 10, 0);
        table.add(hard);
        table.row().pad(10, 0, 10, 0);
        table.add(back);
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
