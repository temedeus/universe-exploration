package com.universe.exploration.screens.planetselection;

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
import com.universe.exploration.component.dialog.Dialog;
import com.universe.exploration.component.dialog.DialogFactory;
import com.universe.exploration.component.dialog.DialogType;
import com.universe.exploration.controller.game.planetselection.PlanetSelectionController;
import com.universe.exploration.model.ActorPosition;
import com.universe.exploration.model.GameScreen;
import com.universe.exploration.screens.AbstractScreen;
import com.universe.exploration.screens.mainmenu.PlayerCreationDialog;
import com.universe.exploration.utils.GdxHelper;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.HudAssetProvider;

import java.util.ArrayList;
import java.util.List;

public class PlanetSelectionScreen extends AbstractScreen {
    private PlanetSelectionController planetSelectionController;

    public PlanetSelectionScreen(UniverseExploration universeExploration) {
        super(universeExploration);
    }

    @Override
    protected List<Actor> addActors() {
        List<Actor> actors = new ArrayList(planetSelectionController.getPlanets());
        actors.add(createLeftButton());
        actors.add(createRightButton());
        actors.add(createSurveyButton());

        if(this.universeExploration.getGamestatus().isInitialStatus()) {
            planetSelectionController.moveSelectedPlanetRight();
            this.universeExploration.getGamestatus().setPlanets(planetSelectionController.getPlanets());
            this.universeExploration.getGamestatus().setInitialStatus(false);
        }

        return actors;
    }

    @Override
    protected void initialiseControllers(UniverseExploration universeExploration) {
        planetSelectionController = new PlanetSelectionController(universeExploration);
        planetSelectionController.generatePlanets();
    }

    private ImageButton createLeftButton() {
        Texture left = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.ARROW_LEFT);
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(left));
        ImageButton leftButton = new ImageButton(drawable);
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
        ImageButton rightButton = new ImageButton(drawable);
        rightButton.setPosition(Gdx.graphics.getWidth() - rightButton.getWidth(), GdxHelper.getScreenCenterY() - rightButton.getHeight() / 2);
        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                planetSelectionController.moveSelectedPlanetRight();
            }
        });
        return rightButton;
    }

    private Button createSurveyButton() {
        Dialog surveyModeDialog = new DialogFactory().createDialog(DialogType.ENGAGE_SURVEY_MODE, new PlayerCreationDialog(), ((event, x, y) -> {
            universeExploration.getScreenHandler().navigateToWhenReady(GameScreen.COMBAT);
        }));
        Button surveyButton = new ButtonFactory().createTextButton(getLocale("BTN_SURVEY_PLANET"), (a, b, c) -> {
        });
        surveyButton.setWidth(800);
        surveyButton.setHeight(200);
        surveyButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        screenStage.addActor(surveyModeDialog);
                    }
                });
        positionActor(surveyButton, ActorPosition.CENTER, 0, 0);
        return surveyButton;
    }
}