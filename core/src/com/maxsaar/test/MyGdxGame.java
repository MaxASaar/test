package com.maxsaar.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	int fpsPrinter = 0;
	ArrayList<Troop> ally = new ArrayList<Troop>();
	ArrayList<Troop> enemy = new ArrayList<Troop>();
	@Override
	public void create () {
		ally.add(new Troop(troopNames.Knight, 50));
		batch = new SpriteBatch();
	}

	@Override
	public void render () {

		printFps();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Troop x: ally) {
			for (Unit u: x.getTroop()) {
				batch.draw(
						x.getTexmex(),
						u.getX(),
						u.getY(),
						128,
						128
				);
				if(u.getX() >= getScreenWidth()){
					u.setX(u.getX()-getScreenWidth() - 128);
				}else{
					u.move();
				}
			}
		}
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Troop x: ally) {
			x.getTexmex().dispose();
		}
	}

	public static int getScreenWidth() {
		return Gdx.graphics.getWidth();
	}

	public static int getScreenHeight() {
		return Gdx.graphics.getHeight();
	}

	public void printFps() {
		fpsPrinter++;
		if(fpsPrinter >= 60){
			System.out.println(Gdx.graphics.getFramesPerSecond());
			fpsPrinter = 0;
		}
		//Want to be able to put fps in top right of screen
	}
}
