package com.universe.exploration.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.boardgrid.BoardGrid;
import com.universe.exploration.component.button.ButtonFactory;
import com.universe.exploration.controller.game.GameController;
import com.universe.exploration.controller.game.planetselection.PlanetSelectionController;
import com.universe.exploration.model.ActorPosition;
import com.universe.exploration.model.Coordinate;
import com.universe.exploration.model.gamecharacter.GameCharacter;
import com.universe.exploration.model.gamecharacter.action.CharacterActionMode;
import com.universe.exploration.screens.AbstractScreen;
import com.universe.exploration.utils.GdxHelper;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.HudAssetProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameScreen extends AbstractScreen {
    private GameController gameController;
    private PlanetSelectionController planetSelectionController;

    private BoardGrid boardGrid;
    private ImageButton leftButton;
    private ImageButton rightButton;
    private Button surveyButton;
    private Button endSurveyButton;
    private Button walkModeButton;
    private Button talkModeButton;
    private Button attackModeButton;

    private Map<GameCharacter, Image> gameCharacterImageMap;


    public GameScreen(UniverseExploration universeExploration) {
        super(universeExploration);
    }

    @Override
    protected List<Actor> addActors() {
        gameCharacterImageMap = new HashMap<>();

        List<Actor> actors = new ArrayList(planetSelectionController.getPlanets());
        actors.add(createLeftButton());
        actors.add(createRightButton());
        actors.add(createSurveyButton());
        actors.add(createEndSurveyButton());
        actors.add(createGrid());
        actors.add(attackModeButton());
        actors.add(walkModeButton());
        actors.add(talkModeButton());
        planetSelectionController.moveSelectedPlanetRight();
        gameController.getPlayerCharacters().forEach(gameCharacter -> actors.add(setupGamecharacter(gameCharacter)));
        gameController.getNpcs().forEach(gameCharacter -> actors.add(setupGamecharacter(gameCharacter)));

        return actors;
    }

    @Override
    protected void initialiseControllers(UniverseExploration universeExploration) {
        planetSelectionController = new PlanetSelectionController(universeExploration);
        gameController = new GameController(universeExploration, this, planetSelectionController.getPlanets());
    }

    private ImageButton createLeftButton() {
        Texture left = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.ARROW_LEFT);
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(left));
        leftButton = new ImageButton(drawable);
        leftButton.setPosition(0, GdxHelper.getScreenCenterY() - leftButton.getHeight() / 2);
        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               planetSelectionController.moveSelectedPlanetLeft();
            }
        });
        return leftButton;
    }

    private ImageButton createRightButton() {
        Texture left = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.ARROW_RIGHT);
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(left));
        rightButton = new ImageButton(drawable);
        rightButton.setPosition(Gdx.graphics.getWidth() - rightButton.getWidth(), GdxHelper.getScreenCenterY() - rightButton.getHeight() / 2);
        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                planetSelectionController.moveSelectedPlanetRight();
            }
        });
        return rightButton;
    }

    private Actor setupGamecharacter(GameCharacter gameCharacter) {
        Texture texture = universeExploration.getAssetManager().getAsset(gameCharacter.getAsset());

        Image image = new Image(texture);
        image.setVisible(false);
        image.setTouchable(Touchable.disabled);
        gameCharacterImageMap.put(gameCharacter, image);
        return image;
    }

    private Actor createGrid() {
        boardGrid = new BoardGrid(universeExploration, this.gameController);
        positionActor(boardGrid, ActorPosition.CENTER, 0, 30);
        boardGrid.setVisible(false);
        return boardGrid;
    }

    private Button createSurveyButton() {
        surveyButton = new ButtonFactory().createTextButton(getLocale("BTN_SURVEY_PLANET"), (a, b, c) -> {
        });
        surveyButton.setWidth(800);
        surveyButton.setHeight(200);
        surveyButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        toggleSurveyMode(true);
                    }
                });
        positionActor(surveyButton, ActorPosition.CENTER, 0, 0);
        return surveyButton;
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
        walkModeButton.setVisible(false);
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
        talkModeButton.setVisible(false);
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
        attackModeButton.setVisible(false);
        return attackModeButton;
    }

    private void reconfigureSelectedAction(CharacterActionMode characterActionMode) {
        if(gameController.getSelectedAction() != characterActionMode) {
            gameController.setSelectedAction(characterActionMode);
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
                        toggleSurveyMode(false);
                    }
                });
        positionActor(endSurveyButton, ActorPosition.MIDDLE_TOP, 0, 0);
        endSurveyButton.setVisible(false);
        return endSurveyButton;
    }

    public void createMoveToActionOnCoordinates(GameCharacter gameCharacter, int gridX, int gridY) {
        Vector2 vector = boardGrid.getCellPosition(gridX, gridY);
        MoveToAction moveAction = new MoveToAction();
        moveAction.setDuration(20);
        moveAction.setPosition(vector.x+7, vector.y+7);
        gameCharacterImageMap.get(gameCharacter).addAction(moveAction);
    }

    private void toggleSurveyMode(boolean surveyMode) {
        // Survey mode components
        endSurveyButton.setVisible(surveyMode);
        walkModeButton.setVisible(surveyMode);
        talkModeButton.setVisible(surveyMode);
        attackModeButton.setVisible(surveyMode);
        boardGrid.setVisible(surveyMode);
        addAsyncAction(() -> {
            Thread.sleep(5);
            gameCharacterImageMap.forEach((key, value) -> {
                value.setVisible(surveyMode);
                if (surveyMode) {
                    Coordinate coordinate = new Coordinate(key.getCoordinateX(), key.getCoordinateY());
                    positionActorToGridCoordinates(coordinate, value);
                }
            });
            return true;
        });

        // Non-survey mode components
        surveyButton.setVisible(!surveyMode);
        leftButton.setVisible(!surveyMode);
        rightButton.setVisible(!surveyMode);
    }

    private void positionActorToGridCoordinates(Coordinate coordinate, Actor actor) {
        Vector2 vector = boardGrid.getCellPosition(coordinate.getX(), coordinate.getY());
        actor.setPosition(vector.x+7, vector.y+7);
    }

}