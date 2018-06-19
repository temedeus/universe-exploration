/**
 *
 */
package com.universe.exploration.spritecontainer;

/**
 * <p>
 * Generic fader that fades within given boundaries.
 * </p>
 *
 * @author 16.1.2016 Teemu Puurunen
 */
public class Fader {
    private float minimumValue;

    private float maximumValue;

    private float decrementRate;

    private float incrementRate;

    private float currentValue;

    private boolean atMinimum;

    /**
     * Initiate fader with min/max for range and change rate.
     *
     * @param minimumValue
     * @param maximumValue
     * @param decrementRate
     * @param incrementRate
     */
    public Fader(float minimumValue, float maximumValue, float decrementRate, float incrementRate) {
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.decrementRate = decrementRate;
        this.incrementRate = incrementRate;
    }

    public float updateAlpha(boolean decreaseAlphaValue) {
        if (decreaseAlphaValue) {
            if (currentValue > minimumValue) {
                currentValue -= decrementRate;
                atMinimum = false;
            } else {
                atMinimum = true;
            }
        } else {
            if (currentValue < maximumValue) {
                currentValue += incrementRate;
            } else {
                atMinimum = false;
            }
        }

        return currentValue;
    }

    /**
     * @return the alphaMinimized
     */
    public boolean isAlphaMinimized() {
        return atMinimum;
    }

    /**
     * @return the alphaValue
     */
    public float getAlphaValue() {
        return currentValue;
    }

    /**
     * @param alphaValue the alphaValue to set
     */
    public void setAlphaValue(float alphaValue) {
        this.currentValue = alphaValue;
    }
}