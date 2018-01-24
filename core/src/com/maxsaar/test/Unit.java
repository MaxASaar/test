package com.maxsaar.test;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Max on 23/01/2018.
 */

public class Unit {
    public Texture texmex;
    public int startY;
    public int startX;
    public Unit(Texture tex, int startX, int startY) {
        texmex = tex;
        this.startY = startY;
        this.startX = startX;
    }

    public Unit(){
        texmex = null;
        startY = 0;
        startX = 0;
    }

}
