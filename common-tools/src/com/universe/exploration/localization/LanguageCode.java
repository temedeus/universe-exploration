/**
 * 
 */
package com.universe.exploration.localization;

/**
 * @author 27.3.2016 Teemu Puurunen
 *
 */
public enum LanguageCode {
    ENG("localization/localization_en");

    private final String localizationFilePath;

    private LanguageCode(String localizationPath) {
	this.localizationFilePath = localizationPath;
    }

    /**
     * @return the localizationPath
     */
    public String getLocalizationFilePath() {
	return localizationFilePath;
    }
}
