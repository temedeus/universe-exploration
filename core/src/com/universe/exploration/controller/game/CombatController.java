package com.universe.exploration.controller.game;

import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.boardgrid.BoardConfig;
import com.universe.exploration.component.starsystem.Planet;
import com.universe.exploration.controller.ControllerBase;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.model.Coordinate;
import com.universe.exploration.model.gamecharacter.GameCharacter;
import com.universe.exploration.model.gamecharacter.Soldier;
import com.universe.exploration.model.gamecharacter.action.CharacterActionConfiguration;
import com.universe.exploration.model.gamecharacter.action.CharacterActionMode;
import com.universe.exploration.model.gamestatus.Gamestatus;
import com.universe.exploration.screens.combat.CombatScreen;
import com.universe.exploration.screens.planetselection.PlanetSelectionScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class CombatController extends ControllerBase {
    private UEListener actionTriggeredListener;

    private Gamestatus gamestatus;
    private CombatScreen combatScreen;

    public CombatController(UniverseExploration universeExploration, CombatScreen combatScreen) {
        super(universeExploration);

        this.gamestatus = universeExploration.getGamestatus();
        this.combatScreen = combatScreen;
        gamestatus.setCharacterActionMode(CharacterActionMode.MOVE);
        gamestatus.setSelectedCharacter(Optional.empty());
        gamestatus.setPlayerCharacters(new ArrayList<>());
        gamestatus.setNpcs(new HashMap<>());
        Soldier soldier = new Soldier();
        soldier.setupActions();
        soldier.setNpc(false);
        soldier.setSelected(false);
        soldier.setCoordinates(0, 0);
        this.gamestatus.getPlayerCharacters().add(soldier);

        List<Planet> planets = this.gamestatus.getPlanets();

        planets.forEach(planet -> {
            for (GameCharacter gameCharacter : planet.getPlanetComponent().getNpcs()) {
                gameCharacter.setupActions();
                gameCharacter.setNpc(true);
                gameCharacter.setSelected(false);
                gameCharacter.setCoordinates(BoardConfig.BOARD_SIZE_MAX_X - 1, BoardConfig.BOARD_SIZE_MAX_Y - 1);
            }
            this.gamestatus.getNpcs().put(planet.getPlanetComponent().getName(), planet.getPlanetComponent().getNpcs());
        });
    }

    public void applyActionWhenPossible(Coordinate coordinateClicked) {
        if (gamestatus.getSelectedCharacter().isPresent()) {
            GameCharacter character = gamestatus.getSelectedCharacter().get();
            if (coordinateClicked.matchesGameCharacterPosition(character)) {
                return;
            }

            if (gamestatus.getCharacterActionMode() == CharacterActionMode.MOVE) {
                character.setCoordinates(coordinateClicked.getX(), (coordinateClicked.getY()));
                combatScreen.createMoveToActionOnCoordinates(character, coordinateClicked.getX(), coordinateClicked.getY());

            }
            gamestatus.setSelectedCharacter(Optional.empty());
        }
    }

    public List<Coordinate> getAreaToPaint(Coordinate coordinateClicked) {
        List<Coordinate> coordinates = new ArrayList<>();

        Optional<GameCharacter> gameCharacter = gamestatus.getPlayerCharacters().stream()
                .filter(playerGameCharacter -> playerGameCharacter.getCoordinateX() == coordinateClicked.getX() && playerGameCharacter.getCoordinateY() == coordinateClicked.getY())
                .findFirst();

        if (gameCharacter.isPresent()) {
            gamestatus.setSelectedCharacter(gameCharacter);
            CharacterActionConfiguration selectedAction = gameCharacter.get().getSelectedActionConfiguration(gamestatus.getCharacterActionMode());
            int verticalReach = selectedAction.getVerticalReach();
            int horizontalReach = selectedAction.getHorizontalReach();

            int verticalRangeStart = Math.max(coordinateClicked.getY() - verticalReach, 0);
            int verticalRangeEnd = Math.min(coordinateClicked.getY() + verticalReach, BoardConfig.BOARD_SIZE_MAX_Y - 1);
            int horizontalRangeStart = Math.max(coordinateClicked.getX() - horizontalReach, 0);
            int horizontalRangeEnd = Math.min(coordinateClicked.getX() + horizontalReach, BoardConfig.BOARD_SIZE_MAX_X - 1);

            IntStream.range(horizontalRangeStart, horizontalRangeEnd + 1).forEach(coordinateX -> coordinates.add(new Coordinate(coordinateX, coordinateClicked.getY())));
            IntStream.range(verticalRangeStart, verticalRangeEnd + 1).forEach(coordinateY -> coordinates.add(new Coordinate(coordinateClicked.getX(), coordinateY)));
        }
        return coordinates;
    }

    public CharacterActionMode getSelectedAction() {
        return gamestatus.getCharacterActionMode();
    }

    public void setSelectedAction(CharacterActionMode characterActionMode) {
        this.gamestatus.setCharacterActionMode(characterActionMode);
    }

    public List<GameCharacter> getPlayerCharacters() {
        return gamestatus.getPlayerCharacters();
    }

    public List<GameCharacter> getNpcs() {
        return gamestatus.getActivePlanetsNpcs();
    }
}

