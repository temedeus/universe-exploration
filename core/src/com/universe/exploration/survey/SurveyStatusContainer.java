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

    /**
	 * 
	 */
    private static final long serialVersionUID = -3335075506066292326L;

    /**
     * <p>
     * Removes surveyStatus from ArrayList if survey is over.
     * </p>
     * 
     * @param currentDay
     * @return
     */
    public SurveyStatus isSurveyOver(int currentDay) {
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
