package com.universe.exploration.ueui;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.CoreConfiguration;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.localization.LocalKeys;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.player.PlayerStatus;
import com.universe.exploration.player.PlayerStatusItemkeys;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.ueui.components.BasicTable;
import com.universe.exploration.ueui.components.BasicWindow;
import com.universe.exploration.ueui.components.LogDisplay;
import com.universe.exploration.ueui.data.DataPair;
import com.universe.exploration.ueui.data.DataPairTableFactory;
import com.universe.exploration.ueui.data.container.LeftSideHUD;
import com.universe.exploration.ueui.forms.FormContainer;
import com.universe.exploration.ueui.forms.PlanetSurveyForm;
import com.universe.exploration.ueui.skins.UEUiSkinBank;
import com.universe.exploration.view.GameObjectCanvas;
import com.universe.exploration.view.GameViewObjectContainer;
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
	
	private List<PlanetCelestialComponent> planetList;
	
	private UEListener planetClickListener;

	private UEListener volumeListener;
	


	final SelectBox<Object> planetSelectBox = new SelectBox<Object>(UEUiSkinBank.ueUISkin); 
	
	private GameViewObjectContainer gameViewObjectContainer = new GameViewObjectContainer();
	
	/**
	 * <p>This flag determines is hyperspace jump is allowed. Jump can be disabled for example when
	 * game over window is shown.</p>
	 */
	private boolean isHyperspaceJumpAllowed = true;

	/**
	 * <p>Visual representation of game log.</p>
	 */
	private final LogDisplay logDisplay;
	
	public UIController(GameViewObjectContainer gameViewObjectContainer, List<PlanetCelestialComponent> planetList) {
		this.gameViewObjectContainer = gameViewObjectContainer;
		this.planetList = planetList;
		
		logDisplay = new LogDisplay(10, UEUiSkinBank.ueUISkin);
		uiStage = new Stage(new ScreenViewport());
		leftsidePlayerStatus = new LeftSideHUD();
		leftsidePlayerStatus.createPairs();
		
		uiStage.addActor(createLeftHUD());
		uiStage.addActor(createTopHUDTable());
		//uiStage.addActor(createTopCenterHUDTable());
		uiStage.addActor(createBottomHUDTable());
		uiStage.addActor(createLogDisplay());
		
		gameStatusPaused = false;
	}
	
	private HorizontalGroup createTopCenterHUDTable() {
		ButtonFactory bf = new ButtonFactory(UEUiSkinBank.ueUISkin);
		
		HorizontalGroup table = new HorizontalGroup();
		
		table.align(Align.left | Align.top);
		table.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
		
		table.addActor(createVolumeChangeButton(Localizer.get("BTN_MIN_VOLUME"), 0f));
		table.addActor(createVolumeSlider());
		table.addActor(createVolumeChangeButton(Localizer.get("BTN_MAX_VOLUME"), 100f));
		
		return table;
	}
	
	private Slider createVolumeSlider() {
		final Slider volumeSlider = new Slider(1, 100, 1, false, UEUiSkinBank.ueUISkin);
		volumeSlider.addCaptureListener(new ClickListener() {
			/* (non-Javadoc)
			 * @see com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com.badlogic.gdx.scenes.scene2d.InputEvent, float, float)
			 */
			@Override
			public void clicked(InputEvent event, float x, float y) {
				fireVolumeChangedListener(volumeSlider.getValue());
			}
		});
		
		return volumeSlider;
	}
	
	private void fireVolumeChangedListener(float newVolumeVal) {
		long val = (long) newVolumeVal / 100;
		if(val <= 100) {
			volumeListener.handleEventClassEvent(new UEEvent(val));
		}
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
	
	public VerticalGroup createLeftHUD() {
		VerticalGroup table = new VerticalGroup();
		table.padTop(30);
		table.padLeft(30);
		table.align(Align.left | Align.top);
		table.setPosition(0, Gdx.graphics.getHeight());
		table.addActor(populateWithStatus());
		
		table.addActor(createPlanetSelectionTable());

		return table;
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
	
	private VerticalGroup createPlanetSelectionTable() {
		VerticalGroup table = new VerticalGroup();
		table.align(Align.left | Align.bottom);
		table.addActor(createSpacer());
		
		if(planetList.size() > 0) {
			
			table.addActor(new Label(Localizer.get("LABEL_PLANET_SELECTION"), UEUiSkinBank.ueUISkin));
			table.addActor(createPlanetSelectBox());
			table.addActor(new ButtonFactory(UEUiSkinBank.ueUISkin).createTextButton(Localizer.get("BTN_SURVEY"), new ClickListener() {
				/* (non-Javadoc)
				 * @see com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com.badlogic.gdx.scenes.scene2d.InputEvent, float, float)
				 */
				@Override
				public void clicked(InputEvent event, float x, float y) {
					firePlanetClickListener();
				}
			}));
		} else {
			table.addActor(new Label(Localizer.get(LocalKeys.LABEL_NO_PLANETS_FOUND.getLocalKey()), UEUiSkinBank.ueUISkin));
		}

		return table;
	}
	
	private Table populateWithStatus() {		
		Table playerStatusTable = new Table();
		playerStatusTable.align(Align.left | Align.top);
		
		for(DataPair playerStatus : leftsidePlayerStatus.getPairList()) {
			playerStatusTable.add(playerStatus.getLabel()).left();
			playerStatusTable.add(playerStatus.getValue()).left();
			playerStatusTable.row();
		}
		
		return playerStatusTable;
	}
	
	private Label createSpacer() {
		return new Label("", UEUiSkinBank.ueUISkin);
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
	 * <p>TODO: Fix this logic. We deal with both {@link PlanetGfxContainer} and {@link PlanetCelestialComponent}... wtf?!</p> 
	 * <p>Check {@link GameObjectCanvas} and the firePlanetClickListener method there.</p>
	 * <p>Currently the planet is dug out using the number in front of the planet list. This is not very smart
	 * way to perform this. SelectBox seems to accept Object but I didn't figure out how to setup captions. </p>
	 */
	private void firePlanetClickListener() {
		int planetIndex = Integer.parseInt(((String)planetSelectBox.getSelected()).substring(0,1)) - 1;
		PlanetCelestialComponent planet = planetList.get(planetIndex);
		planetClickListener.handleEventClassEvent(new UEEvent(gameViewObjectContainer.getPlanetGfxContainerByComponent(planet)));
	}
	
	private SelectBox<Object> createPlanetSelectBox() {
		Object[] labelList = new Object[planetList.size()];
		
		int x=0;
		for(PlanetCelestialComponent planet : planetList) {
			labelList[x++] = "" + x + ": " + planet.getComponentName();
		}
		
		planetSelectBox.setItems(labelList); 
		
		return planetSelectBox;
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
		
		// TODO: Make volume toggle between 0-1.
		table.addActor(createVolumeChangeButton(Localizer.get("BTN_MAX_VOLUME"), 100f));
		table.addActor(createVolumeChangeButton(Localizer.get("BTN_MIN_VOLUME"), 0f));
		

		table.addActor(createQuitButton());
		
		return table;
	}
	
	public TextButton createVolumeChangeButton(String caption, final float value) {
		ButtonFactory bf = new ButtonFactory(UEUiSkinBank.ueUISkin);
		return bf.createTextButton(caption, new ClickListener() {
			/* (non-Javadoc)
			 * @see com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com.badlogic.gdx.scenes.scene2d.InputEvent, float, float)
			 */
			@Override
			public void clicked(InputEvent event, float x, float y) {
				fireVolumeChangedListener(value);
			}
		});
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
		BasicWindow gameOverWindow = wf.createDescriptionWindowWithSecondaryAction(Localizer.get("TITLE_GAME_OVER"), gameoverData, 
				Localizer.get("BTN_TRY_AGAIN"),
				Localizer.get("BTN_QUIT_GAME"), 
				tryAgainAction, 
				new ClickListener() {
			    	@Override
			    	public void clicked(InputEvent event, float x, float y) {
			    		if(Gdx.app.getType().equals(ApplicationType.WebGL)) {
			    			Gdx.net.openURI(CoreConfiguration.WEBSITE_URL);
			    		}

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
	private TextButton createQuitButton() {
		ButtonFactory bf = new ButtonFactory(UEUiSkinBank.ueUISkin);

		return bf.createTextButton(Localizer.get("BTN_QUIT_GAME"), new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				createQuitDialog();
			}
		});
	}

	public void createQuitDialog() {
		final WindowFactory wf = new WindowFactory(UEUiSkinBank.ueUISkin);
		uiStage.addActor(wf.createQuitWindow(Localizer.get(LocalKeys.TITLE_QUIT_GAME.getLocalKey())));
	}
	
	/**
	 * Planetary survey window. 
	 * @param pgfx
	 */
	public BasicWindow createPlanetarySurveyWindow(PlanetGfxContainer pgfx, ClickListener okAction) {
		final WindowFactory wf = new WindowFactory(UEUiSkinBank.ueUISkin);
		final DataPairTableFactory dptf = new DataPairTableFactory();
		
		Table planetInformationTable = dptf.createPlanetInformationTable(pgfx);
		
		return wf.createLargeDescriptionWindow(Localizer.get(LocalKeys.TITLE_SURVEY_PLANET.getLocalKey()), planetInformationTable, okAction);
	}

	public BasicWindow createPlanetSurveyedWindow(PlanetGfxContainer pgfx, int surveyTeamSize) {
		final WindowFactory wf = new WindowFactory(UEUiSkinBank.ueUISkin);

		Table planetInformationTable = new Table();
		
		planetInformationTable.add(new Label(Localizer.get("LABEL_CREWMEN_COUNT"), UEUiSkinBank.ueUISkin));
		planetInformationTable.row();
		
		TableFormContainerPair pair = UIComponentFactory.createHorizontalSlider(0, surveyTeamSize, 1);
		((PlanetSurveyForm)pair.getFormContainer()).setPlanet((PlanetCelestialComponent)pgfx.getComponentType());
		
		planetInformationTable.add(pair.getTable());
		planetInformationTable.row();

		BasicWindow window = wf.createMediumDescriptionWindow(
				Localizer.get("TITLE_SURVEY_PLANET_CONFIGURATION_SCREEN"), 
				planetInformationTable,  
				createdPlanetSurveyTeamDispatchedClickListener(
						(PlanetSurveyForm)pair.getFormContainer()
			)
		);
		
		return window;
	}
	
	private ClickListener createdPlanetSurveyTeamDispatchedClickListener(final PlanetSurveyForm planetSurveyForm) {
		return new ClickListener() {
	    	@Override
	    	public void clicked(InputEvent event, float x, float y) {
	    		firePlanetSurveyListener(planetSurveyForm);
	    	}
	    };
	}
	
	private void firePlanetSurveyListener(FormContainer formContainer) {
		if(planetSurveyListener != null) {
			planetSurveyListener.handleEventClassEvent(new UEEvent(formContainer));
		}
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

	/**
	 * @return the planetClickListener
	 */
	public UEListener getPlanetClickListener() {
		return planetClickListener;
	}

	/**
	 * @param planetClickListener the planetClickListener to set
	 */
	public void setPlanetClickListener(UEListener planetClickListener) {
		this.planetClickListener = planetClickListener;
	}
	
	/**
	 * @return the volumeListener
	 */
	public UEListener getVolumeListener() {
		return volumeListener;
	}

	/**
	 * @param volumeListener the volumeListener to set
	 */
	public void setVolumeListener(UEListener volumeListener) {
		this.volumeListener = volumeListener;
	}
}
