/**
 *
 */
package com.universe.exploration.userinterface;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.universe.exploration.userinterface.skins.UserInterfaceBank;

/**
 * Create different types of buttons.
 *
 * @author 13.1.2017 Teemu Puurunen
 */
public class ButtonFactory {
    private final Skin uiSkin = UserInterfaceBank.userInterfaceSkin;

    /**
     * Create button.
     *
     * @param caption
     * @param clickListener
     * @return
     */
    public TextButton createTextButton(String caption, ClickListener clickListener) {
        final TextButton button = new TextButton(caption, uiSkin, "default");
        button.addListener(clickListener);
        button.setTransform(true);
        button.setScale(1.0f);
        return button;
    }
}
