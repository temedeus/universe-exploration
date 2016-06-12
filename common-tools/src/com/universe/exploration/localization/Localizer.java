/**
 * 
 */
package com.universe.exploration.localization;

import java.util.Locale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;
import com.universe.exploration.common.TranslatableEnum;

/**
 * <p>
 * Use this class to get localized strings. You can either use a string or
 * {@link LocalKey}.
 * </p>
 * 
 * @author 25.8.2015 Teemu Puurunen
 *
 */
public class Localizer {

    private static Localizer localizer;

    private I18NBundle localizationBundle;

    /**
     * Initialize once. If localizations change in the middle of the game...
     * well, boohoo.
     * 
     * @return
     */
    public static Localizer getInstance() {
	if (localizer == null) {
	    localizer = new Localizer();
	}

	return localizer;
    }

    /**
     * We read all the localizations at once to avoid repetetive IO. There is
     * not so many localization items so far that we couldn't load them all into
     * memory right now. We'll see if we have to change this in the future.
     */
    private Localizer() {
	FileHandle baseFileHandle = Gdx.files.internal("localization/DefaultBundle");
	Locale locale = new Locale("en", "GB", "VAR1");
	localizationBundle = I18NBundle.createBundle(baseFileHandle, locale);
    }

    /**
     * Use string local key. Get localization based on given key. Return key (as
     * string) if none found.
     * 
     * @param key
     * @return
     */
    public String get(String key) {
	return localizationBundle.get(key);
    }

    /**
     * Get localization based on given {@link LocalKey}. Return key (as string)
     * if none found.
     * 
     * @param key
     * @return
     */
    public String get(TranslatableEnum local) {
	return get(local.toString());
    }
}
