package com.maxsaar.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	int fpsPrinter = 0;
	Unit myKnights[] = new Unit[100];
	@Override
	public void create () {
		Random rand = new Random();
		System.out.println(myKnights[0]);
		for(int x = 0; x < myKnights.length; x++) {
			myKnights[x] = new Unit(new Texture("basic_knight.png"), 0, rand.nextInt(getScreenHeight()-128));
//			myKnights[x].texmex = new Texture("basic_knight.png");
//			myKnights[x].startX = 0;
//			myKnights[x].startY = rand.nextInt(getScreenHeight()-64);
		}
		batch = new SpriteBatch();
	}

	@Override
	public void render () {

		printFps();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		for(Unit x: myKnights){
			batch.begin();
			batch.draw(
					x.texmex,
					x.startX,
					x.startY,
					128,
					128
			);
			if(x.startX >= getScreenWidth()){
				x.startX-=getScreenWidth();
			}else{
				x.startX+=5;
			}
			batch.end();
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Unit y: myKnights) {
			y.texmex.dispose();
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
