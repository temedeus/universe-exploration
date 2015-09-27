/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration;

import com.universe.exploration.common.tools.MathTools;

/**
 * @author 22.9.2015 Teemu Puurunen 
 *
 */
public class PlanetComponent extends ComponentType {
	public String randomizePlanetLife() {
		if(MathTools.calculateIfOddsHit(getChanceCivilization())) {
			return "civilized";
		}
		
		if(MathTools.calculateIfOddsHit(getChanceAnimal())) {
			return "animal";
		}
		
		if(MathTools.calculateIfOddsHit(getChanceForBacterial())) {
			return "bacterial";
		} 

		return "none";
	}
	
	/**
	 * @return the cHANCE_CIVILIZATION
	 */
	public float getChanceCivilization() {
		return chanceForCivilization;
	}

	/**
	 * @return the cHANCE_BACTERIAL
	 */
	public float getChanceForBacterial() {
		return chanceForBacterial;
	}

	/**
	 * @return the cHANCE_ANIMAL
	 */
	public float getChanceAnimal() {
		return chanceForAnimalLife;
	}

	/**
	 * @return the cHANCE_TO_EXTRACT_WATER
	 */
	public float getCHANCE_TO_EXTRACT_WATER() {
		return chanceToExtractWater;
	}

	/**
	 * @return the cHANCE_TO_EXTRACT_OXYGEN
	 */
	public float getCHANCE_TO_EXTRACT_OXYGEN() {
		return chanceToExtractOxygen;
	}

	/**
	 * @return the cHANCE_TO_FIND_FOOD
	 */
	public float getChanceToFindFood() {
		return chanceToFindFood;
	}

	/**
	 * @param cHANCE_CIVILIZATION the cHANCE_CIVILIZATION to set
	 */
	public void setCHANCE_CIVILIZATION(float chanceForCivilization) {
		this.chanceForCivilization = chanceForCivilization;
	}

	/**
	 * @param cHANCE_BACTERIAL the cHANCE_BACTERIAL to set
	 */
	public void setCHANCE_BACTERIAL(float chanceForBacterial) {
		this.chanceForBacterial = chanceForBacterial;
	}

	/**
	 * @param cHANCE_ANIMAL the cHANCE_ANIMAL to set
	 */
	public void setCHANCE_ANIMAL(float chanceForAnimalLife) {
		this.chanceForAnimalLife = chanceForAnimalLife;
	}

	/**
	 * @param cHANCE_TO_EXTRACT_WATER the cHANCE_TO_EXTRACT_WATER to set
	 */
	public void setChanceToExtractWater(float chanceToExtractWater) {
		this.chanceToExtractWater = chanceToExtractWater;
	}

	/**
	 * @param cHANCE_TO_EXTRACT_OXYGEN the cHANCE_TO_EXTRACT_OXYGEN to set
	 */
	public void setCHANCE_TO_EXTRACT_OXYGEN(float chanceToExtractOxygen) {
		this.chanceToExtractOxygen = chanceToExtractOxygen;
	}

	/**
	 * @param cHANCE_TO_FIND_FOOD the cHANCE_TO_FIND_FOOD to set
	 */
	public void setCHANCE_TO_FIND_FOOD(float chanceToFindFood) {
		this.chanceToFindFood = chanceToFindFood;
	}

	protected float chanceForCivilization;
	
	protected float chanceForBacterial;
	
	protected float chanceForAnimalLife;
	
	protected float chanceToExtractWater;
	
	protected float chanceToExtractOxygen;
	
	protected float chanceToFindFood;
}
