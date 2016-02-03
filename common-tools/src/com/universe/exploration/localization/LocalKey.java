/**
 * 
 */
package com.universe.exploration.localization;

/**
 * <p>
 * References to localization keys. This way we don't have to use magic strings
 * around the code.
 * </p>
 * 
 * @author 12.11.2015 Teemu Puurunen
 *
 */
public enum LocalKey {
    // Titles.
    TITLE_SURVEY_PLANET,
    TITLE_QUIT_GAME,
    TITLE_SURVEY_CLOSED,
    TITLE_GAME_OVER,
    TITLE_SURVEY_PLANET_CONFIGURATION_SCREEN,
    TITLE_SURVEY,
    TITLE_TIME,
    TITLE_CREWMEN_LEFT,
    TITLE_AIR,
    TITLE_WATER,
    TITLE_FOOD,
    TITLE_POWER,
    TITLE_SURVEY_TEAM_SURVIVED,

    SUBHEADER_ORBITAL_RADIUS,
    SUBHEADER_PRESENT_LIFEFORMS,
    SUBHEADER_WATER_FOUND,
    SUBHEADER_FOOD_FOUND,
    SUBHEADER_OXYGEN_PRESENT,

    // Label texts.
    LABEL_NO_PLANETS_FOUND,
    LABEL_CREWMEN_COUNT,
    LABEL_PLANET_SELECTION,

    // description texts.
    DESC_HYPERSPACE_JUMP,
    DESC_PLANET_TYPE,
    DESC_HYPERSPACE_JUMP_COMMENCED,

    // Buttons.
    BTN_SURVEY,
    BTN_TRY_AGAIN,
    BTN_CANCEL,
    BTN_QUIT_GAME,
    BTN_OK,
    BTN_HYPERSPACE_JUMP,
    BTN_MIN_VOLUME,
    BTN_MAX_VOLUME,
    BTN_CREW_CONTROL,

    // Generic terms
    GENERIC_AIR,
    GENERIC_WATER,
    GENERIC_FOOD,

    GENERAL_YES,
    GENERAL_NO,
    GENERAL_NONE,
    GENERIC_LIQUID_WATER;

    /**
     * Localization keys.
     */
    private LocalKey() {
    }
}
