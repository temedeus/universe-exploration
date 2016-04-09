/**
 * 
 */
package com.universe.exploration.userinterface.components;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.universe.exploration.userinterface.skins.UserInterfaceBank;

/**
 * @author 8.4.2016 Teemu Puurunen 
 *
 */
public class UELabel extends Label{
    
    /**
     * @param text
     * @param skin
     */
    public UELabel(CharSequence text) {
	super(text, UserInterfaceBank.userInterfaceSkin);
    }
    
    /**
     * @param text
     * @param skin
     * @param styleName
     */
    public UELabel(CharSequence text, Skin skin, String styleName) {
	super(text, skin, styleName);
    }
}
