/**
 *
 */
package com.universe.exploration.player;

import com.universe.exploration.crew.CrewMemberCondition;
import com.universe.exploration.resource.Air;
import com.universe.exploration.resource.Resource;

/**
 * @author 14.1.2017 Teemu Puurunen
 */
public class CrewStatusAir extends CrewStatus {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.universe.exploration.player.ICrewStatus#mapCrewStatusToResource()
     */
    @Override
    public Class<? extends Resource> mapCrewStatusToResource() {
        return Air.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.universe.exploration.player.ICrewStatus#deprivationCausesCondition()
     */
    @Override
    public CrewMemberCondition deprivationCausesCondition() {
        return CrewMemberCondition.OXYGEN_DEPRIVATION;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.universe.exploration.player.ICrewStatus#getSetup()
     */
    @Override
    public CrewStatusSetup getSetup() {
        return CrewStatusSetup.AIR;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.universe.exploration.player.ICrewStatus#healthDecreaseWhenDepleated()
     */
    @Override
    public float healthDecreaseWhenDepleated() {
        return StatusConsumption.HEALTH_DECREASE_WHEN_AIR_DEPLETED;
    }

    /* (non-Javadoc)
     * @see com.universe.exploration.player.ICrewStatus#getDecrement()
     */
    @Override
    public float getDecrement() {
        return StatusConsumption.AIR_DECREMENT;
    }

}
