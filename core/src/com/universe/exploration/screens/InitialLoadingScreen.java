package com.universe.exploration.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.group.VerticalCenteredGroup;
import com.universe.exploration.utils.GdxHelper;

public class InitialLoadingScreen implements Screen {
    private UniverseExploration universeExploration;

    private final Skin uiSkin = UniverseExploration.commonUIAssets.getUserInterfaceSkin();

    private Stage loadingScreenStage;

    public InitialLoadingScreen(final UniverseExploration universeExploration) {
        this.universeExploration = universeExploration;
        loadingScreenStage = new Stage(new ScreenViewport());
        loadingScreenStage.addActor(createInitialLoadingScreen());
    }

    private VerticalGroup createInitialLoadingScreen() {
        VerticalGroup loadingScreenGroup = new VerticalCenteredGroup();
        Label loadingLabel = new Label("LOADING", uiSkin);

        loadingScreenGroup.addActor(loadingLabel);

        return loadingScreenGroup;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        loadingScreenStage.act(GdxHelper.getDeltaTime());
        loadingScreenStage.draw();
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
