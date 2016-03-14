package com.universe.exploration.userinterface.components;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.universe.exploration.userinterface.components.BasicTable;

public class BasicTableTest {

    @Test
    public void testUETable() throws Exception {
	Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

	BasicTable ueTable = new BasicTable(Align.left | Align.top);

	String[][] captionValuaPairTable = { new String[] { "Orange: ", "orange" }, new String[] { "Apple: ", "red" } };

	for (String[] pair : captionValuaPairTable) {
	    ueTable.add(new Label(pair[0], skin));
	    ueTable.column();
	    ueTable.add(new Label(pair[1], skin));
	    ueTable.row();
	}
    }

}
