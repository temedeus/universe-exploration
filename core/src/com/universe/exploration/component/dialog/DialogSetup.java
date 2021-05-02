/**
 *
 */
package com.universe.exploration.component.dialog;

/**
 * Dialog setups.
 *
 * @author 18.9.2015 Teemu Puurunen
 */
public enum DialogSetup {
    SMALL(0.3f), MEDIUM(0.6f), LARGE(0.8f);

    private final float screenSizeRatio;

    DialogSetup(float ratio) {
        this.screenSizeRatio = ratio;
    }

    public float getScreenSizeRatio() {
        return screenSizeRatio;
    }
}
