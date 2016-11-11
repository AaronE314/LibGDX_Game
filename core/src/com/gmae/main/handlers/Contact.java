package com.gmae.main.handlers;

import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class Contact implements ContactListener {

    private int numOfContacts = 0;

    public void beginContact(com.badlogic.gdx.physics.box2d.Contact c) {

        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
            numOfContacts++;

        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
            numOfContacts++;
        }



    }

    @Override
    public void endContact(com.badlogic.gdx.physics.box2d.Contact c) {

        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
            numOfContacts--;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
            numOfContacts--;
        }

    }

    public boolean isPlayerOnGround() { return numOfContacts >0; }

    @Override
    public void preSolve(com.badlogic.gdx.physics.box2d.Contact contact,Manifold oldManifold) {


    }

    @Override
    public void postSolve(com.badlogic.gdx.physics.box2d.Contact contact,ContactImpulse impulse) {


    }

}