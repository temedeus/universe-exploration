package com.universe.exploration;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.universe.exploration.screens.InitialLoadingScreen;
import com.universe.exploration.screens.MainMenuScreen;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.CommonAssetProvider;

/**
 * Main game class used as an entry point for all game screens.
 */
public class UniverseExploration extends Game {
    private SpriteBatch batch;

    private GameAssetManager assetManager;

    public static BasicUI basicUI;

    private boolean loadingInitially = true;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetManager = new GameAssetManager();
        basicUI = new BasicUI();
        new CommonAssetProvider(assetManager).setupAssets();
        assetManager.getAssetManager().finishLoading();
        this.setScreen(new InitialLoadingScreen(this));
    }

    @Override
    public void render() {
        super.render();
        if (loadingInitially && assetManager.getAssetManager().update()) {
            basicUI.setFont(assetManager.getAssetManager().get(CommonAssetProvider.CommonAsset.BITMAP_FONT.getPath()));
            basicUI.setUserInterfaceSkin(assetManager.getAssetManager().get(CommonAssetProvider.CommonAsset.UI_SKIN.getPath()));
            this.setScreen(new MainMenuScreen(this));
            loadingInitially = false;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public GameAssetManager getAssetManager() {
        return assetManager;
    }
}
