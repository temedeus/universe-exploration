/**
 *
 */
package com.universe.exploration.component.dialog;


import com.universe.exploration.UniverseExploration;

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
    EXIT_GAME("TITLE_EXIT_GAME", "BTN_OK", DialogSetup.MEDIUM, true),
    START_GAME("TITLE_START_GAME", "BTN_START_GAME", DialogSetup.LARGE, true),
    ENGAGE_SURVEY_MODE("TITLE_ENGAGE_SURVEY_MODE", "BTN_ENGAGE_SURVEY_MODE", DialogSetup.LARGE, true),
    SETTINGS("TITLE_OPTIONS", "BTN_SAVE_SETTINGS", DialogSetup.LARGE, true);

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
        return UniverseExploration.getLocaliser().get(caption);
    }

    /**
     * @return the okButtonCaption
     */
    public String getLocalizedOkButtonCaption() {
        return UniverseExploration.getLocaliser().get(okButtonCaption);
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
