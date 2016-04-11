package com.universe.exploration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.camera.CameraMonitor;
import com.universe.exploration.casualty.Casualty;
import com.universe.exploration.common.tools.TextManipulationTools;
import com.universe.exploration.common.tools.exceptions.PlanetCountOutOfRangeException;
import com.universe.exploration.crew.CrewMemberStatus;
import com.universe.exploration.crewmember.Crew;
import com.universe.exploration.crewmember.CrewFactory;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.gamegraphics.GameObjectCanvas;
import com.universe.exploration.gamegraphics.PlanetGfxContainer;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.localization.LocalKey;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.logger.MinimalLogger;
import com.universe.exploration.player.CrewStatusManager;
import com.universe.exploration.player.StatusConsumption;
import com.universe.exploration.starsystem.StarSystem;
import com.universe.exploration.starsystem.StarSystemFactory;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.survey.ResourcesFound;
import com.universe.exploration.survey.Survey;
import com.universe.exploration.survey.SurveyContainer;
import com.universe.exploration.survey.SurveyFactory;
import com.universe.exploration.userinterface.UIController;
import com.universe.exploration.userinterface.WindowContainer;
import com.universe.exploration.userinterface.WindowContainerEvent;
import com.universe.exploration.userinterface.components.window.BasicWindow;
import com.universe.exploration.userinterface.components.window.WindowType;
import com.universe.exploration.userinterface.forms.PlanetSurveyForm;

/**
 * Main game class.
 */
public class UniverseExploration extends ApplicationAdapter implements InputProcessor {
    /**
     * Game objects are handled here
     */
    private GameObjectCanvas gameObjectCanvas;

    /**
     * Controls the camera
     */
    private CameraMonitor playerMonitor;

    /**
     * Star system
     */
    private StarSystem starSystem;

    public static Crew crew;
    /**
     * User interface.
     */
    private UIController uiController;

    public static GameStatus gameStatus;

    /**
     * Contains player spaceship status.
     */
    private CrewStatusManager crewStatus;

    // TODO: handle in {@link UIController}
    public static WindowContainer windowContainer;

    private MinimalLogger logger;
    private SurveyContainer surveyStatusContainer;

    private Sound backgroundMusic;
    private long bgId;

    @SuppressWarnings("unused")
    private Stage uiStage;

