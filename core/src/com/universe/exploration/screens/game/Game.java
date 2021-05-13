package com.universe.exploration.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.utils.GdxHelper;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public class Game implements Screen {
    private UniverseExploration universeExploration;

    private Stage mainMenuStage;

    public Game(final UniverseExploration universeExploration) {
        this.universeExploration = universeExploration;
        mainMenuStage = new Stage(new ScreenViewport());

        mainMenuStage.addActor(createPlanets());

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(mainMenuStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private Image createPlanets() {
        Texture planetTexture = universeExploration.getAssetManager().getAsset(PlanetAssetProvider.PlanetAsset.EARTHLIKE);
        Image earthLike = new Image(planetTexture);

       // earthLike.setPosition(GdxHelper.getScreenCenterX(), GdxHelper.getScreenCenterY());
        return earthLike;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainMenuStage.act(GdxHelper.getDeltaTime());
        mainMenuStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
