/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.PlanetTemplate;

/**
 * @author 24.8.2015 Teemu Puurunen
 *
 */
public class ExteriorTerrestrialPlanet extends PlanetTemplate {

    public ExteriorTerrestrialPlanet() {
	templateName = "Exterior terrestrial planet";
	graphicsFiles = new String[] { "planet2.png" };
	spriteSizes = new int[] { 8, 10, 12 };

	chanceForCivilization = 7;
	chanceForBacterial = 20;
	chanceForAnimalLife = 1;
	chanceToExtractWater = 40f;
	chanceToExtractOxygen = 30f;
	chanceToFindFood = 10;
	chanceForVegetation = 10;
    }
}
