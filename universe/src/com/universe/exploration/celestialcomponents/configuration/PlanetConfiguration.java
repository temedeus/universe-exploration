/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration;

import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.survey.Lifeform;

/**
 * Provides boundaries for creating planets.
 *
 * @author 14.1.2017 Teemu Puurunen
 */
public class PlanetConfiguration extends AbstractConfiguration {
    protected float chanceForCivilization;

    protected float chanceForBacterial;

    protected float chanceForAnimalLife;

    protected float chanceToExtractWater;

    protected float chanceToExtractOxygen;

    protected float chanceToFindFood;

    protected float chanceForVegetation;

    /**
     * In order to have any life at all, planet needs to contain water.
     *
     * @param necessitiesMet
     * @return
     */
    public Lifeform randomizePlanetLifeForm(boolean necessitiesMet) {
        if (necessitiesMet) {
            if (MathTools.calculateIfOddsHit(getChanceCivilization())) {
                return Lifeform.CIVILIZED;
            }

            if (MathTools.calculateIfOddsHit(getChanceAnimal())) {
                return Lifeform.ANIMAL;
            }

            if (MathTools.calculateIfOddsHit(getChanceForVegetation())) {
                return Lifeform.VEGETATION;
            }

            if (MathTools.calculateIfOddsHit(getChanceForBacterial())) {
                return Lifeform.BACTERIAL;
            }
        }

        return Lifeform.NONE;
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
