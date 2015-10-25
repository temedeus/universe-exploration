/**
 * 
 */
package com.universe.exploration.survey;

import java.util.ArrayList;

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
	private ArrayList<Mortality> mortalities;

	private ResourcesFound resourcesFound;
	
	/**
	 * @return the resourcesFound
	 */
	public ResourcesFound getResourcesFound() {
		return resourcesFound;
	}

	/**
	 * @param resourcesFound the resourcesFound to set
	 */
	public void setResourcesFound(ResourcesFound resourcesFound) {
		this.resourcesFound = resourcesFound;
	}

	/**
	 * @return the mortalities
	 */
	public ArrayList<Mortality> getMortalities() {
		return mortalities;
	}

	/**
	 * @param mortalities the mortalities to set
	 */
	public void setMortalities(ArrayList<Mortality> mortalities) {
		this.mortalities = mortalities;
	}

	/**
	 * @return the crewmenInSurveyTeam
	 */
	public int getCrewmenInSurveyTeam() {
		return crewmenInSurveyTeam;
	}

	/**
	 * @param crewmenInSurveyTeam the crewmenInSurveyTeam to set
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
	 * @param surveyStartDay the surveyStartDay to set
	 */
	public void setSurveyStartDay(int surveyStartDay) {
		this.surveyStartDay = surveyStartDay;
	}

	/**
	 * @param surveyEndDay the surveyEndDay to set
	 */
	public void setSurveyEndDay(int surveyEndDay) {
		this.surveyEndDay = surveyEndDay;
	}
	
}
