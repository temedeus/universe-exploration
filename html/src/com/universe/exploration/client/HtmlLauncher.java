package com.universe.exploration.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.universe.exploration.UniverseExploration;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig () {
            return new GwtApplicationConfiguration(800, 600);
    }

    @Override
    public ApplicationListener getApplicationListener () {
            return new UniverseExploration();
    }
}