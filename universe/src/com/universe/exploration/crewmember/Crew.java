/**
 *
 */
package com.universe.exploration.crewmember;

import com.universe.exploration.crew.CrewMemberStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of the crew composed of {@link CrewMember}.
 *
 * @author 14.1.2017 Teemu Puurunen
 */
public class Crew {
    private List<CrewMember> crewmen = new ArrayList<CrewMember>();

    public void addCrewman(CrewMember crewman) {
        crewmen.add(crewman);
    }

    public void removeCrewman(CrewMember c) {
        crewmen.remove(c);
    }

    public List<CrewMember> getCrewmen() {
        return crewmen;
    }

    /**
     * Retrieve a list of alive {@link CrewMember}.
     *
     * @return List
     */
    public List<CrewMember> getAliveCrewmen() {
        List<CrewMemberStatus> statuses = new ArrayList<CrewMemberStatus>();
        statuses.add(CrewMemberStatus.ALIVE);
        statuses.add(CrewMemberStatus.ONSURVEY);
        return getCrewMembersByStatus(statuses);
    }

    /**
     * Retrieve a list of alive {@link CrewMember} on board spaceship.
     *
     * @return List
     */
    public List<CrewMember> getCrewMenAboardSpaceShip() {
        List<CrewMemberStatus> statuses = new ArrayList<CrewMemberStatus>();
        statuses.add(CrewMemberStatus.ALIVE);
        return getCrewMembersByStatus(statuses);
    }

    /**
     * Retrieve a list of alive {@link CrewMember} on survey.
     *
     * @return List
     */
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
