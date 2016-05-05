package com.universe.exploration.userinterface;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.common.tools.GdxHelper;
import com.universe.exploration.crew.CrewMemberStatus;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.gamegraphics.PlanetGfxContainer;
import com.universe.exploration.gamegraphics.PlanetHandler;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.localization.LocalKey;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.player.CrewStatusManager;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.userinterface.components.BasicTable;
import com.universe.exploration.userinterface.components.LogDisplay;
import com.universe.exploration.userinterface.components.PlanetSelection;
import com.universe.exploration.userinterface.components.SurveyTeamSelection;
import com.universe.exploration.userinterface.components.UELabel;
import com.universe.exploration.userinterface.components.window.BasicWindow;
import com.universe.exploration.userinterface.components.window.WindowFactory;
import com.universe.exploration.userinterface.components.window.WindowType;
import com.universe.exploration.userinterface.data.DataPair;
import com.universe.exploration.userinterface.data.DataPairTableFactory;
import com.universe.exploration.userinterface.data.container.CrewMemberDetails;
import com.universe.exploration.userinterface.data.container.DataPairContainer;
import com.universe.exploration.userinterface.data.container.LeftSideHUD;
import com.universe.exploration.userinterface.forms.FormContainer;
import com.universe.exploration.userinterface.forms.PlanetSurveyForm;
import com.universe.exploration.userinterface.skins.UserInterfaceBank;

/**
 * {@link UniverseExploration} and no other class should have an instance of
 * UIController.
 * 
 * @author 25.8.2015 Teemu Puurunen
 *
 */
public class UIController {

    private Stage uiStage;

    private UEListener hyperspaceJumpListener;

    private UEListener planetSurveyListener;

    private LeftSideHUD leftsidePlayerStatus;

    private PlanetSelection planetSelection;

    /**
     * Listen for change in volume.
     */
    private UEListener volumeListener;

    /**
     * <p>
     * This flag determines is hyperspace jump is allowed. Jump can be disabled
     * for example when game over window is shown.
     * </p>
     */
    private boolean isHyperspaceJumpAllowed = true;

    /**
     * Use to send messages for logging.
     */
    private UEListener logMessageListener;
    
    /**
     * <p>
     * Visual representation of game log.
     * </p>
     */
    private final LogDisplay logDisplay;

    public UIController(PlanetHandler gameViewObjectContainer, List<PlanetCelestialComponent> planetList) {

	planetSelection = new PlanetSelection(gameViewObjectContainer, planetList);

	logDisplay = new LogDisplay(10, UserInterfaceBank.userInterfaceSkin);
	uiStage = new Stage(new ScreenViewport());
	
	leftsidePlayerStatus = new LeftSideHUD();
	leftsidePlayerStatus.createPairs();

	uiStage.addActor(createLeftHUD());
	uiStage.addActor(createTopHUDTable());
	uiStage.addActor(createTopCenterHUDTable());
	uiStage.addActor(createBottomHUDTable());
	uiStage.addActor(createLogDisplay());
    }

    private HorizontalGroup createTopCenterHUDTable() {
	HorizontalGroup table = new HorizontalGroup();

	table.align(Align.left | Align.top);
	table.setPosition(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight());

	Slider volumeSlider = createVolumeSlider();
	
	table.addActor(createVolumeChangeButton(Localizer.getInstance().get(LocalKey.BTN_MIN_VOLUME), 0f, volumeSlider));
	table.addActor(volumeSlider);
	table.addActor(createVolumeChangeButton(Localizer.getInstance().get(LocalKey.BTN_MAX_VOLUME), 100f, volumeSlider));

	return table;
    }

