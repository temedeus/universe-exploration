/**
 * 
 */
package com.universe.exploration.ueui.data;

import com.universe.exploration.CoreConfiguration;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.player.PlayerStatusItemkeys;

/**
 * @author 1.10.2015 Teemu Puurunen 
 *TODO: perhaps you could link this to PlayerStatus?
 */
public class LeftSideHUD extends DataAndValueContainer {

	/* (non-Javadoc)
	 * @see com.universe.exploration.ui.data.DataAndValueContainer#createPairs()
	 */
	@Override
	public void createPairs() {
		add(new DataAndValuePair(PlayerStatusItemkeys.CREWMEN, Localizer.get("TITLE_CREWMEN_LEFT"), "" + CoreConfiguration.MAX_CREWMEN));
		add(new DataAndValuePair(PlayerStatusItemkeys.AIR, Localizer.get("Air:"), "" + CoreConfiguration.MAX_AIR + " %"));
		add(new DataAndValuePair(PlayerStatusItemkeys.WATER, Localizer.get("Water:"), "" + CoreConfiguration.MAX_AIR + " %"));
		add(new DataAndValuePair(PlayerStatusItemkeys.FOOD, Localizer.get("Food:"), "" + CoreConfiguration.MAX_FOOD + " %"));
		add(new DataAndValuePair(PlayerStatusItemkeys.POWER, Localizer.get("Power"), "" + CoreConfiguration.MAX_POWER + " %"));
	}
}
