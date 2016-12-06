/**
 * 
 */
package com.universe.exploration.userinterface.components.window;

/**
 * Window setup enumeration. Defines window sizes.
 * 
 * @author 18.9.2015 Teemu Puurunen
 *
 */
public enum WindowSetup {
    WINDOW_SMALL(210, 105), WINDOW_MEDIUM(380, 205), WINDOW_LARGE(560, 600);

    private final int width;

    private final int height;

    private WindowSetup(int width, int height) {
	this.width = width;
	this.height = height;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }
}
