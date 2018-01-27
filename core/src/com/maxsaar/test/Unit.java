package com.maxsaar.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

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
    private Body body;
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

    public void createBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(X, Y);
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(64, 64);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.001f;
        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();
    }

    public Body getBody() {
        return body;
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

    public void move(int right) {
        if (right == 0) {
            body.applyLinearImpulse(-1000.0f, 0, body.getPosition().x, body.getPosition().y, true);
        }
        else {
            body.applyLinearImpulse(1000.0f, 0, body.getPosition().x, body.getPosition().y, true);
        }
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
