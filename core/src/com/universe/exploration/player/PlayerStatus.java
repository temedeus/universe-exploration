/**
 * 
 */
package com.universe.exploration.player;

import com.universe.exploration.CoreConfiguration;
import com.universe.exploration.common.tools.MathTools;

/**
 * 
 * General container for supply and crew status
 * 
 * @author 31.8.2015 Teemu Puurunen 
 *
 */
public class PlayerStatus {
	private int crewmen;
	
	private float air;
	
	private float water;
	
	private float food;
	
	private float power;
	
	/**
	 * Setup initial values.
	 * Start with full values
	 */
	public PlayerStatus(){
		crewmen = CoreConfiguration.MAX_CREWMEN;
		air = CoreConfiguration.MAX_AIR;
		water = CoreConfiguration.MAX_WATER;
		food = CoreConfiguration.MAX_FOOD;
		power = CoreConfiguration.MAX_POWER;
	}
	
	/**
	 * 
	 * @return
	 */
	public void updateStatus() {
		decreaseAirBy((power > 0) ? StatusConsumption.AIR_DECREMENT : StatusConsumption.AIR_DECREMENT * 9);
		decreaseFoodBy(StatusConsumption.CREWMEN_FOOD_CONSUMPTION_PER_CREWMAN * crewmen);
		decreaseWaterBy(StatusConsumption.CREWMEN_WATER_CONSUMPTION_PER_CREWMAN * crewmen);
		decreaseCrewmen();
	}
	
	/**
	 * Based on available resources, return mortality rate (if all is well, return 0)
	 * @return float
	 */
	private float calculateCrewmenMortalityRate() {	
		// Return crewmen death rate by what most crucial resource is depleted
		if(air == 0) {
			return StatusConsumption.CREWMEN_DECREMENT_AIR_DEPLETED;
		} else {
			if(water == 0) {
				return StatusConsumption.CREWMEN_DECREMENT_WATER_DEPLETED;
			} else {
				if(food == 0) {
					return StatusConsumption.CREWMEN_DECREMENT_FOOD_DEPLETED;
				}
			}
		}
		
		return 0f;
	}
	/**
	 * Crewman dies
	 * @param d
	 */
	public void decreaseCrewmen() {
		float dec = calculateCrewmenMortalityRate();
		
		if(dec > 0) {
			crewmen = (int)MathTools.decreaseIfResultPositive(crewmen, dec);
		}
	}
	
	/**
	 * Air gets sucked out
	 * @param d
	 */
	private boolean decreaseAirBy(float d) {
		air = MathTools.decreaseIfResultPositive(air, d);return true;
	}
	
	/**
	 * Water leaks
	 * @param d
	 */
	private void decreaseWaterBy(float d) {
		water = MathTools.decreaseIfResultPositive(water, d);
	}
	
	/**
	 * Food gets eaten
	 * @param d
	 */
	private void decreaseFoodBy(float d) {
		food = MathTools.decreaseIfResultPositive(food, d);
	}
	
	/**
	 * Someones charging their phones
	 * @param d
	 */
	public void decreasePowerBy(float d) {
		power = MathTools.decreaseIfResultPositive(power, d);
	}
	
	/**
	 * @return the crewmen
	 */
	public int getCrewmen() {
		return crewmen;
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
	 * @param crewmen the crewmen to set
	 */
	public void setCrewmen(int crewmen) {
		this.crewmen = crewmen;
	}

	/**
	 * @param air the air to set
	 */
	public void setAir(float air) {
		this.air = air;
	}

	/**
	 * @param water the water to set
	 */
	public void setWater(float water) {
		this.water = water;
	}

	/**
	 * @param food the food to set
	 */
	public void setFood(float food) {
		this.food = food;
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(float power) {
		this.power = power;
	}
}
