package com.fdahl.apps.ponggdx;

import com.fdahl.apps.ponggdx.objects.Ball;
import com.fdahl.apps.ponggdx.objects.Player;

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
    private Box2DDebugRenderer box2DDebugRenderer;

    // Game Objects
    private Player player;
    private Ball ball;

    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        this.camera.position.set(new Vector3((PongGdx.getInstance().getScreenWidth()/2),
                (PongGdx.getInstance().getScreenHeight()/2), 0));
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();

        this.player = new Player(16, PongGdx.getInstance().getScreenHeight()/2, this);
        this.ball = new Ball(this);
    }

    public void update() {
        world.step(1/60f, 6, 2);
        batch.setProjectionMatrix(camera.combined);

        this.player.update();
        this.ball.update();

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
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
        this.player.render(batch);
        this.ball.render(batch);

        batch.end();
    }

    public World getWorld() {
        return world;
    }
}
