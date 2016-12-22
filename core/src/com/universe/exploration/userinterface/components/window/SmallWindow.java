/**
 * 
 */
package com.universe.exploration.userinterface.components.window;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Small version of {@link BasicWindow}.
 * 
 * @author 20.9.2015 Teemu Puurunen
 *
 */
public class SmallWindow extends BasicWindow {

    public SmallWindow(String title, Skin skin) {
	super(title, skin);
    }

    public SmallWindow(String title, Skin skin, String styleName) {
	super(title, skin, styleName);
    }

    public SmallWindow(String title, WindowStyle style) {
	super(title, style);
    }

    @Override
    public void setWindowSize() {
	setSize(WindowSetup.SMALL.getWidth(), WindowSetup.SMALL.getHeight());
    }
}
