/**
 * 
 */
package com.universe.exploration.common.tools;

import java.io.BufferedReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * <p>
 * LibGdx specific helper methods
 * </p>
 * 
 * @author 25.8.2015 Teemu Puurunen
 *
 */
public class GdxHelper {

    public static BufferedReader provideAssetReader(String path) {
	return new BufferedReader(Gdx.files.internal(path).reader());

    }

    public static FileHandle provideFileHandle(String path) {
	return Gdx.files.external(path);
    }

    /**
     * Get screen center X
     * 
     * @return float
     */
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
