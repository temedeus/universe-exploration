/**
 * 
 */
package com.universe.exploration.survey;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.crew.CrewMemberStatus;
import com.universe.exploration.crewmember.CrewMember;

/**
 * Contains list of currently active surveys. Can be used to track down survey
 * that should end. In practice maintains an {@link ArrayList} of {@link Survey}
 * 
 * @author 28.10.2015 Teemu Puurunen
 *
 */
public class SurveyContainer extends ArrayList<Survey> {

    private static final long serialVersionUID = -3335075506066292326L;

    /**
     * <p>
     * Removes surveyStatus from ArrayList if survey is over. We immediately
     * return a {@link Survey} because this check is run in a loop. In a normal
     * scenario surveys should not end simultaneously anyway.
     * </p>
     * 
     * @param currentDay
     *            Day the player is currently at.
     * @return {@link Survey} Info on the ended survey and team status.
     * 
     */
    public Survey findAndRemoveOpenSurvey(int currentDay) {
	for (Survey surveyStatus : this) {
	    if (currentDay >= surveyStatus.getSurveyEndDay()) {
		remove(surveyStatus);
		return surveyStatus;
	    }
	}

	return null;
    }

    public boolean isSurveyTeamAcceptable(List<CrewMember> crewmen) {
	for (CrewMember member : crewmen) {
	    if (member.getStatus() != CrewMemberStatus.ALIVE) {
		return false;
	    }
	}

	return true;
    }

    public int crewmenOnSurvey() {
	int crewmenOnSurvey = 0;
	for (Survey surveyStatus : this) {
	    crewmenOnSurvey += surveyStatus.getSurveyTeamSize();
	}

	return crewmenOnSurvey;
    }

}
