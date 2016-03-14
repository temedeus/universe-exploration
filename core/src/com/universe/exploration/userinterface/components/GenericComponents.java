/**
 * 
 */
package com.universe.exploration.userinterface.components;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.universe.exploration.userinterface.skins.UEUiSkinBank;

/**
 * @author 1.2.2016 Teemu Puurunen
 *
 */
public class GenericComponents {

    private static GenericComponents genericComponents;

    private GenericComponents() {
	// Ensure this class is instantiated once.
    }

    public static GenericComponents createInstance() {
	if (genericComponents == null) {
	    return new GenericComponents();
	} else {
	    return genericComponents;
	}
    }

    public Label createSpacer() {
	return new Label("", UEUiSkinBank.ueUISkin);
    }
}
