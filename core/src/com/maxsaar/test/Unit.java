package com.maxsaar.test;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Max on 23/01/2018.
 */

public class Unit {
    private int Y;
    private int X;
    private int hp;
    private int attack;
    private double attackSpd; //Attacks per Second
    private int moveSpd; //Pixels per Frame
    private ArrayList<Buff> buffs = new ArrayList<Buff>();
    public Unit(int X, int Y, int hp, int attack, double attackSpd, int moveSpd, Buff[] buffs) {
        this.Y = Y;
        this.X = X;
        this.hp = hp;
        this.attack = attack;
        this.attackSpd = attackSpd;
        this.moveSpd = moveSpd;
        if (buffs != null) {
            for (int x = 0; x < buffs.length; x++) {
                this.buffs.add(buffs[x]);
            }
        }
    }

    public void addBuff(Buff b) {
        buffs.add(b);
    }

    public int checkBuffs() {
        int counter = 0;
        for (int x = 0; x < buffs.size(); x++) {
            if (buffs.get(x).expired()) {
                buffs.remove(x);
                counter++;
            }
        }
        return counter; //number of buffs removed
    }

    public Buff[] getBuffs() {
        Buff[] temp = new Buff[buffs.size()];
        return buffs.toArray(temp);
    }

    public void removeAllBuffs() {
        this.buffs.clear();
    }

    public void move() {
        X += moveSpd;
    }

    public int getY() {
        return Y;
    }

    public int getX(){
        return X;
    }

    public int getHp(){
        return hp;
    }

    public int getAttack(){
        return attack;
    }

    public double getAttackSpd(){
        return attackSpd;
    }

    public int getMoveSpd(){
        return moveSpd;
    }

    public void setX(int newx) {
        X = newx;
    }

    public void setY(int newy) {
        Y = newy;
    }

    public void setHp(int newy) {
        hp = newy;
    }

    public void setAttack(int newy) {
        attack = newy;
    }

    public void setAttackSpd(double newy) {
        attackSpd = newy;
    }

    public void setMoveSpd(int newy) {
        moveSpd = newy;
    }

}
