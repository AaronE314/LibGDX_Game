package com.gmae.main.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {

    private TextureRegion[] frame;
    private float time;
    private float delay;
    private int currentFrame;
    private int timesPlayed;

    public Animation() {

    }
    public Animation(TextureRegion[] frames){
        this(frames, 2/12f);
    }
    public Animation(TextureRegion[] frames, float delay){
        setFrames(frames, delay);
    }

    public void setFrames(TextureRegion[] frames, float delay) {
        this.frame = frames;
        this.delay = delay;
        time = 0;
        currentFrame = 0;
        timesPlayed = 0;
    }

    public void update(float dt){
        if(delay <= 0) return;
        time += dt;
        while (time >= delay){
            step();
        }
    }

    private void step() {
        time -=delay;
        currentFrame++;
        if(currentFrame == frame.length){
            currentFrame = 0;
            timesPlayed++;
        }
    }

    public TextureRegion getFrame() { return frame[currentFrame];}
    public int getTimesPlayed() { return timesPlayed;}

}
