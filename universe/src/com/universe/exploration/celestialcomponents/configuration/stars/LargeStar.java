/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration.stars;

import com.universe.exploration.celestialcomponents.configuration.StarTemplate;

/**
 * @author 24.8.2015 Teemu Puurunen
 */
public class LargeStar extends StarTemplate {
    @Override
    public void setup() {
        templateName = "Large star";
        graphicsFiles.add("star1.png");
        spriteSizes.add(4096);
    }
}
