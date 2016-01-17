/**
 * 
 */
package com.universe.exploration.localization;

/**
 * <p>References to localization keys. This way we don't have to use magic strings around the code.</p>
 * 
 * @author 12.11.2015 Teemu Puurunen 
 *
 */
public enum LocalKeys {
	// Titles.
	TITLE_SURVEY_PLANET("TITLE_SURVEY_PLANET"),
	TITLE_QUIT_GAME("TITLE_QUIT_GAME"),
	TITLE_SURVEY_CLOSED("TITLE_SURVEY_CLOSED"),
	TITLE_GAME_OVER("TITLE_GAME_OVER"),
	TITLE_SURVEY_CONFIGURATION_SCREEN("TITLE_SURVEY_PLANET_CONFIGURATION_SCREEN"),
	TITLE_SURVEY("TITLE_SURVEY"),
	
	// Label texts.
	LABEL_NO_PLANETS_FOUND("LABEL_NO_PLANETS_FOUND"),
	
	// description texts.
	DESC_HYPERSPACE_JUMP("DESC_HYPERSPACE_JUMP"),
	
	// Buttons.
	BTN_SURVEY("BTN_SURVEY"),
	BTN_TRY_AGAIN("BUTTON_TRY_AGAIN"),
	BTN_CANCEL("BTN_CANCEL"),
	BTN_QUIT_GAME("BTN_QUIT_GME");
	
	private final String localKey;
	
	/**
	 * Localization keys.
	 */
	private LocalKeys(String localKey) {
		this.localKey = localKey;
	}

	/**
	 * @return the localKey
	 */
	public String getLocalKey() {
		return localKey;
	}
}
