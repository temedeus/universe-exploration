package com.universe.exploration.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.button.ButtonFactory;
import com.universe.exploration.component.dialog.Dialog;
import com.universe.exploration.component.dialog.DialogFactory;
import com.universe.exploration.component.dialog.DialogType;
import tools.GdxHelper;

/**
 * Main menu screen.
 */
public class MainMenuScreen implements Screen {

    private Stage mainMenuStage;

    private UniverseExploration universeExploration;

    public MainMenuScreen(final UniverseExploration universeExploration) {
        this.universeExploration = universeExploration;
        mainMenuStage = new Stage(new ScreenViewport());
        mainMenuStage.addActor(createMainMenuButtons());

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(mainMenuStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private VerticalGroup createMainMenuButtons() {
        VerticalGroup mainMenuButtonGroup = new VerticalGroup();
        mainMenuButtonGroup.align(Align.center | Align.center);
        mainMenuButtonGroup.setPosition(GdxHelper.getScreenCenterX(), GdxHelper.getScreenCenterY());
        mainMenuButtonGroup.padTop(30);
        mainMenuButtonGroup.padRight(30);
        Dialog dialog = new DialogFactory().createDialog(DialogType.QUIT_WINDOW, new Table(), ((event, x, y) -> { Gdx.app.exit();}));
        TextButton startGame = new ButtonFactory().createTextButton("Start Game", ((event, x, y) -> {}));
        TextButton settings = new ButtonFactory().createTextButton("Settings", (event, x, y) -> {});
        TextButton quit = new ButtonFactory().createTextButton("Quit", (event, x, y) -> { mainMenuStage.addActor(dialog); });

        mainMenuButtonGroup.addActor(startGame);
        mainMenuButtonGroup.addActor(settings);
        mainMenuButtonGroup.addActor(quit);

        return  mainMenuButtonGroup;
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
