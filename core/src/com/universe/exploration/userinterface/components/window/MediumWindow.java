/**
 *
 */
package com.universe.exploration.userinterface.components.window;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author 20.9.2015 Teemu Puurunen
 */
public class MediumWindow extends BasicWindow {
    /**
     * @param windowType
     * @param skin
     * @param styleName
     */
    public MediumWindow(WindowType windowType, Skin skin, String styleName) {
        super(windowType.getLocalizedCaption(), skin, styleName);
        setWindowType(windowType);
    }

    @Override
    public void setWindowSize() {
        setSize(WindowSetup.MEDIUM.getWidth(), WindowSetup.MEDIUM.getHeight());
    }
}
