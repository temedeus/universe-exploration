/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration;

import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.survey.Lifeform;

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

    private Map<Lifeform, Supplier> lifeformMapping;

    public PlanetConfiguration() {
        lifeformMapping = new HashMap<>();
        lifeformMapping.put(Lifeform.CIVILIZED, () -> MathTools.calculateIfOddsHit(getChanceCivilization()));
        lifeformMapping.put(Lifeform.ANIMAL, () -> MathTools.calculateIfOddsHit(getChanceAnimal()));
        lifeformMapping.put(Lifeform.VEGETATION, () -> MathTools.calculateIfOddsHit(getChanceForVegetation()));
        lifeformMapping.put(Lifeform.BACTERIAL, () -> MathTools.calculateIfOddsHit(getChanceForBacterial()));
    }

    /**
     * Deduces the level of lifeforms on a planet. Given enumeration tells the minimum level of life forms.
     * If conditions are not met (boolean given directly), return enum representing no lifeforms.
     *
     * @param necessitiesMet
     * @return
     */
    public Lifeform randomizePlanetLifeFormLevel(boolean necessitiesMet) {
        if (necessitiesMet) {
            return lifeformMapping.entrySet()
                    .stream()
                    .filter(lifeformCallableEntry -> (Boolean) lifeformCallableEntry.getValue().get())
                    .findFirst()
                    .map(lifeformSupplierEntry -> lifeformSupplierEntry.getKey())
                    .orElse(Lifeform.NONE);
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
     * @return the chanceForVegetation
     */
    public float getChanceForVegetation() {
        return chanceForVegetation;
    }
}
