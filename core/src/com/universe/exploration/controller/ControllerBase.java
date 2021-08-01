package com.universe.exploration.controller;

import com.universe.exploration.UniverseExploration;

public abstract class ControllerBase {
    protected UniverseExploration universeExploration;

    public ControllerBase(UniverseExploration universeExploration) {
        this.universeExploration = universeExploration;
    }
}
