package com.universe.exploration;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ComponentStyle {

    private static final String USERINTERFACE_SKIN = "star-soldier/skin/star-soldier-ui.json";
    /**
     * Alternate font for showing values.
     *
     * @static
     */
    private static final BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/ueimpact_emp.fnt"), Gdx.files.internal("fonts/ueimpact_emp.png"), false);

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
    public static final Skin userInterfaceSkin = new Skin(Gdx.files.internal(USERINTERFACE_SKIN));

}