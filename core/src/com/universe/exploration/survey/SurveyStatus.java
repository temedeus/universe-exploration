/**
 * 
 */
package com.universe.exploration.survey;

import java.util.ArrayList;

import com.universe.exploration.mortality.Casualty;

/**
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public class SurveyStatus {
    private int crewmenInSurveyTeam;

    /**
     * The day survey started.
     */
    private int surveyStartDay;

    /**
     * The day survey ends.
     */
    private int surveyEndDay;

    /**
     * Determines how many crewmen will die during the survey.
     */
    private ArrayList<Casualty> mortalities;

    private ResourcesFound resourcesFound;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "SurveyStatus [crewmenInSurveyTeam=" + crewmenInSurveyTeam + ", surveyStartDay=" + surveyStartDay + ", surveyEndDay="
		+ surveyEndDay + ", mortalities=" + mortalities + ", resourcesFound=" + resourcesFound + "]";
    }

    /**
     * @return the resourcesFound
     */
    public ResourcesFound getResourcesFound() {
	return resourcesFound;
    }

    /**
     * @param resourcesFound
     *            the resourcesFound to set
     */
    public void setResourcesFound(ResourcesFound resourcesFound) {
	this.resourcesFound = resourcesFound;
    }

    /**
     * @return the mortalities
     */
    public ArrayList<Casualty> getMortalities() {
	return mortalities;
    }

    /**
     * @param mortalities
     *            the mortalities to set
     */
    public void setMortalities(ArrayList<Casualty> mortalities) {
	this.mortalities = mortalities;
    }

    /**
     * @return the crewmenInSurveyTeam
     */
    public int getCrewmenInSurveyTeam() {
	return crewmenInSurveyTeam;
    }

    /**
     * @param crewmenInSurveyTeam
     *            the crewmenInSurveyTeam to set
     */
    public void setCrewmenInSurveyTeam(int crewmenInSurveyTeam) {
	this.crewmenInSurveyTeam = crewmenInSurveyTeam;
    }

    /**
     * @return the surveyStartDay
     */
    public int getSurveyStartDay() {
	return surveyStartDay;
    }

    /**
     * @return the surveyEndDay
     */
    public int getSurveyEndDay() {
	return surveyEndDay;
    }

    /**
     * @param surveyStartDay
     *            the surveyStartDay to set
     */
    public void setSurveyStartDay(int surveyStartDay) {
	this.surveyStartDay = surveyStartDay;
    }

    /**
     * @param surveyEndDay
     *            the surveyEndDay to set
     */
    public void setSurveyEndDay(int surveyEndDay) {
	this.surveyEndDay = surveyEndDay;
    }

}
