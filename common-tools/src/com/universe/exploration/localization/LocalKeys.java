/**
 * 
 */
package com.universe.exploration.localization;

/**
 * @author 12.11.2015 Teemu Puurunen 
 *
 */
public enum LocalKeys {
	TITLE_SURVEY_PLANET("TITLE_SURVEY_PLANET"),
	TITLE_QUIT_GAME("TITLE_QUIT_GAME");
	
	private final String localKey;
	
	/**
	 * 
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
