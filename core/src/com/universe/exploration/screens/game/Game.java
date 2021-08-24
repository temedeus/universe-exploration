package com.universe.exploration.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.Grid;
import com.universe.exploration.component.button.ButtonFactory;
import com.universe.exploration.controller.PlanetController;
import com.universe.exploration.model.ActorPosition;
import com.universe.exploration.screens.AbstractScreen;
import com.universe.exploration.utils.GdxHelper;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.HudAssetProvider;

import java.util.ArrayList;
import java.util.List;

public class Game extends AbstractScreen {
    private PlanetController planetController;
    private Grid grid;
    private ImageButton leftButton;
    private ImageButton rightButton;
    private Button surveyButton;
    private Button endSurveyButton;
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
        return actors;
    }

    @Override
    protected void initialiseControllers(UniverseExploration universeExploration) {
        planetController = new PlanetController(universeExploration);
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

    private Actor createGrid() {
        grid = new Grid(universeExploration);
        positionActor(grid, ActorPosition.CENTER, 0, 0);
        grid.setVisible(false);
        return grid;
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
        endSurveyButton.setWidth(800);
        endSurveyButton.setHeight(200);
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
        grid.setVisible(surveyMode);

        // Non-survey mode components
        surveyButton.setVisible(!surveyMode);
        leftButton.setVisible(!surveyMode);
        rightButton.setVisible(!surveyMode);
    }

    private List<Actor> createPlanets() {
        return planetController.getPlanets();
    }
}