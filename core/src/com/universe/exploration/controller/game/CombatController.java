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
import com.universe.exploration.model.gamestatus.GlobalStatus;
import com.universe.exploration.screens.combat.CombatScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class CombatController extends ControllerBase {
    private UEListener actionTriggeredListener;

    private final GlobalStatus globalStatus;
    private final CombatScreen combatScreen;

    public CombatController(UniverseExploration universeExploration, CombatScreen combatScreen) {
        super(universeExploration);

        this.globalStatus = universeExploration.getGamestatus();
        this.combatScreen = combatScreen;
        globalStatus.setCharacterActionMode(CharacterActionMode.MOVE);
        globalStatus.setSelectedCharacter(Optional.empty());
        globalStatus.setPlayerCharacters(new ArrayList<>());
        globalStatus.setNpcs(new HashMap<>());
        Soldier soldier = new Soldier();
        soldier.setupActions();
        soldier.setNpc(false);
        soldier.setSelected(false);
        soldier.setCoordinates(0, 0);
        this.globalStatus.getPlayerCharacters().add(soldier);
        this.globalStatus.setCharacterInTurn(soldier);

        List<Planet> planets = this.globalStatus.getPlanets();

        planets.forEach(planet -> {
            for (GameCharacter gameCharacter : planet.getPlanetComponent().getNpcs()) {
                gameCharacter.setupActions();
                gameCharacter.setNpc(true);
                gameCharacter.setSelected(false);
                gameCharacter.setCoordinates(BoardConfig.BOARD_SIZE_MAX_X - 1, BoardConfig.BOARD_SIZE_MAX_Y - 1);
            }
            this.globalStatus.getNpcs().put(planet.getPlanetComponent().getName(), planet.getPlanetComponent().getNpcs());
        });
    }

    public void applyActionWhenPossible(Coordinate coordinateClicked) {
        if (globalStatus.getSelectedCharacter().isPresent()) {
            GameCharacter character = globalStatus.getSelectedCharacter().get();
            if (coordinateClicked.matchesGameCharacterPosition(character)) {
                return;
            }

            if (globalStatus.getCharacterActionMode() == CharacterActionMode.MOVE) {
                character.setCoordinates(coordinateClicked.getX(), (coordinateClicked.getY()));
                combatScreen.createMoveToActionOnCoordinates(character, coordinateClicked.getX(), coordinateClicked.getY());
                updateCharacterInTurn();
            }
            globalStatus.setSelectedCharacter(Optional.empty());
        }
    }

    public List<Coordinate> getAreaToPaint(Coordinate coordinateClicked) {
        List<Coordinate> coordinates = new ArrayList<>();

        Optional<GameCharacter> gameCharacter = globalStatus.getPlayerCharacters().stream()
                .filter(playerGameCharacter -> playerGameCharacter.getCoordinateX() == coordinateClicked.getX() && playerGameCharacter.getCoordinateY() == coordinateClicked.getY())
                .findFirst();

        if (gameCharacter.isPresent()) {
            globalStatus.setSelectedCharacter(gameCharacter);
            CharacterActionConfiguration selectedAction = gameCharacter.get().getSelectedActionConfiguration(globalStatus.getCharacterActionMode());
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
        return globalStatus.getCharacterActionMode();
    }

    public void setSelectedAction(CharacterActionMode characterActionMode) {
        this.globalStatus.setCharacterActionMode(characterActionMode);
    }

    public List<GameCharacter> getPlayerCharacters() {
        return globalStatus.getPlayerCharacters();
    }

    public List<GameCharacter> getNpcs() {
        return globalStatus.getActivePlanetsNpcs();
    }

    private void updateCharacterInTurn() {
        List<GameCharacter> gameCharacters = this.globalStatus.getCharacterInTurn().isNpc() ? this.globalStatus.getActivePlanetsNpcs() : this.globalStatus.getPlayerCharacters();
        GameCharacter character = this.globalStatus.getCharacterInTurn();

        int indexofCurrent = gameCharacters.indexOf(character);
        GameCharacter newCharacter = indexofCurrent == gameCharacters.size() - 1 ?
                gameCharacters.get(0) :
                gameCharacters.get(indexofCurrent + 1);
        this.globalStatus.setCharacterInTurn(newCharacter);
    }
}

