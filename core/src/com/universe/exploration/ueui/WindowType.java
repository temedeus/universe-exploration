/**
 * 
 */
package com.universe.exploration.ueui;

import com.universe.exploration.localization.LocalKeys;
import com.universe.exploration.localization.Localizer;

/**
 * @author 1.11.2015 Teemu Puurunen 
 *
 */
public enum WindowType {
	SURVEY_WINDOW(LocalKeys.TITLE_SURVEY_PLANET.getLocalKey(), Localizer.get("BTN_SURVEY")), 
	GAME_OVER(Localizer.get("TITLE_GAME_OVER"), Localizer.get("BTN_TRY_AGAIN")),
	PLANET_DETAILS(Localizer.get("TITLE_SURVEY_PLANET_CONFIGURATION_SCREEN"), Localizer.get("BTN_SURVEY")), 
	SURVEY_CLOSED("Survey", "Survey");
	
	private final String caption;
	
	private final String okButtonCaption; 
	
	/**
	 * 
	 */
	private WindowType(String caption, String okButtonCaption) {
		this.caption = caption;
		this.okButtonCaption = okButtonCaption;
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @return the okButtonCaption
	 */
	public String getOkButtonCaption() {
		return okButtonCaption;
	}
}
