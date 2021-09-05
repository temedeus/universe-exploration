package com.universe.exploration.screens.game;

import com.universe.exploration.UniverseExploration;
import com.universe.exploration.controller.ControllerBase;
import com.universe.exploration.model.AreaToPaint;
import com.universe.exploration.model.crew.Soldier;

import java.util.ArrayList;
import java.util.List;

public class GameController extends ControllerBase {
    private static final int BOARD_X = 6;
    private static final int BOARD_Y = 10;
    private List<CharacterDetails> characterDetails;


    public GameController(UniverseExploration universeExploration) {
        super(universeExploration);
        characterDetails = new ArrayList<>();
        CharacterDetails soldier = new CharacterDetails.Builder()
                .selected(false)
                .coordinateX(0)
                .coordinateY(0)
                .gameCharacter(new Soldier())
                .build();
        characterDetails.add(soldier);
    }


    public AreaToPaint getPaintAreas() {
       /* characterDetails.get(0).getGameCharacter().getMoveAction().getVerticalReach();
        List<Vector> areaToPaint = new ArrayList<>();
        areaToPaint.add(new Vector())
        areaToPaint.add()*/
        AreaToPaint areaToPaint = new AreaToPaint.Builder()
                .build();
        return areaToPaint;
    }
}

