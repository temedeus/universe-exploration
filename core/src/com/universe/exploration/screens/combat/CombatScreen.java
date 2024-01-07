package com.universe.exploration.screens.combat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.boardgrid.BoardGrid;
import com.universe.exploration.component.button.ButtonFactory;
import com.universe.exploration.component.dialog.Dialog;
import com.universe.exploration.component.dialog.DialogFactory;
import com.universe.exploration.component.dialog.DialogType;
import com.universe.exploration.controller.game.CombatController;
import com.universe.exploration.model.ActorPosition;
import com.universe.exploration.model.Coordinate;
import com.universe.exploration.model.GameScreen;
import com.universe.exploration.model.gamecharacter.GameCharacter;
import com.universe.exploration.model.gamecharacter.action.CharacterActionMode;
import com.universe.exploration.screens.AbstractScreen;
import com.universe.exploration.screens.mainmenu.PlayerCreationDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombatScreen extends AbstractScreen {
    private CombatController combatController;

    private BoardGrid boardGrid;

    private Button endSurveyButton;
    private Button walkModeButton;
    private Button talkModeButton;
    private Button attackModeButton;

    private Map<GameCharacter, Image> gameCharacterImageMap;


    public CombatScreen(UniverseExploration universeExploration) {
        super(universeExploration);
    }

    @Override
    protected List<Actor> addActors() {
        gameCharacterImageMap = new HashMap<>();
        List<Actor> actors = new ArrayList();

        actors.add(createEndSurveyButton());
        actors.add(createGrid());
        actors.add(attackModeButton());
        actors.add(walkModeButton());
        actors.add(talkModeButton());

        combatController.getPlayerCharacters().forEach(gameCharacter -> actors.add(setupGamecharacter(gameCharacter)));
        combatController.getNpcs().forEach(gameCharacter -> actors.add(setupGamecharacter(gameCharacter)));

        gameCharacterImageMap.forEach((key, value) -> {
            Coordinate coordinate = new Coordinate(key.getCoordinateX(), key.getCoordinateY());
            positionActorToGridCoordinates(coordinate, value);
        });

        return actors;
    }

    @Override
    protected void initialiseControllers(UniverseExploration universeExploration) {
        combatController = new CombatController(universeExploration, this);
    }


    private Actor setupGamecharacter(GameCharacter gameCharacter) {
        Texture texture = universeExploration.getAssetManager().getAsset(gameCharacter.getAsset());

        Image image = new Image(texture);
        image.setTouchable(Touchable.disabled);
        gameCharacterImageMap.put(gameCharacter, image);
        return image;
    }

    private Actor createGrid() {
        boardGrid = new BoardGrid(universeExploration, this.combatController);
        positionActor(boardGrid, ActorPosition.CENTER, 0, 30);
        return boardGrid;
    }

    private Button walkModeButton() {
        walkModeButton = new ButtonFactory().createTextButton(getLocale("BTN_WALK_MODE"), (a, b, c) -> {
        });
        walkModeButton.setWidth(400);
        walkModeButton.setHeight(100);
        walkModeButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        reconfigureSelectedAction(CharacterActionMode.MOVE);
                    }
                });
        anchorOnBottomOfAnotherActor(walkModeButton, boardGrid, 0, 0);
        return walkModeButton;
    }

    private Button talkModeButton() {
        talkModeButton = new ButtonFactory().createTextButton(getLocale("BTN_TALK_MODE"), (a, b, c) -> {
        });
        talkModeButton.setWidth(400);
        talkModeButton.setHeight(100);
        talkModeButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        reconfigureSelectedAction(CharacterActionMode.TALK);
                    }
                });
        anchorOnBottomOfAnotherActor(talkModeButton, boardGrid, 420, 0);
        return talkModeButton;
    }

    private Button attackModeButton() {
        attackModeButton = new ButtonFactory().createTextButton(getLocale("BTN_ATTACK_MODE"), (a, b, c) -> {
        });
        attackModeButton.setWidth(400);
        attackModeButton.setHeight(100);
        attackModeButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        reconfigureSelectedAction(CharacterActionMode.ATTACK);
                    }
                });
        anchorOnBottomOfAnotherActor(attackModeButton, boardGrid, -420, 0);
        return attackModeButton;
    }

    private void reconfigureSelectedAction(CharacterActionMode characterActionMode) {
        if (combatController.getSelectedAction() != characterActionMode) {
            combatController.setSelectedAction(characterActionMode);
            boardGrid.redrawPotentialActionArea();
        }
    }

    private Button createEndSurveyButton() {
        endSurveyButton = new ButtonFactory().createTextButton(getLocale("BTN_SURVEY_PLANET_END"), (a, b, c) -> {
        });
        endSurveyButton.setWidth(600);
        endSurveyButton.setHeight(120);
        endSurveyButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        universeExploration.getScreenHandler().navigateToWhenReady(GameScreen.GAME);
                    }
                });
        positionActor(endSurveyButton, ActorPosition.MIDDLE_TOP, 0, 0);
        return endSurveyButton;
    }

    public void createMoveToActionOnCoordinates(GameCharacter gameCharacter, int gridX, int gridY) {
        Vector2 vector = boardGrid.getCellPosition(gridX, gridY);
        MoveToAction moveAction = new MoveToAction();
        moveAction.setDuration(20);
        moveAction.setPosition(vector.x + 7, vector.y + 7);
        gameCharacterImageMap.get(gameCharacter).addAction(moveAction);
    }


    private void positionActorToGridCoordinates(Coordinate coordinate, Actor actor) {
        Vector2 vector = boardGrid.getCellPosition(coordinate.getX(), coordinate.getY());
        actor.setPosition(vector.x + 7, vector.y + 7);
    }

}