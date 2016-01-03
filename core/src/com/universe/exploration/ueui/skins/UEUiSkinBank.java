/**
 * 
 */
package com.universe.exploration.ueui.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.universe.exploration.CoreConfiguration;

/**
 * @author 1.10.2015 Teemu Puurunen 
 * TODO: right now I just made a duplicate font with large size and orange color. Maybe this could be performed smarter? Either include this in the
 * current skin JSON. (Maybe stop using BitMapFonts altogether?) 
 */
public class UEUiSkinBank {

	/**
	 * Alternate font for showing values.
	 * 
	 * @static
	 */
	private static BitmapFont font = new BitmapFont(Gdx.files.internal("ueimpact_emp.fnt"),  Gdx.files.internal("ueimpact_emp.png"), false);

	/**
	 * Style used for representing various values around the game.
	 * @static
	 */
	public static LabelStyle valueStyle = new LabelStyle(font, Color.WHITE);
	
	/**
	 * Common skin used most of the time.
	 * @static
	 */
	public static Skin ueUISkin = new Skin(Gdx.files.internal(CoreConfiguration.UISKINSOURCE));
	
	
}
