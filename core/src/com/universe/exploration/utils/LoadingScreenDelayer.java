package com.universe.exploration.utils;

import com.badlogic.gdx.Gdx;

/**
 * Create a small delay between screens.
 */
public class LoadingScreenDelayer {
    private boolean screenLoading = false;

    public void delay() {
        screenLoading = true;
        new Thread(() -> {
            // Let's sleep a bit to avoid annoying blinking effect in loading screen.
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Gdx.app.error("InitialLoadingScreen", "Error sleeping in loading screen", e);
            }
            screenLoading = false;
        }).start();
    }

    public boolean isScreenLoading() {
        return screenLoading;
    }
}
