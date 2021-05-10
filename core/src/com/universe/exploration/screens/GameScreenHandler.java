package com.universe.exploration.screens;

import com.badlogic.gdx.Screen;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.screens.mainmenu.MainMenuScreen;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class GameScreenHandler {

    private GameScreen currentScreen;

    private GameScreen targetScreen;

    private UniverseExploration universeExploration;

    private Map<GameScreen, Supplier<Screen>> transitionMap;

    public GameScreenHandler(UniverseExploration universeExploration) {
        this.universeExploration = universeExploration;

        transitionMap = new HashMap<>();
        transitionMap.put(GameScreen.MAIN_MENU, () -> new MainMenuScreen(universeExploration));
    }

    public void handleScreenTransition() {
        transitionMap.forEach((key, value) -> {
            if (targetScreen == key && currentScreen != key) {
                universeExploration.setScreenWithId(value.get(), key);
            }
        });
    }

    public GameScreen getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(GameScreen currentScreen) {
        this.currentScreen = currentScreen;
    }

    public GameScreen getTargetScreen() {
        return targetScreen;
    }

    public void setTargetScreen(GameScreen targetScreen) {
        this.targetScreen = targetScreen;
    }
}
