/**
 * 
 */
package com.universe.exploration.userinterface.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.universe.exploration.common.CoreConfiguration;

/**
 * TODO: get rid of this class. Utilize uiskin.json.
 */
public class UEUiSkinBank {

    /**
     * Alternate font for showing values.
     * 
     * @static
     */
    private static BitmapFont font = new BitmapFont(Gdx.files.internal("ueimpact_emp.fnt"), Gdx.files.internal("ueimpact_emp.png"), false);

    /**
     * Style used for representing various values around the game.
     * 
     * @static
     */
    public static LabelStyle valueStyle = new LabelStyle(font, Color.WHITE);

    /**
     * Common skin used most of the time.
     * 
     * @static
     */
    public static Skin ueUISkin = new Skin(Gdx.files.internal(CoreConfiguration.UISKINSOURCE));

}