    private Slider createVolumeSlider() {
	final Slider volumeSlider = new Slider(1, 100, 1, false, UserInterfaceBank.userInterfaceSkin);
	volumeSlider.addListener(new ClickListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com
	     * .badlogic.gdx.scenes.scene2d.InputEvent, float, float)
	     */
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		fireVolumeChangedListener(volumeSlider.getValue());
	    }
	});

	volumeSlider.setValue(100);
	return volumeSlider;
    }

    private void fireVolumeChangedListener(float newVolumeVal) {
	float val = newVolumeVal / 100;
	if (val <= 100) {
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
    public void updateUI(CrewStatusManager ps) {
	updatePlayerStatusToUI(ps);

	if ((int) ps.getPower() == 0) {
	    isHyperspaceJumpAllowed = false;
	}

	planetSelection.disablePlanetSelection(UniverseExploration.gameStatus.isZoomIn());
	uiStage.act(GdxHelper.getDeltaTime());
	uiStage.draw();
    }

    public VerticalGroup createLeftHUD() {
	VerticalGroup table = new VerticalGroup();
	table.padTop(30);
	table.padLeft(30);
	table.align(Align.left | Align.top);
	table.setPosition(0, Gdx.graphics.getHeight());
	table.addActor(populateWithStatus(leftsidePlayerStatus));

	table.addActor(planetSelection.createPlanetSelectionTable());

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

    private Table populateWithStatus(DataPairContainer dataPairContainer) {
	Table tableRepresentation = new Table();
	tableRepresentation.align(Align.left | Align.top);

	for (DataPair dataPair : dataPairContainer.getPairList()) {
	    tableRepresentation.add(dataPair.getLabel()).left();
	    tableRepresentation.add(dataPair.getValue()).left();
	    tableRepresentation.row();
	}

	return tableRepresentation;
    }

    public void updateLog(LinkedList<String> logItems) {
	logDisplay.updateValuesToTable(logItems);
    }

    /**
     * Create HUD top. Add all the buttons and their actions.
     * 
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
	table.addActor(createCrewControlButton());

	return table;
    }

    /**
     * Create HUD bottom. Add all the buttons and their actions.
     * 
     * @return
     */
    public VerticalGroup createBottomHUDTable() {
	VerticalGroup table = new VerticalGroup();
	table.setWidth(uiStage.getWidth());
	table.align(Align.right | Align.bottom);
	table.setPosition(0, 100);
	table.padTop(30);
	table.padRight(30);

	if (!Gdx.app.getType().equals(ApplicationType.WebGL)) {
	    table.addActor(createQuitButton());
	}

	return table;
    }

    public TextButton createVolumeChangeButton(String caption, final float value, final Slider volumeslider) {
	return new ButtonFactory().createTextButton(caption, new ClickListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com
	     * .badlogic.gdx.scenes.scene2d.InputEvent, float, float)
	     */
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		fireVolumeChangedListener(value);
		volumeslider.setValue(value);
	    }
	});
    }

    /**
     * Create hyperspace jump button
     * 
     * @return
     */
    public TextButton createHyperspaceJumpButton() {
	return new ButtonFactory().createTextButton(Localizer.getInstance().get(LocalKey.BTN_HYPERSPACE_JUMP), new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		if (!UniverseExploration.gameStatus.isPaused() && isHyperspaceJumpAllowed) {
		    Sound announcement = Gdx.audio.newSound(Gdx.files.internal("soundeffects/hyperspacejump.ogg"));
		    announcement.play();
		    final Dialog dialog = new Dialog(Localizer.getInstance().get(LocalKey.DESC_HYPERSPACE_JUMP),
			    UserInterfaceBank.userInterfaceSkin);
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

    /**
     * Create hyperspace jump button
     * 
     * @return
     */
    public TextButton createCrewControlButton() {
	return new ButtonFactory().createTextButton(Localizer.getInstance().get(LocalKey.BTN_CREW_CONTROL), new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		show(createCrewManagementWindow());
	    }
	});
    }

    public BasicWindow createGameOverWindow(WindowType windowType, ClickListener tryAgainAction) {
	BasicTable gameoverData = new BasicTable(Align.left | Align.top);
	BasicWindow gameOverWindow = new WindowFactory().createDescriptionWindowWithSecondaryAction(windowType, gameoverData,
		LocalKey.BTN_QUIT_GAME, tryAgainAction, new ClickListener() {
		    @Override
		    public void clicked(InputEvent event, float x, float y) {
			if (!Gdx.app.getType().equals(ApplicationType.WebGL)) {
			    Gdx.app.exit();
			}
		    }
		});

	return gameOverWindow;
    }

    public <T extends Actor> void show(T actor) {
	if (!UniverseExploration.gameStatus.isPaused()) {
	    uiStage.addActor(actor);
	}
    }

    /**
     * Create quit button
     * 
     * @return
     */
    private TextButton createQuitButton() {
	return new ButtonFactory().createTextButton(Localizer.getInstance().get(LocalKey.BTN_QUIT_GAME), new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		createQuitDialog();
	    }
	});
    }

    private void fireLogMessageListener(String message) {
	logMessageListener.handleEventClassEvent(new UEEvent(message));
    }

    public void createQuitDialog() {
	uiStage.addActor(new WindowFactory().createQuitWindow(Localizer.getInstance().get(LocalKey.TITLE_QUIT_GAME)));
    }

    public BasicWindow createSurveyClosedWindow(List<String> surveydata) {
	Table table = new Table(UserInterfaceBank.userInterfaceSkin);

	for (String row : surveydata) {
	    table.add(row);
	    table.row();
	}

	return new WindowFactory().createOKWindow(Localizer.getInstance().get(LocalKey.TITLE_SURVEY_CLOSED), table);
    }

    /**
     * Planetary survey window.
     * 
     * @param pgfx
     */
    public BasicWindow createPlanetarySurveyWindow(PlanetGfxContainer pgfx, ClickListener okAction) {
	final DataPairTableFactory dptf = new DataPairTableFactory();

	Table planetInformationTable = dptf.createPlanetInformationTable(pgfx);

	return new WindowFactory().createLargeDescriptionWindow(WindowType.PLANET_DETAILS, planetInformationTable, okAction);
    }

    /**
     * Planetary survey window.
     * 
     * @param pgfx
     */
    public BasicWindow createCrewManagementWindow() {
	BasicWindow window = new WindowFactory().createMediumDescriptionWindow(WindowType.CREW_MANAGEMENT, createCrewTable(),
		createCrewManagementClickListener());

	UniverseExploration.windowContainer.add(WindowType.CREW_MANAGEMENT, window);

	return window;
    }

    private ClickListener createCrewManagementClickListener() {
	return new ClickListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked
	     * (com.badlogic.gdx.scenes.scene2d.InputEvent, float, float)
	     */
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		UniverseExploration.windowContainer.closeWindow(WindowType.CREW_MANAGEMENT);
	    }
	};
    }

    private Table createCrewTable() {
	Table table = new Table();

	int x = 0;

	for (CrewMember crewmember : UniverseExploration.crew.getCrewmen()) {
	    x++;
	    Table cell = new Table();
	    cell.padBottom(15);
	    cell.padRight(15);
	    cell.add(new Label(crewmember.getName(), UserInterfaceBank.userInterfaceSkin));
	    cell.row();
	    cell.add(new Label(Localizer.getInstance().get(crewmember.getStatus()), UserInterfaceBank.userInterfaceSkin));
	    cell.row();
	    cell.add(new ButtonFactory().createTextButton("Details", createCrewmemberDetailsClickListerener(crewmember)));
	    table.add(cell);

	    // 5 per row seems good.
	    if (x == 5) {
		table.row();
	    }
	}

	return table;
    }

    private ClickListener createCrewmemberDetailsClickListerener(final CrewMember crewMember) {
	return new ClickListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com
	     * .badlogic.gdx.scenes.scene2d.InputEvent, float, float)
	     */
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		final CrewMemberDetails crewMemberDetails = new CrewMemberDetails(crewMember);
		crewMemberDetails.createPairs();

		BasicWindow window = new WindowFactory().createLargeDescriptionWindow(WindowType.CREWMEMBER_DETAILS,
			createCrewMemberDetailsPane(crewMemberDetails), createNewCrewMemberDetailsCLickListener());

		UniverseExploration.windowContainer.add(WindowType.CREWMEMBER_DETAILS, window);
		show(window);
	    }
	};
    }

    private ScrollPane createCrewMemberDetailsPane(CrewMemberDetails details) {
	VerticalGroup group = new VerticalGroup();
	group.addActor(populateWithStatus(details));
	group.addActor(UIComponentFactory.createSpacer());
	group.addActor(new ButtonFactory().createTextButton(Localizer.getInstance().get(LocalKey.BTN_KICK_OUT_OF_AIRLOCK),
		createaKillCrewManClickListener(details.getCrewMember())));
	group.addActor(UIComponentFactory.createSpacer());

	return new ScrollPane(group);
    }

    private ClickListener createaKillCrewManClickListener(final CrewMember crewmember) {
	return new ClickListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com
	     * .badlogic.gdx.scenes.scene2d.InputEvent, float, float)
	     */
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		CrewMemberStatus status = crewmember.getStatus();
		if (status == CrewMemberStatus.ALIVE) {
		    crewmember.setStatus(CrewMemberStatus.KIA);
		    fireLogMessageListener("You tossed " + crewmember.getName() + " out of the airlock!");
		} else {
		    fireLogMessageListener("Cannot throw " + crewmember.getName() + " out of the airlock! This person is "
			    + Localizer.getInstance().get(status));

		}

		UniverseExploration.windowContainer.closeWindow(WindowType.CREWMEMBER_DETAILS);
		UniverseExploration.windowContainer.closeWindow(WindowType.CREW_MANAGEMENT);
		// TODO: work out a way to refresh crew management window
		show(createCrewManagementWindow());
	    }
	};
    }

    private ClickListener createNewCrewMemberDetailsCLickListener() {
	return new ClickListener() {
	    /* (non-Javadoc)
	     * @see com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com.badlogic.gdx.scenes.scene2d.InputEvent, float, float)
	     */
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		UniverseExploration.windowContainer.closeWindow(WindowType.CREWMEMBER_DETAILS);
	    }
	};
    }

    public BasicWindow createSurveyTeamSelectionWindow(PlanetGfxContainer pgfx) {
	Table planetInformationTable = new Table();
	SurveyTeamSelection teamSelection = new SurveyTeamSelection(UniverseExploration.crew);
	planetInformationTable.add(teamSelection.createSurveyTeamSelectionTable());
	planetInformationTable.row();
	
	TableFormContainerPair pair = UIComponentFactory.createHorizontalSlider(0, CoreConfiguration.MAX_DAYS_ON_SURVEY, 1);
	final PlanetSurveyForm form = (PlanetSurveyForm) pair.getFormContainer();

	final UELabel label = new UELabel(Localizer.getInstance().get(LocalKey.LABEL_SURVEY_LENGTH) + " (" + form.getSurveyLength().getValue() + ") days");
	planetInformationTable.add(label);
	planetInformationTable.row();

	
	form.setPlanet((PlanetCelestialComponent) pgfx.getComponentType());
	form.setSelectedCrewMembers(teamSelection.getSelectedCrewMembers());
	planetInformationTable.add(pair.getTable());
	planetInformationTable.row();

	form.getSurveyLength().addListener(new EventListener() {

	    @Override
	    public boolean handle(Event event) {
		label.setText(Localizer.getInstance().get(LocalKey.LABEL_SURVEY_LENGTH) + " (" + form.getSurveyLength().getValue() + ") days");
		
		return true;
	    }
	});

	BasicWindow window = new WindowFactory().createLargeDescriptionWindow(WindowType.SURVEY_WINDOW, planetInformationTable,
		createdPlanetSurveyTeamDispatchedClickListener(form));

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
	if (planetSurveyListener != null) {
	    planetSurveyListener.handleEventClassEvent(new UEEvent(formContainer));
	}
    }

    private void updatePlayerStatusToUI(CrewStatusManager playerStatus) {
	leftsidePlayerStatus.updateHUDValues(playerStatus);
    }

    /**
     * @return the hyperspaceJumpListener
     */
    public UEListener getHyperspaceJumpListener() {
	return hyperspaceJumpListener;
    }

    /**
     * @param hyperspaceJumpListener
     *            the hyperspaceJumpListener to set
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
     * @param planetSurveyListener
     *            the planetSurveyListener to set
     */
    public void setPlanetSurveyListener(UEListener planetSurveyListener) {
	this.planetSurveyListener = planetSurveyListener;
    }

    /**
     * @param planetClickListener
     *            the planetClickListener to set
     */
    public void setPlanetClickListener(UEListener planetClickListener) {
	planetSelection.setPlanetClickListener(planetClickListener);
    }

    /**
     * @return the volumeListener
     */
    public UEListener getVolumeListener() {
	return volumeListener;
    }

    /**
     * @param volumeListener
     *            the volumeListener to set
     */
    public void setVolumeListener(UEListener volumeListener) {
	this.volumeListener = volumeListener;
    }

    /**
     * @param selectedPlanetChangedListener
     *            the selectedPlanetChangedListener to set
     */
    public void setSelectedPlanetChangedListener(UEListener selectedPlanetChangedListener) {
	planetSelection.setSelectedPlanetChangedListener(selectedPlanetChangedListener);
    }

    /**
     * @return the logMessageListener
     */
    public UEListener getLogMessageListener() {
	return logMessageListener;
    }

    /**
     * @param logMessageListener
     *            the logMessageListener to set
     */
    public void setLogMessageListener(UEListener logMessageListener) {
	this.logMessageListener = logMessageListener;
    }

}
