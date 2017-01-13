/**
 * 
 */
package com.universe.exploration.userinterface.data.container;

import com.universe.exploration.GameStatus;
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
	add(PlayerStatusItemkeys.TIME, Localizer.getInstance().get("TITLE_TIME"), String.valueOf(CoreConfiguration.TIME_START + ""));
	add(PlayerStatusItemkeys.CREWMEN, Localizer.getInstance().get("TITLE_CREWMEN_LEFT"), String.valueOf(CoreConfiguration.MAX_CREWMEN));
	add(PlayerStatusItemkeys.CREWMEN_ON_SURVEY, Localizer.getInstance().get("TITLE_CREWMEN_ON_SURVEY"), String.valueOf(0));
	add(PlayerStatusItemkeys.AIR, Localizer.getInstance().get("TITLE_AIR"), String.valueOf(CoreConfiguration.MAX_AIR));
	add(PlayerStatusItemkeys.WATER, Localizer.getInstance().get("TITLE_WATER"), String.valueOf(CoreConfiguration.MAX_AIR + " %"));
	add(PlayerStatusItemkeys.FOOD, Localizer.getInstance().get("TITLE_FOOD"), String.valueOf(CoreConfiguration.MAX_FOOD + " %"));
	add(PlayerStatusItemkeys.POWER, Localizer.getInstance().get("TITLE_POWER"), String.valueOf(CoreConfiguration.MAX_POWER + " %"));
    }

    public void updateHUDValues(CrewStatusManager playerStatus) {
	update(PlayerStatusItemkeys.TIME, String.valueOf((int) playerStatus.getTime() + " days"));
	update(PlayerStatusItemkeys.AIR, String.valueOf((int) playerStatus.getAir()));
	update(PlayerStatusItemkeys.CREWMEN, String.valueOf((int) GameStatus.getCrew().getCrewMenAboardSpaceShip().size()));
	update(PlayerStatusItemkeys.CREWMEN_ON_SURVEY, String.valueOf((int) GameStatus.getCrew().getCrewMenOnSurvey().size()));
	update(PlayerStatusItemkeys.WATER, String.valueOf((int) playerStatus.getWater()));
	update(PlayerStatusItemkeys.FOOD, String.valueOf((int) playerStatus.getFood()));
	update(PlayerStatusItemkeys.POWER, String.valueOf((int) playerStatus.getPower() + " %"));
    }
}
