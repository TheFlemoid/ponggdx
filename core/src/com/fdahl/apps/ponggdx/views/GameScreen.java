package com.fdahl.apps.ponggdx.views;

import com.fdahl.apps.ponggdx.GameContactListener;
import com.fdahl.apps.ponggdx.PongGdx;
import com.fdahl.apps.ponggdx.objects.Ball;
import com.fdahl.apps.ponggdx.objects.Player;
import com.fdahl.apps.ponggdx.objects.PlayerAI;
import com.fdahl.apps.ponggdx.objects.Wall;
import com.fdahl.apps.ponggdx.helper.Const;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends ScreenAdapter {
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

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            player.setScore(0);
            playerAi.setScore(0);
            ball.reset();
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
}
