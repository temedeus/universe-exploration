/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration;

import com.universe.exploration.celestialcomponents.configuration.planets.*;
import com.universe.exploration.celestialcomponents.configuration.stars.LargeStar;
import com.universe.exploration.celestialcomponents.configuration.stars.MediumStar;
import com.universe.exploration.celestialcomponents.configuration.stars.SmallStar;

/**
 * Enumeration of potential stars and planets.
 *
 * @author 15.1.2017 Teemu Puurunen
 */
public enum CelestialComponentTemplate {
    // SYSTEM STARS
    LARGE_STAR(new LargeStar(), 10),
    MEDIUM_STAR(new MediumStar(), 3),
    SMALL_STAR(new SmallStar(), 5),
    // PLANETS
    ACID_RAIN_PLANET(new InteriorTerrestrialPlanet(), 6),
    RED_MINERAL_PLANET(new ExteriorTerrestrialPlanet(), 4),
    GAS_GIANT_PLANET(new GasGiantPlanet(), 10),
    COLD_ROCKY_PLANET(new ColdRockyPlanet(), 15),
    EARTLIKE_PLANET(new GoldilocksPlanet(), 2);

    private final AbstractConfiguration componentType;

    /**
     * Determines the likelihood of the component.
     */
    private final int prevalance;

    CelestialComponentTemplate(AbstractConfiguration componentType, int prevalance) {
        this.componentType = componentType;
        this.prevalance = prevalance;
    }

    /**
     * @return the graphicsFile
     */
    public AbstractConfiguration getComponentType() {
        return componentType;
    }

    /**
     * @return the prevalance
     */
    public int getPrevalance() {
        return prevalance;
    }
}
