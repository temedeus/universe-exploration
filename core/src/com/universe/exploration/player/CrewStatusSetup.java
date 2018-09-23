/**
 *
 */
package com.universe.exploration.player;

/**
 * Setup for crew member statuses.
 *
 * @author 14.1.2017 Teemu Puurunen
 */
public enum CrewStatusSetup {
    AIR(0, 200, "DESC_AIR_LOW"), WATER(0, 200, "DESC_WATER_LOW"), FOOD(0, 200, "DESC_FOOD_LOW");

    private final float minValue;
    private final float maxValue;
    private final String depletionMessageLocale;

    CrewStatusSetup(float minValue, float maxValue, String depletionMessageLocale) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.depletionMessageLocale = depletionMessageLocale;
    }

    /**
     * @return the maxValue
     */
    public float getMaxValue() {
        return maxValue;
    }

    /**
     * @return the minValue
     */
    public float getMinValue() {
        return minValue;
    }

    /**
     * @return the depletionMessageLocale
     */
    public String getDepletionMessageLocale() {
        return depletionMessageLocale;
    }
}
