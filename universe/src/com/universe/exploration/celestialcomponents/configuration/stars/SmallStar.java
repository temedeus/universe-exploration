/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration.stars;

import com.universe.exploration.celestialcomponents.configuration.StarConfiguration;

/**
 * @author 24.8.2015 Teemu Puurunen
 */
public class SmallStar extends StarConfiguration {
    @Override
    public void setup() {
        templateName = "Small star";
        graphicsFiles.add("star3.png");
        spriteSizes.add(4096);
    }
}
