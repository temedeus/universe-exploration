/**
 * 
 */
package com.universe.exploration.userinterface.components;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.universe.exploration.gamegraphics.GameObjectCanvas;
import com.universe.exploration.gamegraphics.GameViewObjectContainer;
import com.universe.exploration.gamegraphics.PlanetGfxContainer;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.localization.LocalKey;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.userinterface.ButtonFactory;
import com.universe.exploration.userinterface.skins.UEUiSkinBank;

/**
 * @author 1.2.2016 Teemu Puurunen
 *
 */
public class PlanetSelection {

    private TextButton surveyButton;

    private GameViewObjectContainer gameViewObjectContainer = new GameViewObjectContainer();

    private List<PlanetCelestialComponent> planetList;

    private UEListener planetClickListener;

    /**
     * Listen for selected planet in {@link #planetSelectBox}.
     */
    private UEListener selectedPlanetChangedListener;

    /**
     * Planets in the current star system.
     */
    private final SelectBox<Object> planetSelectBox = new SelectBox<Object>(UEUiSkinBank.ueUISkin);

    /**
     * <p>
     * Initialize selection with a list of {@link PlanetCelestialComponent}.
     * </p>
     * 
     * @param planetList
     */
    public PlanetSelection(GameViewObjectContainer gameViewObjectContainer, List<PlanetCelestialComponent> planetList) {
	this.gameViewObjectContainer = gameViewObjectContainer;
	this.planetList = planetList;

	surveyButton = new ButtonFactory().createTextButton(Localizer.get(LocalKey.BTN_SURVEY), new ClickListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see com.badlogic.gdx.scenes.scene2d.utils. ClickListener
	     * #clicked(com.badlogic.gdx.scenes. scene2d.InputEvent, float,
	     * float)
	     */
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		firePlanetClickListener();
	    }
	});
    }

    public void disablePlanetSelection(boolean disable) {
	surveyButton.setDisabled(disable);
	planetSelectBox.setDisabled(disable);
    }

    public PlanetCelestialComponent parsePlanetFromSelectBox() {
	int planetIndex = Integer.parseInt(((String) planetSelectBox.getSelected()).substring(0, 1)) - 1;
	return planetList.get(planetIndex);
    }

    private SelectBox<Object> createPlanetSelectBox() {
	Object[] labelList = new Object[planetList.size()];

	int x = 0;
	for (PlanetCelestialComponent planet : planetList) {
	    labelList[x++] = "" + x + ": " + planet.getComponentName();
	}

	planetSelectBox.setItems(labelList);
	planetSelectBox.addCaptureListener(new ChangeListener() {

	    @Override
	    public void changed(ChangeEvent event, Actor actor) {
		fireSelectedPlanetChangedListener();
	    }
	});
	return planetSelectBox;
    }

    private void fireSelectedPlanetChangedListener() {
	selectedPlanetChangedListener.handleEventClassEvent(new UEEvent(parsePlanetFromSelectBox()));
    }

    public VerticalGroup createPlanetSelectionTable() {
	VerticalGroup table = new VerticalGroup();
	table.align(Align.left | Align.bottom);
	table.addActor(GenericComponents.createInstance().createSpacer());

	if (planetList.size() > 0) {

	    table.addActor(new Label(Localizer.get(LocalKey.LABEL_PLANET_SELECTION), UEUiSkinBank.ueUISkin));
	    table.addActor(createPlanetSelectBox());
	    table.addActor(surveyButton);
	} else {
	    table.addActor(new Label(Localizer.get(LocalKey.LABEL_NO_PLANETS_FOUND), UEUiSkinBank.ueUISkin));
	}

	return table;
    }

    /**
     * <p>
     * TODO: Fix this logic. We deal with both {@link PlanetGfxContainer} and
     * {@link PlanetCelestialComponent}... wtf?!
     * </p>
     * <p>
     * Check {@link GameObjectCanvas} and the firePlanetClickListener method
     * there.
     * </p>
     * <p>
     * Currently the planet is dug out using the number in front of the planet
     * list. This is not very smart way to perform this. SelectBox seems to
     * accept Object but I didn't figure out how to setup captions.
     * </p>
     */
    private void firePlanetClickListener() {
	planetClickListener.handleEventClassEvent(new UEEvent(gameViewObjectContainer
		.getPlanetGfxContainerByComponent(parsePlanetFromSelectBox())));
    }

    /**
     * @param selectedPlanetChangedListener
     *            the selectedPlanetChangedListener to set
     */
    public void setSelectedPlanetChangedListener(UEListener selectedPlanetChangedListener) {
	this.selectedPlanetChangedListener = selectedPlanetChangedListener;
    }

    /**
     * @param planetClickListener
     *            the planetClickListener to set
     */
    public void setPlanetClickListener(UEListener planetClickListener) {
	this.planetClickListener = planetClickListener;
    }
}
