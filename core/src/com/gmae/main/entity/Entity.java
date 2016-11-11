package com.gmae.main.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.gmae.main.handlers.Content;

import static com.gmae.main.screen.GameScreen.BOX_TO_WORLD;
import static com.gmae.main.screen.GameScreen.WORLD_TO_BOX;

public abstract class Entity{

    protected Texture texture;
    protected Vector2 pos;
    protected BodyDef playerDef;
    protected Body playerBody;
    protected World world;
    protected int size;
    protected float dens, fric, rest;
    protected String name;
    protected Sprite sprite;
    Content content = new Content();

    public Entity(Vector2 pos, World world, int size, float dens, float fric, float rest, String name, String texture){
        this.pos = pos;
        this.world = world;
        this.size = size;
        this.dens = dens;
        this.fric = fric;
        this.rest = rest;
        this.name = name;
        if(texture != null) {
            this.texture = new Texture(texture);
            if(this.texture == null){
                System.out.println("Error loading image");
            }
            //this.texture.dispose();
        }
        Create();
    }

    public abstract void  update();

    public void render(SpriteBatch sb){

        sb.begin();
        sb.draw(texture,
                playerBody.getPosition().x * BOX_TO_WORLD - texture.getWidth()/2,
                playerBody.getPosition().y * BOX_TO_WORLD - texture.getHeight()/2);
        sb.end();

    }

    public void Move(float forceX){
        if(playerBody.getLinearVelocity().x < 5 && playerBody.getLinearVelocity().x > -5)
            playerBody.applyForceToCenter(forceX,0,true);
    }
    public void Jump(float forceY) {
        playerBody.applyForceToCenter(0,forceY,true);
    }

    public Vector2 getPosition(){
        return playerBody.getPosition().scl(BOX_TO_WORLD);
    }

    public void setPos(float x, float y) {playerBody.setTransform(x * WORLD_TO_BOX,y * WORLD_TO_BOX,0);}

    public void Create(){
        playerDef = new BodyDef();
        playerDef.type = BodyDef.BodyType.DynamicBody;
        playerDef.position.set(pos.scl(WORLD_TO_BOX));
        playerDef.fixedRotation = true;
        playerBody = world.createBody(playerDef);

        PolygonShape playerShape = new PolygonShape();
        playerShape.setAsBox(size * WORLD_TO_BOX, size * WORLD_TO_BOX);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = playerShape;
        fixtureDef.density = dens;
        fixtureDef.friction = fric;
        fixtureDef.restitution = rest;

        playerBody.createFixture(fixtureDef).setUserData(name);
        playerBody.setUserData(sprite);

        //Sensor
        playerShape.setAsBox(size/1.2f * WORLD_TO_BOX, size * WORLD_TO_BOX/4, new Vector2(0, -size*WORLD_TO_BOX),0);
        fixtureDef.shape= playerShape;
        fixtureDef.isSensor = true;
        //fixtureDef.friction = fric;
        playerBody.createFixture(fixtureDef).setUserData("foot");

        playerShape.dispose();
    }

}
