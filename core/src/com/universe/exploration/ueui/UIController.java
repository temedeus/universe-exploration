package com.universe.exploration.ueui;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
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
import com.universe.exploration.ueui.components.BasicTable;
import com.universe.exploration.ueui.components.BasicWindow;
import com.universe.exploration.ueui.components.LogDisplay;
import com.universe.exploration.ueui.data.DataPair;
import com.universe.exploration.ueui.data.DataPairTableFactory;
import com.universe.exploration.ueui.data.container.LeftSideHUD;
import com.universe.exploration.ueui.skins.UEUiSkinBank;
import com.universe.exploration.view.PlanetGfxContainer;



/**
 * @author 25.8.2015 Teemu Puurunen 
 *
 */
public class UIController {
	
	private Stage uiStage;
	
	private UEListener hyperspaceJumpListener;
	
	private UEListener planetSurveyListener;
	
	private boolean gameStatusPaused = false;
	
	private LeftSideHUD leftsidePlayerStatus;
	
	private PlayerStatus playerStatus;
	
	/**
	 * <p>This flag determines is hyperspace jump is allowed. Jump can be disabled for example when
	 * game over window is shown.</p>
	 */
	private boolean isHyperspaceJumpAllowed = true;

	/**
	 * <p>Visual representation of game log.</p>
	 */
	private final LogDisplay logDisplay = new LogDisplay(10, UEUiSkinBank.ueUISkin);
	
	public UIController() {
		uiStage = new Stage(new ScreenViewport());
		leftsidePlayerStatus = new LeftSideHUD();
		leftsidePlayerStatus.createPairs();
		
		uiStage.addActor(createLeftHUD());
		uiStage.addActor(createTopHUDTable());
		uiStage.addActor(createBottomHUDTable());
		uiStage.addActor(createLogDisplay());
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
		
		if((int)ps.getPower() == 0) {
			isHyperspaceJumpAllowed = false;
		}
		
		uiStage.act(Gdx.graphics.getDeltaTime());
		uiStage.draw();
	}
	
	public Table createLeftHUD() {
		Table playerStatusTable = new Table();
		playerStatusTable.setWidth(uiStage.getWidth());
		playerStatusTable.align(Align.left | Align.top);
		playerStatusTable.setPosition(0, Gdx.graphics.getHeight());
		playerStatusTable.padTop(30);
		playerStatusTable.padLeft(30);

		return populateWithStatus(playerStatusTable);
	}
	
	public Table createLogDisplay() {
		Table table = new Table();
		table.setWidth(uiStage.getWidth());
		table.align(Align.left | Align.bottom);
		table.setPosition(30, 30);
		table.padTop(30);
		table.padLeft(30);

		table.add(logDisplay.getLogDisplayTable());
		table.row();
		
		return table;
	}
	
	private Table populateWithStatus(Table verticalGroup) {		
		for(DataPair playerStatus : leftsidePlayerStatus.getPairList()) {
			verticalGroup.add(playerStatus.getLabel()).left();
			verticalGroup.add(playerStatus.getValue()).left();
			verticalGroup.row();
		}
		
		return verticalGroup;
	}
	
	public void updateLog(LinkedList<String> logItems) {
		logDisplay.updateValuesToTable(logItems);
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
		ButtonFactory bf = new ButtonFactory(UEUiSkinBank.ueUISkin);
		
		return bf.createTextButton(Localizer.get("BTN_HYPERSPACE_JUMP"), new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!gameStatusPaused && isHyperspaceJumpAllowed) {
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
	
	public BasicWindow createGameOverWindow(ClickListener tryAgainAction) {
		final WindowFactory wf = new WindowFactory(UEUiSkinBank.ueUISkin);
		BasicTable gameoverData = new BasicTable(Align.left | Align.top);
		BasicWindow gameOverWindow = wf.createDescriptionWindow(Localizer.get("TITLE_GAME_OVER"), gameoverData, 
				Localizer.get("BTN_TRY_AGAIN"),
				Localizer.get("BTN_QUIT_GAME"), 
				tryAgainAction, 
				new ClickListener() {
			    	@Override
			    	public void clicked(InputEvent event, float x, float y) {
			    		Gdx.app.exit();
			    	}
			    });
	
		return gameOverWindow;
	}
	
	
	public <T extends Actor> void show(T actor) {
		uiStage.addActor(actor);
	}
	
	/**
	 * Create quit button
	 * @return
	 */
	public TextButton createQuitButton() {
		ButtonFactory bf = new ButtonFactory(UEUiSkinBank.ueUISkin);
		final WindowFactory wf = new WindowFactory(UEUiSkinBank.ueUISkin);
		
		return bf.createTextButton(Localizer.get("BTN_QUIT_GAME"), new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				uiStage.addActor(wf.createQuitWindow(Localizer.get("TITLE_QUIT_GAME")));
			}
		});
	}

