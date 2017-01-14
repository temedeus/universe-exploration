package com.universe.exploration.player;

import com.universe.exploration.crew.CrewMemberCondition;
import com.universe.exploration.resource.Resource;
import com.universe.exploration.resource.Water;

/**
 * Describes water status of a crew.
 * 
 * @author 14.1.2017 Teemu Puurunen
 *
 */
public class CrewStatusWater extends CrewStatus {
    protected float value;

    /**
     * 
     */
    public CrewStatusWater() {
	value = 200;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.universe.exploration.player.ICrewStatus#mapCrewStatusToResource()
     */
    @Override
    public Class<? extends Resource> mapCrewStatusToResource() {
	return Water.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.universe.exploration.player.ICrewStatus#deprivationCausesCondition()
     */
    @Override
    public CrewMemberCondition deprivationCausesCondition() {
	return CrewMemberCondition.WATER_DEPRIVATION;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.universe.exploration.player.ICrewStatus#getSetup()
     */
    @Override
    public CrewStatusSetup getSetup() {
	return CrewStatusSetup.WATER;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.universe.exploration.player.ICrewStatus#healthDecreaseWhenDepleated()
     */
    @Override
    public float healthDecreaseWhenDepleated() {
	return StatusConsumption.HEALTH_DECREASE_WHEN_WATER_DEPLETED;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.universe.exploration.player.ICrewStatus#getDecrement()
     */
    @Override
    public float getDecrement() {
	return StatusConsumption.CREWMEN_WATER_CONSUMPTION_PER_CREWMAN;
    }
}
