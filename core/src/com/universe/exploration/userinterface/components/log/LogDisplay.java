/**
 *
 */
package com.universe.exploration.userinterface.components.log;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.universe.exploration.userinterface.components.UETable;

import java.util.LinkedList;

/**
 * <p>
 * Visual representation of ingame log.
 * </p>
 * <p>
 * Update values using method {@link #updateValuesToTable}.
 * </p>
 *
 * @author 22.10.2015 Teemu Puurunen
 */
public class LogDisplay {

    private Label[] logEntries;

    private Skin logDisplaySkin;

    private UETable logDisplayTable;

    private int logMax;

    public LogDisplay(int logMax, Skin logDisplaySkin) {
        this.logDisplaySkin = logDisplaySkin;
        this.logMax = logMax;
        logEntries = new Label[logMax];
        clear();
        this.logDisplayTable = createLogDisplayTable();
    }

    /**
     * Clear log display table. (In practice set text to empty string.)
     */
    public void clear() {
        for (int x = 0; x < logEntries.length; x++) {
            logEntries[x] = new Label("", logDisplaySkin);
        }
    }

    /**
     * <p>
     * Updates values to log. Maximum of {@link #logMax} entries can be used.
     * Past maximum value log entries are disposed of.
     * </p>
     *
     * @param logItems
     */
    public void updateValuesToTable(LinkedList<String> logItems) {
        int logItemsSize = logItems.size();
        int y = logItemsSize - 1;

        for (int x = logMax - 1; x > (logMax - 1 - logItemsSize); x--) {
            logEntries[x].setText(logItems.get(y));
            y--;
        }
    }

    private UETable createLogDisplayTable() {
        final UETable logDisplayTable = new UETable();

        for (Label logEntry : logEntries) {
            logDisplayTable.add(logEntry).left();
            logDisplayTable.row();
        }

        return logDisplayTable;
    }

    /**
     * @return the logDisplayTable
     */
    public Table getLogDisplayTable() {
        return logDisplayTable;
    }
}
