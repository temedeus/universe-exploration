/**
 * 
 */
package com.universe.exploration.userinterface.data.container;

import com.universe.exploration.UniverseExploration;
import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.localization.LocalKey;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.player.CrewStatusManager;
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
	add(new DataPair(PlayerStatusItemkeys.TIME, Localizer.getInstance().get(LocalKey.TITLE_TIME), "" + CoreConfiguration.TIME_START
		+ ""));
	add(new DataPair(PlayerStatusItemkeys.CREWMEN, Localizer.getInstance().get(LocalKey.TITLE_CREWMEN_LEFT), ""
		+ CoreConfiguration.MAX_CREWMEN));
	add(new DataPair(PlayerStatusItemkeys.CREWMEN_ON_SURVEY, Localizer.getInstance().get(LocalKey.TITLE_CREWMEN_ON_SURVEY), "" + 0));
	add(new DataPair(PlayerStatusItemkeys.AIR, Localizer.getInstance().get(LocalKey.TITLE_AIR), "" + CoreConfiguration.MAX_AIR));
	add(new DataPair(PlayerStatusItemkeys.WATER, Localizer.getInstance().get(LocalKey.TITLE_WATER), "" + CoreConfiguration.MAX_AIR
		+ " %"));
	add(new DataPair(PlayerStatusItemkeys.FOOD, Localizer.getInstance().get(LocalKey.TITLE_FOOD), "" + CoreConfiguration.MAX_FOOD
		+ " %"));
	add(new DataPair(PlayerStatusItemkeys.POWER, Localizer.getInstance().get(LocalKey.TITLE_POWER), "" + CoreConfiguration.MAX_POWER
		+ " %"));
    }

    public void updateHUDValues(CrewStatusManager playerStatus) {
	update(PlayerStatusItemkeys.TIME, "" + (int) playerStatus.getTime() + " days");
	update(PlayerStatusItemkeys.AIR, "" + (int) playerStatus.getAir());
	update(PlayerStatusItemkeys.CREWMEN, "" + (int) UniverseExploration.crew.getCrewMenAboardSpaceShip().size());
	update(PlayerStatusItemkeys.CREWMEN_ON_SURVEY, "" + (int) UniverseExploration.crew.getCrewMenOnSurvey().size());
	update(PlayerStatusItemkeys.WATER, "" + (int) playerStatus.getWater());
	update(PlayerStatusItemkeys.FOOD, "" + (int) playerStatus.getFood());
	update(PlayerStatusItemkeys.POWER, "" + (int) playerStatus.getPower() + " %");
    }
}
