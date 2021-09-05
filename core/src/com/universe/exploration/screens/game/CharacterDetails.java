package com.universe.exploration.screens.game;

import com.universe.exploration.model.crew.GameCharacter;

class CharacterDetails {
    private boolean selected;
    private int coordinateX;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public GameCharacter getGameCharacter() {
        return gameCharacter;
    }

    public void setGameCharacter(GameCharacter gameCharacter) {
        this.gameCharacter = gameCharacter;
    }

    private int coordinateY;
    private GameCharacter gameCharacter;



    CharacterDetails(Builder builder) {
        selected = builder.selected;
        coordinateX = builder.coordinateX;
        coordinateY = builder.coordinateY;
        gameCharacter = builder.gameCharacter;
    }

    public static final class Builder {
        private boolean selected;
        private int coordinateX;
        private int coordinateY;
        private GameCharacter gameCharacter;

        public Builder() {
        }

        public Builder selected(boolean val) {
            selected = val;
            return this;
        }

        public Builder coordinateX(int val) {
            coordinateX = val;
            return this;
        }

        public Builder coordinateY(int val) {
            coordinateY = val;
            return this;
        }

        public Builder gameCharacter(GameCharacter val) {
            gameCharacter = val;
            return this;
        }

        public CharacterDetails build() {
            return new CharacterDetails(this);
        }
    }}
