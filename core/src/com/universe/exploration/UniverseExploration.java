package com.universe.exploration;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.universe.exploration.screens.GameScreen;
import com.universe.exploration.screens.InitialLoadingScreen;
import com.universe.exploration.screens.MainMenuScreen;
import com.universe.exploration.utils.LoadingScreenDelayer;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.CommonAssetProvider;

/**
 * Main game class used as an entry point for all game screens.
 */
public class UniverseExploration extends Game {
    private SpriteBatch batch;

    private GameAssetManager assetManager;

    public static CommonUIAssets commonUIAssets;

    private GameScreen gameScreen;

    private GameScreen targetScreen;

    private LoadingScreenDelayer loadingScreenDelayer;

    public void setScreenWithId(Screen screen, GameScreen gameScreen) {
        setScreen(screen);
        this.gameScreen = gameScreen;
    }

    @Override
    public void create() {
        assetManager = new GameAssetManager();
        loadingScreenDelayer = new LoadingScreenDelayer();
        setupBasicUI();
    }

    @Override
    public void render() {
        super.render();
        if (!loadingScreenDelayer.isScreenLoading() && assetManager.getAssetManager().update()) {
            if(targetScreen == GameScreen.MAIN_MENU && gameScreen != GameScreen.MAIN_MENU ) {
                this.setScreenWithId(new MainMenuScreen(this), GameScreen.MAIN_MENU);
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }

    private void setupBasicUI() {
        batch = new SpriteBatch();
        commonUIAssets = new CommonUIAssets();
        new CommonAssetProvider(assetManager).setupAssets();
        assetManager.getAssetManager().finishLoading();
        commonUIAssets.setFont(assetManager.getAssetManager().get(CommonAssetProvider.CommonAsset.BITMAP_FONT.getPath()));
        commonUIAssets.setUserInterfaceSkin(assetManager.getAssetManager().get(CommonAssetProvider.CommonAsset.UI_SKIN.getPath()));
        this.setScreenWithId(new InitialLoadingScreen(this), GameScreen.INITIAL_LOADING);
        targetScreen = GameScreen.MAIN_MENU;
        loadingScreenDelayer.delay();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public GameAssetManager getAssetManager() {
        return assetManager;
    }
}
