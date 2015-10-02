/**
 * 
 */
package com.universe.exploration.ueui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * @author 25.8.2015 Teemu Puurunen 
 *
 */
public class UEButtonFactory {
	Skin uiSkin;
	
	/**
	 * @author teemu.puurunen
	 */
	public UEButtonFactory(Skin uiSkin) {
		this.uiSkin = uiSkin;
	}
	
	/**
	 * Create standardized button
	 * @return
	 */
	public TextButton createTextButton(String caption, ClickListener clickListener) {
		return createCustomTextButton(caption, clickListener, "default");
	}
	
	/**
	 * Create custom button
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
