/**
 *
 */
package com.universe.exploration.userinterface.components;

import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.universe.exploration.userinterface.skins.UserInterfaceBank;

/**
 * @author 10.1.2017 Teemu Puurunen
 */
public class VolumeSlider extends Slider {
    public VolumeSlider() {
        super(1, 100, 1, false, UserInterfaceBank.userInterfaceSkin);
        setValue(100);
    }
}
