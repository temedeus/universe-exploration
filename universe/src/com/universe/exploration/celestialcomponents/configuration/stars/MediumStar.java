/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration.stars;

import com.universe.exploration.celestialcomponents.configuration.StarTemplate;

/**
 * @author 24.8.2015 Teemu Puurunen
 */
public class MediumStar extends StarTemplate {
    @Override
    public void setup() {
        templateName = "Medium star";
        graphicsFiles.add("star2.png");
        spriteSizes.add(4096);
    }
}
