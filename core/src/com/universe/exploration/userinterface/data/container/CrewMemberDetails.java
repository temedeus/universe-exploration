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
        add("name", "Name:", crewMember.getName());
        add("status", "Status: ", "" + Localizer.getInstance().get(crewMember.getStatus()));

        String condition = convertCrewMemberConditionToString();
        if (!condition.equals("")) {
            add("status", "Condition: ", condition);
        }

        add("age", "Age: ", "" + crewMember.getAge());
        add("sex", "Sex: ", "" + Localizer.getInstance().get(crewMember.getSex()));
        add("nationality", "Nationality: ", "" + Localizer.getInstance().get(crewMember.getNationality()));
        add("agility", "Agility:", "" + crewMember.getAgility().getValue());
        add("intelligence", "Intelligence: ", "" + crewMember.getIntelligence().getValue());
        add("morale", "Morale: ", "" + crewMember.getMorale().getValue());
        add("strength", "Strength: ", "" + crewMember.getStrength().getValue());

    }

    private String convertCrewMemberConditionToString() {
        String tmp = "";
        for (CrewMemberCondition condition : crewMember.getCondition()) {
            tmp += Localizer.getInstance().get(condition);
        }

        return tmp;
    }

    /**
     * @return the crewMember
     */
    public CrewMember getCrewMember() {
        return crewMember;
    }

    /**
     * @param crewMember the crewMember to set
     */
    public void setCrewMember(CrewMember crewMember) {
        this.crewMember = crewMember;
    }
}
