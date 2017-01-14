/**
 * 
 */
package com.universe.exploration.player;

/**
 * Status consumption.
 * 
 * @author 13.1.2017 Teemu Puurunen
 *
 */
public class StatusConsumption {

    /**
     * When hyperspace jump is engaged, this final float determines the amount
     * of decrement
     */
    public static final float POWER_DECREMENT_HYPERSPACE_JUMP = 15f;

    /**
     * When planet is surveyed, this determines the amount of power decrement.
     */
    public static final float POWER_DECREMENT_PLANETARY_SURVEY = 5f;

    /**
     * Regular water decrement.
     */
    public static final float WATER_DECREMENT = 0.01f;

    /**
     * Regular food decrement.
     */
    public static final float FOOD_DECREMENT = 0.01f;

    public static final float AIR_DECREMENT = 0.0007f;

    public static final float HEALTH_DECREASE_WHEN_AIR_DEPLETED = 1.0f;

    public static final float HEALTH_DECREASE_WHEN_WATER_DEPLETED = 0.1f;

    public static final float HEALTH_DECREASE_WHEN_FOOD_DEPLETED = 0.5f;

    public static final float CREWMEN_FOOD_CONSUMPTION_PER_CREWMAN = 0.001f;

    public static final float CREWMEN_WATER_CONSUMPTION_PER_CREWMAN = 0.003f;

    public static final float AIR_DECREMENT_WHEN_POWER_OUT = 9f;

}
