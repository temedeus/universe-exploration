/**
 *
 */
package com.universe.exploration.localization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;
import java.util.MissingResourceException;

/**
 * <p>
 * Use this class to get localized strings. You can either use a string or
 * {@link TranslatableEnum}.
 * </p>
 *
 * @author 25.8.2015 Teemu Puurunen
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
     * Default to English bundle.
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
        try {
            return localizationBundle.get(key);
        } catch (MissingResourceException e) {
            return key;
        }
    }

    /**
     * Get localization based on given {@link TranslatableEnum}. Return key (as string)
     * if none found.
     *
     * @param local
     * @return
     */
    public String get(TranslatableEnum local) {
        return get(local.toString());
    }
}
