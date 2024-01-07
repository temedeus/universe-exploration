package com.universe.exploration.controller.game;

import com.universe.exploration.model.gamecharacter.GameCharacter;
import com.universe.exploration.model.starsystem.NpcType;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class NpcFactory {
    private final GameAssetManager gameAssetManager;


    public NpcFactory(GameAssetManager gameAssetManager) {
        this.gameAssetManager = gameAssetManager;
    }

    public List<GameCharacter> createNpcs() {
        List<GameCharacter> npcs = new ArrayList<>();

        Random random = new Random();
        int count = 1;
                /*random.ints(1, 1)
                .findFirst()
                .getAsInt();*/

        for(int x=0; x < count; x++) {
            int index = random.ints(0, NpcType.values().length - 1)
                    .findFirst()
                    .getAsInt();
            npcs.add(NpcType.values()[index].npcSupplier().get());
        }
        return npcs;
    }
}
