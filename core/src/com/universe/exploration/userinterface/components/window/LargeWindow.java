/**
 *
 */
package com.universe.exploration.userinterface.components.window;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Large version of {@link BasicWindow}.
 *
 * @author 20.9.2015 Teemu Puurunen
 */
public class LargeWindow extends BasicWindow {
    public LargeWindow(WindowType windowType, Skin skin, String styleName) {
        super(windowType.getLocalizedCaption(), skin, styleName);
        setWindowType(windowType);
    }

    @Override
    public void setWindowSize() {
        setSize(WindowSetup.LARGE.getWidth(), WindowSetup.LARGE.getHeight());
    }
}
