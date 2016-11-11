package com.gmae.main.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gmae.main.Physics_Test;


public class Ground extends StaticEntity {

    public Ground(World world) {
        super(new Vector2((Physics_Test.WIDTH / 2), 16f), world, 10000f , 16f, "Ground");
    }

    @Override
    public void update() {
    }
}
