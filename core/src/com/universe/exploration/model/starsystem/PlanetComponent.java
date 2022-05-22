package com.universe.exploration.model.starsystem;

public class PlanetComponent {
    private String name;

    private PlanetType planetType;

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

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPlanetType(PlanetType planetType) {
            this.planetType = planetType;
            return this;
        }

        public PlanetComponent build() {
            PlanetComponent planetComponent = new PlanetComponent();
            planetComponent.name = this.name;
            planetComponent.planetType = this.planetType;

            return planetComponent;
        }
    }

    private PlanetComponent() {}
}
