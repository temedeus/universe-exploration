/**
 * 
 */
package com.universe.exploration.ueui.data;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.ueui.data.container.PlanetSurvey;
import com.universe.exploration.ueui.skins.UEUiSkinBank;
import com.universe.exploration.view.PlanetGfxContainer;

/**
 * @author 20.10.2015 Teemu Puurunen 
 *
 */
public class DataPairTableFactory {
	public Table createPlanetInformationTable(PlanetGfxContainer pgfx) {
		Table planetInformationTable = new Table();

		PlanetSurvey planetSurveyLabels = new PlanetSurvey((PlanetCelestialComponent)pgfx.getCelestialBodyGfxModel().getStarSystemComponent());
		planetSurveyLabels.createPairs();
	
		for(DataPair planetLabel : planetSurveyLabels.getPairList()) {
			planetInformationTable.add(planetLabel.getLabel()).left();
			planetInformationTable.add(planetLabel.getValue()).left();
			planetInformationTable.row();
		}
		
		planetInformationTable.add(new Label("\n\n", UEUiSkinBank.ueUISkin));
		planetInformationTable.row();
		
		return planetInformationTable;
	}
}
