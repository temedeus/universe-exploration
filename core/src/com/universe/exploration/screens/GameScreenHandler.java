package com.universe.exploration.screens;

public class GameScreenHandler {

    private GameScreen currentScreen;

    private GameScreen targetScreen;

    public boolean isTransitionComplete() {
       return this.currentScreen == targetScreen;
    }
}
