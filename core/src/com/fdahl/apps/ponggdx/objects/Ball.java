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

        this.texture = new Texture("color.png");
        this.width = 32;
        this.height = 32;

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

        // Update scoring here
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
}
