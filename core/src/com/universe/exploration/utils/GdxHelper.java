package com.universe.exploration.utils;

import com.badlogic.gdx.Gdx;

import java.io.BufferedReader;

public class GdxHelper {
    public static BufferedReader provideAssetReader(String path) {
        return new BufferedReader(Gdx.files.internal(path).reader());
    }

    public static float getScreenCenterX() {
        float w = Gdx.graphics.getWidth();
        return (w / 2);
    }

    public static float getScreenCenterY() {
        float h = Gdx.graphics.getHeight();
        return (h / 2);
    }

    public static float getDeltaTime() {
        return Gdx.graphics.getDeltaTime() * 100;
    }
}
