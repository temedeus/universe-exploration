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
import com.universe.exploration.screens.game.GameScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class GameController extends ControllerBase {

    private GameScreen gameScreen;

    private UEListener actionTriggeredListener;

    private Gamestatus gamestatus;

    public GameController(UniverseExploration universeExploration, GameScreen gameScreen, List<Planet> planets) {
        super(universeExploration);

        this.gamestatus = new Gamestatus();
        this.gameScreen = gameScreen;
        gamestatus.setCharacterActionMode(CharacterActionMode.MOVE);
        gamestatus.setSelectedCharacter(Optional.empty());
        gamestatus.setPlayerCharacters(new ArrayList<>());
        gamestatus.setNpcs(new ArrayList<>());
        Soldier soldier = new Soldier();
        soldier.setupActions();
        soldier.setSelected(false);
        soldier.setCoordinates(0, 0);
        GameCharacter npc = planets.get(0).getPlanetComponent().getNpcs().get(0);
        npc.setupActions();
        npc.setSelected(false);
        npc.setCoordinates(BoardConfig.BOARD_SIZE_MAX_X - 1, BoardConfig.BOARD_SIZE_MAX_Y - 1);
        this.gamestatus.getPlayerCharacters().add(soldier);
        this.gamestatus.getNpcs().add(npc);
    }

    public void applyActionWhenPossible(Coordinate coordinateClicked) {
        if (gamestatus.getSelectedCharacter().isPresent()) {
            GameCharacter character = gamestatus.getSelectedCharacter().get();
            if (coordinateClicked.matchesGameCharacterPosition(character)) {
                return;
            }

            if (gamestatus.getCharacterActionMode() == CharacterActionMode.MOVE) {
                character.setCoordinates(coordinateClicked.getX(), (coordinateClicked.getY()));
                gameScreen.createMoveToActionOnCoordinates(character, coordinateClicked.getX(), coordinateClicked.getY());

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
        return gamestatus.getNpcs();
    }
}

