/**
 *
 */
package com.universe.exploration.userinterface.components;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.universe.exploration.userinterface.skins.UserInterfaceBank;

/**
 * Abstraction for LibGdx {@link TextField}.
 *
 * @author 6.12.2016 Teemu Puurunen
 */
public class UETextField extends TextField {

    public UETextField(String text) {
        super(text, UserInterfaceBank.userInterfaceSkin);
    }

}
