/**
 * 
 */
package com.universe.exploration.ueui;

import com.universe.exploration.localization.LocalKeys;
import com.universe.exploration.localization.Localizer;

/**
 * <p>Contains window caption and ok button text. Referenced as {@link LocalKeys}, not direct strings.</p>
 * @author 1.11.2015 Teemu Puurunen 
 *
 */
public enum WindowType {
	SURVEY_WINDOW(LocalKeys.TITLE_SURVEY_PLANET, LocalKeys.BTN_SURVEY), 
	GAME_OVER(LocalKeys.TITLE_GAME_OVER, LocalKeys.BTN_TRY_AGAIN),
	PLANET_DETAILS(LocalKeys.TITLE_SURVEY_CONFIGURATION_SCREEN, LocalKeys.BTN_SURVEY), 
	SURVEY_CLOSED(LocalKeys.TITLE_SURVEY, LocalKeys.BTN_SURVEY);
	
	private final LocalKeys caption;
	
	private final LocalKeys okButtonCaption; 
	
	/**
	 *  Contains window caption and ok button text.
	 */
	private WindowType(LocalKeys caption, LocalKeys okButtonCaption) {
		this.caption = caption;
		this.okButtonCaption = okButtonCaption;
	}

	/**
	 * @return the caption
	 */
	public String getLocalizedCaption() {
		return Localizer.get(caption.getLocalKey());
	}

	/**
	 * @return the okButtonCaption
	 */
	public String getLocalizedOkButtonCaption() {
		return Localizer.get(okButtonCaption.getLocalKey());
	}
}
