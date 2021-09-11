package com.fdahl.apps.ponggdx.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.fdahl.apps.ponggdx.GameScreen;
import com.fdahl.apps.ponggdx.PongGdx;
import com.fdahl.apps.ponggdx.helper.BodyHelper;
import com.fdahl.apps.ponggdx.helper.Const;
import com.fdahl.apps.ponggdx.helper.ContactType;

public class Ball {
    private Body body;
    private float x;
    private float y;
    private float speed;
    private float velX;
    private float velY;
    private int width;
    private int height;
    private GameScreen gameScreen;
    private Texture texture;

    public Ball(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.x = PongGdx.getInstance().getScreenWidth() / 2;
        this.y = PongGdx.getInstance().getScreenHeight() / 2;
        this.speed = 5;
        this.velX = getRandomDirection();
        this.velY = getRandomDirection();

        this.texture = new Texture("spritesheets/color.png");
        this.width = 20;
        this.height = 20;

        this.body = BodyHelper.createBody(x, y, width, height, false, 0,
                gameScreen.getWorld(), ContactType.BALL);
    }

    private float getRandomDirection() {
        return (Math.random() < 0.5) ? 1 : -1;
    }

    public void update() {
        x = body.getPosition().x * Const.PPM - (width/2);
        y = body.getPosition().y * Const.PPM - (height/2);

        this.body.setLinearVelocity(velX * speed, velY * speed);

        // Scoring
        if(x < 0) {
            // Player AI has scored
            gameScreen.getPlayerAi().score();
            reset();
        }

        if(x > PongGdx.getInstance().getScreenWidth()) {
            // Player has scored
            gameScreen.getPlayer().score();
            reset();
        }
    }

    public void reset() {
        this.velX = this.getRandomDirection();
        this.velY = this.getRandomDirection();
        this.speed = 5;
        this.body.setTransform(PongGdx.getInstance().getScreenWidth() / 2 / Const.PPM,
                               PongGdx.getInstance().getScreenHeight() / 2 / Const.PPM, 0);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    /**
     * Reverse the balls X velocity
     */
    public void reverseVelX() {
        this.velX *= -1;
    }

    /**
     * Reverse the balls Y velocity
     */
    public void reverseVelY() {
        this.velY *= -1;
    }

    /**
     * Increase speed of ball by 10%
     */
    public void incSpeed() {
        speed *= 1.1f;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
