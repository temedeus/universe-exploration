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
public class LeftSideHUD extends TitleAndValueContainer {

	/* (non-Javadoc)
	 * @see com.universe.exploration.ui.data.DataAndValueContainer#createPairs()
	 */
	@Override
	public void createPairs() {
		add(new TitleAndValuePair(PlayerStatusItemkeys.CREWMEN, Localizer.get("TITLE_CREWMEN_LEFT"), "" + CoreConfiguration.MAX_CREWMEN));
		add(new TitleAndValuePair(PlayerStatusItemkeys.AIR, Localizer.get("Air:"), "" + CoreConfiguration.MAX_AIR + " %"));
		add(new TitleAndValuePair(PlayerStatusItemkeys.WATER, Localizer.get("Water:"), "" + CoreConfiguration.MAX_AIR + " %"));
		add(new TitleAndValuePair(PlayerStatusItemkeys.FOOD, Localizer.get("Food:"), "" + CoreConfiguration.MAX_FOOD + " %"));
		add(new TitleAndValuePair(PlayerStatusItemkeys.POWER, Localizer.get("Power"), "" + CoreConfiguration.MAX_POWER + " %"));
	}
}
