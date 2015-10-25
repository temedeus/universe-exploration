/**
 * 
 */
package com.universe.exploration.ueui.data.container;

import com.universe.exploration.CoreConfiguration;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.player.PlayerStatusItemkeys;
import com.universe.exploration.ueui.data.DataPair;

/**
 * @author 1.10.2015 Teemu Puurunen 
 *TODO: perhaps you could link this to PlayerStatus?
 */
public class LeftSideHUD extends DataPairContainer {

	/* (non-Javadoc)
	 * @see com.universe.exploration.ui.data.DataAndValueContainer#createPairs()
	 */
	@Override
	public void createPairs() {
		add(new DataPair(PlayerStatusItemkeys.TIME, Localizer.get("TITLE_TIME"), "" + CoreConfiguration.TIME_START + ""));
		add(new DataPair(PlayerStatusItemkeys.CREWMEN, Localizer.get("TITLE_CREWMEN_LEFT"), "" + CoreConfiguration.MAX_CREWMEN));
		add(new DataPair(PlayerStatusItemkeys.AIR, Localizer.get("Air:"), "" + CoreConfiguration.MAX_AIR + " %"));
		add(new DataPair(PlayerStatusItemkeys.WATER, Localizer.get("Water:"), "" + CoreConfiguration.MAX_AIR + " %"));
		add(new DataPair(PlayerStatusItemkeys.FOOD, Localizer.get("Food:"), "" + CoreConfiguration.MAX_FOOD + " %"));
		add(new DataPair(PlayerStatusItemkeys.POWER, Localizer.get("Power"), "" + CoreConfiguration.MAX_POWER + " %"));
	}
}
