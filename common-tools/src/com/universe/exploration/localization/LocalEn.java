/**
 * 
 */
package com.universe.exploration.localization;



/**
 * TODO: XML based localization
 * @author 25.8.2015 Teemu Puurunen 
 *
 */
public enum LocalEn {
	// Buttons
	BTN_OK("OK"),
	BTN_CANCEL("Cancel"),
	BTN_TRY_AGAIN("Try again"),
	BTN_QUIT_GAME("Quit game"),
	BTN_HYPERSPACE_JUMP("Hyperspace jump"),
	BTN_SURVEY("Survey"),
	
	BTN_MAX_VOLUME("Max volume"),
	BTN_MIN_VOLUME("Min volume"),
	
	// Titles
	TITLE_QUIT_GAME("Quit game"),
	TITLE_CREWMEN_LEFT("Crewmen left: "),
	TITLE_TIME("Time: "),
	TITLE_SURVEY_PLANET("Planetary survey"),
	TITLE_GAME_OVER("Game over"),
	TITLE_LIFEFORMS_CIVILIZED("civilized"),
	TITLE_LIFEFORMS_ANIMAL("animal"),
	TITLE_LIFEFORMS_VEGETATION("vegetation"),
	TITLE_LIFEFORMS_BACTERIAL("bacterial"),
	TITLE_LIFEFORMS_NONE("none"),
	TITLE_SURVEY_PLANET_CONFIGURATION_SCREEN("Survey team crew members:"),
	TITLE_SURVEY_CLOSED("Survey closed"),
	TITLE_SURVEY("Survey"),
	TITLE_AIR("Air:"),
	TITLE_WATER("Water: "),
	TITLE_POWER("Power: "),
	TITLE_SURVEY_TEAM_SURVIVED("Entire survey team came back alive!"),
	TITLE_FOOD("Food: "),
	
	// Labels
	LABEL_CREWMEN_COUNT("Crewmen count:"),
	LABEL_PLANET_SELECTION("Select planet:"),
	LABEL_NO_PLANETS_FOUND("No planets found"),
	
	// Descriptions
	DESC_QUIT_GAME("Are you sure you wish to quit?"),
	DESC_HYPERSPACE_JUMP("Preparing for hyperspace jump..."),
	DESC_PLANET_TYPE("Planet type: "),
	
	SUBHEADER_FOOD_FOUND("Food: "),
	SUBHEADER_WATER_FOUND("Water: "),
	SUBHEADER_OXYGEN_PRESENT("Oxygen: "),
	SUBHEADER_PRESENT_LIFEFORMS("Lifeforms: "),
	SUBHEADER_ORBITAL_VELOCITY("Orbital velocity: "),
	SUBHEADER_ORBITAL_RADIUS("Orbital radius: "),
	
	DESC_PRESENT_LIFEFORMS_CIVILIZED("civilized"),
	DESC_PRESENT_LIFEFORMS_ANIMAL("animal"),
	DESC_PRESENT_LIFEFORMS_BACTERIAL("bacterial"),
	
	
	// General terms. Should be safe to use all around as these should not change (or you are attempting to do something wrong).
	GENERAL_YES("Yes"),
	GENERAL_NO("No"),
	GENERAL_NONE("None"),
	
	// Cause of death
	HELMET_BREACH("Crewman died because of a helmet breach"),
	RIP_ON_SUIT("Crewman had a rip on suit."),
	
	// Animals present
	ATTACKED_BY_ANIMALS("Due to unfortunate attack by local wildlife a crewman was lost!"),
	
	// Civilization present
	KILLED_BY_LOCALS("Due to native population a casualty was met!"),
	SEDUCED_BY_AMAZONS("One of your crew decided to stay on the planet because of he was seduced by a native wildling!"),
	
	// General
	FELL_OFF_CLIFF("Crewman met his early demise because of an unfortunate step into oblivion on a cliff."),
	MALNUTRITION("Crewman got lost and died of hunger."),
	CREWMEN_WENT_NUTS("Crewman could no longer take it and took his own life."),
	WATER_DEPRIVATION("Crewman got lost and died of dehydration"),
	
	// Generic air
	GENERIC_AIR("air"),
	GENERIC_FOOD("food"),
	GENERIC_WATER("water"),
	GENERIC_LIQUID_WATER("Liquid water");
	
	private final String description;

	LocalEn(String description) {
		this.description = description;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
}
