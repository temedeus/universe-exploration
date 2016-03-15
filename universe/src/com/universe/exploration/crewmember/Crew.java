/**
 * 
 */
package com.universe.exploration.crewmember;

import java.util.ArrayList;
import java.util.List;

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
}
