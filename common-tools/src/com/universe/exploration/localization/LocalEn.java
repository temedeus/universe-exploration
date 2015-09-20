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
	BTN_QUIT_GAME("Quit game"),
	BTN_HYPERSPACE_JUMP("Hyperspace jump"),
	// Titles
	TITLE_QUIT_GAME("Quit game"),
	TITLE_CREWMEN_LEFT("Crewmen left:"),
	TITLE_SURVEY_PLANET("Survey planet:"),
	// Descriptions
	DESC_QUIT_GAME("Are you sure you wish to quit?"),
	DESC_HYPERSPACE_JUMP("Preparing for hyperspace jump..."),
	DESC_PLANET_TYPE("Planet type:");
	
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
