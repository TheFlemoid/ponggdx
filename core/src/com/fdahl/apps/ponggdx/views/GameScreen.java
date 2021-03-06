package com.fdahl.apps.ponggdx.views;

import com.fdahl.apps.ponggdx.GameContactListener;
import com.fdahl.apps.ponggdx.PongGdx;
import com.fdahl.apps.ponggdx.objects.Ball;
import com.fdahl.apps.ponggdx.objects.Player;
import com.fdahl.apps.ponggdx.objects.PlayerAI;
import com.fdahl.apps.ponggdx.objects.Wall;
import com.fdahl.apps.ponggdx.helper.Const;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends ScreenAdapter implements InputProcessor {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private GameContactListener gameContactListener;

    // Game Objects
    private Player player;
    private PlayerAI playerAi;
    private Ball ball;
    private Wall wallTop;
    private Wall wallBottom;

    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        this.camera.position.set(new Vector3((PongGdx.getInstance().getScreenWidth()/2),
                (PongGdx.getInstance().getScreenHeight()/2), 0));
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,0), false);
        this.gameContactListener = new GameContactListener(this);
        this.world.setContactListener(gameContactListener);

        this.player = new Player(16, PongGdx.getInstance().getScreenHeight()/2, this, 20,
                PongGdx.getInstance().getScreenHeight()-20);
        this.playerAi = new PlayerAI(PongGdx.getInstance().getScreenWidth() - 16,
                PongGdx.getInstance().getScreenHeight()/2, this,
                PongGdx.getInstance().getScreenWidth()-70, PongGdx.getInstance().getScreenHeight()-20);
        this.ball = new Ball(this);
        this.wallTop = new Wall(32, this);
        this.wallBottom = new Wall(PongGdx.getInstance().getScreenHeight() - 32, this);
    }

    public void update() {
        world.step(1/60f, 6, 2);

        this.camera.update();
        this.player.update();
        this.playerAi.update();
        this.ball.update();

        batch.setProjectionMatrix(camera.combined);

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            player.setScore(0);
            playerAi.setScore(0);
            ball.reset();
            PongGdx.getInstance().changeScreen(PongGdx.MENU);
        }
    }

    @Override
    public void render(float delta) {
        update();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // All texture renders get called here
        // Render the walls before the paddles, that way the score gets rendered on top of the wall
        this.wallTop.render(batch);
        this.wallBottom.render(batch);
        this.player.render(batch);
        this.playerAi.render(batch);
        this.ball.render(batch);

        batch.end();
    }

    public void setGameDifficulty(int difficultyLevel) {
        switch(difficultyLevel) {
            case PongGdx.EASY:
                playerAi.setSpeed(6);
                break;
            case PongGdx.MID:
                playerAi.setSpeed(10);
                break;
            case PongGdx.HARD:
                playerAi.setSpeed(15);
                break;
            default:
                playerAi.setSpeed(6);
                break;
        }
    }

    public World getWorld() {
        return world;
    }

    public Ball getBall() {
        return ball;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerAI getPlayerAi() {
        return playerAi;
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
