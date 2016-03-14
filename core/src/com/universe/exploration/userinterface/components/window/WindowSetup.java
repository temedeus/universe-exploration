/**
 * 
 */
package com.universe.exploration.userinterface.components.window;

/**
 * @author 18.9.2015 Teemu Puurunen
 *
 */
public enum WindowSetup {
    WINDOW_SMALL(210, 105), WINDOW_MEDIUM(350, 175), WINDOW_LARGE(560, 600);

    private final int width;

    private final int height;

    /**
	 * 
	 */
    WindowSetup(int width, int height) {
	this.width = width;
	this.height = height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
	return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
	return height;
    }
}
