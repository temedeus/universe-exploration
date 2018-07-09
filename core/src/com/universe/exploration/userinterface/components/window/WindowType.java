/**
 *
 */
package com.universe.exploration.userinterface.components.window;

import com.universe.exploration.localization.Localizer;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Contains window caption and ok button text.
 * </p>
 *
 * @author 1.11.2015 Teemu Puurunen
 */
public enum WindowType {
    QUIT_WINDOW("TITLE_QUIT_GAME", "BTN_OK", WindowSetup.SMALL, true),

    /**
     * Open up details for sending a survey team.
     */
    SURVEY_WINDOW("TITLE_SURVEY_PLANET", "BTN_SURVEY", WindowSetup.LARGE, true),

    OPTIONS_WINDOW("TITLE_OPTIONS", "BTN_SAVE_SETTINGS", WindowSetup.MEDIUM, true),

    /**
     * Game over window.
     */
    GAME_OVER("TITLE_GAME_OVER", "BTN_TRY_AGAIN", WindowSetup.SMALL, true),

    /**
     * List of planet general details. Allows to open up e.g.
     * {@link #SURVEY_WINDOW}.
     */
    PLANET_DETAILS("TITLE_SURVEY_PLANET_CONFIGURATION_SCREEN", "BTN_SURVEY", WindowSetup.LARGE, true),

    SURVEY_MANAGEMENT("TITLE_SURVEY_MANAGEMENT", "BTN_OK", WindowSetup.MEDIUM, false) {
        /*
         * (non-Javadoc)
         *
         * @see
         * com.universe.exploration.userinterface.components.window.WindowType#
         * relatedViews()
         */
        @Override
        public List<WindowType> relatedViews() {
            List<WindowType> dependencies = new ArrayList<WindowType>();
            dependencies.add(SURVEY_DETAILS);

            return dependencies;
        }
    },

    SURVEY_DETAILS("TITLE_SURVEY_DETAILS", "BTN_OK", WindowSetup.LARGE, false) {
        @Override
        public List<WindowType> relatedViews() {
            List<WindowType> dependencies = new ArrayList<WindowType>();
            dependencies.add(PLANET_DETAILS);
            return dependencies;
        }
    },

    CREW_MANAGEMENT("TITLE_CREW_MANAGEMENT", "BTN_OK", WindowSetup.LARGE, false) {
        /*
         * (non-Javadoc)
         *
         * @see com.universe.exploration.ueui.components.window.WindowType#
         * relatedViews()
         */
        @Override
        public List<WindowType> relatedViews() {
            List<WindowType> dependencies = new ArrayList<WindowType>();
            dependencies.add(CREWMEMBER_DETAILS);

            return dependencies;
        }
    },

    CREWMEMBER_DETAILS("TITLE_CREWMEMBER_DETAILS", "BTN_OK", WindowSetup.LARGE, false),

    /**
     * Notification of a closed survey.
     */
    SURVEY_CLOSED("TITLE_SURVEY", "BTN_SURVEY", WindowSetup.LARGE, false);

    private final String caption;

    private final String okButtonCaption;

    private final WindowSetup windowSetup;

    private final boolean hasCancelButton;

    /**
     * Contains window caption and ok button text.
     */
    WindowType(String caption, String okButtonCaption, WindowSetup windowSetup, boolean hasCancelButton) {
        this.caption = caption;
        this.okButtonCaption = okButtonCaption;
        this.windowSetup = windowSetup;
        this.hasCancelButton = hasCancelButton;
    }

    /**
     * By default no window has any dependencies. Override method to set them.
     * If window has dependencies, closing this given window will close these
     * other windows as well.
     *
     * @return List<WindowType> list of windows that this WindowType is
     * dependent of.
     */
    public List<WindowType> relatedViews() {
        return null;
    }

    public boolean windowPausesGame() {
        return false;
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

    /**
     * @return the windowSetup
     */
    public WindowSetup getWindowSetup() {
        return windowSetup;
    }

    /**
     * @return the hasCancelButton
     */
    public boolean isHasCancelButton() {
        return hasCancelButton;
    }
}
