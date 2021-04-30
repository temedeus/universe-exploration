/**
 *
 */
package com.universe.exploration.component.dialog;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Small version of {@link Dialog}.
 *
 * @author 20.9.2015 Teemu Puurunen
 */
public class SmallDialog extends Dialog {
    public SmallDialog(DialogType windowType, Skin skin, String styleName) {
        super(windowType.getLocalizedCaption(), skin, styleName);
        setWindowType(windowType);
    }

    @Override
    DialogSetup getWindowSetup() {
        return DialogSetup.SMALL;
    }
}
