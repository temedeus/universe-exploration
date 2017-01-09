/**
 * 
 */
package com.universe.exploration.userinterface.data.container;

import com.universe.exploration.UniverseExploration;
import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.player.CrewStatusManager;
import com.universe.exploration.player.PlayerStatusItemkeys;

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
	add(PlayerStatusItemkeys.TIME, Localizer.getInstance().get("TITLE_TIME"), "" + CoreConfiguration.TIME_START + "");
	add(PlayerStatusItemkeys.CREWMEN, Localizer.getInstance().get("TITLE_CREWMEN_LEFT"), "" + CoreConfiguration.MAX_CREWMEN);
	add(PlayerStatusItemkeys.CREWMEN_ON_SURVEY, Localizer.getInstance().get("TITLE_CREWMEN_ON_SURVEY"), "" + 0);
	add(PlayerStatusItemkeys.AIR, Localizer.getInstance().get("TITLE_AIR"), "" + CoreConfiguration.MAX_AIR);
	add(PlayerStatusItemkeys.WATER, Localizer.getInstance().get("TITLE_WATER"), "" + CoreConfiguration.MAX_AIR + " %");
	add(PlayerStatusItemkeys.FOOD, Localizer.getInstance().get("TITLE_FOOD"), "" + CoreConfiguration.MAX_FOOD + " %");
	add(PlayerStatusItemkeys.POWER, Localizer.getInstance().get("TITLE_POWER"), "" + CoreConfiguration.MAX_POWER + " %");
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
