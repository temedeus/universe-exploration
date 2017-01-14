/**
 * 
 */
package com.universe.exploration.survey;

import java.util.List;

import com.universe.exploration.casualty.Casualty;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.resource.ResourcesFoundBean;

/**
 * Survey abstraction. Contains information about the survey itself, resources
 * found during the expedition, start and end dates.
 * 
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public class Survey {
    private List<CrewMember> surveyTeam;

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
    private List<Casualty> mortalities;

    private ResourcesFoundBean resourcesFound;

    private String surveyName;

    /**
     * @return the resourcesFound
     */
    public ResourcesFoundBean getResourcesFound() {
	return resourcesFound;
    }

    /**
     * @param resourcesFound
     *            the resourcesFound to set
     */
    public void setResourcesFound(ResourcesFoundBean resourcesFound) {
	this.resourcesFound = resourcesFound;
    }

    /**
     * @return the mortalities
     */
    public List<Casualty> getMortalities() {
	return mortalities;
    }

    /**
     * @param mortalities
     *            the mortalities to set
     */
    public void setMortalities(List<Casualty> mortalities) {
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
    public List<CrewMember> getSurveyTeam() {
	return surveyTeam;
    }

    /**
     * @param surveyTeam
     *            the surveyTeam to set
     */
    public void setSurveyTeam(List<CrewMember> surveyTeam) {
	this.surveyTeam = surveyTeam;
    }

    /**
     * 
     * @return
     */
    public int getSurveyTeamSize() {
	return surveyTeam.size();
    }

    /**
     * @return the surveyName
     */
    public String getSurveyName() {
	return surveyName;
    }

    /**
     * @param surveyName
     *            the surveyName to set
     */
    public void setSurveyName(String surveyName) {
	this.surveyName = surveyName;
    }

}
