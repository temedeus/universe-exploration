package com.universe.exploration.utils;

import com.badlogic.gdx.Gdx;

public class LoggerWrapper {
    public static void logDebug(String tag, String message) {
        Gdx.app.log(tag, message);
    }

    public static void logError(String tag, String message, Exception e) {
        Gdx.app.error(tag, message, e);
    }

    public static void logDebug(String tag, String message, Exception e) {
        Gdx.app.debug(tag, message, e);
    }
}
