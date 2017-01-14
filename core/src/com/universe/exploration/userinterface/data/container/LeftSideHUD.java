/**
 * 
 */
package com.universe.exploration.userinterface.data.container;

import com.universe.exploration.UniverseExploration;
import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.player.CrewStatusManager;
import com.universe.exploration.player.CrewStatusSetup;
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
	add(PlayerStatusItemkeys.TIME, Localizer.getInstance().get("TITLE_TIME"), String.valueOf(CoreConfiguration.TIME_START + ""));
	add(PlayerStatusItemkeys.CREWMEN, Localizer.getInstance().get("TITLE_CREWMEN_LEFT"), String.valueOf(CoreConfiguration.MAX_CREWMEN));
	add(PlayerStatusItemkeys.CREWMEN_ON_SURVEY, Localizer.getInstance().get("TITLE_CREWMEN_ON_SURVEY"), String.valueOf(0));
	add(PlayerStatusItemkeys.AIR, Localizer.getInstance().get("TITLE_AIR"), String.valueOf(CrewStatusSetup.AIR.getMaxValue()));
	add(PlayerStatusItemkeys.WATER, Localizer.getInstance().get("TITLE_WATER"),
		String.valueOf(CrewStatusSetup.WATER.getMaxValue() + " %"));
	add(PlayerStatusItemkeys.FOOD, Localizer.getInstance().get("TITLE_FOOD"),
		String.valueOf(CrewStatusSetup.FOOD.getMaxValue() + " %"));
	add(PlayerStatusItemkeys.POWER, Localizer.getInstance().get("TITLE_POWER"), String.valueOf(CoreConfiguration.MAX_POWER + " %"));
    }

    public void updateHUDValues(CrewStatusManager playerStatus) {
	update(PlayerStatusItemkeys.TIME, String.valueOf((int) playerStatus.getTime() + " days"));
	update(PlayerStatusItemkeys.AIR, String.valueOf((int) playerStatus.getStatusValue(CrewStatusSetup.AIR)));
	update(PlayerStatusItemkeys.CREWMEN, String.valueOf((int) UniverseExploration.gameStatus.getCrew().getCrewMenAboardSpaceShip().size()));
	update(PlayerStatusItemkeys.CREWMEN_ON_SURVEY, String.valueOf((int) UniverseExploration.gameStatus.getCrew().getCrewMenOnSurvey().size()));
	update(PlayerStatusItemkeys.WATER, String.valueOf((int) playerStatus.getStatusValue(CrewStatusSetup.WATER)));
	update(PlayerStatusItemkeys.FOOD, String.valueOf((int) playerStatus.getStatusValue(CrewStatusSetup.FOOD)));
	update(PlayerStatusItemkeys.POWER, String.valueOf((int) playerStatus.getPower() + " %"));
    }
}
