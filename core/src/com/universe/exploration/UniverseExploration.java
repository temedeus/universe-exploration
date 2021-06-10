package com.universe.exploration;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.universe.exploration.component.asset.CommonUIAssets;
import com.universe.exploration.model.GameScreen;
import com.universe.exploration.screens.ScreenHandler;
import com.universe.exploration.screens.LoadingScreen;
import com.universe.exploration.utils.LoadingScreenDelayer;
import com.universe.exploration.utils.Localiser;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.CommonAssetProvider;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.LocaleAssetProvider;

/**
 * Main game class used as an entry point for all game screens.
 */
public class UniverseExploration extends Game {
    public static CommonUIAssets commonUIAssets;
    private SpriteBatch batch;
    private GameAssetManager assetManager;

    public ScreenHandler getScreenHandler() {
        return screenHandler;
    }

    public void setScreenHandler(ScreenHandler screenHandler) {
        this.screenHandler = screenHandler;
    }

    private ScreenHandler screenHandler;
    private LoadingScreenDelayer loadingScreenDelayer;

    private static Localiser localiser;

    public void setScreenWithId(Screen screen, GameScreen gameScreen) {
        setScreen(screen);
        screenHandler.setCurrentScreen(gameScreen);
    }

    @Override
    public void create() {
        assetManager = new GameAssetManager();
        loadingScreenDelayer = new LoadingScreenDelayer();
        screenHandler = new ScreenHandler(this);
        setupCommonAssets();
        localiser = Localiser.getInstance(assetManager);
    }

    @Override
    public void render() {
        super.render();
        if (!loadingScreenDelayer.isScreenLoading() && assetManager.getAssetManager().update()) {
            screenHandler.handleScreenTransition();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }

    public static Localiser getLocaliser() {
        return localiser;
    }

    private void setupCommonAssets() {
        batch = new SpriteBatch();
        commonUIAssets = new CommonUIAssets();
        new CommonAssetProvider(assetManager).setupAssets();
        new LocaleAssetProvider(assetManager).setupAssets();
        assetManager.getAssetManager().finishLoading();
        commonUIAssets.setFont(assetManager.getAsset(CommonAssetProvider.CommonAsset.BITMAP_FONT));
        commonUIAssets.setUserInterfaceSkin(assetManager.getAsset(CommonAssetProvider.CommonAsset.UI_SKIN));
        screenHandler.navigateToWhenReady(GameScreen.MAIN_MENU);
        loadingScreenDelayer.delay();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public GameAssetManager getAssetManager() {
        return assetManager;
    }
}
