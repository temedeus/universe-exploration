package com.universe.exploration.component.asset;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Common assets needed basically everywhere within game.
 */
public class CommonUIAssets {

    private BitmapFont font;

    private Skin userInterfaceSkin;

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