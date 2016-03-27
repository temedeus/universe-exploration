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

    private LanguageCode languageCode = LanguageCode.ENG;

    public static Localizer getInstance() {
	if (localizer == null) {
	    localizer = new Localizer();
	}

	return localizer;
    }

    private Localizer() {
	FileReader fileReader = new FileReader();
	for (LanguageCode code : LanguageCode.values()) {
	    try {
		List<String> rawLocalizationStrings = fileReader.readTextFile(code.getLocalizationFilePath());
		Localization newLocalization = new Localization();
		localization.put(code, newLocalization);
		for (String rawLocalizationString : rawLocalizationStrings) {
		    rawLocalizationString.trim();
		    if (rawLocalizationString.isEmpty() || rawLocalizationString.startsWith("//"));
		    String[] splitted = rawLocalizationString.split(":", 2);
		    if (splitted.length == 2) {
			newLocalization.putIntoLocalizationMap(splitted[0], splitted[1]);
		    }

		}

	    } catch (IOException e) {
		Gdx.app.log("ERROR", "Localization file " + code.getLocalizationFilePath() + " could not be read for some reason!", e);
	    }
	}
    }

    public void resetLanguageTo(LanguageCode languageCode) {
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
	String result;
	try {
	    result = localization.get(languageCode).getFromLocalizationMap(key);
	} catch (NullPointerException e) {
	    result = key;
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
