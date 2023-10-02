package com.universe.exploration.model.gamestatus;

import com.universe.exploration.model.gamecharacter.GameCharacter;
import com.universe.exploration.model.gamecharacter.action.CharacterActionMode;

import java.util.List;
import java.util.Optional;

public class Gamestatus {
    private List<GameCharacter> playerCharacters;
    private List<GameCharacter> npcs;
    private Optional<GameCharacter> selectedCharacter;
    private CharacterActionMode characterActionMode;

    public List<GameCharacter> getPlayerCharacters() {
        return playerCharacters;
    }

    public void setPlayerCharacters(List<GameCharacter> playerCharacters) {
        this.playerCharacters = playerCharacters;
    }

    public List<GameCharacter> getNpcs() {
        return npcs;
    }

    public void setNpcs(List<GameCharacter> npcs) {
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



}
