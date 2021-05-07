package com.universe.exploration;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class CommonUIAssets {

    private BitmapFont font;// = new BitmapFont(Gdx.files.internal("fonts/ueimpact_emp.fnt"), Gdx.files.internal("fonts/ueimpact_emp.png"), false);

    private Skin userInterfaceSkin; // = new Skin(Gdx.files.internal(USERINTERFACE_SKIN));

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public Skin getUserInterfaceSkin() {
        return userInterfaceSkin;
    }

    public void setUserInterfaceSkin(Skin userInterfaceSkin) {
        this.userInterfaceSkin = userInterfaceSkin;
    }

}