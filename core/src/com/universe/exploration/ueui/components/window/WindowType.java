/**
 * 
 */
package com.universe.exploration.ueui.components.window;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.localization.LocalKey;
import com.universe.exploration.localization.Localizer;

/**
 * <p>
 * Contains window caption and ok button text. Referenced as {@link LocalKey},
 * not direct strings.
 * </p>
 * 
 * @author 1.11.2015 Teemu Puurunen
 *
 */
public enum WindowType {
    /**
     * Open up details for sending a survey team.
     */
    SURVEY_WINDOW(LocalKey.TITLE_SURVEY_PLANET, LocalKey.BTN_SURVEY),

    /**
     * Game over window.
     */
    GAME_OVER(LocalKey.TITLE_GAME_OVER, LocalKey.BTN_TRY_AGAIN),

    /**
     * List of planet general details. Allows to open up e.g.
     * {@link #SURVEY_WINDOW}.
     */
    PLANET_DETAILS(LocalKey.TITLE_SURVEY_PLANET_CONFIGURATION_SCREEN, LocalKey.BTN_SURVEY) {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.universe.exploration.ueui.WindowType#retreiveDependencies()
	 */
	@Override
	public List<WindowType> retreiveChildWindows() {
	    ArrayList<WindowType> dependencies = new ArrayList<WindowType>();
	    dependencies.add(SURVEY_WINDOW);

	    return dependencies;
	}
    },
    
    CREW_MANAGEMENT(LocalKey.TITLE_CREW_MANAGEMENT, LocalKey.BTN_OK),

    /**
     * Notification of a closed survey.
     */
    SURVEY_CLOSED(LocalKey.TITLE_SURVEY, LocalKey.BTN_SURVEY);

    private final LocalKey caption;

    private final LocalKey okButtonCaption;

    /**
     * Contains window caption and ok button text.
     */
    private WindowType(LocalKey caption, LocalKey okButtonCaption) {
	this.caption = caption;
	this.okButtonCaption = okButtonCaption;
    }

    /**
     * By default no window has any dependencies. Override method to set them.
     * 
     * @return List<WindowType> list of windows that this WindowType is
     *         dependent of.
     */
    public List<WindowType> retreiveChildWindows() {
	return null;
    }

    /**
     * @return the caption
     */
    public String getLocalizedCaption() {
	return Localizer.get(caption);
    }

    /**
     * @return the okButtonCaption
     */
    public String getLocalizedOkButtonCaption() {
	return Localizer.get(okButtonCaption);
    }
}
