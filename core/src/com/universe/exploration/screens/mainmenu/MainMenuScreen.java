package com.universe.exploration.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.button.ButtonFactory;
import com.universe.exploration.component.dialog.Dialog;
import com.universe.exploration.component.dialog.DialogFactory;
import com.universe.exploration.component.dialog.DialogType;
import com.universe.exploration.component.group.VerticalCenteredGroup;
import com.universe.exploration.model.GameScreen;
import com.universe.exploration.screens.AbstractScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Main menu screen.
 */
public class MainMenuScreen extends AbstractScreen {
    public MainMenuScreen(UniverseExploration universeExploration) {
        super(universeExploration);
    }

    @Override
    protected List<Actor> addActors() {
        java.util.List<Actor> actors = new ArrayList();
        actors.add(createMainMenuButtons());
        return actors;
    }

    private VerticalGroup createMainMenuButtons() {
        VerticalGroup mainMenuButtonGroup = new VerticalCenteredGroup();

        mainMenuButtonGroup.addActor(createStartGameButton());
        mainMenuButtonGroup.addActor(createSettingsButton());
        mainMenuButtonGroup.addActor(createQuitGameButton());

        return mainMenuButtonGroup;
    }

    TextButton createStartGameButton() {
        Dialog startGameDialog = new DialogFactory().createDialog(DialogType.START_GAME, new PlayerCreationDialog(), ((event, x, y) -> {
            universeExploration.getScreenHandler().navigateToWhenReady(GameScreen.GAME);
        }));
        return new ButtonFactory().createTextButton(UniverseExploration.getLocaliser().get("BTN_START_GAME"), ((event, x, y) -> {
            screenStage.addActor(startGameDialog);
        }));
    }

    TextButton createSettingsButton() {
        Dialog settingsDialog = new DialogFactory().createDialog(DialogType.SETTINGS, new Table(), ((event, x, y) -> {
        }));

        return new ButtonFactory().createTextButton(UniverseExploration.getLocaliser().get("BTN_SETTINGS"), (event, x, y) -> {
            screenStage.addActor(settingsDialog);
        });
    }

    TextButton createQuitGameButton() {
        Dialog quitGameDialog = new DialogFactory().createDialog(DialogType.EXIT_GAME, new Table(), ((event, x, y) -> {
            Gdx.app.exit();
        }));
        return new ButtonFactory().createTextButton(UniverseExploration.getLocaliser().get("BTN_QUIT"), (event, x, y) -> {
            screenStage.addActor(quitGameDialog);
        });
    }
}
