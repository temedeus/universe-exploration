package com.universe.exploration.ueui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.player.PlayerStatus;
import com.universe.exploration.player.PlayerStatusItemkeys;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.ueui.components.UETable;
import com.universe.exploration.ueui.components.UEWindow;
import com.universe.exploration.ueui.data.DataAndValuePair;
import com.universe.exploration.ueui.data.LeftSideHUD;
import com.universe.exploration.ueui.data.PlanetSurvey;
import com.universe.exploration.ui.skins.UEUiSkinBank;
import com.universe.exploration.view.PlanetGfxContainer;



/**
 * @author 25.8.2015 Teemu Puurunen 
 *
 */
public class UIController {
	
	private Stage uiStage;
	
	private UEListener hyperspaceJumpListener;
	
	private boolean gameStatusPaused = false;
	
	private LeftSideHUD leftsidePlayerStatus;

	public UIController() {
		uiStage = new Stage(new ScreenViewport());
		leftsidePlayerStatus = new LeftSideHUD();
		
		uiStage.addActor(createLeftHUD());
		uiStage.addActor(createTopHUDTable());
		uiStage.addActor(createBottomHUDTable());
	
		gameStatusPaused = false;
	}
	
	/**
	 * @return the uiStage
	 */
	public Stage getUiStage() {
		return uiStage;
	}

	/**
	 * Update UI
	 */
	public void updateUI(PlayerStatus ps) {
		updatePlayerStatusToUI(ps);
		uiStage.act(Gdx.graphics.getDeltaTime());
		uiStage.draw();
	}
	
	public Table createLeftHUD() {
		Table playerStatus = new Table();
		playerStatus.setWidth(uiStage.getWidth());
		playerStatus.align(Align.left | Align.top);
		playerStatus.setPosition(0, Gdx.graphics.getHeight());
		playerStatus.padTop(30);
		playerStatus.padLeft(30);

		return populateWithStatus(playerStatus);
	}
	
	private Table populateWithStatus(Table verticalGroup) {		
		for(DataAndValuePair playerStatus : leftsidePlayerStatus.getPairList()) {
			verticalGroup.add(playerStatus.getLabel()).left();
			verticalGroup.add(playerStatus.getValue()).left();
			verticalGroup.row();
		}
		
		return verticalGroup;
	}
	
	/**
	 * Create HUD top. Add all the buttons and their actions.
	 * @return
	 */
	public VerticalGroup createTopHUDTable() {
		VerticalGroup table = new VerticalGroup();
		table.setWidth(uiStage.getWidth());
		table.align(Align.right | Align.top);
		table.setPosition(0, Gdx.graphics.getHeight());
		table.padTop(30);
		table.padRight(30);
		table.addActor(createHyperspaceJumpButton());
		
		return table;
	}
	
	/**
	 * Create HUD bottom. Add all the buttons and their actions.
	 * @return
	 */
	public VerticalGroup createBottomHUDTable() {
		VerticalGroup table = new VerticalGroup();
		table.setWidth(uiStage.getWidth());
		table.align(Align.right | Align.bottom);
		table.setPosition(0,100);
		table.padTop(30);
		table.padRight(30);
		table.addActor(createQuitButton());
		
		return table;
	}
	
