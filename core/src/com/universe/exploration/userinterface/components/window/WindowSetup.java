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
    SMALL(210, 105, SmallWindow.class), MEDIUM(380, 205, MediumWindow.class), LARGE(560, 600, LargeWindow.class);

    private final int width;

    private final int height;

    private final Class<? extends BasicWindow> basicWindow;

    private WindowSetup(int width, int height, Class<? extends BasicWindow> basicWindow) {
	this.width = width;
	this.height = height;
	this.basicWindow = basicWindow;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public Class<? extends BasicWindow> getBasicWindow() {
        return basicWindow;
    }
}
