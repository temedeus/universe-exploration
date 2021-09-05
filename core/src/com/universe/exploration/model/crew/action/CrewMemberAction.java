package com.universe.exploration.model.crew.action;

import com.universe.exploration.model.starsystem.PlanetComponent;

public class CrewMemberAction {
    private int verticalReach;

    private int horizontalReach;

    private boolean crossReach;

    public int getVerticalReach() {
        return verticalReach;
    }

    public void setVerticalReach(int verticalReach) {
        this.verticalReach = verticalReach;
    }

    public int getHorizontalReach() {
        return horizontalReach;
    }

    public void setHorizontalReach(int horizontalReach) {
        this.horizontalReach = horizontalReach;
    }

    public boolean isCrossReach() {
        return crossReach;
    }

    public void setCrossReach(boolean crossReach) {
        this.crossReach = crossReach;
    }

    public boolean isFillReach() {
        return fillReach;
    }

    public void setFillReach(boolean fillReach) {
        this.fillReach = fillReach;
    }

    public CrewMemberActionType getCrewMemberActionType() {
        return crewMemberActionType;
    }

    public void setCrewMemberActionType(CrewMemberActionType crewMemberActionType) {
        this.crewMemberActionType = crewMemberActionType;
    }

    private boolean fillReach;


    private CrewMemberActionType crewMemberActionType;

    private CrewMemberAction(Builder builder) {
        verticalReach = builder.verticalReach;
        horizontalReach = builder.horizontalReach;
        crossReach = builder.crossReach;
        fillReach = builder.fillReach;
        crewMemberActionType = builder.crewMemberActionType;
    }

    public enum CrewMemberActionType {
        ATTACK, TALK, MOVE;
    }

    public static final class Builder {
        private int verticalReach;
        private int horizontalReach;
        private boolean crossReach;
        private boolean fillReach;
        private CrewMemberActionType crewMemberActionType;

        public Builder() {
        }

        public Builder verticalReach(int val) {
            verticalReach = val;
            return this;
        }

        public Builder horizontalReach(int val) {
            horizontalReach = val;
            return this;
        }

        public Builder crossReach(boolean val) {
            crossReach = val;
            return this;
        }

        public Builder fillReach(boolean val) {
            fillReach = val;
            return this;
        }

        public Builder crewMemberActionType(CrewMemberActionType val) {
            crewMemberActionType = val;
            return this;
        }

        public CrewMemberAction build() {
            return new CrewMemberAction(this);
        }
    }
}
