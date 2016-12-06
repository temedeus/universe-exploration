/**
 * 
 */
package com.universe.exploration.userinterface.components.window;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.universe.exploration.common.tools.GdxHelper;

/**
 * Abstract base class for all windows.
 * 
 * @author 20.9.2015 Teemu Puurunen
 *
 */
public abstract class BasicWindow extends Window implements IBasicWindow {
    public BasicWindow(String title, Skin skin) {
	super(title, skin);
	setDefault();
    }

    public BasicWindow(String title, Skin skin, String styleName) {
	super(title, skin, styleName);
	setDefault();
    }

    public BasicWindow(String title, WindowStyle style) {
	super(title, style);
	setDefault();
    }

    private void setDefault() {
	setMovable(true);
	padTop(20);
	setWindowSize();
	setPosition(GdxHelper.getScreenCenterX() - getWidth() / 2, GdxHelper.getScreenCenterY() - getHeight() / 2);
    }

    public void setWindowSize() {
	setSize(WindowSetup.WINDOW_MEDIUM.getWidth(), WindowSetup.WINDOW_MEDIUM.getHeight());
    }
}
