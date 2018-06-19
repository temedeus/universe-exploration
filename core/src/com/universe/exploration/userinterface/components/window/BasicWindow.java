/**
 *
 */
package com.universe.exploration.userinterface.components.window;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.universe.exploration.common.tools.GdxHelper;

/**
 * Abstract base class for all windows.
 *
 * @author 20.9.2015 Teemu Puurunen
 */
public abstract class BasicWindow extends Window implements IBasicWindow {
    private WindowType windowType;

    public BasicWindow(String title, Skin skin, String styleName) {
        super(title, skin, styleName);
        setDefault();
    }

    private void setDefault() {
        getTitleLabel().setAlignment(Align.center, Align.bottom);
        setMovable(true);

        setPosition(GdxHelper.getScreenCenterX() - getWidth() / 2, GdxHelper.getScreenCenterY() - getHeight() / 2);
    }

    /**
     * @return the windowType
     */
    public WindowType getWindowType() {
        return windowType;
    }

    @Override
    public void setWindowSize() {
        setSize(WindowSetup.MEDIUM.getWidth(), WindowSetup.MEDIUM.getHeight());
    }

    /**
     * @param windowType the windowType to set
     */
    public void setWindowType(WindowType windowType) {
        this.windowType = windowType;
    }
}
