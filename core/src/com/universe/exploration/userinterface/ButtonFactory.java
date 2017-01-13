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
 *
 */
public class ButtonFactory {
    private Skin uiSkin = UserInterfaceBank.userInterfaceSkin;

    /**
     * Create text button with default style.
     * 
     * @param caption
     *            Caption of the button.
     * @param clickListener
     *            Action taken on button click.
     * @return
     */
    public TextButton createTextButton(String caption, ClickListener clickListener) {
	return createCustomTextButton(caption, clickListener, "default");
    }

    /**
     * Create customized button.
     * 
     * @param caption
     *            Caption of the button.
     * @param clickListener
     *            Action taken upon button click.
     * @param style
     *            Determine the style of the button.
     * @return
     */
    public TextButton createCustomTextButton(String caption, ClickListener clickListener, String style) {
	final TextButton button = new TextButton(caption, uiSkin, style);
	button.setWidth(200);
	button.setHeight(50);

	button.addListener(clickListener);

	return button;
    }
}