    @Override
    public void create() {
	Gdx.app.setLogLevel(Application.LOG_DEBUG);

	basicSetup();
	stageSetup();

	pauseGame(false);

	backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("music/space.mp3"));
	bgId = backgroundMusic.loop();
    }

    @Override
    public void dispose() {
	gameObjectCanvas.destroy();
    }

    @Override
    public void render() {
	playerMonitor.zoom(gameStatus.isZoomIn());

	gameObjectCanvas.updateCameraOnCanvas(playerMonitor.getOrthographicCamera());
	gameObjectCanvas.drawGameContent();

	crewStatus.updateStatus();
	uiController.updateUI(crewStatus);

	if (UniverseExploration.crew.getAliveCrewmen().size() == 0 && !gameStatus.isPaused()) {
	    BasicWindow gameOverWindow = uiController.createGameOverWindow(WindowType.GAME_OVER, createGameOverClicklistener());
	    windowContainer.closeAllWindows();
	    windowContainer.add(WindowType.GAME_OVER, gameOverWindow);
	    uiController.show(gameOverWindow);
	    pauseGame(true);
	}

	closeFinishedSurveys();

	if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && !Gdx.app.getType().equals(ApplicationType.WebGL)) {
	    uiController.createQuitDialog();
	}
    }

    /**
     * Basic setup to be run only at initial game start.
     */
    private void basicSetup() {
	gameStatus = new GameStatus();
	logger = new MinimalLogger();
	uiStage = new Stage(new ScreenViewport());
	crewStatus = new CrewStatusManager();
	crewStatus.setCrewMemberStatusChangeListener(createLogMessageListener());
	playerMonitor = new CameraMonitor();
	windowContainerSetup();
	surveyStatusContainer = new SurveyContainer();
    }

    private void windowContainerSetup() {
	windowContainer = new WindowContainer();
	windowContainer.setWindowsThatMustAlert(WindowType.PLANET_DETAILS, WindowType.SURVEY_WINDOW);
	windowContainer.setSpecificedWindowChangeListener(createWindowChangeListener());
    }

    private UEListener createLogMessageListener() {
	return new UEListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.universe.exploration.listener.UEListener#handleEventClassEvent
	     * (java.util.EventObject)
	     */
	    @Override
	    public void handleEventClassEvent(UEEvent e) {
		updateIngameLog((String) e.getPayLoad());
	    }
	};
    }

    private UEListener createWindowChangeListener() {
	return new UEListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.universe.exploration.listener.UEListener#handleEventClassEvent
	     * (com.universe.exploration.listener.UEEvent)
	     */
	    @Override
	    public void handleEventClassEvent(UEEvent e) {
		WindowContainerEvent event = (WindowContainerEvent) e.getPayLoad();
		gameStatus.activateSurveyMode(event.equals(WindowContainerEvent.ADD));
	    }
	};
    }

    /**
     * First setup star system and then UiController because UI uses star system
     * data.
     * 
     * @throws IOException
     */
    private void stageSetup() {
	createCrew();
	createStarSystem();
	setupUiController();
	setupInputProcessors();
    }

    private void createCrew() {
	crew = null;
	CrewMembersInitializer initializer;
	try {
	    initializer = new CrewMembersInitializer();
	    CrewFactory cf = new CrewFactory(initializer.getMaleProfiles(), initializer.getFemaleProfiles());
	    crew = cf.createRandomizedCrew();
	} catch (IOException e) {
	    Gdx.app.log("ERROR", "Reading crewmember profile source files failed!", e);
	}
    }

    private void setupUiController() {
	uiController = new UIController(gameObjectCanvas.getGameViewObjectContainer(), starSystem.getPlanets());
	uiController.setLogMessageListener(createLogMessageListener());
	uiController.setPlanetClickListener(createPlanetClickListener());
	uiController.setHyperspaceJumpListener(new UEListener() {
	    @Override
	    public void handleEventClassEvent() {
		if (!gameStatus.isPaused()) {
		    windowContainerSetup();
		    stageSetup();
		    gameStatus.activateSurveyMode(false);
		    crewStatus.decreasePowerBy(StatusConsumption.POWER_DECREMENT_HYPERSPACE_JUMP);
		    updateIngameLog(Localizer.getInstance().get(LocalKey.DESC_HYPERSPACE_JUMP_COMMENCED));
		}
	    };
	});

	uiController.setPlanetSurveyListener(new UEListener() {
	    @Override
	    public void handleEventClassEvent(UEEvent e) {
		if (e.getPayLoad() instanceof PlanetSurveyForm) {
		    startSurvey((PlanetSurveyForm) e.getPayLoad());
		    windowContainer.closeWindow(WindowType.SURVEY_WINDOW);
		    windowContainer.closeWindow(WindowType.PLANET_DETAILS);
		}
	    };
	});

	uiController.setSelectedPlanetChangedListener(new UEListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.universe.exploration.listener.UEListener#handleEventClassEvent
	     * (com.universe.exploration.listener.UEEvent)
	     */
	    @Override
	    public void handleEventClassEvent(UEEvent e) {
		PlanetCelestialComponent planet = (PlanetCelestialComponent) e.getPayLoad();
		gameObjectCanvas.setSelectedPlanet(planet);
	    }
	});

	uiController.setVolumeListener(createVolumeListener());
    }

    private UEListener createVolumeListener() {
	return new UEListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.universe.exploration.listener.UEListener#handleEventClassEvent
	     * ()
	     */
	    @Override
	    public void handleEventClassEvent(UEEvent e) {
		Float volume = (Float) e.getPayLoad();
		backgroundMusic.setVolume(bgId, volume);
	    }
	};
    }

    private void startSurvey(PlanetSurveyForm form) {
	int surveyLength = (int) form.getSurveyLength().getValue();
	List<CrewMember> crew = form.getSelectedCrewMembers();

	if (surveyLength > 0 && crew.size() > 0) {
	    if (surveyStatusContainer.isSurveyTeamAcceptable(crew)) {

		SurveyFactory ssf = new SurveyFactory();
		Survey ss = ssf.createSurveyStatus((int) crewStatus.getTime(), surveyLength, form.getSelectedCrewMembers(),
			(PlanetCelestialComponent) form.getPlanet());
		ss.setSurveyTeam(crew);
		surveyStatusContainer.add(ss);

		updateIngameLog("Survey team of " + crew.size() + " men and women dispatched.");
	    } else {
		updateIngameLog("Cannot dispatch survey team (" + crew.size() + "). Not enough crewmen available!"
			+ UniverseExploration.crew.getCrewMenAboardSpaceShip().size());
	    }
	}
    }

    private void pauseGame(boolean pause) {
	setGameStatusPaused(pause);
    }

    private ClickListener createGameOverClicklistener() {
	return new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		stageSetup();
		crewStatus = new CrewStatusManager();
		pauseGame(false);
		windowContainer.closeWindow(WindowType.GAME_OVER);
	    }
	};
    }

    /**
     * Both UniverseExploration and UIController use InputProcessors so we must
     * use InputMultiPlexer to catch all those events.
     */
    private void setupInputProcessors() {
	// We monitor events from both this ApplicationAdapter and our UI stage
	// TODO: check if we could work with just one InputProcessor?
	InputMultiplexer inputMultiplexer = new InputMultiplexer();
	inputMultiplexer.addProcessor(uiController.getUiStage());
	inputMultiplexer.addProcessor(this);
	Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * Attempts to create a new star system. Returns boolean result.
     * 
     * @return
     */
    private boolean createStarSystem() {
	StarSystemFactory uf = new StarSystemFactory();

	try {
	    starSystem = uf.makeStarSystem();
	    gameObjectCanvas = new GameObjectCanvas(starSystem);
	    gameObjectCanvas.updateCameraOnCanvas(playerMonitor.getOrthographicCamera());
	    gameObjectCanvas.setPlanetClickListener(createPlanetClickListener());
	} catch (PlanetCountOutOfRangeException e) {
	    Gdx.app.log("ERROR", "Generated planet count is not within range!", e);
	}

	return true;
    }

    public UEListener createPlanetClickListener() {
	return new UEListener() {
	    @Override
	    public void handleEventClassEvent(final UEEvent e) {
		final BasicWindow surveyWindow = uiController.createPlanetarySurveyWindow((PlanetGfxContainer) e.getPayLoad(),
			new ClickListener() {
			    @Override
			    public void clicked(InputEvent event, float x, float y) {
				final BasicWindow surveyedWindow = uiController.createSurveyTeamSelectionWindow((PlanetGfxContainer) e
					.getPayLoad());
				windowContainer.closeWindow(WindowType.PLANET_DETAILS);
				windowContainer.add(WindowType.SURVEY_WINDOW, surveyedWindow);
				uiController.show(surveyedWindow);
			    }
			});

		windowContainer.add(WindowType.PLANET_DETAILS, surveyWindow);
		surveyWindow.setPosition(Gdx.graphics.getWidth() / 2 + 70, Gdx.graphics.getHeight() / 2 - 300);
		uiController.show(surveyWindow);
	    };
	};
    }

    private void closeFinishedSurveys() {
	Survey ss = surveyStatusContainer.findAndRemoveOpenSurvey((int) crewStatus.getTime());
	showSurveyCompleteNotification(ss);
    }

    private void showSurveyCompleteNotification(Survey survey) {

	if (survey != null) {
	    String caption = "";
	    Sound announcement = Gdx.audio.newSound(Gdx.files.internal("soundeffects/announcement.ogg"));
	    announcement.play();
	    List<Casualty> casualtyList = survey.getMortalities();

	    if (casualtyList.size() > 0) {
		updateIngameLog("You have lost " + casualtyList.size() + " crewmen on survey.");
		handleCrewmenCasualtiesFromSurvey(casualtyList);
	    } else {
		caption = Localizer.getInstance().get(LocalKey.TITLE_SURVEY_TEAM_SURVIVED);
		updateIngameLog(caption);
	    }
	    
	    for(CrewMember member : survey.getSurveyTeam()) {
		if(member.getStatus() == CrewMemberStatus.ONSURVEY) {
		    member.setStatus(CrewMemberStatus.ALIVE);
		}
	    }

	    ResourcesFound rf = survey.getResourcesFound();
	    String resourcesCaption = updateResources(rf);

	    List<String> surveyData = generateSurveyDataRows(caption, resourcesCaption, casualtyList, rf);
	    final BasicWindow surveyClosedWindow = uiController.createSurveyClosedWindow(surveyData);

	    windowContainer.add(WindowType.SURVEY_CLOSED, surveyClosedWindow);

	    uiController.show(surveyClosedWindow);
	}
    }

    private List<String> generateSurveyDataRows(String caption, String resourcesCaption, List<Casualty> casualtyList, ResourcesFound rf) {
	List<String> surveydata = new ArrayList<String>();

	surveydata.add(caption);
	if (casualtyList.size() > 0) {
	    for (Casualty casualty : casualtyList) {
		surveydata.add(casualty.getMember().getName() + ": "
			+ Localizer.getInstance().get(casualty.getSurveyIncident().getLocalizationKey()));
	    }
	}

	surveydata.add("");
	surveydata.add(resourcesCaption);

	return surveydata;
    }

    private String updateResources(ResourcesFound rf) {
	ArrayList<String> resources = new ArrayList<String>();
	String caption = "";

	if (rf.getAir() > 0) {
	    resources.add(Localizer.getInstance().get(LocalKey.GENERIC_AIR));
	    crewStatus.increaseAir(rf.getAir());
	}

	if (rf.getFood() > 0) {
	    resources.add(Localizer.getInstance().get(LocalKey.GENERIC_FOOD));
	    crewStatus.increaseFood(rf.getFood());
	}

	if (rf.getWater() > 0) {
	    resources.add(Localizer.getInstance().get(LocalKey.GENERIC_WATER));
	    crewStatus.increaseWater(rf.getWater());
	}

	if (resources.size() == 0) {
	    caption = "You found nothing during your survey!";
	    updateIngameLog(caption);
	} else {
	    caption = "During your survey you found: " + TextManipulationTools.implodeListAsString(resources, ", ");
	    updateIngameLog(caption);
	}

	return caption;
    }

    private void handleCrewmenCasualtiesFromSurvey(List<Casualty> casualtyList) {
	for (Casualty casualty : casualtyList) {
	    String localizationKey = casualty.getSurveyIncident().getLocalizationKey();

	    CrewMember member = casualty.getMember();
	    member.setStatus(casualty.getSurveyIncident().causesStatus());
	    member.addToCondition(casualty.getSurveyIncident().causesCondition());

	    updateIngameLog(member.getName() + " (" + Localizer.getInstance().get(member.getStatus()) + ") - "
		    + Localizer.getInstance().get(localizationKey));
	}
    }

    private void updateIngameLog(String message) {
	logger.add(message);
	uiController.updateLog(logger.getLog());
    }

    /**
     * @param gameStatusPaused
     *            the gameStatusPaused to set
     */
    public void setGameStatusPaused(boolean gameStatusPaused) {
	gameStatus.setPaused(gameStatusPaused);
    }

    // *************** PAST THIS GENERATED METHODS FOR INPUT PROCESSOR
    // ***************//

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#keyDown(int)
     */
    @Override
    public boolean keyDown(int keycode) {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#keyUp(int)
     */
    @Override
    public boolean keyUp(int keycode) {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#keyTyped(char)
     */
    @Override
    public boolean keyTyped(char character) {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#touchDown(int, int, int, int)
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	gameObjectCanvas.checkIfHitCoordinatesMatchPlanets();
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#touchDragged(int, int, int)
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#mouseMoved(int, int)
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#scrolled(int)
     */
    @Override
    public boolean scrolled(int amount) {
	return false;
    }
}
