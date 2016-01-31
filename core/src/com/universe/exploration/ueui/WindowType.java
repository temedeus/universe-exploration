/**
 * 
 */
package com.universe.exploration.ueui;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.localization.LocalKeys;
import com.universe.exploration.localization.Localizer;

/**
 * <p>Contains window caption and ok button text. Referenced as {@link LocalKeys}, not direct strings.</p>
 * @author 1.11.2015 Teemu Puurunen 
 *
 */
public enum WindowType {
	/**
	 * Open up details for sending a survey team.
	 */
	SURVEY_WINDOW(LocalKeys.TITLE_SURVEY_PLANET, LocalKeys.BTN_SURVEY),
	
	/**
	 * Game over window.
	 */
	GAME_OVER(LocalKeys.TITLE_GAME_OVER, LocalKeys.BTN_TRY_AGAIN),
	
	/**
	 * List of planet general details. Allows to open up e.g. {@link #SURVEY_WINDOW}. 
	 */
	PLANET_DETAILS(LocalKeys.TITLE_SURVEY_CONFIGURATION_SCREEN, LocalKeys.BTN_SURVEY) {
		/* (non-Javadoc)
		 * @see com.universe.exploration.ueui.WindowType#retreiveDependencies()
		 */
		@Override
		protected List<WindowType> retreiveChildWindows() {
			ArrayList<WindowType> dependencies = new ArrayList<WindowType>();
			dependencies.add(SURVEY_WINDOW);
			
			return dependencies;
		}
	}, 
	
	/**
	 * Notification of a closed survey.
	 */
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
	 * By default no window has any dependencies. Override method 
	 * to set them.
	 * @return List<WindowType> list of windows that this WindowType is dependent of.
	 */
	protected List<WindowType> retreiveChildWindows() {
		return null;
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
