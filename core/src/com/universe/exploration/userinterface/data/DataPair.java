/**
 * 
 */
package com.universe.exploration.userinterface.data;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.universe.exploration.userinterface.skins.UEUiSkinBank;

/**
 * <p>
 * Paired up with {@link DataPairContainer} based classes it makes up an easy
 * way to show data on screen using two labels showing title and value. Value
 * can easily be assigned to different styles.
 * </p>
 * 
 * @author 1.10.2015 Teemu Puurunen
 *
 */
public class DataPair {
    /**
     * Use this ID to identify HUD item
     */
    private String id;

    /**
     * Explanation label
     */
    private Label label;

    /**
     * Concrete value
     */
    private Label value;

    public DataPair() {

    }

    /**
     * <p>
     * Holds a pair of {@link Label} displaying data/value pair.
     * </p>
     * 
     * @param id
     *            ID in case you need to modify this data later on.
     * @param label
     *            Label for the data.
     * @param value
     *            Value of the given label.
     */
    public DataPair(String id, String label, String value) {
	this.id = id;
	this.label = new Label(label, UEUiSkinBank.ueUISkin);
	this.value = new Label(value, UEUiSkinBank.ueUISkin);
	this.value.setStyle(UEUiSkinBank.valueStyle);
    }

    /**
     * @return the id
     */
    public String getId() {
	return id;
    }

    /**
     * @return the label
     */
    public Label getLabel() {
	return label;
    }

    /**
     * @return the value
     */
    public Label getValue() {
	return value;
    }

    /**
     * Update player statistic value
     * 
     * @param value
     */
    public void updateValue(String value) {
	this.value.setText(value);
    }
}
