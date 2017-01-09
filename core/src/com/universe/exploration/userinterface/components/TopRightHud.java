/**
 * 
 */
package com.universe.exploration.userinterface.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;

/**
 * @author 10.1.2017 Teemu Puurunen
 *
 */
public class TopRightHud extends VerticalGroup {
    /**
     * 
     */
    public TopRightHud(float width, TextButton hyperSpaceJumpButton, TextButton crewControlButton, TextButton followSurveyButton,
	    TextButton optionsButton) {
	setWidth(width);
	align(Align.right | Align.top);
	setPosition(0, Gdx.graphics.getHeight());
	padTop(30);
	padRight(30);
	addActor(hyperSpaceJumpButton);
	addActor(crewControlButton);
	addActor(followSurveyButton);
	addActor(optionsButton);
    }
}
