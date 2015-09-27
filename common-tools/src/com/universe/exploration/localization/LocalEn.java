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
	
	// Titles
	TITLE_QUIT_GAME("Quit game"),
	TITLE_CREWMEN_LEFT("Crewmen left: "),
	TITLE_SURVEY_PLANET("Planetary survey"),
	TITLE_GAME_OVER("Game over"),
	TITLE_LIFEFORMS_CIVILIZED("civilized"),
	TITLE_LIFEFORMS_ANIMAL("animal"),
	TITLE_LIFEFORMS_VEGETATION("vegetation"),
	TITLE_LIFEFORMS_BACTERIAL("bacterial"),
	TITLE_LIFEFORMS_NONE("none"),
	
	// Descriptions
	DESC_QUIT_GAME("Are you sure you wish to quit?"),
	DESC_HYPERSPACE_JUMP("Preparing for hyperspace jump..."),
	DESC_PLANET_TYPE("Planet type: "),
	SUBHEADER_PRESENT_LIFEFORMS("Lifeforms: "),
	SUBHEADER_ORBITAL_VELOCITY("Orbital velocity: "),
	SUBHEADER_ORBITAL_RADIUS("Orbital radius: "),
	DESC_PRESENT_LIFEFORMS_CIVILIZED("civilized"),
	DESC_PRESENT_LIFEFORMS_ANIMAL("animal"),
	DESC_PRESENT_LIFEFORMS_BACTERIAL("bacterial");
	
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
