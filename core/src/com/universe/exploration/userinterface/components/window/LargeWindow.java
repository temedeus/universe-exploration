/**
 * 
 */
package com.universe.exploration.userinterface.components.window;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Large version of {@link BasicWindow}.
 * 
 * @author 20.9.2015 Teemu Puurunen
 *
 */
public class LargeWindow extends BasicWindow {
    public LargeWindow(String title, Skin skin) {
	super(title, skin);
    }

    public LargeWindow(String title, Skin skin, String styleName) {
	super(title, skin, styleName);
    }

    public LargeWindow(String title, WindowStyle style) {
	super(title, style);
    }

    @Override
    public void setWindowSize() {
	setSize(WindowSetup.WINDOW_LARGE.getWidth(), WindowSetup.WINDOW_LARGE.getHeight());
    }
}
