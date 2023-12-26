package com.universe.exploration.controller.game;

import com.universe.exploration.model.gamecharacter.GameCharacter;
import com.universe.exploration.model.starsystem.NpcType;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;

import java.util.Random;

public class NpcFactory {
    private final GameAssetManager gameAssetManager;


    public NpcFactory(GameAssetManager gameAssetManager) {
        this.gameAssetManager = gameAssetManager;
    }

    public GameCharacter createNpc() {
        Random random = new Random();
        int index = random.ints(0, NpcType.values().length - 1)
                .findFirst()
                .getAsInt();
        NpcType npcType = NpcType.values()[index];
        return npcType.npcSupplier().get();
    }
}
