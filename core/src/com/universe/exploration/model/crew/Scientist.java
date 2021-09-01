package com.universe.exploration.model.crew;

import com.universe.exploration.model.crew.action.CrewMemberAction;

public class Scientist extends GameCharacter {
    @Override
    public CrewMemberAction setupActions() {
        talkAction = new CrewMemberAction.Builder()
                .verticalReach(3)
                .horizontalReach(3)
                .crewMemberActionType(CrewMemberAction.CrewMemberActionType.TALK)
                .fillReach(false)
                .crossReach(false)
                .build();

        talkAction = new CrewMemberAction.Builder()
                .verticalReach(3)
                .horizontalReach(3)
                .crewMemberActionType(CrewMemberAction.CrewMemberActionType.MOVE)
                .fillReach(false)
                .crossReach(false)
                .build();

        return null;
    }
}
