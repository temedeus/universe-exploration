/**
 * 
 */
package com.universe.exploration.localization;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.universe.exploration.common.TranslatableEnum;
import com.universe.exploration.common.tools.FileReader;

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

    private HashMap<LanguageCode, Localization> localization = new HashMap<LanguageCode, Localization>();

    // English by default
    private LanguageCode languageCode = LanguageCode.ENG;

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
	FileReader fileReader = new FileReader();
	for (LanguageCode code : LanguageCode.values()) {
	    try {
		List<String> rawLocalizationStrings = fileReader.readTextFile(code.getLocalizationFilePath());
		Localization newLocalization = new Localization();
		localization.put(code, newLocalization);

		for (String rawLocalizationString : rawLocalizationStrings) {
		    rawLocalizationString.trim();
		    if (!rawLocalizationString.isEmpty() && !rawLocalizationString.startsWith("//")) {
			String[] splitted = rawLocalizationString.split(":", 2);
			if (splitted.length == 2) {
			    newLocalization.putIntoLocalizationMap(splitted[0], splitted[1]);
			}
		    }
		}

	    } catch (IOException e) {
		Gdx.app.log("ERROR", "Localization file " + code.getLocalizationFilePath() + " could not be read for some reason!", e);
	    }
	}
    }

    public void setLanguage(LanguageCode languageCode) {
	this.languageCode = languageCode;
    }

    /**
     * Use string local key. Get localization based on given key. Return key (as
     * string) if none found.
     * 
     * @param key
     * @return
     */
    public String get(String key) {
	String result = null;
	try {
	    result = localization.get(languageCode).getFromLocalizationMap(key);
	} catch (NullPointerException e) {
	    result = key;
	} finally {
	    if(result == null) {
		result = key;
	    }
	}

	return result;
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
