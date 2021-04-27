package com.universe.exploration.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.universe.exploration.UniverseExploration;

public class MainMenuScreen implements Screen {
    Texture img;

    private UniverseExploration universeExploration;

    public MainMenuScreen(final UniverseExploration universeExploration) {
        this.universeExploration = universeExploration;
        img = new Texture("images/planets/earthlike.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        universeExploration.getBatch().begin();
        universeExploration.getBatch().draw(img, 0, 0);
        universeExploration.getBatch().end();
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
        img.dispose();
    }
}
