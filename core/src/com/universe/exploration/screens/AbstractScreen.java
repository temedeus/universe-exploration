package com.universe.exploration.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.utils.GdxHelper;

import java.util.List;

public abstract class AbstractScreen implements Screen {
    protected Stage screenStage;

    protected UniverseExploration universeExploration;

    public AbstractScreen(final UniverseExploration universeExploration) {
        this.universeExploration = universeExploration;
        screenStage = new Stage(new ScreenViewport());
        addActors().forEach(actor -> screenStage.addActor(actor));
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(screenStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    protected abstract List<Actor> addActors();

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        screenStage.act(GdxHelper.getDeltaTime());
        screenStage.draw();
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
