/**
 * 
 */
package com.universe.exploration.userinterface.data.container;

import com.universe.exploration.crew.CrewMemberCondition;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.userinterface.data.DataPair;

/**
 * @author 9.4.2016 Teemu Puurunen
 *
 */
public class CrewMemberDetails extends DataPairContainer {
    private CrewMember crewMember;

    /**
     * 
     */
    public CrewMemberDetails(CrewMember crewMember) {
	this.crewMember = crewMember;
    }

    /**
     * {@link DataPair} keys are hard-coded as they are not really used anywhere
     * outside this context. If things change, they should be somewhere else.
     * 
     * @param crewMember
     * @return
     */
    @Override
    public void createPairs() {
	add(new DataPair("name", "Name:", crewMember.getName()));
	add(new DataPair("status", "Status: ", "" + Localizer.getInstance().get(crewMember.getStatus())));
	
	String condition = convertCrewMemberConditionToString();
	if(!condition.equals("")) {
	    add(new DataPair("status", "Condition: ", "" + condition));
	}
	
	add(new DataPair("age", "Age: ", "" + crewMember.getAge()));
	add(new DataPair("sex", "Sex: ", "" + Localizer.getInstance().get(crewMember.getSex())));
	add(new DataPair("nationality", "Nationality: ", "" + Localizer.getInstance().get(crewMember.getNationality())));
	add(new DataPair("agility", "Agility:", "" + crewMember.getAgility().getValue()));
	add(new DataPair("intelligence", "Intelligence: ", "" + crewMember.getIntelligence().getValue()));
	add(new DataPair("morale", "Morale: ", "" + crewMember.getMorale().getValue()));
	add(new DataPair("strength", "Strength: ", "" + crewMember.getStrength().getValue()));

    }

    private String convertCrewMemberConditionToString() {
	String tmp = "";
	for(CrewMemberCondition condition : crewMember.getCondition()) {
	    tmp += Localizer.getInstance().get(condition);
	}
	
	return tmp;
    }
}
