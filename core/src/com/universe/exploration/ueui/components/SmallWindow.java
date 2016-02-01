/**
 * 
 */
package com.universe.exploration.ueui.components;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author 20.9.2015 Teemu Puurunen
 *
 */
public class SmallWindow extends BasicWindow {
    /**
     * @param title
     * @param skin
     */
    public SmallWindow(String title, Skin skin) {
	super(title, skin);
    }

    /**
     * @param title
     * @param skin
     * @param styleName
     */
    public SmallWindow(String title, Skin skin, String styleName) {
	super(title, skin, styleName);
    }

    /**
     * @param title
     * @param style
     */
    public SmallWindow(String title, WindowStyle style) {
	super(title, style);
    }

    @Override
    public void setWindowSize() {
	setSize(WindowSetup.WINDOW_SMALL.getWidth(), WindowSetup.WINDOW_SMALL.getHeight());
    }
}
