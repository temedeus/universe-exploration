package com.universe.exploration.utils;

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
                LoggerWrapper.logError("InitialLoadingScreen", "Error sleeping in loading screen", e);
            }
            screenLoading = false;
        }).start();
    }

    public boolean isScreenLoading() {
        return screenLoading;
    }
}
