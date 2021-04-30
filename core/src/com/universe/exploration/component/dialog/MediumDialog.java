/**
 *
 */
package com.universe.exploration.component.dialog;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author 20.9.2015 Teemu Puurunen
 */
public class MediumDialog extends Dialog {
    /**
     * @param windowType
     * @param skin
     * @param styleName
     */
    public MediumDialog(DialogType windowType, Skin skin, String styleName) {
        super(windowType.getLocalizedCaption(), skin, styleName);
        setWindowType(windowType);
    }

    @Override
    DialogSetup getWindowSetup() {
        return DialogSetup.MEDIUM;
    }
}
