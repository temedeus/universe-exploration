/**
 * 
 */
package com.universe.exploration;

import com.badlogic.gdx.Gdx;

/**
 * @author 25.8.2015 Teemu Puurunen
 *
 */
public class GdxHelper {
    /**
     * Get screen center X
     * 
     * @return float
     */
    public static float getScreenCenterX() {
	float w = Gdx.graphics.getWidth();
	return (w / 2);
    }

    /**
     * Get screen center Y
     * 
     * @return float
     */
    public static float getScreenCenterY() {
	float h = Gdx.graphics.getHeight();
	return (h / 2);
    }

    /**
     * Get delta time. Let's create an abstraction layer before Gdx's own
     * mechanism in case there's something to add in the calculation mechanism
     * later on.
     * 
     * @return
     */
    public static float getDeltaTime() {
	return Gdx.graphics.getDeltaTime() * 100;
    }
}
