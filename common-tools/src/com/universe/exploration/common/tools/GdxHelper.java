/**
 *
 */
package com.universe.exploration.common.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;

/**
 * LibGdx specific helper methods
 *
 * @author 25.8.2015 Teemu Puurunen
 */
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
