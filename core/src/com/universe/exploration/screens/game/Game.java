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
        return actors;
    }

    @Override
    protected void initialiseControllers(UniverseExploration universeExploration) {
        planetController = new PlanetController(universeExploration);
    }

    private ImageButton createLeftButton() {
        Texture left = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.ARROW_LEFT);
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(left));
        ImageButton leftButton = new ImageButton(drawable);
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
        ImageButton leftButton = new ImageButton(drawable);
        leftButton.setPosition(Gdx.graphics.getWidth() - leftButton.getWidth(), GdxHelper.getScreenCenterY() - leftButton.getHeight() / 2);
        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                planetController.moveSelectedPlanetRight();
            }
        });
        return leftButton;
    }

    private Button createSurveyButton() {
        Button surveyButton = new ButtonFactory().createTextButton(getLocale("BTN_SURVEY_PLANET"), (a, b, c) -> {
        });
        surveyButton.setWidth(800);
        surveyButton.setHeight(200);
        positionActor(surveyButton, ActorPosition.CENTER, 0, 0);
        return surveyButton;
    }

    private List<Actor> createPlanets() {
        return planetController.getPlanets();
    }
}