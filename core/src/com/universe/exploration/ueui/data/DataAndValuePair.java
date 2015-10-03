/**
 * 
 */
package com.universe.exploration.ueui.data;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.universe.exploration.ueui.skins.UEUiSkinBank;

/**
 * @author 1.10.2015 Teemu Puurunen 
 *
 */
public class DataAndValuePair {
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
	
	/**
	 * Holds a pair of labels for displaying data/value pair.
	 * 
	 * @param label
	 * @param value
	 * @param skin
	 */
	public DataAndValuePair(String id, String label, String value) {
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
	 * @param value
	 */
	public void updateValue(String value) {
		this.value.setText(value);
	}
}
