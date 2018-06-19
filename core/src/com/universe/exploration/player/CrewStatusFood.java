/**
 *
 */
package com.universe.exploration.player;

import com.universe.exploration.crew.CrewMemberCondition;
import com.universe.exploration.resource.Food;
import com.universe.exploration.resource.Resource;

/**
 * @author 14.1.2017 Teemu Puurunen
 */
public class CrewStatusFood extends CrewStatus {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.universe.exploration.player.ICrewStatus#mapCrewStatusToResource()
     */
    @Override
    public Class<? extends Resource> mapCrewStatusToResource() {
        return Food.class;
    }

    /* (non-Javadoc)
     * @see com.universe.exploration.player.ICrewStatus#deprivationCausesCondition()
     */
    @Override
    public CrewMemberCondition deprivationCausesCondition() {
        return CrewMemberCondition.MALNUTRITION;
    }

    /* (non-Javadoc)
     * @see com.universe.exploration.player.ICrewStatus#getSetup()
     */
    @Override
    public CrewStatusSetup getSetup() {
        return CrewStatusSetup.FOOD;
    }

    /* (non-Javadoc)
     * @see com.universe.exploration.player.ICrewStatus#healthDecreaseWhenDepleated()
     */
    @Override
    public float healthDecreaseWhenDepleated() {
        return StatusConsumption.HEALTH_DECREASE_WHEN_FOOD_DEPLETED;
    }

    /* (non-Javadoc)
     * @see com.universe.exploration.player.ICrewStatus#getDecrement()
     */
    @Override
    public float getDecrement() {
        return StatusConsumption.CREWMEN_FOOD_CONSUMPTION_PER_CREWMAN;
    }
}
