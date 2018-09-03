/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration.stars;

import com.universe.exploration.celestialcomponents.configuration.StarTemplate;

/**
 * @author 24.8.2015 Teemu Puurunen
 */
public class SmallStar extends StarTemplate {
    @Override
    public void setup() {
        templateName = "Small star";
        graphicsFiles.add("star3.png");
        spriteSizes.add(4096);
    }
}
