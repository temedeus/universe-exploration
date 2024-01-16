package com.universe.exploration.model.gamestatus;

import com.universe.exploration.component.starsystem.Planet;
import com.universe.exploration.model.gamecharacter.GameCharacter;
import com.universe.exploration.model.gamecharacter.action.CharacterActionMode;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Gamestatus {
    private List<GameCharacter> playerCharacters;
    private Map<String, List<GameCharacter>> npcs;
    private Optional<GameCharacter> selectedCharacter;
    private CharacterActionMode characterActionMode;

    private List<Planet> planets;

    private GameCharacter characterInTurn;

    private boolean initialStatus;

    private int planetIndex;

    public boolean isInitialStatus() {
        return initialStatus;
    }

    public void setInitialStatus(boolean initialStatus) {
        this.initialStatus = initialStatus;
    }

    public List<GameCharacter> getActivePlanetsNpcs() {
        return planets.get(planetIndex).getPlanetComponent().getNpcs();
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public List<GameCharacter> getPlayerCharacters() {
        return playerCharacters;
    }

    public void setPlayerCharacters(List<GameCharacter> playerCharacters) {
        this.playerCharacters = playerCharacters;
    }

    public Map<String, List<GameCharacter>> getNpcs() {
        return npcs;
    }

    public void setNpcs(Map<String, List<GameCharacter>> npcs) {
        this.npcs = npcs;
    }

    public Optional<GameCharacter> getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setSelectedCharacter(Optional<GameCharacter> selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }

    public CharacterActionMode getCharacterActionMode() {
        return characterActionMode;
    }

    public void setCharacterActionMode(CharacterActionMode characterActionMode) {
        this.characterActionMode = characterActionMode;
    }

    public int getPlanetIndex() {
        return planetIndex;
    }

    public void setPlanetIndex(int planetIndex) {
        this.planetIndex = planetIndex;
    }

    public GameCharacter getCharacterInTurn() {
        return characterInTurn;
    }

    public void setCharacterInTurn(GameCharacter characterInTurn) {
        this.characterInTurn = characterInTurn;
    }
}
