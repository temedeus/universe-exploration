/**
 * 
 */
package com.universe.exploration.casualty;

/**
 * <p>
 * Bean describing crewmans death, reason of fatality etc.
 * </p>
 * 
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public class Casualty {

    private int crewmenID;

    /**
     * Determine cause of death.
     */
    private CauseOfDeath causeOfDeath;

    /**
     * @return the crewmenID
     */
    public int getCrewmenID() {
	return crewmenID;
    }

    /**
     * @return the causeOfDeath
     */
    public CauseOfDeath getCauseOfDeath() {
	return causeOfDeath;
    }

    /**
     * @param crewmenID
     *            the crewmenID to set
     */
    public void setCrewmenID(int crewmenID) {
	this.crewmenID = crewmenID;
    }

    /**
     * @param causeOfDeath
     *            the causeOfDeath to set
     */
    public void setCauseOfDeath(CauseOfDeath causeOfDeath) {
	this.causeOfDeath = causeOfDeath;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Casualty [crewmenID=" + crewmenID + ", causeOfDeath=" + causeOfDeath + "]";
    }
}
