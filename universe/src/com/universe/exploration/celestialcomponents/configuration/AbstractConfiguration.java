/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration;

import com.universe.exploration.common.tools.RandomizationTools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 24.8.2015 Teemu Puurunen
 */
public abstract class AbstractConfiguration implements IAbstractConfiguration {

    protected String templateName;

    protected List<String> graphicsFiles;

    protected List<Integer> spriteSizes;

    AbstractConfiguration() {
        graphicsFiles = new ArrayList<>();
        spriteSizes = new ArrayList<>();
        setup();
    }

    public String getRandomGraphicsFile() {
        int x = RandomizationTools.getRandomInteger(0, graphicsFiles.size() - 1);
        return graphicsFiles.get(x);
    }

    public int getRandomSpriteSize() {
        int x = RandomizationTools.getRandomInteger(0, spriteSizes.size() - 1);
        return spriteSizes.get(x);
    }

    /**
     * @return the componentName
     */
    public String getComponentName() {
        return templateName;
    }

}
