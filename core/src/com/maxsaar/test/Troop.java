package com.maxsaar.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by DELL on 1/24/2018.
 */

public class Troop {
    private Random rand = new Random();
    private int totalHP;
    private int size;
    private Texture texmex;
    ArrayList<Unit> troop = new ArrayList<Unit>();

    public Troop(troopNames name, int size) {
        for(int x = 0; x < size; x++) {
            troop.add(createUnit(name, x));
            totalHP += troop.get(troop.size()-1).getHp();
        }
    }

    public Unit createUnit(troopNames name, int xgap) {
        if (name == troopNames.Knight) {
            texmex = new Texture("basic_knight.png");
            Unit knight = new Unit(-50 * xgap, rand.nextInt(Gdx.graphics.getHeight()-128), 50, 50, 2.0, 5, null);
            return knight;
        }
        return null;
    }

    public ArrayList<Unit> getTroop() {
        return troop;
    }

    public Texture getTexmex() {
        return texmex;
    }
}
