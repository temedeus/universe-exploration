package com.universe.exploration.model.crew;

import com.universe.exploration.model.crew.action.CrewMemberAction;

public abstract class GameCharacter {

    protected CrewMemberAction talkAction;

    protected CrewMemberAction moveAction;

    public CrewMemberAction getTalkAction() {
        return  talkAction;
    }

    public CrewMemberAction getMoveAction() {
        return moveAction;
    }

    abstract CrewMemberAction setupActions();
}
