package com.universe.exploration.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.group.VerticalCenteredGroup;

import java.util.ArrayList;
import java.util.List;

public class LoadingScreen extends AbstractScreen {
    public LoadingScreen(UniverseExploration universeExploration) {
        super(universeExploration);
    }

    @Override
    protected List<Actor> addActors() {
        VerticalGroup loadingScreenGroup = new VerticalCenteredGroup();
        Label loadingLabel = new Label("LOADING", UniverseExploration.commonUIAssets.getUserInterfaceSkin());

        loadingScreenGroup.addActor(loadingLabel);
        java.util.List<Actor> actors = new ArrayList();
        actors.add(loadingScreenGroup);
        return actors;

    }
}
