package com.universe.exploration.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.BoardGrid;
import com.universe.exploration.component.button.ButtonFactory;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.model.ActorPosition;
import com.universe.exploration.screens.AbstractScreen;
import com.universe.exploration.utils.GdxHelper;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.HudAssetProvider;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.function.Supplier;

public class Game extends AbstractScreen {
    private PlanetController planetController;
    private GameController gameController;
    private BoardGrid boardGrid;
    private ImageButton leftButton;
    private ImageButton rightButton;
    private Button surveyButton;
    private Button endSurveyButton;
    private Image astronautActor;

    public Game(UniverseExploration universeExploration) {
        super(universeExploration);
    }

    @Override
    protected List<Actor> addActors() {
        List<Actor> actors = new ArrayList();
        List<Actor> planets = createPlanets();
        actors.addAll(planets);
        actors.add(createLeftButton());
        actors.add(createRightButton());
        actors.add(createSurveyButton());
        actors.add(createEndSurveyButton());
        actors.add(createGrid());
        actors.add(createAstronaut());

        return actors;
    }

    @Override
    protected void initialiseControllers(UniverseExploration universeExploration) {
        planetController = new PlanetController(universeExploration);
        gameController = new GameController(universeExploration);
    }

    private ImageButton createLeftButton() {
        Texture left = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.ARROW_LEFT);
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(left));
        leftButton = new ImageButton(drawable);
        leftButton.setPosition(0, GdxHelper.getScreenCenterY() - leftButton.getHeight() / 2);
        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                planetController.moveSelectedPlanetLeft();
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
                planetController.moveSelectedPlanetRight();
            }
        });
        return rightButton;
    }

    private Actor createAstronaut() {
        Texture astronautTexture = universeExploration.getAssetManager().getAsset(PlanetAssetProvider.PlanetAsset.ASTRONAUT);
        astronautActor = new Image(astronautTexture);
        astronautActor.setVisible(false);
        astronautActor.setTouchable(Touchable.disabled);
        return astronautActor;
    }

    private Actor createGrid() {
        boardGrid = new BoardGrid(universeExploration);
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

    private Button createEndSurveyButton() {
        endSurveyButton = new ButtonFactory().createTextButton(getLocale("BTN_SURVEY_PLANET_END"), (a, b, c) -> {
        });
        endSurveyButton.setWidth(600);
        endSurveyButton.setHeight(160);
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

    private void toggleSurveyMode(boolean surveyMode) {
        // Survey mode components
        endSurveyButton.setVisible(surveyMode);
        boardGrid.setVisible(surveyMode);
        addAsyncAction(() -> {
            Thread.sleep(2);
            astronautActor.setVisible(surveyMode);
            if (surveyMode) {
                Vector2 vector = boardGrid.getCellPosition(0, 0);
                astronautActor.setPosition(vector.x, vector.y + 15);
            }

            return true;
        });

        // Non-survey mode components
        surveyButton.setVisible(!surveyMode);
        leftButton.setVisible(!surveyMode);
        rightButton.setVisible(!surveyMode);

    }

    private List<Actor> createPlanets() {
        return planetController.getPlanets();
    }
}