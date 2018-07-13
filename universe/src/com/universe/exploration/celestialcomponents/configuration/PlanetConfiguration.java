/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration;

import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.survey.LifeformLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

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
     * Provides list of life form levels and how to deduce their probability. Order matters here since first applicable
     * is used.
     */
    private Map<LifeformLevel, Supplier> lifeformMapping;

    public PlanetConfiguration() {
        lifeformMapping = new HashMap<>();
        lifeformMapping.put(LifeformLevel.CIVILIZED, () -> MathTools.calculateIfOddsHit(getChanceCivilization()));
        lifeformMapping.put(LifeformLevel.ANIMAL, () -> MathTools.calculateIfOddsHit(getChanceAnimal()));
        lifeformMapping.put(LifeformLevel.VEGETATION, () -> MathTools.calculateIfOddsHit(getChanceForVegetation()));
        lifeformMapping.put(LifeformLevel.BACTERIAL, () -> MathTools.calculateIfOddsHit(getChanceForBacterial()));
    }

    /**
     * Deduces the level of lifeforms on a planet. Given enumeration tells the minimum level of life forms.
     * If conditions are not met (boolean given directly), return enum representing no lifeforms.
     *
     * @param necessitiesMet
     * @return
     */
    public LifeformLevel randomizePlanetLifeFormLevel(boolean necessitiesMet) {
        if (necessitiesMet) {
            return lifeformMapping.entrySet()
                    .stream()
                    .filter(lifeformCallableEntry -> (Boolean) lifeformCallableEntry.getValue().get())
                    .findFirst()
                    .map(lifeformSupplierEntry -> lifeformSupplierEntry.getKey())
                    .orElse(LifeformLevel.NONE);
        }

        return LifeformLevel.NONE;
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
     * @return the chanceForVegetation
     */
    public float getChanceForVegetation() {
        return chanceForVegetation;
    }
}
