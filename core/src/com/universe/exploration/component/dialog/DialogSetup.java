/**
 *
 */
package com.universe.exploration.component.dialog;

/**
 * Dialog setups.
 *
 * @author 18.9.2015 Teemu Puurunen
 */
public enum DialogSetup {
    SMALL(480, 300), MEDIUM(800, 600), LARGE(1024, 768);

    private final int width;
    private final int height;

    DialogSetup(int width, int height) {
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
