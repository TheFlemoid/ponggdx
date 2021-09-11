package com.fdahl.apps.ponggdx.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.fdahl.apps.ponggdx.views.GameScreen;
import com.fdahl.apps.ponggdx.PongGdx;
import com.fdahl.apps.ponggdx.helper.BodyHelper;
import com.fdahl.apps.ponggdx.helper.ContactType;

public class Wall {
    private Body body;
    private float x, y;
    private int width, height;
    private Texture texture;

    public Wall(float y, GameScreen gameScreen) {
        this.x = PongGdx.getInstance().getScreenWidth() / 2;
        this.y = y;
        this.width = PongGdx.getInstance().getScreenWidth();
        this.height = 64;

        this.texture = new Texture("sprites/color.png");
        this.body = BodyHelper.createBody(x, y, width, height, true, 0, gameScreen.getWorld(), ContactType.WALL);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - (width/2), y - (height/2), width, height);
    }
}
