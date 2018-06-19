/**
 *
 */
package com.universe.exploration.userinterface.components.window;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Small version of {@link BasicWindow}.
 *
 * @author 20.9.2015 Teemu Puurunen
 */
public class SmallWindow extends BasicWindow {
    public SmallWindow(WindowType windowType, Skin skin, String styleName) {
        super(windowType.getLocalizedCaption(), skin, styleName);
        setWindowType(windowType);
    }

    @Override
    public void setWindowSize() {
        setSize(WindowSetup.SMALL.getWidth(), WindowSetup.SMALL.getHeight());
    }
}
