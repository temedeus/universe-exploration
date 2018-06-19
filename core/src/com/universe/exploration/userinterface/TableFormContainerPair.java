/**
 *
 */
package com.universe.exploration.userinterface;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.universe.exploration.userinterface.forms.FormContainer;

/**
 * @author 26.10.2015 Teemu Puurunen
 */
public class TableFormContainerPair {

    private FormContainer actorMap;

    private Table table;

    public TableFormContainerPair(FormContainer formContainer, Table table) {
        this.actorMap = formContainer;
        this.table = table;
    }

    /**
     * @return the actorMap
     */
    public FormContainer getFormContainer() {
        return actorMap;
    }

    /**
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * @param actorMap the actorMap to set
     */
    public void setFormContainer(FormContainer actorMap) {
        this.actorMap = actorMap;
    }

    /**
     * @param table the table to set
     */
    public void setTable(Table table) {
        this.table = table;
    }
}
