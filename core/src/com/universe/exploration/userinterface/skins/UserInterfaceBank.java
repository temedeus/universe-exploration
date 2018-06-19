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
 * TODO: rationalize the use of this class. Should some of the stuff found here be in the uiskin.json?
 */
public class UserInterfaceBank {

    /**
     * Alternate font for showing values.
     *
     * @static
     */
    private static final BitmapFont font = new BitmapFont(Gdx.files.internal("ueimpact_emp.fnt"), Gdx.files.internal("ueimpact_emp.png"), false);

    /**
     * Style used for representing various values around the game.
     *
     * @static
     */
    public static final LabelStyle valueStyle = new LabelStyle(font, Color.WHITE);

    /**
     * Common skin used most of the time.
     *
     * @static
     */
    public static final Skin userInterfaceSkin = new Skin(Gdx.files.internal(CoreConfiguration.USERINTERFACE_SKIN));

}
