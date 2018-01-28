package com.maxsaar.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter implements GestureDetector.GestureListener {
	gameStates currentState; // the state of the game
	SpriteBatch batch;
	int fpsPrinter = 0;
	ArrayList<Troop> ally = new ArrayList<Troop>();
	ArrayList<Troop> enemy = new ArrayList<Troop>();
	World world;
	static final int WORLD_WIDTH = 3840;
	static final int WORLD_HEIGHT = 1080;
	OrthographicCamera cam;
	Sprite mapSprite;
	@Override
	public void create () {
		currentState = gameStates.MAIN_MENU; // starts at the main menu when load app
		//Makes libgdx handle gestures
		Gdx.input.setInputProcessor(new GestureDetector(this));
		//Sets the background
		mapSprite = new Sprite(new Texture("background.png"));
		mapSprite.setPosition(0,0);
		mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
		//Creates cam that is 1920x1080 (1 screen)
		cam = new OrthographicCamera(1920, 1080);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		cam.update();

		ally.add(new Troop(troopNames.Knight, 50, 1));
		enemy.add(new Troop(troopNames.Knight, 2, 0));
		world = new World(new Vector2(0, 0), true);
		//Sets the default body for each unit
		for (Troop x: ally) {
			x.createBodies(world);
		}
		for (Troop x: enemy) {
			x.createBodies(world);
		}
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		printFps();
		handleCamera();
		cam.update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (currentState == gameStates.GAME) {
      world.step(1/60f, 6, 2);
      batch.setProjectionMatrix(cam.combined);
      batch.begin();
      mapSprite.draw(batch);
      for (Troop x: ally) {
        for (Unit u: x.getTroop()) {
          batch.draw(
              x.getTexmex(),
              u.getBody().getPosition().x,
              u.getBody().getPosition().y,
              64,
              64
          );
          //Find closest enemy to unit u
          Unit closestEnemy = null;
          double closestDistance = -1;
          for (Troop y: enemy) {
            for (Unit e: y.getTroop()) {
              double distance = Math.sqrt(Math.pow((e.getBody().getPosition().x - u.getBody().getPosition().x),2) + Math.pow((e.getBody().getPosition().y - u.getBody().getPosition().y),2));
              if (closestDistance == -1) {
                closestDistance = distance;
              }
              System.out.println("Distance " + distance);
              if (distance < 1000 && distance > 200) {
                System.out.println("Distance < 1000");
                u.changeState(unitState.AGGRO);
                if (closestDistance > distance) {
                  closestDistance = distance;
                  closestEnemy = e;
                }
              }
              else if (distance <= 200) {
                System.out.println("Distance < 200");
                //Point of no return will only change target if enemy dies
                u.changeState(unitState.AGGRO);
                closestDistance = distance;
                closestEnemy = e;
                u.setEnemyTarget(e);
              }
            }
          }
          int xx = 0;
          int yy = 0;
          if (closestEnemy != null && u.getEnemyTarget() != null) {
            if((u.getEnemyTarget().getBody().getPosition().x - u.getBody().getPosition().x) < 0){
              //Enemy is on the left
              xx = -1;
            }
            else {
              //Enemy is on the right
              xx = 1;
            }
            if ((u.getEnemyTarget().getBody().getPosition().y - u.getBody().getPosition().y) < 0) {
              //Enemy is on bottom
              yy = -1;
            }
            else {
              //Enemy is on top
              yy = 1;
            }
          }
          else if (closestEnemy != null && u.getEnemyTarget() == null) {
            if((closestEnemy.getBody().getPosition().x - u.getBody().getPosition().x) < 0){
              //Enemy is on the left
              xx = -1;
            }
            else {
              //Enemy is on the right
              xx = 1;
            }
            if ((closestEnemy.getBody().getPosition().y - u.getBody().getPosition().y) < 0) {
              //Enemy is on bottom
              yy = -1;
            }
            else {
              //Enemy is on top
              yy = 1;
            }
          }
          u.move(1, xx, yy);
        }
      }
      for (Troop x: enemy) {
        for (Unit u: x.getTroop()) {
          batch.draw(
              x.getTexmex(),
              u.getBody().getPosition().x,
              u.getBody().getPosition().y,
              64,
              64, 0, 0, 16, 16, true, false
          );
          u.move(0, 0, 0);
        }
			}
			batch.end();
		}else if(currentState == gameStates.MAIN_MENU){
			//xd
		}
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

	public void handleCamera() {

//		cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, WORLD_HEIGHT/cam.viewportWidth);
//		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
//		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
		cam.position.x = MathUtils.clamp(cam.position.x, 1920/2f, 3840 - 1920/2f);
		cam.position.y = MathUtils.clamp(cam.position.y, 1080/2f, 1080/2f);
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		cam.translate(-deltaX, 0);
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if (currentState == gameStates.MAIN_MENU){
			currentState = gameStates.GAME;
		}
		else if (currentState == gameStates.GAME){
			currentState = gameStates.MAIN_MENU;
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
						 Vector2 pointer1, Vector2 pointer2) {
		return true;
	}

	@Override
	public void pinchStop(){

	}

}
