/**
 * 
 */
package com.universe.exploration.userinterface.components.window;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.localization.Localizer;

/**
 * <p>
 * Contains window caption and ok button text. Referenced as {@link LocalKey},
 * not direct strings.
 * </p>
 * 
 * TODO: Maybe {@link BasicWindow} could incorporate type within?
 * 
 * @author 1.11.2015 Teemu Puurunen
 *
 */
public enum WindowType {
    /**
     * Open up details for sending a survey team.
     */
    SURVEY_WINDOW("TITLE_SURVEY_PLANET", "BTN_SURVEY"),
    
    OPTIONS_WINDOW("TITLE_OPTIONS", "BTN_SAVE_SETTINGS"),
    
    /**
     * Game over window.
     */
    GAME_OVER("TITLE_GAME_OVER", "BTN_TRY_AGAIN"),

    /**
     * List of planet general details. Allows to open up e.g.
     * {@link #SURVEY_WINDOW}.
     */
    PLANET_DETAILS("TITLE_SURVEY_PLANET_CONFIGURATION_SCREEN", "BTN_SURVEY") {
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

    CREW_MANAGEMENT("TITLE_CREW_MANAGEMENT", "BTN_OK") {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.universe.exploration.ueui.components.window.WindowType#
	 * retreiveChildWindows()
	 */
	@Override
	public List<WindowType> retreiveChildWindows() {
	    ArrayList<WindowType> dependencies = new ArrayList<WindowType>();
	    dependencies.add(CREWMEMBER_DETAILS);

	    return dependencies;
	}
    },

    CREWMEMBER_DETAILS("TITLE_CREWMEMBER_DETAILS", "BTN_OK"),

    /**
     * Notification of a closed survey.
     */
    SURVEY_CLOSED("TITLE_SURVEY", "BTN_SURVEY");

    private final String caption;

    private final String okButtonCaption;

    /**
     * Contains window caption and ok button text.
     */
    private WindowType(String caption, String okButtonCaption) {
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
	return Localizer.getInstance().get(caption);
    }

    /**
     * @return the okButtonCaption
     */
    public String getLocalizedOkButtonCaption() {
	return Localizer.getInstance().get(okButtonCaption);
    }
}
