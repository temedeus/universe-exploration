package com.universe.exploration.screens.mainmenu;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.commoncomponent.CommonComponentFactory;

public class PlayerCreationDialog extends Table {
    public PlayerCreationDialog() {
        Table cell = new Table();
        cell.padBottom(15);
        cell.padRight(15);
        cell.add(new CommonComponentFactory().createLabel(UniverseExploration.getLocaliser().get("LABEL_PLAYER_NAME")));
        cell.row();
        cell.add(createPlayerNameTextField()).width(500).height(70);
        cell.row();
        add(cell);
    }

    private TextField createPlayerNameTextField() {
        TextField textField = new CommonComponentFactory().createTextField(UniverseExploration.getLocaliser().get(""));
        textField.setMaxLength(50);
        return textField;
    }
}
