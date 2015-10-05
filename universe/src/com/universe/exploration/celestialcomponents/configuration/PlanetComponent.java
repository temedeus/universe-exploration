/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration;

import com.universe.exploration.common.tools.MathTools;
import common.universe.exploration.common.Lifeforms;

/**
 * @author 22.9.2015 Teemu Puurunen 
 *
 */
public class PlanetComponent extends ComponentType {
	protected float chanceForCivilization;
	
	protected float chanceForBacterial;
	
	protected float chanceForAnimalLife;
	
	protected float chanceToExtractWater;
	
	protected float chanceToExtractOxygen;
	
	protected float chanceToFindFood;
	
	protected float chanceForVegetation;
	
	
	/**
	 * In order to have any life at all, planet needs to contain water.
	 * @param necessitiesMet
	 * @return
	 */
	public Lifeforms randomizePlanetLife(boolean necessitiesMet) {
		if(necessitiesMet) {
			if(MathTools.calculateIfOddsHit(getChanceCivilization())) {
				return Lifeforms.CIVILIZED;
			}
			
			if(MathTools.calculateIfOddsHit(getChanceAnimal())) {
				return Lifeforms.ANIMAL;
			}
			
			if(MathTools.calculateIfOddsHit(getChanceForVegetation())) {
				return Lifeforms.VEGETATION;
			}
			
			if(MathTools.calculateIfOddsHit(getChanceForBacterial())) {
				return Lifeforms.BACTERIAL;
			} 
		}

		return Lifeforms.NONE;
	}
	
	/**
	 * @return the chanceForCivilization
	 */
	public float getChanceCivilization() {
		return chanceForCivilization;
	}

	/**
	 * @return the chanceForBacterial
	 */
	public float getChanceForBacterial() {
		return chanceForBacterial;
	}

	/**
	 * @return the chanceForAnimalLife
	 */
	public float getChanceAnimal() {
		return chanceForAnimalLife;
	}

	/**
	 * @return the chanceToExtractWater
	 */
	public float getChanceToExtractWater() {
		return chanceToExtractWater;
	}

	/**
	 * @return the chanceToExtractOxygen
	 */
	public float getChanceToExtractOxygen() {
		return chanceToExtractOxygen;
	}

	/**
	 * @return the chanceToFindFood
	 */
	public float getChanceToFindFood() {
		return chanceToFindFood;
	}

	/**
	 * @param chanceForCivilization the chanceForCivilization to set
	 */
	public void setChanceForCivilization(float chanceForCivilization) {
		this.chanceForCivilization = chanceForCivilization;
	}

	/**
	 * @param chanceForBacterial the chanceForBacterial to set
	 */
	public void setChanceForBacterial(float chanceForBacterial) {
		this.chanceForBacterial = chanceForBacterial;
	}

	/**
	 * @param cHANCE_ANchanceForAnimalLifeIMAL the chanceForAnimalLife to set
	 */
	public void setChanceForAnimalLife(float chanceForAnimalLife) {
		this.chanceForAnimalLife = chanceForAnimalLife;
	}

	/**
	 * @param chanceToExtractWater the chanceToExtractWater to set
	 */
	public void setChanceToExtractWater(float chanceToExtractWater) {
		this.chanceToExtractWater = chanceToExtractWater;
	}

	/**
	 * @param chanceToExtractOxygen the chanceToExtractOxygen to set
	 */
	public void setChanceToExractOxygen(float chanceToExtractOxygen) {
		this.chanceToExtractOxygen = chanceToExtractOxygen;
	}

	/**
	 * @param chanceToFindFood the chanceToFindFood to set
	 */
	public void setChanceToFindFood(float chanceToFindFood) {
		this.chanceToFindFood = chanceToFindFood;
	}
	

	/**
	 * @return the chanceForVegetation
	 */
	public float getChanceForVegetation() {
		return chanceForVegetation;
	}

	/**
	 * @param chanceForVegetation the chanceForVegetation to set
	 */
	public void setChanceForVegetation(float chanceForVegetation) {
		this.chanceForVegetation = chanceForVegetation;
	}
}
