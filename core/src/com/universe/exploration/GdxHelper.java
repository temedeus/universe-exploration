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
	 * @return float
	 */
	public static float getScreenCenterX() {
		float w = Gdx.graphics.getWidth();
		return (w/2);
	}
	
	/**
	 * Get screen center Y
	 * @return float
	 */
	public static float getScreenCenterY() {
        float h = Gdx.graphics.getHeight();
        return (h/2);
	}
	
	public class Align {
		static public final int center = 1 << 0;
		static public final int top = 1 << 1;
		static public final int bottom = 1 << 2;
		static public final int left = 1 << 3;
		static public final int right = 1 << 4;

		static public final int topLeft = top | left;
		static public final int topRight = top | right;
		static public final int bottomLeft = bottom | left;
		static public final int bottomRight = bottom | right;
	}
}

