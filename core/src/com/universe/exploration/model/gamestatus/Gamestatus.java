package com.universe.exploration.model.gamestatus;

import com.universe.exploration.model.player.CharacterStatus;

import java.util.List;

public class Gamestatus {
    private List<CharacterStatus> characterStatuses;
    private List<CharacterStatus> npcStatuses;
    private boolean playerTurn;

    public List<CharacterStatus> getCharacterStatuses() {
        return characterStatuses;
    }

    public void setCharacterStatuses(List<CharacterStatus> characterStatuses) {
        this.characterStatuses = characterStatuses;
    }

    public List<CharacterStatus> getNpcStatuses() {
        return npcStatuses;
    }

    public void setNpcStatuses(List<CharacterStatus> npcStatuses) {
        this.npcStatuses = npcStatuses;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }
}
