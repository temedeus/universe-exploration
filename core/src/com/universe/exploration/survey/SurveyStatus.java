/**
 * 
 */
package com.universe.exploration.survey;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.casualty.Casualty;
import com.universe.exploration.crewmember.Crew;

/**
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public class SurveyStatus {
    private List<Crew> surveyTeam;

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

    /**
     * @return the surveyTeam
     */
    public List<Crew> getSurveyTeam() {
        return surveyTeam;
    }

    /**
     * @param surveyTeam the surveyTeam to set
     */
    public void setSurveyTeam(List<Crew> surveyTeam) {
        this.surveyTeam = surveyTeam;
    }
    
    /**
     * 
     * @return
     */
    public int getCrewmenInSurveyTeam() {
	return surveyTeam.size();
    }

}
