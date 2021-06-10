package com.universe.exploration.screens;

import com.badlogic.gdx.Screen;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.model.GameScreen;
import com.universe.exploration.screens.game.Game;
import com.universe.exploration.screens.mainmenu.MainMenuScreen;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * All screen transition should happen via this class.
 */
public class ScreenHandler {

    private GameScreen currentScreen;

    private GameScreen targetScreen;

    private UniverseExploration universeExploration;

    private Map<GameScreen, Supplier<Screen>> transitionMap;

    public ScreenHandler(UniverseExploration universeExploration) {
        this.universeExploration = universeExploration;

        transitionMap = new HashMap<>();
        transitionMap.put(GameScreen.MAIN_MENU, () -> new MainMenuScreen(universeExploration));
        transitionMap.put(GameScreen.GAME, () -> new Game(universeExploration));
    }

    public void handleScreenTransition() {
        transitionMap.forEach((key, value) -> {
            if (targetScreen == key && currentScreen != key) {
                universeExploration.setScreenWithId(value.get(), key);
            }
        });
    }

    public void setCurrentScreen(GameScreen currentScreen) {
        this.currentScreen = currentScreen;
    }

    /**
     * Setup target screen and start loading necessary assets. {@link #handleScreenTransition()} ensures
     * actual transition once done.
     *
     * @param targetScreen screen to navigate to
     */
    public void navigateToWhenReady(GameScreen targetScreen) {
        this.targetScreen = targetScreen;
        universeExploration.setScreenWithId(new LoadingScreen(universeExploration), GameScreen.INITIAL_LOADING);
        targetScreen.retrieveAssets(universeExploration.getAssetManager());
    }
}
