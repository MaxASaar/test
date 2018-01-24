package com.maxsaar.test;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by DELL on 1/24/2018.
 */

public class Buff {
    private double startTime; //In Actual Time, nanoS
    private double duration; //In Actual Time, Seconds
    private Texture texmex;
    public Buff(buffNames name) {
        startTime = System.nanoTime();
        if (name == buffNames.Garrison_Haste) {
            duration = 5.0;
        }
    }

    public boolean expired() {
        if (System.nanoTime() - startTime >= duration * (Math.pow(10, 9))){
            return true;
        }
        return false;
    }
    public double getDuration() {
        return duration;
    }
}
