/**
 * 
 */
package com.universe.exploration.crewmember;

import java.util.List;

/**
 * @author 23.12.2016 Teemu Puurunen
 *
 */
public class CrewMemberTools {
    /**
     * Creates concatenated string of crewmember names.
     * 
     * @param crewMembers
     * @return
     */
    public String concatenateCrewMemberListNames(List<CrewMember> crewMembers) {
	StringBuilder sb = new StringBuilder();
	for (CrewMember member : crewMembers) {
	    if (crewMembers.indexOf(member) > 0) {
		sb.append(", ");
	    }
	    sb.append(member.getName());
	}
	return sb.toString();
    }
}
