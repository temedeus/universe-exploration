/**
 *
 */
package com.universe.exploration.component.dialog;


import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Contains window caption and ok button text.
 * </p>
 *
 * @author 1.11.2015 Teemu Puurunen
 */
public enum DialogType {
    QUIT_WINDOW("TITLE_QUIT_GAME", "BTN_OK", DialogSetup.SMALL, true),

    /**
     * Open up details for sending a survey team.
     */
    SURVEY_WINDOW("TITLE_SURVEY_PLANET", "BTN_SURVEY", DialogSetup.LARGE, true),

    OPTIONS_WINDOW("TITLE_OPTIONS", "BTN_SAVE_SETTINGS", DialogSetup.MEDIUM, false),

    /**
     * Game over window.
     */
    GAME_OVER("TITLE_GAME_OVER", "BTN_TRY_AGAIN", DialogSetup.SMALL, true),

    /**
     * List of planet general details. Allows to open up e.g.
     * {@link #SURVEY_WINDOW}.
     */
    PLANET_DETAILS("TITLE_SURVEY_PLANET_CONFIGURATION_SCREEN", "BTN_SURVEY", DialogSetup.LARGE, true),

    SURVEY_MANAGEMENT("TITLE_SURVEY_MANAGEMENT", "BTN_OK", DialogSetup.MEDIUM, false) {
        /*
         * (non-Javadoc)
         *
         * @see
         * com.universe.exploration.userinterface.components.window.WindowType#
         * relatedViews()
         */
        @Override
        public List<DialogType> relatedViews() {
            List<DialogType> dependencies = new ArrayList<>();
            dependencies.add(SURVEY_DETAILS);

            return dependencies;
        }
    },

    SURVEY_DETAILS("TITLE_SURVEY_DETAILS", "BTN_OK", DialogSetup.LARGE, false) {
        @Override
        public List<DialogType> relatedViews() {
            List<DialogType> dependencies = new ArrayList<>();
            dependencies.add(PLANET_DETAILS);
            return dependencies;
        }
    },

    CREW_MANAGEMENT("TITLE_CREW_MANAGEMENT", "BTN_OK", DialogSetup.LARGE, false) {
        /*
         * (non-Javadoc)
         *
         * @see com.universe.exploration.ueui.components.window.WindowType#
         * relatedViews()
         */
        @Override
        public List<DialogType> relatedViews() {
            List<DialogType> dependencies = new ArrayList<>();
            dependencies.add(CREWMEMBER_DETAILS);

            return dependencies;
        }
    },

    CREWMEMBER_DETAILS("TITLE_CREWMEMBER_DETAILS", "BTN_OK", DialogSetup.LARGE, false),

    /**
     * Notification of a closed survey.
     */
    SURVEY_CLOSED("TITLE_SURVEY", "BTN_SURVEY", DialogSetup.LARGE, false);

    private final String caption;

    private final String okButtonCaption;

    private final DialogSetup dialogSetup;

    private final boolean hasCancelButton;

    /**
     * Contains window caption and ok button text.
     */
    DialogType(String caption, String okButtonCaption, DialogSetup dialogSetup, boolean hasCancelButton) {
        this.caption = caption;
        this.okButtonCaption = okButtonCaption;
        this.dialogSetup = dialogSetup;
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
    public List<DialogType> relatedViews() {
        return null;
    }

    /**
     * @return the caption
     */
    public String getLocalizedCaption() {
        return caption;
    }

    /**
     * @return the okButtonCaption
     */
    public String getLocalizedOkButtonCaption() {
        return okButtonCaption;
    }

    /**
     * @return the windowSetup
     */
    public DialogSetup getDialogSetup() {
        return dialogSetup;
    }

    /**
     * @return the hasCancelButton
     */
    public boolean isHasCancelButton() {
        return hasCancelButton;
    }
}
