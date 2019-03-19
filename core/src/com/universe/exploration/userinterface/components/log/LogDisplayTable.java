/**
 *
 */
package com.universe.exploration.userinterface.components.log;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.universe.exploration.userinterface.components.UETable;

/**
 * @author 10.1.2017 Teemu Puurunen
 */
public class LogDisplayTable extends UETable {

    public LogDisplayTable(float width, Table table) {
        setWidth(width);
        align(Align.left | Align.bottom);
        setPosition(30, 30);
        padTop(30);
        padLeft(30);

        addRow(table);
    }
}
