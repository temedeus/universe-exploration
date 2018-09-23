package com.universe.exploration.starsystem;

import com.universe.exploration.celestialcomponents.configuration.CelestialComponentTemplate;
import com.universe.exploration.celestialcomponents.configuration.PlanetTemplate;
import com.universe.exploration.celestialcomponents.configuration.StarTemplate;
import com.universe.exploration.common.tools.WeightedRandomizationItem;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Star system configuration representating the potential star, planets and the
 * boundaries for given star system configuration.
 * </p>
 * <p>
 * <p>
 * As a result {@link StarSystemFactory} generates a {@link StarSystem} based on
 * this configuration.
 * </p>
 * <p>
 *
 * @author 6.6.2015 Teemu Puurunen
 */
public class StarSystemConfiguration {
    /**
     * Max planet count
     *
     * @access private
     */
    private final int maxPlanetCount = 6;

    /**
     * Minimum of planets.
     *
     * @access private
     */
    private final int minPlanetCount = 0;

    /**
     * Star types and their weighted probabilities.
     */
    private List<WeightedRandomizationItem> potentialStars;

    /**
     * Planet types and their weighted probabilities.
     */
    private List<WeightedRandomizationItem> potentialPlanets;

    public StarSystemConfiguration() {
        potentialPlanets = new ArrayList<>();
        potentialStars = new ArrayList<>();

        for (CelestialComponentTemplate template : CelestialComponentTemplate.values()) {
            if (template.getComponentType() instanceof StarTemplate) {
                potentialStars.add(new WeightedRandomizationItem(template.getPrevalance(), template));
            }

            if (template.getComponentType() instanceof PlanetTemplate) {
                potentialPlanets.add(new WeightedRandomizationItem(template.getPrevalance(), template));
            }
        }
    }

    /**
     * @return the minPlanetCount
     */
    public int getMinPlanetCount() {
        return minPlanetCount;
    }

    /**
     * @return the maxPlanetCount
     */
    public int getMaxPlanetCount() {
        return maxPlanetCount;
    }

    /**
     * @return the startypeListing
     */
    public List<WeightedRandomizationItem> getPotentialStars() {
        return this.potentialStars;
    }

    /**
     * @return the planettypeListing
     */
    public List<WeightedRandomizationItem> getPotentialsPlanets() {
        return potentialPlanets;
    }
}
