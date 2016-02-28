/**
 * 
 */
package com.universe.exploration.survey;

import java.util.ArrayList;

/**
 * <p>
 * Contains list of currently active surveys. Can be used to track down survey
 * that should end.
 * </p>
 * 
 * <p>
 * De facfo maintains a list of {@link SurveyStatus}.
 * 
 * @author 28.10.2015 Teemu Puurunen
 *
 */
public class SurveyStatusContainer extends ArrayList<SurveyStatus> {

    private static final long serialVersionUID = -3335075506066292326L;

    /**
     * <p>
     * Removes surveyStatus from ArrayList if survey is over. We immediately
     * return a {@link SurveyStatus} because this check is run in a loop. In a
     * normal scenario surveys should not end simultaneously anyway.
     * </p>
     * 
     * @param currentDay
     *            Day the player is currently at.
     * @return {@link SurveyStatus} Info on the ended survey and team status.
     * 
     */
    public SurveyStatus findAndRemoveOpenSurvey(int currentDay) {
	for (SurveyStatus surveyStatus : this) {
	    if (currentDay >= surveyStatus.getSurveyEndDay()) {
		remove(surveyStatus);
		return surveyStatus;
	    }
	}

	return null;
    }

    public boolean isSurveyTeamSizeAcceptable(int size, int crewmen) {
	return (size <= crewmen - crewmenOnSurvey()) ? true : false;
    }

    public int crewmenOnSurvey() {
	int crewmenOnSurvey = 0;
	for (SurveyStatus surveyStatus : this) {
	    crewmenOnSurvey += surveyStatus.getCrewmenInSurveyTeam();
	}

	return crewmenOnSurvey;
    }

}
