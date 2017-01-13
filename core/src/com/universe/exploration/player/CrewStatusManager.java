/**
 * 
 */
package com.universe.exploration.player;

import java.util.List;

import com.universe.exploration.GameStatus;
import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.crew.CrewMemberCondition;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;

/**
 * 
 * 
 * @author 31.8.2015 Teemu Puurunen
 *
 */
public class CrewStatusManager {

    /**
     * Air left
     */
    private float air;

    private float water;

    private float food;

    private float power;

    private float time;

    private boolean warnedOnAir = false;

    private boolean warnedOnWater = false;

    private boolean warnedOnFood = false;

    private UEListener crewMemberStatusChangeListener;

    /**
     * Setup initial values. Start with full values
     */
    public CrewStatusManager() {
	time = CoreConfiguration.TIME_START;
	air = CoreConfiguration.MAX_AIR;
	water = CoreConfiguration.MAX_WATER;
	food = CoreConfiguration.MAX_FOOD;
	power = CoreConfiguration.MAX_POWER;
    }

    /**
     * 
     * @return
     * 
     */
    public void updateStatus() {
	// TODO: utilize crewmember attributes when decreasing values
	List<CrewMember> crewmen = GameStatus.getCrew().getCrewMenAboardSpaceShip();
	increaseDaysPassed();

	int crewsize = crewmen.size();
	float airDecrement = StatusConsumption.AIR_DECREMENT * crewsize;

	decreaseAirBy((power > 0) ? airDecrement : airDecrement * StatusConsumption.AIR_DECREMENT_WHEN_POWER_OUT);
	decreaseFoodBy(StatusConsumption.CREWMEN_FOOD_CONSUMPTION_PER_CREWMAN * crewsize);
	decreaseWaterBy(StatusConsumption.CREWMEN_WATER_CONSUMPTION_PER_CREWMAN * crewsize);

	updateCrewMemberStatuses(crewmen);
    }

    // TODO: maybe you could generalize these attributes?
    private void updateCrewMemberStatuses(List<CrewMember> crewmen) {
	float airDecrement = (air == 0) ? StatusConsumption.CREWMEN_DECREMENT_AIR_DEPLETED : 0;
	float foodDecrement = (food == 0) ? StatusConsumption.CREWMEN_DECREMENT_FOOD_DEPLETED : 0;
	float waterDecrement = (water == 0) ? StatusConsumption.CREWMEN_DECREMENT_WATER_DEPLETED : 0;

	if (airDecrement > 0 && !warnedOnAir) {
	    fireCrewStatusChangeListener("Warning! Air level substantially low.");
	    warnedOnAir = true;
	} 
	
	if (foodDecrement > 0 && !warnedOnWater) {
	    fireCrewStatusChangeListener("Warning! Food depleted.");
	    warnedOnWater = true;
	} 
	
	if (waterDecrement > 0 && !warnedOnFood) {
	    fireCrewStatusChangeListener("Warning! Water depleted.");
	    warnedOnFood = true;
	} 

	for (CrewMember member : crewmen) {
	    if (airDecrement > 0) {
		member.decreaseHealth(airDecrement - ((airDecrement * ((float) member.getMorale().getValue() / 100)) / 2));
		member.addToCondition(CrewMemberCondition.OXYGEN_DEPRIVATION);
	    } else {
		member.getCondition().remove(CrewMemberCondition.OXYGEN_DEPRIVATION);
	    }

	    if (foodDecrement > 0) {
		member.decreaseHealth(foodDecrement - ((foodDecrement * ((float) member.getStrength().getValue() / 100)) / 2));
		member.addToCondition(CrewMemberCondition.MALNUTRITION);
	    } else {
		member.getCondition().remove(CrewMemberCondition.MALNUTRITION);
	    }

	    if (waterDecrement > 0) {
		member.decreaseHealth(waterDecrement - ((waterDecrement * ((float) member.getStrength().getValue() / 100)) / 2));
		member.addToCondition(CrewMemberCondition.WATER_DEPRIVATION);
	    } else {
		member.getCondition().remove(CrewMemberCondition.WATER_DEPRIVATION);
	    }
	}
    }

    /**
     * Essentially we are using this listener to transmit messages for logging.
     * Not a good practise. TODO: Come up with better way to deal with logger.
     * 
     * @param message
     */
    private void fireCrewStatusChangeListener(String message) {
	if(crewMemberStatusChangeListener != null) {
	    crewMemberStatusChangeListener.handleEventClassEvent(new UEEvent(message));   
	}	
    }

    public void increaseDaysPassed() {
	time += CoreConfiguration.TIME_FLOW;
    }

    public void increaseAir(float airInc) {
	if(air == 0) {
	    warnedOnAir = false;
	}
	
	air += airInc;
	if (air > 100)
	    air = 100;
    }

    public void increaseFood(float foodInc) {
	if(food == 0) {
	    warnedOnFood = false;
	}
	
	food += foodInc;
	if (food > 100)
	    food = 100;
    }

    public void increaseWater(float foodInc) {
	if(water == 0) {
	    warnedOnWater = false;
	}
	
	water += foodInc;
	if (water > 100)
	    water = 100;
    }

    /**
     * Air gets sucked out
     * 
     * @param d
     */
    private boolean decreaseAirBy(float d) {
	air = MathTools.decreaseIfResultMoreOrEqualToZero(air, d);
	return true;
    }

    /**
     * Water leaks
     * 
     * @param d
     */
    private void decreaseWaterBy(float d) {
	water = MathTools.decreaseIfResultMoreOrEqualToZero(water, d);
    }

    /**
     * Food gets eaten
     * 
     * @param d
     */
    private void decreaseFoodBy(float d) {
	food = MathTools.decreaseIfResultMoreOrEqualToZero(food, d);
    }

    /**
     * Someones charging their phones
     * 
     * @param d
     */
    public void decreasePowerBy(float d) {
	power = MathTools.decreaseIfResultMoreOrEqualToZero(power, d);
    }

    /**
     * @return the air
     */
    public float getAir() {
	return air;
    }

    /**
     * @return the water
     */
    public float getWater() {
	return water;
    }

    /**
     * @return the food
     */
    public float getFood() {
	return food;
    }

    /**
     * @return the power
     */
    public float getPower() {
	return power;
    }

    /**
     * @param air
     *            the air to set
     */
    public void setAir(float air) {
	this.air = air;
    }

    /**
     * @param water
     *            the water to set
     */
    public void setWater(float water) {
	this.water = water;
    }

    /**
     * @param food
     *            the food to set
     */
    public void setFood(float food) {
	this.food = food;
    }

    /**
     * @param power
     *            the power to set
     */
    public void setPower(float power) {
	this.power = power;
    }

    /**
     * @return the time
     */
    public float getTime() {
	return time;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(float time) {
	this.time = time;
    }

    /**
     * @return the crewMemberStatusChangeListener
     */
    public UEListener getCrewMemberStatusChangeListener() {
	return crewMemberStatusChangeListener;
    }

    /**
     * @param crewMemberStatusChangeListener
     *            the crewMemberStatusChangeListener to set
     */
    public void setCrewMemberStatusChangeListener(UEListener crewMemberStatusChangeListener) {
	this.crewMemberStatusChangeListener = crewMemberStatusChangeListener;
    }
}
