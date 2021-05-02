/**
 *
 */
package com.universe.exploration.component.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import tools.GdxHelper;

/**
 * Abstract base class for all dialogs.
 *
 * @author 20.9.2015 Teemu Puurunen
 */
public abstract class Dialog extends Window {
    private DialogType windowType;

    public Dialog(String title, Skin skin, String styleName) {
        super(title, skin, styleName);
        setDefault();
    }

    private void setDefault() {
        getTitleLabel().setAlignment(Align.center, Align.bottom);
        setMovable(true);
        setWindowSize();
        setPosition(GdxHelper.getScreenCenterX() - getWidth() / 2, GdxHelper.getScreenCenterY() - getHeight() / 2);
    }

    public DialogType getWindowType() {
        return windowType;
    }

    void setWindowSize() {
        setSize((float) (Gdx.graphics.getWidth() * getWindowSetup().getScreenSizeRatio()), (float) (Gdx.graphics.getHeight() * getWindowSetup().getScreenSizeRatio()));
    };

    abstract DialogSetup getWindowSetup();

    public void setWindowType(DialogType windowType) {
        this.windowType = windowType;
    }
}
