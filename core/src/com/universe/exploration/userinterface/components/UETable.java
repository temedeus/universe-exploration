/**
 *
 */
package com.universe.exploration.userinterface.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Abstraction of LibGdx {@link Table}.
 *
 * @author 9.4.2016 Teemu Puurunen
 */
public class UETable extends Table {
    public <T extends Actor> void addRow(T actor) {
        add(actor);
        row();
    }
}
