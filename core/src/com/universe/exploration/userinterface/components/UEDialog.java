package com.universe.exploration.userinterface.components;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.Align;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.userinterface.skins.UserInterfaceBank;

public class UEDialog extends Dialog {
    public UEDialog(String title) {
        super(Localizer.getInstance().get(title), UserInterfaceBank.userInterfaceSkin);
        getTitleLabel().setAlignment(Align.center, Align.bottom);
    }

    @Override
    public float getPrefWidth() {
        return 900f;
    }
}
