package com.universe.exploration.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.screens.AbstractScreen;
import com.universe.exploration.utils.GdxHelper;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.HudAssetProvider;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

import java.util.ArrayList;
import java.util.List;

public class Game extends AbstractScreen {
    public Game(UniverseExploration universeExploration) {
        super(universeExploration);
    }

    @Override
    protected List<Actor> addActors() {
        java.util.List<Actor> actors = new ArrayList();
        actors.add( createPlanets());
        actors.add(createLeftButton());
        actors.add(createRightButton());

        return actors;
    }

    private ImageButton createLeftButton() {
        Texture left = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.ARROW_LEFT);
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(left));
        ImageButton leftButton = new ImageButton(drawable);
        leftButton.setPosition(0, GdxHelper.getScreenCenterY()-leftButton.getHeight()/2);
        return leftButton;
    }

    private ImageButton createRightButton() {
        Texture left = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.ARROW_RIGHT);
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(left));
        ImageButton leftButton = new ImageButton(drawable);
        leftButton.setPosition(Gdx.graphics.getWidth() - leftButton.getWidth(), GdxHelper.getScreenCenterY()-leftButton.getHeight()/2);
        return leftButton;
    }

    private Image createPlanets() {
        Texture planetTexture = universeExploration.getAssetManager().getAsset(PlanetAssetProvider.PlanetAsset.EARTHLIKE);
        Image earthLike = new Image(planetTexture);

        earthLike.setPosition(GdxHelper.getScreenCenterX() - earthLike.getWidth() / 2, GdxHelper.getScreenCenterY() - earthLike.getHeight() / 2);
        earthLike.setOrigin(earthLike.getWidth() / 2, earthLike.getHeight() / 2);


        RepeatAction action = Actions.repeat(RepeatAction.FOREVER, Actions.rotateBy(360, 60000));

        earthLike.addAction(action);

        return earthLike;
    }
}