package com.maxsaar.game;

/**
 * Created by Max on 23/01/2018.
 */
public class GameStateManager {

    private gameStates currentState;

    //Constructor
    public GameStateManager(){
        currentState = gameStates.MAIN_MENU;
    }

    public void setGameState(gameStates newState){
        currentState = newState;
    }

    public gameStates getGameState(){
        return currentState;
    }
}
