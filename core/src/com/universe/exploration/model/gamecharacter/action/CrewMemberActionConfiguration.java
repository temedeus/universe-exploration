package com.universe.exploration.model.gamecharacter.action;

public class CrewMemberActionConfiguration {
    private int verticalReach;

    private int horizontalReach;

    private boolean crossReach;

    private CharacterActionMode characterActionMode;

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

    public CharacterActionMode getCrewMemberActionType() {
        return characterActionMode;
    }

    public void setCrewMemberActionType(CharacterActionMode characterActionMode) {
        this.characterActionMode = characterActionMode;
    }

    private boolean fillReach;

    private CrewMemberActionConfiguration(Builder builder) {
        verticalReach = builder.verticalReach;
        horizontalReach = builder.horizontalReach;
        crossReach = builder.crossReach;
        fillReach = builder.fillReach;
        characterActionMode = builder.characterActionMode;
    }

    public static final class Builder {
        private int verticalReach;
        private int horizontalReach;
        private boolean crossReach;
        private boolean fillReach;
        private CharacterActionMode characterActionMode;

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

        public Builder crewMemberActionType(CharacterActionMode val) {
            characterActionMode = val;
            return this;
        }

        public CrewMemberActionConfiguration build() {
            return new CrewMemberActionConfiguration(this);
        }
    }
}
