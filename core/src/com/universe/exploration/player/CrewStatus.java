/**
 * 
 */
package com.universe.exploration.player;

/**
 * Abstract class for crew statuses. Deals with handling min/max values and the
 * current value. Also provides a warning if given resource is about to be
 * depleted.
 * 
 * @author 14.1.2017 Teemu Puurunen
 *
 */
public abstract class CrewStatus implements ICrewStatus {

    private float value;

    /**
     * In case a status values reaches zero, user must be notified using this
     * boolean.
     */
    protected boolean providedWarningOnDepletion;

    /**
     * Set {@link #providedWarningOnDepletion} as false so as not to initially
     * warn on depletion of this given resource.
     */
    public CrewStatus() {
	value = getSetup().getMaxValue();
	providedWarningOnDepletion = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.universe.exploration.player.ICrewStatus#incrementValue(float)
     */
    @Override
    public void incrementValue(float increment) {
	if (value + increment > getSetup().getMaxValue()) {
	    value = getSetup().getMaxValue();
	} else {
	    value += increment;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.universe.exploration.player.ICrewStatus#decrementValue(float)
     */
    @Override
    public void decrementValue(float decrement) {
	if (value - decrement <= getSetup().getMinValue()) {
	    value = getSetup().getMinValue();
	    providedWarningOnDepletion = false;
	} else {
	    value -= decrement;
	}
    }

    @Override
    public float getValue() {
	return value;
    }

    @Override
    public boolean isProvidedWarningOnDepletion() {
	return providedWarningOnDepletion;
    }

    @Override
    public void setProvidedWarningOnDepletion(boolean providedWarningOnDepletion) {
	this.providedWarningOnDepletion = providedWarningOnDepletion;
    }

}
