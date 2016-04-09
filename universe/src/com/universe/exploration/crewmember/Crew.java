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
	List<CrewMember> aliveMembers = new ArrayList<CrewMember>();
	for(CrewMember member : crewmen) {
	    if(member.getStatus() == CrewMemberStatus.ALIVE) {
		aliveMembers.add(member);
	    }
	}
	
	return aliveMembers;
    }
}
