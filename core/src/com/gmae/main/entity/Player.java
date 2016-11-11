package com.gmae.main.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gmae.main.handlers.Contact;


public class Player extends Entity{

    private Contact contact;
    private int numOfCoins = 0;

    public Player(Vector2 pos, World world, Contact contact) {
        super(pos,world,14,1f,0.3f,0f,"Player", "Player.png");
        this.contact = contact;
    }

    @Override
    public void update() {
        //System.out.println(playerBody.getLinearVelocity());
        if (Gdx.input.isKeyPressed(Input.Keys.A)) //left
            Move(-1f);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) //right
            Move(1f);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && contact.isPlayerOnGround())
            Jump(14);

    }

    public int getCoinNum(){return numOfCoins;}
    public void addCoin(){numOfCoins++;}
    public void removeCoin(){numOfCoins--;}
    public void setCoin(int num) {numOfCoins=num;}
    
}
