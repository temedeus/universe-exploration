/**
 * 
 */
package com.universe.exploration.userinterface;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.universe.exploration.userinterface.skins.UEUiSkinBank;

/**
 * @author 25.8.2015 Teemu Puurunen
 *
 */
public class ButtonFactory {
    private Skin uiSkin = UEUiSkinBank.ueUISkin;
    
    /**
     * Create standardized button
     * 
     * @return
     */
    public TextButton createTextButton(String caption, ClickListener clickListener) {
	return createCustomTextButton(caption, clickListener, "default");
    }

    /**
     * Create custom button
     * 
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
