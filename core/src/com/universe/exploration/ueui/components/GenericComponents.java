/**
 * 
 */
package com.universe.exploration.ueui.components;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.universe.exploration.ueui.skins.UEUiSkinBank;

/**
 * @author 1.2.2016 Teemu Puurunen 
 *
 */
public class GenericComponents {
    private GenericComponents() {
	// Ensure this class is run only as singleton.
    }
    
    public static GenericComponents createInstance() {
	return new GenericComponents();
    }
    
    public Label createSpacer() {
	return new Label("", UEUiSkinBank.ueUISkin);
    }
}