	/**
	 * Create hyperspace jump button
	 * @return
	 */
	public TextButton createHyperspaceJumpButton() {
		UEButtonFactory bf = new UEButtonFactory(UEUiSkinBank.ueUISkin);
		
		return bf.createTextButton(Localizer.get("BTN_HYPERSPACE_JUMP"), new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!gameStatusPaused) {
					final Dialog dialog = new Dialog(Localizer.get("DESC_HYPERSPACE_JUMP"),UEUiSkinBank.ueUISkin);
					dialog.setSize(200, 100);
					dialog.show(uiStage);
					Timer.schedule(new Timer.Task() {
						@Override
						public void run() {
							dialog.hide();
							hyperspaceJumpListener.handleEventClassEvent();
						}
					}, 1);
				}
			}
		});
	}
	
	public void showGameOverWindow(ClickListener tryAgainAction) {
		final UEWindowFactory wf = new UEWindowFactory(UEUiSkinBank.ueUISkin);
		UETable gameoverData = new UETable(Align.left | Align.top);
		UEWindow gameOverWindow = wf.createDescriptionWindow(Localizer.get("TITLE_GAME_OVER"), gameoverData, 
				Localizer.get("BTN_TRY_AGAIN"),
				Localizer.get("BTN_QUIT_GAME"), 
				tryAgainAction, 
				new ClickListener() {
			    	@Override
			    	public void clicked(InputEvent event, float x, float y) {
			    		Gdx.app.exit();
			    	}
			    });
	
		uiStage.addActor(gameOverWindow);
	}
	
	/**
	 * Create quit button
	 * @return
	 */
	public TextButton createQuitButton() {
		UEButtonFactory bf = new UEButtonFactory(UEUiSkinBank.ueUISkin);
		final UEWindowFactory wf = new UEWindowFactory(UEUiSkinBank.ueUISkin);
		
		return bf.createTextButton(Localizer.get("BTN_QUIT_GAME"), new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				uiStage.addActor(wf.createQuitWindow(Localizer.get("TITLE_QUIT_GAME")));
			}
		});
	}

	
	/**
	 * @return the hyperspaceJumpListener
	 */
	public UEListener getHyperspaceJumpListener() {
		return hyperspaceJumpListener;
	}

	/**
	 * Planetary survey window. 
	 * @param pgfx
	 */
	public void showPlanetarySurveyWindow(PlanetGfxContainer pgfx) {
		final UEWindowFactory wf = new UEWindowFactory(UEUiSkinBank.ueUISkin);
		
		
		UETable planetInformationTable = new UETable(Align.left | Align.top);

		PlanetSurvey planetSurveyLabels = new PlanetSurvey((PlanetCelestialComponent)pgfx.getCelestialBodyGfxModel().getStarSystemComponent());
		
		for(DataAndValuePair planetLabel : planetSurveyLabels.getPairList()) {
			planetInformationTable.add(planetLabel.getLabel());
			planetInformationTable.column();
			planetInformationTable.add(planetLabel.getValue());
			planetInformationTable.row();
		}
		
		planetInformationTable.add(new Label("\n\n", UEUiSkinBank.ueUISkin));
		planetInformationTable.column();
		planetInformationTable.row();
		
		Image planet = new Image(pgfx.getSprite());

		UETable planetSurveyTable = new UETable(Align.left | Align.top);
		planetSurveyTable.add(planetInformationTable.getTable());
		planetSurveyTable.column();
		planetSurveyTable.add(planet);
		planetSurveyTable.row();
		
		uiStage.addActor(wf.createDescriptionWindow(Localizer.get("TITLE_SURVEY_PLANET"), planetInformationTable, new ClickListener() {
	    	@Override
	    	public void clicked(InputEvent event, float x, float y) {
	    	}
	    }));
	}
	
	/**
	 * @param hyperspaceJumpListener the hyperspaceJumpListener to set
	 */
	public void setHyperspaceJumpListener(UEListener hyperspaceJumpListener) {
		this.hyperspaceJumpListener = hyperspaceJumpListener;
	}

	/**
	 * Perform int cast to ensure sensible values. (Do not cast until here so that we
	 * don't lose accuracy apart from what we do using float.)
	 * @param ps
	 */
	private void updatePlayerStatusToUI(PlayerStatus ps) {
		leftsidePlayerStatus.update(PlayerStatusItemkeys.AIR, playerStatusValueToHUDString("" + (int)ps.getAir()));
		leftsidePlayerStatus.update(PlayerStatusItemkeys.CREWMEN, playerStatusValueToHUDString("" + (int)ps.getCrewmen()));
		leftsidePlayerStatus.update(PlayerStatusItemkeys.WATER, playerStatusValueToHUDString("" + (int)ps.getWater()));
		leftsidePlayerStatus.update(PlayerStatusItemkeys.FOOD, playerStatusValueToHUDString("" + (int)ps.getFood()));
		leftsidePlayerStatus.update(PlayerStatusItemkeys.POWER, playerStatusValueToHUDString("" + (int)ps.getPower()));
	}

	private String playerStatusValueToHUDString(Object val) {
		return val + " %";
	}
	
	/**
	 * @return the gameStatusPaused
	 */
	public boolean isGameStatusPaused() {
		return gameStatusPaused;
	}

	/**
	 * @param gameStatusPaused the gameStatusPaused to set
	 */
	public void setGameStatusPaused(boolean gameStatusPaused) {
		this.gameStatusPaused = gameStatusPaused;
	}
}