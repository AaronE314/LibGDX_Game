package com.gmae.main.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.gmae.main.handlers.Contact;

import static com.gmae.main.screen.GameScreen.WORLD_TO_BOX;

public abstract class StaticEntity {

    protected Texture texture;
    protected Vector2 pos;
    protected BodyDef staticEntityDef;
    protected Body staticEntityBody;
    protected World world;
    protected float height,width;
    protected String name;

    public StaticEntity(Vector2 pos, World world, float width, float height, String name){
        this.pos = pos;
        this.world = world;
        this.height = height;
        this.width = width;
        this.name = name;
        Create();
    }

    public abstract void  update();

    public void render(SpriteBatch sb){
    }

    public Vector2 getPosition(){
        return pos;
    }


    public void Create(){
        staticEntityDef = new BodyDef();
        staticEntityDef.position.set(pos.scl(WORLD_TO_BOX));
        staticEntityBody = world.createBody(staticEntityDef);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(width * WORLD_TO_BOX, height * WORLD_TO_BOX);
        staticEntityBody.createFixture(groundShape, 0f).setUserData(name);
        groundShape.dispose();
    }

}
