/**
 * 
 */
package com.universe.exploration.ueui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.universe.exploration.ueui.skins.UEUiSkinBank;

/**
 * @author 18.10.2015 Teemu Puurunen 
 *
 */
public class UIComponentFactory {
	public static Table createHorizontalSlider(float minVal, float maxVal, float interval) {
		return createHorizontalSlider(minVal, maxVal, interval, false);
	}
	
	public static Table createVerticalSlider(float minVal, float maxVal, float interval) {
		return createHorizontalSlider(minVal, maxVal, interval, true);
	}
	
	private static Table createHorizontalSlider(float minVal, float maxVal, float interval, boolean vertical) {
		Table sliderTable = new Table();
		Slider slider = new Slider(0, 10, interval, vertical, UEUiSkinBank.ueUISkin);
		
		sliderTable.add(new Label(""+minVal, UEUiSkinBank.ueUISkin));
		sliderTable.add(slider);
		sliderTable.add(new Label(""+maxVal, UEUiSkinBank.ueUISkin));
		sliderTable.row();
		
		return sliderTable;
	}
}
