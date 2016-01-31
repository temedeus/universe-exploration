package com.universe.exploration.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.universe.exploration.UniverseExploration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Universe Exploration";
		config.width = 1300;
		config.height = 760;
		config.fullscreen = false;
		config.vSyncEnabled = true;
		new LwjglApplication(new UniverseExploration(), config);
		
	}
}
