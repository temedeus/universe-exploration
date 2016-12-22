/**
 * 
 */
package com.universe.exploration.userinterface.components.window;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author 20.9.2015 Teemu Puurunen
 *
 */
public class MediumWindow extends BasicWindow {
    /**
     * @param title
     * @param skin
     */
    public MediumWindow(String title, Skin skin) {
	super(title, skin);
    }

    /**
     * @param title
     * @param skin
     * @param styleName
     */
    public MediumWindow(WindowType windowType, Skin skin, String styleName) {
	super(windowType.getLocalizedCaption(), skin, styleName);
	setWindowType(windowType);
    }

    /**
     * @param title
     * @param style
     */
    public MediumWindow(String title, WindowStyle style) {
	super(title, style);
    }

    @Override
    public void setWindowSize() {
	setSize(WindowSetup.MEDIUM.getWidth(), WindowSetup.MEDIUM.getHeight());
    }
}
