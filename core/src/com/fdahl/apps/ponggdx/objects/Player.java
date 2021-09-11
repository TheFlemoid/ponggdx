package com.fdahl.apps.ponggdx.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.fdahl.apps.ponggdx.GameScreen;

public class Player extends PlayerPaddle {
    public Player(float x, float y, GameScreen gameScreen, int scoreXPos, int scoreYPos) {
        super(x, y, gameScreen, scoreXPos, scoreYPos);
    }

    @Override
    public void update() {
        super.update();

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            velY = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            velY = -1;
        }

        body.setLinearVelocity(0, velY * speed);
    }
}
