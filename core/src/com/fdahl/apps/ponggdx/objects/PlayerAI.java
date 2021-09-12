package com.fdahl.apps.ponggdx.objects;

import com.fdahl.apps.ponggdx.views.GameScreen;

public class PlayerAI extends PlayerPaddle {

    public PlayerAI(float x, float y, GameScreen gameScreen, int scoreXPos, int scoreYPos) {
        super(x, y, gameScreen, scoreXPos, scoreYPos);
    }

    @Override
    public void update() {
        super.update();

        // Enemy AI here
        Ball ball = gameScreen.getBall();
        if(ball.getY() + 10 > this.y && ball.getY() - 10 > this.y) {
            velY = 1;
        } else if(ball.getY() + 10 < this.y && ball.getY() - 10 < this.y) {
            velY = -1;
        } else {
            velY = 0;
        }

        body.setLinearVelocity(0, velY * speed);
    }

    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }
}
