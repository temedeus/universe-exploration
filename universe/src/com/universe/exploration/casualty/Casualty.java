/**
 * 
 */
package com.universe.exploration.casualty;

import com.universe.exploration.crewmember.CrewMember;

/**
 * <p>
 * Bean describing crewmans death, reason of fatality etc.
 * </p>
 * 
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public class Casualty {

    private CrewMember member;

    /**
     * Incident occurred during a survey.
     */
    private SurveyIncident incident;

    /**
     * @return the causeOfDeath
     */
    public SurveyIncident getSurveyIncident() {
	return incident;
    }

    /**
     * @param causeOfDeath
     *            the causeOfDeath to set
     */
    public void setCauseOfDeath(SurveyIncident causeOfDeath) {
	this.incident = causeOfDeath;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Casualty [crewmenID=" + member.getId() + ", causeOfDeath=" + incident + "]";
    }

    /**
     * @return the member
     */
    public CrewMember getMember() {
	return member;
    }

    /**
     * @param member
     *            the member to set
     */
    public void setMember(CrewMember member) {
	this.member = member;
    }
}
