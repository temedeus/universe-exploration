/**
 * 
 */
package com.universe.exploration.ueui.components.window;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author 20.9.2015 Teemu Puurunen
 *
 */
public class LargeWindow extends BasicWindow {
    /**
     * @param title
     * @param skin
     */
    public LargeWindow(String title, Skin skin) {
	super(title, skin);
    }

    /**
     * @param title
     * @param skin
     * @param styleName
     */
    public LargeWindow(String title, Skin skin, String styleName) {
	super(title, skin, styleName);
    }

    /**
     * @param title
     * @param style
     */
    public LargeWindow(String title, WindowStyle style) {
	super(title, style);
    }

    @Override
    public void setWindowSize() {
	setSize(WindowSetup.WINDOW_LARGE.getWidth(), WindowSetup.WINDOW_LARGE.getHeight());
    }
}
