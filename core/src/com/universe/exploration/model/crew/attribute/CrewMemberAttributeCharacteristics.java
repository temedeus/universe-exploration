/**
 *
 */
package com.universe.exploration.model.crew.attribute;

/**
 * @author 10.1.2017 Teemu Puurunen
 */
public enum CrewMemberAttributeCharacteristics {
    AGILITY(10, 4),

    MORALE(10, 0),

    STRENGTH(10, 4),

    INTELLIGENCE(10, 4);

    private final int maxValue;

    private final int minValue;

    /**
     *
     */
    private CrewMemberAttributeCharacteristics(int maxValue, int minValue) {
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    /**
     * @return the maxValue
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * @return the minValue
     */
    public int getMinValue() {
        return minValue;
    }
}
