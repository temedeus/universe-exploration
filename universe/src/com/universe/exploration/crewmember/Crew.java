/**
 * 
 */
package com.universe.exploration.crewmember;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.crew.CrewMemberStatus;

public class Crew {
    private List<CrewMember> crewmen = new ArrayList<CrewMember>();

    public void addCrewman(CrewMember crewman) {
	crewmen.add(crewman);
    }

    public void removeCrewman(CrewMember c) {
	crewmen.remove(c);
    }

    /**
     * @return the crewmen
     */
    public List<CrewMember> getCrewmen() {
	return crewmen;
    }

    public List<CrewMember> getAliveCrewmen() {
	List<CrewMemberStatus> statuses = new ArrayList<CrewMemberStatus>();
	statuses.add(CrewMemberStatus.ALIVE);
	statuses.add(CrewMemberStatus.ONSURVEY);
	return getCrewMembersByStatus(statuses);
    }

    public List<CrewMember> getCrewMenAboardSpaceShip() {
	List<CrewMemberStatus> statuses = new ArrayList<CrewMemberStatus>();
	statuses.add(CrewMemberStatus.ALIVE);
	return getCrewMembersByStatus(statuses);
    }

    public List<CrewMember> getCrewMenOnSurvey() {
	List<CrewMemberStatus> statuses = new ArrayList<CrewMemberStatus>();
	statuses.add(CrewMemberStatus.ONSURVEY);
	return getCrewMembersByStatus(statuses);
    }

    public List<CrewMember> getCrewMembersByStatus(List<CrewMemberStatus> statuses) {
	List<CrewMember> filteredMembers = new ArrayList<CrewMember>();
	for (CrewMember member : crewmen) {
	    if (statuses.contains(member.getStatus())) {
		filteredMembers.add(member);
	    }
	}

	return filteredMembers;
    }
}
