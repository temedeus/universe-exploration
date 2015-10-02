/**
 * 
 */
package com.universe.exploration.ueui.components;

/**
 * @author 18.9.2015 Teemu Puurunen 
 *
 */
public enum WindowSetup {
	WINDOW_SMALL(300, 150),
	WINDOW_MEDIUM(450, 200),
	WINDOW_LARGE(800,600);
	
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
