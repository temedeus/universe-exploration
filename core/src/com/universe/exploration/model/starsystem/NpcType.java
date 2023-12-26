package com.universe.exploration.model.starsystem;

import com.universe.exploration.model.gamecharacter.*;

import java.util.function.Supplier;

public enum NpcType {
    Amazalien() {
        @Override
        public Supplier<GameCharacter> npcSupplier() {
            return Amazalien::new;
        }
    },
    GummybearAlien() {
        @Override
        public Supplier<GameCharacter> npcSupplier() {
            return GummyBearAlien::new;
        }
    },
    HorseAlien() {
        @Override
        public Supplier<GameCharacter> npcSupplier() {
            return HorseAlien::new;
        }
    },
    FuzzyAlien() {
        @Override
        public Supplier<GameCharacter> npcSupplier() {
            return FuzzyAlien::new;
        }
    },
    Monstrosity() {
        @Override
        public Supplier<GameCharacter> npcSupplier() {
            return Monstrosity::new;
        }
    },
    Pinkalien() {
        @Override
        public Supplier<GameCharacter> npcSupplier() {
            return Pinkalien::new;
        }

    },
    Pleasantalien() {
        @Override
        public Supplier<GameCharacter> npcSupplier() {
            return Pleasantalien::new;
        }
    },
    Rockalien() {
        @Override
        public Supplier<GameCharacter> npcSupplier() {
            return Rockalien::new;
        }
    },
    Tortalien() {
        @Override
        public Supplier<GameCharacter> npcSupplier() {
            return Tortalien::new;
        }
    };

    public Supplier<GameCharacter> npcSupplier() {
        throw new RuntimeException("Must override");
    }
}
