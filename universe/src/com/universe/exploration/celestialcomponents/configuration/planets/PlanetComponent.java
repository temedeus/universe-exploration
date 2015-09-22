/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;

/**
 * @author 22.9.2015 Teemu Puurunen 
 *
 */
public class PlanetComponent extends ComponentType {
	/**
	 * @return the cHANCE_CIVILIZATION
	 */
	public float getCHANCE_CIVILIZATION() {
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
	public float getCHANCE_ANIMAL() {
		return chanceForAnimalLife;
	}

	/**
	 * @return the cHANCE_TO_EXTRACT_WATER
	 */
	public float getCHANCE_TO_EXTRACT_WATER() {
		return CHANCE_TO_EXTRACT_WATER;
	}

	/**
	 * @return the cHANCE_TO_EXTRACT_OXYGEN
	 */
	public float getCHANCE_TO_EXTRACT_OXYGEN() {
		return CHANCE_TO_EXTRACT_OXYGEN;
	}

	/**
	 * @return the cHANCE_TO_FIND_FOOD
	 */
	public float getCHANCE_TO_FIND_FOOD() {
		return CHANCE_TO_FIND_FOOD;
	}

	/**
	 * @param cHANCE_CIVILIZATION the cHANCE_CIVILIZATION to set
	 */
	public void setCHANCE_CIVILIZATION(float cHANCE_CIVILIZATION) {
		chanceForCivilization = cHANCE_CIVILIZATION;
	}

	/**
	 * @param cHANCE_BACTERIAL the cHANCE_BACTERIAL to set
	 */
	public void setCHANCE_BACTERIAL(float cHANCE_BACTERIAL) {
		chanceForBacterial = cHANCE_BACTERIAL;
	}

	/**
	 * @param cHANCE_ANIMAL the cHANCE_ANIMAL to set
	 */
	public void setCHANCE_ANIMAL(float cHANCE_ANIMAL) {
		chanceForAnimalLife = cHANCE_ANIMAL;
	}

	/**
	 * @param cHANCE_TO_EXTRACT_WATER the cHANCE_TO_EXTRACT_WATER to set
	 */
	public void setCHANCE_TO_EXTRACT_WATER(float cHANCE_TO_EXTRACT_WATER) {
		CHANCE_TO_EXTRACT_WATER = cHANCE_TO_EXTRACT_WATER;
	}

	/**
	 * @param cHANCE_TO_EXTRACT_OXYGEN the cHANCE_TO_EXTRACT_OXYGEN to set
	 */
	public void setCHANCE_TO_EXTRACT_OXYGEN(float cHANCE_TO_EXTRACT_OXYGEN) {
		CHANCE_TO_EXTRACT_OXYGEN = cHANCE_TO_EXTRACT_OXYGEN;
	}

	/**
	 * @param cHANCE_TO_FIND_FOOD the cHANCE_TO_FIND_FOOD to set
	 */
	public void setCHANCE_TO_FIND_FOOD(float cHANCE_TO_FIND_FOOD) {
		CHANCE_TO_FIND_FOOD = cHANCE_TO_FIND_FOOD;
	}

	protected float chanceForCivilization;
	
	protected float chanceForBacterial;
	
	protected float chanceForAnimalLife;
	
	protected float CHANCE_TO_EXTRACT_WATER;
	
	protected float CHANCE_TO_EXTRACT_OXYGEN;
	
	protected float CHANCE_TO_FIND_FOOD;
}
