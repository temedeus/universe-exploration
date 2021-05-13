package com.universe.exploration.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.button.ButtonFactory;
import com.universe.exploration.component.dialog.Dialog;
import com.universe.exploration.component.dialog.DialogFactory;
import com.universe.exploration.component.dialog.DialogType;
import com.universe.exploration.component.group.VerticalCenteredGroup;
import com.universe.exploration.model.crew.CrewMember;
import com.universe.exploration.model.crew.CrewMemberStatus;
import com.universe.exploration.screens.GameScreen;
import com.universe.exploration.utils.GdxHelper;

import java.util.ArrayList;
import java.util.List;

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
        VerticalGroup mainMenuButtonGroup = new VerticalCenteredGroup();

        mainMenuButtonGroup.addActor(createStartGameButton());
        mainMenuButtonGroup.addActor(createSettingsButton());
        mainMenuButtonGroup.addActor(createQuitGameButton());

        return mainMenuButtonGroup;
    }

    TextButton createStartGameButton() {
        List<CrewMember> crewMembers = new ArrayList<>();
        CrewMember crewMember = new CrewMember();
        crewMember.setName("Pelle");
        crewMember.setStatus(CrewMemberStatus.ALIVE);
        crewMembers.add(crewMember);
        crewMembers.add(crewMember);
        crewMembers.add(crewMember);
        Dialog startGameDialog = new DialogFactory().createDialog(DialogType.START_GAME, new CrewMemberCreationDialog(crewMembers), ((event, x, y) -> {
            universeExploration.getScreenHandler().navigateToWhenReady(GameScreen.GAME);
        }));
        return new ButtonFactory().createTextButton(universeExploration.getLocaliser().get("BTN_START_GAME"), ((event, x, y) -> {
            mainMenuStage.addActor(startGameDialog);
        }));
    }

    TextButton createSettingsButton() {
        Dialog settingsDialog = new DialogFactory().createDialog(DialogType.SETTINGS, new Table(), ((event, x, y) -> {
        }));
        TextButton settings = new ButtonFactory().createTextButton(universeExploration.getLocaliser().get("BTN_SETTINGS"), (event, x, y) -> {
            mainMenuStage.addActor(settingsDialog);
        });

        return settings;
    }

    TextButton createQuitGameButton() {
        Dialog quitGameDialog = new DialogFactory().createDialog(DialogType.EXIT_GAME, new Table(), ((event, x, y) -> {
            Gdx.app.exit();
        }));
        return new ButtonFactory().createTextButton(universeExploration.getLocaliser().get("BTN_QUIT"), (event, x, y) -> {
            mainMenuStage.addActor(quitGameDialog);
        });
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
