package com.fdahl.apps.ponggdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.fdahl.apps.ponggdx.helper.ContactType;

public class GameContactListener implements ContactListener {

    private GameScreen gameScreen;
    private Sound pongBeep;
    private Sound lowBeep;

    public GameContactListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.pongBeep = Gdx.audio.newSound(Gdx.files.internal("sounds/pong_beep.wav"));
        this.lowBeep = Gdx.audio.newSound(Gdx.files.internal("sounds/low_beep.wav"));
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(fixtureA == null || fixtureB == null) {
            return;
        }

        if(fixtureA.getUserData() == null || fixtureB.getUserData() == null) {
            return;
        }

        if(fixtureA.getUserData() == ContactType.BALL || fixtureB.getUserData() == ContactType.BALL) {
            // We know that one of the fixtures is a ball, now check to see if the other is a player paddle
            if(fixtureA.getUserData() == ContactType.PLAYER || fixtureB.getUserData() == ContactType.PLAYER) {
                pongBeep.play();
                gameScreen.getBall().reverseVelX();
                gameScreen.getBall().incSpeed();
            }

            // Check if other fixture is wall
            if(fixtureA.getUserData() == ContactType.WALL || fixtureB.getUserData() == ContactType.WALL) {
                lowBeep.play();
                gameScreen.getBall().reverseVelY();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
