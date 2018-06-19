/**
 *
 */
package com.universe.exploration.userinterface.components;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.universe.exploration.userinterface.skins.UserInterfaceBank;

/**
 * Abstraction of LibGdx {@link SelectBox}.
 *
 * @author 6.12.2016 Teemu Puurunen
 */
public class UESelectBox<T> extends SelectBox<T> {
    public UESelectBox() {
        super(UserInterfaceBank.userInterfaceSkin);
    }
}