package com.universe.exploration.common;

/**
 * GameConfiguration.java
 * <p>
 * Game configuration. TODO: XML configuration instead of hard-coding.
 *
 * @author 6.6.2015 Teemu Puurunen
 */
public class CoreConfiguration {

    /**
     * Background tile width/height (for simplicity tiles are all squares so
     * width equals height)
     */
    public static final int BACKGROUND_TILE_DEFAULT_WIDTH = 2048;

    public static final float ENLARGED_PLANET_SPRITE_SIZE = 1600;

    public static final int MAX_CREWMEN = 10;

    public static final float MAX_POWER = 100;

    public static final String USERINTERFACE_SKIN = "star-soldier/skin/star-soldier-ui.json";

    public static final float TIME_FLOW = 0.005f;

    public static final float TIME_START = 0;

    public static final String WEBSITE_URL = "http://www.universe-exploration.com";

    public static final float SPACE_BACKGROUND_POSITION_X = -1000;

    public static final float SPACE_BACKGROUND_POSITION_Y = -600;

    public static final int MAX_DAYS_ON_SURVEY = 30;

    public static final float PLANET_ENHANCEMENT_MIN = 120;

    public static final float PLANET_ENHANCEMENT_MAX = 300;

    public static final float PLANET_ENHANCEMENT_INC = 10;
}
