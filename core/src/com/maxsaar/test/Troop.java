package com.maxsaar.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

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

    public Troop(troopNames name, int size, int right) {
        for(int x = 0; x < size; x++) {
            troop.add(createUnit(name, x, right));
            totalHP += troop.get(troop.size()-1).getHp();
        }
    }

    public Unit createUnit(troopNames name, int xgap, int right) {
        if (name == troopNames.Knight) {
            texmex = new Texture("basic_knight.png");
            Unit knight;
            if (right == 0) {
                knight = new Unit(Gdx.graphics.getWidth()+ 50* xgap, rand.nextInt(Gdx.graphics.getHeight()-128), 50, 50, 2.0, 5, null);
            }else {
                knight = new Unit(-50 * xgap, rand.nextInt(Gdx.graphics.getHeight()-128), 50, 50, 2.0, 5, null);
            }
            return knight;
        }
        return null;
    }

    public void createBodies(World world) {
        for (Unit x: troop) {
            x.createBody(world);
        }
    }

    public ArrayList<Unit> getTroop() {
        return troop;
    }

    public Texture getTexmex() {
        return texmex;
    }
}