	/**
	 * Planetary survey window. 
	 * @param pgfx
	 */
	public BasicWindow createPlanetarySurveyWindow(PlanetGfxContainer pgfx, ClickListener okAction) {
		final WindowFactory wf = new WindowFactory(UEUiSkinBank.ueUISkin);
		final DataPairTableFactory dptf = new DataPairTableFactory();
		
		Table planetInformationTable = dptf.createPlanetInformationTable(pgfx);
		
		return wf.createLargeDescriptionWindow(Localizer.get("TITLE_SURVEY_PLANET"), planetInformationTable, okAction);
	}

		
	public BasicWindow createPlanetSurveyedWindow(PlanetGfxContainer pgfx, ClickListener okAction) {
		final WindowFactory wf = new WindowFactory(UEUiSkinBank.ueUISkin);

		Table planetInformationTable = new Table();
		
		
		planetInformationTable.add(new Label(Localizer.get("LABEL_CREWMEN_COUNT"), UEUiSkinBank.ueUISkin));
		planetInformationTable.row();
		
		planetInformationTable.add(UIComponentFactory.createHorizontalSlider(0, playerStatus.getCrewmen(), 1));
		planetInformationTable.row();
		
		return wf.createMediumDescriptionWindow(Localizer.get("TITLE_SURVEY_PLANET_CONFIGURATION_SCREEN"), planetInformationTable,  okAction);
	}
	
	/**
	 * Perform int cast to ensure sensible values. (Do not cast until here so that we
	 * don't lose accuracy apart from what we do using float.)
	 * @param ps
	 */
	private void updatePlayerStatusToUI(PlayerStatus playerStatus) {
		this.playerStatus = playerStatus;
		updateValuesToHUD();
	}


	private void updateValuesToHUD() {
		leftsidePlayerStatus.update(PlayerStatusItemkeys.TIME, "" + (int)playerStatus.getTime() + " days");
		leftsidePlayerStatus.update(PlayerStatusItemkeys.AIR, playerStatusValueToHUDString("" + (int)playerStatus.getAir()));
		leftsidePlayerStatus.update(PlayerStatusItemkeys.CREWMEN, "" + (int)playerStatus.getCrewmen());
		leftsidePlayerStatus.update(PlayerStatusItemkeys.WATER, playerStatusValueToHUDString("" + (int)playerStatus.getWater()));
		leftsidePlayerStatus.update(PlayerStatusItemkeys.FOOD, playerStatusValueToHUDString("" + (int)playerStatus.getFood()));
		leftsidePlayerStatus.update(PlayerStatusItemkeys.POWER, playerStatusValueToHUDString("" + (int)playerStatus.getPower()));
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
	
	/**
	 * @return the hyperspaceJumpListener
	 */
	public UEListener getHyperspaceJumpListener() {
		return hyperspaceJumpListener;
	}
	
	/**
	 * @param hyperspaceJumpListener the hyperspaceJumpListener to set
	 */
	public void setHyperspaceJumpListener(UEListener hyperspaceJumpListener) {
		this.hyperspaceJumpListener = hyperspaceJumpListener;
	}

	/**
	 * @return the planetSurveyListener
	 */
	public UEListener getPlanetSurveyListener() {
		return planetSurveyListener;
	}

	/**
	 * @param planetSurveyListener the planetSurveyListener to set
	 */
	public void setPlanetSurveyListener(UEListener planetSurveyListener) {
		this.planetSurveyListener = planetSurveyListener;
	}
}
