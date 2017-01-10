/**
 * 
 */
package com.universe.exploration.userinterface;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.universe.exploration.userinterface.forms.PlanetSurveyForm;
import com.universe.exploration.userinterface.skins.UserInterfaceBank;

/**
 * @author 18.10.2015 Teemu Puurunen
 *
 */
public class UIComponentFactory {
    public static TableFormContainerPair createHorizontalSlider(float minVal, float maxVal, float interval) {
	return createSliderWithMinMaxValues(minVal, maxVal, interval, false);
    }

    public static TableFormContainerPair createVerticalSlider(float minVal, float maxVal, float interval) {
	return createSliderWithMinMaxValues(minVal, maxVal, interval, true);
    }

    /**
     * Create a slider that also shows the minimum and maximum value.
     * 
     * @param minVal
     * @param maxVal
     * @param interval
     * @param vertical
     * @return
     */
    private static TableFormContainerPair createSliderWithMinMaxValues(float minVal, float maxVal, float interval, boolean vertical) {
	Table sliderTable = new Table();
	Slider slider = new Slider(minVal, maxVal, interval, vertical, UserInterfaceBank.userInterfaceSkin);

	sliderTable.add(new Label("" + minVal, UserInterfaceBank.userInterfaceSkin));
	sliderTable.add(slider);
	sliderTable.add(new Label("" + maxVal, UserInterfaceBank.userInterfaceSkin));
	sliderTable.row();

	PlanetSurveyForm surveyForm = new PlanetSurveyForm();
	surveyForm.setSurveyLength(slider);

	return new TableFormContainerPair(surveyForm, sliderTable);
    }

    public static Table createSpacer() {
	Table table = new Table();
	table.add(new Label("", UserInterfaceBank.userInterfaceSkin));

	return table;
    }
}
