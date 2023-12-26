package com.universe.exploration.model.starsystem;

import com.universe.exploration.model.gamecharacter.GameCharacter;

import java.util.List;

public class PlanetComponent {
    private String name;

    private PlanetType planetType;

    private List<GameCharacter> npcs;

    public List<GameCharacter> getNpcs() {
        return npcs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlanetType getPlanetType() {
        return planetType;
    }


    public static class Builder {
        private String name;

        private PlanetType planetType;
        private List<GameCharacter> npcs;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPlanetType(PlanetType planetType) {
            this.planetType = planetType;
            return this;
        }

        public Builder withNpcs(List<GameCharacter> npcs) {
            this.npcs = npcs;
            return this;
        }

        public PlanetComponent build() {
            PlanetComponent planetComponent = new PlanetComponent();
            planetComponent.name = this.name;
            planetComponent.planetType = this.planetType;
            planetComponent.npcs = this.npcs;

            return planetComponent;
        }
    }

    private PlanetComponent() {}
}
