package com.universe.exploration.screens.game;

import com.universe.exploration.UniverseExploration;
import com.universe.exploration.controller.ControllerBase;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.model.Coordinate;
import com.universe.exploration.model.crew.GameCharacter;
import com.universe.exploration.model.crew.Soldier;
import com.universe.exploration.model.crew.action.CrewMemberAction;
import com.universe.exploration.model.crew.action.CrewMemberActionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameController extends ControllerBase {
    private static final int BOARD_SIZE_X = 10;
    private static final int BOARD_SIZE_Y = 6;

    private List<GameCharacter> playerGameCharacters;
    private Optional<GameCharacter> selectedCharacter;

    private Game game;

    private UEListener actionTriggeredListener;

    public GameController(UniverseExploration universeExploration, Game game) {
        super(universeExploration);
        this.game = game;
        selectedCharacter = Optional.empty();
        playerGameCharacters = new ArrayList<>();
        Soldier soldier = new Soldier();
        soldier.setupActions();
        soldier.setSelected(false);
        soldier.setCoordinates(0, 0);
        playerGameCharacters.add(soldier);
    }

    public void applyActionWhenPossible(Coordinate coordinateClicked) {
        if (selectedCharacter.isPresent()) {
            GameCharacter character = selectedCharacter.get();
            if (coordinateClicked.matchesGameCharacterPosition(character)) {
                return;
            }

            if (character.getSelectedAction().getCrewMemberActionType() == CrewMemberActionType.WALK) {
                character.setCoordinates(coordinateClicked.getX(), (coordinateClicked.getY()));
                game.createMoveToActionOnCoordinates(character, coordinateClicked.getX(), coordinateClicked.getY());
            }
            selectedCharacter = Optional.empty();
        }
    }

    public List<Coordinate> getAreaToPaint(Coordinate coordinateClicked) {
        Optional<GameCharacter> gameCharacter = playerGameCharacters.stream()
                .filter(playerGameCharacter -> playerGameCharacter.getCoordinateX() == coordinateClicked.getX() && playerGameCharacter.getCoordinateY() == coordinateClicked.getY())
                .findFirst();

        if (gameCharacter.isPresent()) {
            selectedCharacter = gameCharacter;
            CrewMemberAction moveAction = gameCharacter.get().getSelectedAction();
            int verticalReach = moveAction.getVerticalReach();
            int horizontalReach = moveAction.getHorizontalReach();

            int verticalRangeStart = (coordinateClicked.getY() - verticalReach >= 0) ? coordinateClicked.getY() - verticalReach : 0;
            int verticalRangeEnd = (coordinateClicked.getY() + verticalReach <= BOARD_SIZE_Y - 1) ? coordinateClicked.getY() + verticalReach : BOARD_SIZE_Y - 1;
            int horizontalRangeStart = (coordinateClicked.getX() - horizontalReach >= 0) ? coordinateClicked.getX() - horizontalReach : 0;
            int horizontalRangeEnd = (coordinateClicked.getX() + horizontalReach <= BOARD_SIZE_X - 1) ? coordinateClicked.getX() + horizontalReach : BOARD_SIZE_X - 1;

            List<Coordinate> coordinates = new ArrayList<>();
            for (int coordinateX = horizontalRangeStart; coordinateX <= horizontalRangeEnd; coordinateX++) {
                coordinates.add(new Coordinate(coordinateX, coordinateClicked.getY()));
            }

            for (int coordinateY = verticalRangeStart; coordinateY <= verticalRangeEnd; coordinateY++) {
                coordinates.add(new Coordinate(coordinateClicked.getX(), coordinateY));
            }

            return coordinates;
        }
        return new ArrayList<>();
    }


    public void setActionTriggeredListener(UEListener actionTriggeredListener) {
        this.actionTriggeredListener = actionTriggeredListener;
    }

    public List<GameCharacter> getPlayerGameCharacters() {
        return playerGameCharacters;
    }
}

