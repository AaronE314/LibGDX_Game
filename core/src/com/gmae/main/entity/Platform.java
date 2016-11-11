package com.gmae.main.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gmae.main.handlers.Contact;

public class Platform extends StaticEntity{

    public Platform(Vector2 pos, World world, float width, float height, Contact contact) {
        super(pos, world, width, height, "Platform");
    }

    @Override
    public void update() {

    }
}
