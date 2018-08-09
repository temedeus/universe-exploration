/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration.stars;

import com.universe.exploration.celestialcomponents.configuration.StarConfiguration;

/**
 * @author 24.8.2015 Teemu Puurunen
 */
public class LargeStar extends StarConfiguration {
    @Override
    public void setup() {
        templateName = "Large star";
        graphicsFiles.add("star1.png");
        spriteSizes.add(4096);
    }
}
