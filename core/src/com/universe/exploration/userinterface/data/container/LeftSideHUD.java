/**
 * 
 */
package com.universe.exploration.userinterface.data.container;

import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.localization.LocalKey;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.player.PlayerStatus;
import com.universe.exploration.player.PlayerStatusItemkeys;
import com.universe.exploration.userinterface.data.DataPair;

/**
 * @author 1.10.2015 Teemu Puurunen TODO: perhaps you could link this to
 *         PlayerStatus?
 */
public class LeftSideHUD extends DataPairContainer {

    /*
     * (non-Javadoc)
     * 
     * @see com.universe.exploration.ui.data.DataAndValueContainer#createPairs()
     */
    @Override
    public void createPairs() {
	add(new DataPair(PlayerStatusItemkeys.TIME, Localizer.getInstance().get(LocalKey.TITLE_TIME), "" + CoreConfiguration.TIME_START + ""));
	add(new DataPair(PlayerStatusItemkeys.CREWMEN, Localizer.getInstance().get(LocalKey.TITLE_CREWMEN_LEFT), "" + CoreConfiguration.MAX_CREWMEN));
	add(new DataPair(PlayerStatusItemkeys.AIR, Localizer.getInstance().get(LocalKey.TITLE_AIR), "" + CoreConfiguration.MAX_AIR + " %"));
	add(new DataPair(PlayerStatusItemkeys.WATER, Localizer.getInstance().get(LocalKey.TITLE_WATER), "" + CoreConfiguration.MAX_AIR + " %"));
	add(new DataPair(PlayerStatusItemkeys.FOOD, Localizer.getInstance().get(LocalKey.TITLE_FOOD), "" + CoreConfiguration.MAX_FOOD + " %"));
	add(new DataPair(PlayerStatusItemkeys.POWER, Localizer.getInstance().get(LocalKey.TITLE_POWER), "" + CoreConfiguration.MAX_POWER + " %"));
    }

    public void updateHUDValues(PlayerStatus playerStatus) {
	update(PlayerStatusItemkeys.TIME, "" + (int) playerStatus.getTime() + " days");
	update(PlayerStatusItemkeys.AIR, playerStatusValueToHUDString("" + (int) playerStatus.getAir()));
	update(PlayerStatusItemkeys.CREWMEN, "" + (int) playerStatus.getCrewmen());
	update(PlayerStatusItemkeys.WATER, "" + (int) playerStatus.getWater() + " litres");
	update(PlayerStatusItemkeys.FOOD, "" + (int) playerStatus.getFood() + " kcal");
	update(PlayerStatusItemkeys.POWER, playerStatusValueToHUDString("" + (int) playerStatus.getPower()));
    }

    private String playerStatusValueToHUDString(Object val) {
	return val + " %";
    }
}
