package com.universe.exploration.component.commoncomponent;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.universe.exploration.UniverseExploration;

public class CommonComponentFactory {
    private final Skin uiSkin = UniverseExploration.commonUIAssets.getUserInterfaceSkin();

    public TextField createTextField(String text) {
        TextField textField = new TextField(text, uiSkin);
        textField.setOrigin(Align.center);
        return  textField;
    }

    public Label createLabel(String text) {
        Label label = new Label(text, uiSkin);
        label.setFontScale(3f);
        label.setOrigin(Align.center);
        return label;
    }
}
