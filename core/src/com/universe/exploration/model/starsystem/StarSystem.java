package com.universe.exploration.model.starsystem;

import com.universe.exploration.component.starsystem.Planet;

import java.util.List;

public class StarSystem {
    private List<Planet> planets;

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }
}
