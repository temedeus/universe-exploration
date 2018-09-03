package com.universe.exploration.starsystem;

import com.universe.exploration.celestialcomponents.configuration.CelestialComponentTemplate;
import com.universe.exploration.celestialcomponents.configuration.PlanetTemplate;
import com.universe.exploration.common.tools.IngameAstronomicalConstants;
import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.common.tools.RandomizationTools;
import com.universe.exploration.common.tools.WeightedRandomizationItem;
import com.universe.exploration.resource.Air;
import com.universe.exploration.resource.Food;
import com.universe.exploration.resource.Water;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.starsystem.components.StarCelestialComponent;
import com.universe.exploration.survey.LifeformLevel;

/**
 * Generates {@link StarSystem}.
 *
 * @author 7.6.2015 Teemu Puurunen
 */
public class StarSystemFactory {
    private StarSystem starsystem;
    private StarSystemConfiguration starSystemConfiguration;

    /**
     * Default configuration
     */
    public StarSystemFactory() {
        this.starSystemConfiguration = new StarSystemConfiguration();
        this.starsystem = new StarSystem();
    }

    /**
     * Create a star system.
     *
     * @return StarSystem starsystem
     */
    public StarSystem makeStarSystem()  {
        int planetCount = RandomizationTools.getRandomInteger(starSystemConfiguration.getMinPlanetCount(),
                starSystemConfiguration.getMaxPlanetCount());

        populateWithPlanets(planetCount);

        CelestialComponentTemplate template = (CelestialComponentTemplate) (RandomizationTools
                .getWeightedRandomItem(starSystemConfiguration.getPotentialStars())).getItem();

        StarCelestialComponent systemstar = new StarCelestialComponent();
        systemstar.setGraphicsFile(template.getComponentType().getRandomGraphicsFile());
        this.starsystem.setSystemstar(systemstar);

        return this.starsystem;
    }

    /**
     * Populate starsystem with planets
     *
     * @param planetCount
     */
    private void populateWithPlanets(int planetCount) {
        double planetarySpace = MathTools.calculatePlanetarySpace(planetCount);

        double previousOrbitalRadious = 0;

        // Generate required number of planets.
        for (int x = 0; x < planetCount; x++) {
            // Container for our planet data.
            PlanetCelestialComponent planet = new PlanetCelestialComponent();

            WeightedRandomizationItem item = ( RandomizationTools
                    .getWeightedRandomItem(starSystemConfiguration.getPotentialsPlanets()));
            CelestialComponentTemplate template = ((CelestialComponentTemplate) item.getItem());
            PlanetTemplate planetTemplate = (PlanetTemplate) template.getComponentType();

            // Generate all the new values
            planet = calculatePlanetOrbitalData(planet, planetarySpace, previousOrbitalRadious, x);
            planet = calculatePlanetHabitability(planet, planetTemplate);
            planet = setupPlanetBasicInfo(planet, planetTemplate);

            // Store old orbital radius value so we do not create orbits that
            // cross with previous ones.
            previousOrbitalRadious = planet.getOrbitalRadius();

            // Add planet
            starsystem.addPlanet(planet);
        }
    }

    private PlanetCelestialComponent calculatePlanetOrbitalData(PlanetCelestialComponent planet, double planetarySpace,
                                                                double previousOrbitalRadious, int x) {
        double planetOrbitalVelocity = RandomizationTools.getRandomDouble(
                IngameAstronomicalConstants.MIN_ORBITAL_VELOCITY.getValue(),
                IngameAstronomicalConstants.MAX_ORBITAL_VELOCITY.getValue());

        double minOrbitalRadius = x * planetarySpace + IngameAstronomicalConstants.MIN_ORBITAL_RADIUS.getValue();
        double maxOrbitalRadius = minOrbitalRadius + planetarySpace;
        minOrbitalRadius = verifyDistance(minOrbitalRadius, previousOrbitalRadious);

        double orbitalRadius = RandomizationTools.getRandomDouble(minOrbitalRadius, maxOrbitalRadius);
        double angle = MathTools.generateRandomAngle();

        planet.setOrbitalVelocity(planetOrbitalVelocity);
        planet.setOrbitalRadius(orbitalRadius);
        planet.setAngle(angle);

        return planet;
    }

    private PlanetCelestialComponent calculatePlanetHabitability(PlanetCelestialComponent planet,
                                                                 PlanetTemplate cc) {
        if (MathTools.calculateIfOddsHit(cc.getChanceToExtractOxygen())) {
            planet.addFoundResource(new Air());
        }

        if (MathTools.calculateIfOddsHit(cc.getChanceToExtractWater())) {
            planet.addFoundResource(new Water());
        }

        boolean mandatoryForLife = planet.containsInstanceOfResource(Water.class)
                && planet.containsInstanceOfResource(Air.class);
        planet.setLifeforms(cc.randomizePlanetLifeFormLevel(mandatoryForLife));
        setupFoodPresence(planet);

        return planet;
    }

    private void setupFoodPresence(PlanetCelestialComponent planet) {
        if (planet.getLifeforms().getRank() >= LifeformLevel.VEGETATION.getRank()) {
            planet.addFoundResource(new Food());
        }
    }

    /**
     * Sprite configuration and component related data.
     *
     * @param planet
     * @return
     */
    private PlanetCelestialComponent setupPlanetBasicInfo(PlanetCelestialComponent planet,
                                                          PlanetTemplate planetTemplate) {
        planet.setGraphicsFile(planetTemplate.getRandomGraphicsFile());
        planet.setSpriteSize(planetTemplate.getRandomSpriteSize());
        planet.setComponentName(planetTemplate.getComponentName());

        return planet;
    }

    /**
     * Ensure that new distance is far away enough from the old distance
     *
     * @param distance
     * @param previousDistance
     * @return
     */
    private double verifyDistance(double distance, double previousDistance) {
        double difference = distance - previousDistance;

        return (difference <= IngameAstronomicalConstants.MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII.getValue())
                ? distance + IngameAstronomicalConstants.MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII.getValue()
                - difference
                : distance;
    }
}
