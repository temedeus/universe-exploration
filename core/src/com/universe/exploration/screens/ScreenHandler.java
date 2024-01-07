package com.universe.exploration.screens;

import com.badlogic.gdx.Screen;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.model.GameScreen;
import com.universe.exploration.screens.combat.CombatScreen;
import com.universe.exploration.screens.planetselection.PlanetSelectionScreen;
import com.universe.exploration.screens.mainmenu.MainMenuScreen;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * All screen transition should happen via this class.
 */
public class ScreenHandler {

    private com.universe.exploration.model.GameScreen currentScreen;

    private com.universe.exploration.model.GameScreen targetScreen;

    private UniverseExploration universeExploration;

    private Map<com.universe.exploration.model.GameScreen, Supplier<Screen>> transitionMap;

    public ScreenHandler(UniverseExploration universeExploration) {
        this.universeExploration = universeExploration;

        transitionMap = new HashMap<>();
        transitionMap.put(com.universe.exploration.model.GameScreen.MAIN_MENU, () -> new MainMenuScreen(universeExploration));
        transitionMap.put(com.universe.exploration.model.GameScreen.GAME, () -> new PlanetSelectionScreen(universeExploration));
        transitionMap.put(GameScreen.COMBAT, () -> new CombatScreen(universeExploration));

    }

    public void handleScreenTransition() {
        if (currentScreen != targetScreen) {
            universeExploration.setScreenWithId(transitionMap.get(targetScreen).get(), targetScreen);
        }
    }

    public void setCurrentScreen(com.universe.exploration.model.GameScreen currentScreen) {
        this.currentScreen = currentScreen;
    }

    /**
     * Setup target screen and start loading necessary assets. {@link #handleScreenTransition()} ensures
     * actual transition once done.
     *
     * @param targetScreen screen to navigate to
     */
    public void navigateToWhenReady(com.universe.exploration.model.GameScreen targetScreen) {
        this.targetScreen = targetScreen;
        universeExploration.setScreenWithId(new LoadingScreen(universeExploration), com.universe.exploration.model.GameScreen.INITIAL_LOADING);
        targetScreen.retrieveAssets(universeExploration.getAssetManager());
    }
}
